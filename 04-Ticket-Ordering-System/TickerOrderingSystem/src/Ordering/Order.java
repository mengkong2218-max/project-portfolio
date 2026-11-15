package Ordering;

public class Order {
    private int order_id;
    private int user_id;
    private int event_id;
    private int quantity;
    private String order_date;

    public Order() {}
    public Order(int order_id, int user_id, int event_id, int quantity, String order_date) {
        this.order_id = order_id;
        this.user_id = user_id;
        this.event_id = event_id;
        this.quantity = quantity;
        this.order_date = order_date;
    }

    public void printOrder() {
        System.out.println("| " + this.order_id + " | " + this.user_id + " | " + this.event_id + " | " + this.quantity + " | " + this.order_date + " |");
    }
    public int getOrderId() {
        return order_id;
    }

    public void setOrderId(int order_id) {
        this.order_id = order_id;
    }

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public int getEventId() {
        return event_id;
    }

    public void setEventId(int event_id) {
        this.event_id = event_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOrderDate() {
        return order_date;
    }

    public void setOrderDate(String order_date) {
        this.order_date = order_date;
    }
}
