package Ordering;

import Database.DataSQL;
import Events.Event;
import Events.EventsManager;
import Users.UsersMag;
import Users.User;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Ordering {
    private final DataSQL orderData;
    private final List<Order> orderList = new ArrayList<>();
    private final UsersMag myUser;
    private final EventsManager myEvent;

    public Ordering(DataSQL data) {
        this.orderData = data;
        try (ResultSet rs = this.orderData.loadData("ordering_table")) {
            while (rs.next()) {
                int order_id = rs.getInt("order_id");
                int user_id = rs.getInt("user_id");
                int event_id = rs.getInt("event_id");
                int quantity = rs.getInt("quantity");
                String order_date = rs.getString("order_date");
                orderList.add(new Order(order_id, user_id, event_id, quantity, order_date));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.myUser = new UsersMag(data);
        this.myEvent = new EventsManager(data);
    }

    public void displayListOfOrder() {
        if (this.orderList.isEmpty()) {
            System.out.println(" --- Not received any order ---");
            return;
        }
        System.out.println("| ID | User ID | Event ID | Quantity | Date |");
        this.orderList.forEach(order -> order.printOrder());
    }

    public void addNewOrder(User user, Event event, int ticketQuantity) {
        if (!myUser.checkAccountExistByEmail(user.getUserGmail())) {
            System.out.println("User Not Found");
            return;
        }
        if (!myEvent.isEventExist(event.getEventName())) {
            System.out.println("No Event Was Found.");
            return;
        }
        String task = """
                INSERT INTO ordering_table (user_id, event_id, quantity)
                VALUES (?, ?, ?);
                """;
        int orderID = orderData.executeInsert(task, user.getUserID(), event.getEventId(), ticketQuantity);
        if (orderID != -1) {
            String orderDate = getDateForAddingNewOrder(orderID);
            orderList.add(new Order(orderID, user.getUserID(), event.getEventId(), ticketQuantity, orderDate));
        }
    }

    private String getDateForAddingNewOrder(int orderID) {
        String cmd = "SELECT order_date FROM ordering_table WHERE ordering_id = ?;";
        try (ResultSet result = orderData.executeQueryCommand(cmd, orderID)) {
            if (result.next())
                return result.getString("order_date");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Not Found";
    }
}
