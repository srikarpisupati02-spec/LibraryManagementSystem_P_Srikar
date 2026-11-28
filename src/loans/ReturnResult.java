package loans;

public class ReturnResult {

    private int fine;
    private Loan reissuedLoan;

    public ReturnResult(int fine, Loan reissuedLoan) {
        this.fine = fine;
        this.reissuedLoan = reissuedLoan;
    }

    public int getFine() {
        return fine;
    }

    public Loan getReissuedLoan() {
        return reissuedLoan;
    }
}
