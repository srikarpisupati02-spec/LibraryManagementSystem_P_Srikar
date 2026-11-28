
import java.util.*;
import items.*;
import loans.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        while (sc.hasNextLine()) {

            String line = sc.nextLine();

            if (line.trim().isEmpty()) {
                continue;
            }

            String[] parts = line.split("\\|", -1);

            try {

                String command = parts[0];

                switch (command) {

                    case "ADD_BOOK": {
                        library.addBook(
                            parts[1], parts[2], parts[3]
                        );
                        System.out.println("OK");
                        break;
                    }

                    case "ADD_MAGAZINE": {
                        library.addMagazine(
                            parts[1], parts[2], parts[3]
                        );
                        System.out.println("OK");
                        break;
                    }

                    case "ADD_REFERENCE": {
                        library.addReferenceBook(
                            parts[1], parts[2], parts[3]
                        );
                        System.out.println("OK");
                        break;
                    }

                    case "ADD_MEMBER": {
                        library.addMember(
                            parts[1], parts[2], parts[3]
                        );
                        System.out.println("OK");
                        break;
                    }

                    case "BORROW": {
                        Loan loan = library.borrowItem(
                            parts[1], parts[2]
                        );

                        System.out.println(
                            "OK due on day " + loan.getDueDay()
                        );
                        break;
                    }

                    case "RETURN": {
                        ReturnResult result =
                            library.returnItem(parts[1]);

                        Loan reissued = result.getReissuedLoan();

                        if (reissued == null) {
                            System.out.println(
                                "OK fine " + result.getFine()
                            );
                        }
                        else {
                            System.out.println(
                                "OK fine " + result.getFine()
                                + ", issued to "
                                + reissued.getMember().getId()
                                + " due on day "
                                + reissued.getDueDay()
                            );
                        }

                        break;
                    }

                    case "RESERVE": {
                        library.reserveItem(
                            parts[1], parts[2]
                        );

                        System.out.println("OK reserved");
                        break;
                    }

                    case "ADVANCE": {
                        int days = Integer.parseInt(parts[1]);

                        int today = library.advanceDays(days);

                        System.out.println(
                            "OK today is day " + today
                        );
                        break;
                    }

                    case "SEARCH": {
                        List<LibraryItem> result =
                            library.search(parts[1]);

                        if (result.isEmpty()) {
                            System.out.println("NONE");
                        }
                        else {
                            for (LibraryItem item : result) {
                                System.out.println(
                                    item.getId() + "|"
                                    + item.getType() + "|"
                                    + item.getTitle()
                                );
                            }
                        }

                        break;
                    }

                    case "LOANS": {
                        List<Loan> result =
                            library.getMemberLoans(parts[1]);

                        if (result.isEmpty()) {
                            System.out.println("NONE");
                        }
                        else {
                            for (Loan loan : result) {
                                System.out.println(
                                    loan.getItem().getId() + "|"
                                    + loan.getItem().getTitle() + "|"
                                    + loan.getDueDay()
                                );
                            }
                        }

                        break;
                    }

                    case "OVERDUE": {
                        List<Loan> result =
                            library.getOverdueLoans();

                        if (result.isEmpty()) {
                            System.out.println("NONE");
                        }
                        else {
                            for (Loan loan : result) {
                                System.out.println(
                                    loan.getItem().getId() + "|"
                                    + loan.getMember().getId() + "|"
                                    + loan.getDaysLate(library.getToday()) + "|"
                                    + loan.calculateFine(library.getToday())
                                );
                            }
                        }

                        break;
                    }

                    case "INVENTORY": {
                        List<LibraryItem> result =
                            library.getInventory();

                        if (result.isEmpty()) {
                            System.out.println("NONE");
                        }
                        else {
                            for (LibraryItem item : result) {
                                System.out.println(
                                    item.getId() + "|"
                                    + item.getType() + "|"
                                    + library.getItemStatus(item.getId())
                                );
                            }
                        }

                        break;
                    }

                    default:
                        System.out.println(
                            "ERROR unknown command"
                        );
                }

            }
            catch (RuntimeException e) {
                System.out.println("ERROR " + e.getMessage());
            }
        }

        sc.close();
    }
}
