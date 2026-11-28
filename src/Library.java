
import java.util.*;

import items.*;
import members.*;
import loans.*;
import exceptions.*;

public class Library {

    private int today;

    private Map<String, LibraryItem> catalogue;
    private Map<String, Member> members;
    private Map<String, Loan> activeLoans;
    private Map<String, Queue<Member>> reservations;

    public Library() {
        today = 0;

        catalogue = new HashMap<>();
        members = new HashMap<>();
        activeLoans = new HashMap<>();
        reservations = new HashMap<>();
    }

    public void addBook(String id, String title, String author) {
        Book book = new Book(id, title, author);
        catalogue.put(id, book);
    }

    public void addMagazine(String id, String title, String issue) {
        Magazine magazine = new Magazine(id, title, issue);
        catalogue.put(id, magazine);
    }

    public void addReferenceBook(String id, String title, String author) {
        ReferenceBook referenceBook = new ReferenceBook(id, title, author);

        catalogue.put(id, referenceBook);
    }

    public void addMember(String id, String name, String type) {

        Member member;

        if (type.equalsIgnoreCase("STUDENT")) {
            member = new Student(id, name);
        } else if (type.equalsIgnoreCase("STAFF")) {
            member = new Staff(id, name);
        } else {
            member = new Faculty(id, name);
        }

        members.put(id, member);
    }

    private LibraryItem findItem(String itemId) {

        LibraryItem item = catalogue.get(itemId);

        if (item == null) {
            throw new ItemNotFoundException(
                    "item " + itemId + " not found");
        }

        return item;
    }

    public String getItemStatus(String itemId) {

        LibraryItem item = findItem(itemId);

        if (!(item instanceof Borrowable)) {
            return "NOT_BORROWABLE";
        }

        Loan loan = activeLoans.get(itemId);

        if (loan == null) {
            return "AVAILABLE";
        }

        return "ON_LOAN|" + loan.getMember().getId();
    }

    private Member findMember(String memberId) {

        Member member = members.get(memberId);

        if (member == null) {
            throw new MemberNotFoundException(
                    "member " + memberId + " not found");
        }

        return member;
    }

    private Loan issueLoan(Member member, LibraryItem item) {

        Loan loan = new Loan(member, item, today);

        activeLoans.put(item.getId(), loan);
        member.addLoan(loan);

        return loan;
    }

    public Loan borrowItem(String memberId, String itemId) {

        Member member = findMember(memberId);
        LibraryItem item = findItem(itemId);

        if (!(item instanceof Borrowable)) {
            throw new ItemNotAvailableException(
                    itemId + " cannot be borrowed");
        }

        if (activeLoans.containsKey(itemId)) {
            throw new ItemNotAvailableException(
                    itemId + " is on loan");
        }

        member.validateCanBorrow();

        return issueLoan(member, item);
    }

    private Member findNextEligibleMember(String itemId) {

        Queue<Member> queue = reservations.get(itemId);

        if (queue == null) {
            return null;
        }

        Iterator<Member> iterator = queue.iterator();

        while (iterator.hasNext()) {

            Member member = iterator.next();

            if (member.canBorrow()) {

                iterator.remove();

                if (queue.isEmpty()) {
                    reservations.remove(itemId);
                }

                return member;
            }
        }

        return null;
    }

    public ReturnResult returnItem(String itemId) {

        Loan loan = activeLoans.get(itemId);

        if (loan == null) {
            findItem(itemId);

            throw new ItemNotOnLoanException(
                    itemId + " is not on loan");
        }

        int fine = loan.calculateFine(today);

        Member oldMember = loan.getMember();

        activeLoans.remove(itemId);
        oldMember.removeLoan(loan);

        Member nextMember = findNextEligibleMember(itemId);

        Loan reissuedLoan = null;

        if (nextMember != null) {

            LibraryItem item = findItem(itemId);

            reissuedLoan = issueLoan(nextMember, item);
        }

        return new ReturnResult(fine, reissuedLoan);
    }

    public void reserveItem(String memberId, String itemId) {

        Member member = findMember(memberId);
        LibraryItem item = findItem(itemId);

        if (!(item instanceof Borrowable)) {
            throw new ItemNotAvailableException(
                    itemId + " cannot be borrowed");
        }

        if (!activeLoans.containsKey(itemId)) {
            throw new ReservationNotAllowedException(
                    itemId + " is available");
        }

        for (Loan loan : member.getActiveLoans()) {

            if (loan.getItem().getId().equals(itemId)) {
                throw new ReservationNotAllowedException(
                        memberId + " already has " + itemId);
            }
        }

        Queue<Member> queue = reservations.get(itemId);

        if (queue == null) {
            queue = new LinkedList<>();
            reservations.put(itemId, queue);
        }

        if (queue.contains(member)) {
            throw new ReservationNotAllowedException(
                    memberId + " already reserved " + itemId);
        }

        queue.add(member);
    }

    public int advanceDays(int days) {

        today = today + days;

        return today;
    }

    public List<LibraryItem> search(String text) {

        List<LibraryItem> result = new ArrayList<>();

        for (LibraryItem item : catalogue.values()) {

            if (item.getTitle().toLowerCase()
                    .contains(text.toLowerCase())) {

                result.add(item);
            }
        }

        result.sort(
                Comparator.comparing(LibraryItem::getTitle));

        return result;
    }

    public List<Loan> getMemberLoans(String memberId) {

        Member member = findMember(memberId);

        List<Loan> result = new ArrayList<>(member.getActiveLoans());

        result.sort(
                Comparator.comparingInt(Loan::getDueDay)
                        .thenComparing(
                                loan -> loan.getItem().getId()));

        return result;
    }

    public List<Loan> getOverdueLoans() {

        List<Loan> result = new ArrayList<>();

        for (Loan loan : activeLoans.values()) {

            if (loan.isOverdue(today)) {
                result.add(loan);
            }
        }

        result.sort(
                Comparator.comparingInt(
                        (Loan loan) -> loan.getDaysLate(today)).reversed()
                        .thenComparing(
                                loan -> loan.getItem().getId()));

        return result;
    }

    public List<LibraryItem> getInventory() {

        List<LibraryItem> result = new ArrayList<>(catalogue.values());

        result.sort(
                Comparator.comparing(LibraryItem::getId));

        return result;
    }

    public int getToday() {
        return today;
    }
}
