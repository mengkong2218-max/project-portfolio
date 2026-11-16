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

    public Ordering(DataSQL data, UsersMag usersMag, EventsManager eventMan) {
        this.orderData = data;
        try (ResultSet rs = this.orderData.loadData("ordering_table")) {
            while (rs.next()) {
                int order_id = rs.getInt("ordering_id");
                int user_id = rs.getInt("user_id");
                int event_id = rs.getInt("event_id");
                int quantity = rs.getInt("quantity");
                String order_date = rs.getString("order_date");
                orderList.add(new Order(order_id, user_id, event_id, quantity, order_date));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.myUser = usersMag;
        this.myEvent = eventMan;
    }

    public void displayListOfOrder() {
        if (this.orderList.isEmpty()) {
            System.out.println(" --- Not received any order ---");
            return;
        }
        System.out.println("| ID | User ID | Event ID | Quantity | Date |");
        this.orderList.forEach(order -> order.printOrder());
    }

    public void userOrderTicket(User user, String eventName, int ticketQuantity) {
        if (!myUser.checkAccountExistByEmail(user.getUserGmail())) {
            System.out.println("User Not Found");
            return;
        }
        if (!myEvent.isEventExist(eventName)) {
            System.out.println("No Event Was Found.");
            return;
        }
        Event event = myEvent.getMyEventByEventName(eventName);
        String task = """
                INSERT INTO ordering_table (user_id, event_id, quantity)
                VALUES (?, ?, ?);
                """;
        int orderID = orderData.executeInsert(task, user.getUserID(), event.getEventId(), ticketQuantity);
        double fullPrice = event.getTicketPrice() * ticketQuantity;
        if (orderID != -1 && updateEventAvailableSeat(event, ticketQuantity) && updateUserBalance(user, fullPrice)) {
            String orderDate = getDateForAddingNewOrder(orderID);
            orderList.add(new Order(orderID, user.getUserID(), event.getEventId(), ticketQuantity, orderDate));
            System.out.println("Successfully Order.");
        }
    }

    public void printAllUserOrdered(User user) {
        if (!myUser.checkAccountExistByEmail(user.getUserGmail())) {
            System.out.println("Sorry, We cannot find this user in Our System.");
            return;
        }
        int i = 1;
        System.out.println("| No | Username | Event Name | Ticket Price | Ticket Quantity | Full Paid ($) |");
        for (Order order : orderList) {
            if (order.getUserId() == user.getUserID()) {
                Event event = myEvent.getEventByEventID(order.getEventId());
                double fullPrice = event.getTicketPrice() * order.getQuantity();
                System.out.println("| " + i + " | " + user.getName() + " | " + event.getEventName()  +" | " + event.getTicketPrice() + " | " + order.getQuantity() + " | " + fullPrice + " |");
                i++;
            }
        }
    }

    public void userCancelOrderTicket(User user, String eventName) {
        if (!myUser.checkAccountExistByEmail(user.getUserGmail())) {
            System.out.println("Sorry, We can not perform this action.");
            return;
        }
        if (!myEvent.isEventExist(eventName)) {
            System.out.println("Sorry, We can not perform this action.");
            return;
        }
    }

    private boolean updateEventAvailableSeat(Event event, int quantity) {
        String task = """
                UPDATE event SET available_seats = ? WHERE event_id = ?;
                """;
        int newAvailableSeat = event.getAvailableSeats() - quantity;
        if (!event.isEnoughSeat(newAvailableSeat)) {
            System.out.println("Not Enough Seats. We decided to cancel your order");
            return false;
        }
        orderData.executeUpdateCommand(task, newAvailableSeat, event.getEventId());
        return true;
    }

    private boolean updateUserBalance(User user, double calculatedPrice) {
        String task = """
                UPDATE users SET user_balance = ? WHERE user_id = ?;
                """;
        if (!user.haveEnoughBalance(calculatedPrice)) {
            System.out.println("Sorry! You don't have money in your account.");
            return false;
        }
        double newBalance = user.getUserBalance() - calculatedPrice;
        orderData.executeUpdateCommand(task, newBalance, user.getUserID());
        return true;
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
