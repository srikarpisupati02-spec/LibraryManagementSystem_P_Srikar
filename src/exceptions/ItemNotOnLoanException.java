package exceptions;

public class ItemNotOnLoanException extends LibraryException {

    public ItemNotOnLoanException(String message) {
        super(message);
    }
}
