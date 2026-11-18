package exceptions;

public class UnableToLocateTableException extends RuntimeException {
    public UnableToLocateTableException(String message) {
        super(message);
    }
}
