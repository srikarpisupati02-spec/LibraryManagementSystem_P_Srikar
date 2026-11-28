package loans;

import members.Member;
import items.LibraryItem;
import items.Borrowable;

public class Loan {
    private Member member;
    private LibraryItem item;
    private int borrowDay;
    private int dueDay;

    public Loan(Member member, LibraryItem item, int borrowDay) {
        this.member = member;
        this.item = item;
        this.borrowDay = borrowDay;

        this.dueDay = borrowDay + member.getLoanPeriod();
    }

    public Member getMember() {
        return member;
    }

    public LibraryItem getItem() {
        return item;
    }

    public int getBorrowDay() {
        return borrowDay;
    }

    public int getDueDay() {
        return dueDay;
    }

    public boolean isOverdue(int today) {
        return today > dueDay;
    }

    public int getDaysLate(int today) {
        if (!isOverdue(today)) {
            return 0;
        }

        return today - dueDay;
    }

    public int calculateFine(int today) {
        int daysLate = getDaysLate(today);

        if (daysLate == 0) {
            return 0;
        }

        Borrowable borrowableItem = (Borrowable) item;
        return daysLate * borrowableItem.getFineRate();
    }
}
