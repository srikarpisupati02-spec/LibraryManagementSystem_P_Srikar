package exceptions;

public class ReservationNotAllowedException extends LibraryException {

    public ReservationNotAllowedException(String message) {
        super(message);
    }
}
