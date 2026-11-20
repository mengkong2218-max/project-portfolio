package exceptions;

public class OrderIDNotExist extends RuntimeException {
    public OrderIDNotExist(String message) {
        super(message);
    }
}
