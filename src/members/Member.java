package members;

import java.util.ArrayList;
import java.util.List;
import loans.Loan;
import exceptions.BorrowLimitExceededException;

public abstract class Member {

    private String id;
    private String name;
    private List<Loan> activeLoans;

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
        this.activeLoans = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Loan> getActiveLoans() {
        return activeLoans;
    }

    public boolean canBorrow() {
        return activeLoans.size() < getMaxItems();
    }

    public void validateCanBorrow() {
        if (!canBorrow()) {
            throw new BorrowLimitExceededException(
                id + " has reached the limit of " + getMaxItems()
            );
        }
    }

    public void addLoan(Loan loan) {
        validateCanBorrow();
        activeLoans.add(loan);
    }

    public void removeLoan(Loan loan) {
        activeLoans.remove(loan);
    }

    public abstract int getMaxItems();

    public abstract int getLoanPeriod();
}
