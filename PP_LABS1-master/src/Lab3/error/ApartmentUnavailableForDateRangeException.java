package Lab3.error;

public class ApartmentUnavailableForDateRangeException extends RuntimeException {

    public ApartmentUnavailableForDateRangeException(String message) {
        super(message);
    }
}