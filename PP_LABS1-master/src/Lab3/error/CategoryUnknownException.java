package Lab3.error;

public class CategoryUnknownException extends RuntimeException {

    public CategoryUnknownException(String message) {
        super(message);
    }
}