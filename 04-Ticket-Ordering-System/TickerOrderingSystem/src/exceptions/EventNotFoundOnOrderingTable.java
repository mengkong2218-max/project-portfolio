package exceptions;

public class EventNotFoundOnOrderingTable extends RuntimeException {
    public EventNotFoundOnOrderingTable(String message) {
        super(message);
    }
}
