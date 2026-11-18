package Ordering;

import Database.DataSQL;
import Events.Event;
import Events.EventsManager;
import Users.UsersMag;
import Users.User;
import exceptions.*;

import java.sql.ResultSet;
import java.sql.SQLException;
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
        } catch (SQLException e) {
            System.out.println("[ERROR]" + e.getMessage());
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
            throw new UserNotFoundException("[Failed]: Account with this email '" + user.getUserGmail() + "' was not found in System.");
        }
        if (!myEvent.isEventExist(eventName)) {
            throw new EventNotExistException("[Failed]: We can not find this event in our data.");
        }
        Event event = myEvent.getMyEventByEventName(eventName);
        int newAvailableSeat = event.getAvailableSeats() - ticketQuantity;
        if (!event.isEnoughSeat(newAvailableSeat)) {
            throw new NotEnoughSeatProblemException("Not Enough Seats. We decided to cancel your order");
        }
        double fullPrice = event.getTicketPrice() * ticketQuantity;
        if (!user.haveEnoughBalance(fullPrice)) {
            throw new NotEnoughBalanceException("User balance is not enough to contribute in the event ");
        }
        String task = """
                INSERT INTO ordering_table (user_id, event_id, quantity)
                VALUES (?, ?, ?);
                """;
        int orderID = orderData.executeInsert(task, user.getUserID(), event.getEventId(), ticketQuantity);
        if (orderID != -1) {
            updateEventAvailableSeat(event, newAvailableSeat);
            updateUserBalance(user, fullPrice);
            String orderDate = getDateForAddingNewOrder(orderID);
            orderList.add(new Order(orderID, user.getUserID(), event.getEventId(), ticketQuantity, orderDate));
            System.out.println("Successfully Order.");
        }
    }

    public void printAllUserOrdered(User user) {
        if (!myUser.checkAccountExistByEmail(user.getUserGmail())) {
            throw new UserNotFoundException("[Failed]: Account with this email '" + user.getUserGmail() + "' was not found in System.");
        }
        int i = 1;
        System.out.println("| No | Username | Event Name | Ticket Price | Ticket Quantity | Full Paid ($) |");
        for (Order order : orderList) {
            if (order.getUserId() == user.getUserID()) {
                Event event = myEvent.getEventByEventID(order.getEventId());
                double fullPrice = event.getTicketPrice() * order.getQuantity();
                System.out.println("| " + i + " | " + user.getName() + " | " + event.getEventName() + " | " + event.getTicketPrice() + " | " + order.getQuantity() + " | " + fullPrice + " |");
                i++;
            }
        }
    }

    public void userCancelOrderTicket(User user, String eventName) {
        if (!myUser.checkAccountExistByEmail(user.getUserGmail())) {
            throw new UserNotFoundException("[Failed]: Account with this email '" + user.getUserGmail() + "' was not found in System.");
        }
        if (!myEvent.isEventExist(eventName)) {
            throw new EventNotExistException("[Failed]: We can not find this event in our data.");
        }
        Event event = this.myEvent.getMyEventByEventName(eventName);
        String task = """
                DELETE FROM ordering_table WHERE user_id = ? AND event_id = ?;
                """;
        this.orderData.executeUpdateCommand(task, user.getUserID(), event.getEventId());
        if (this.orderData.isExecuteSuccessfully()) {
            this.orderList.remove(findOrderIndexIdByUserIdAndEventId(user.getUserID(), event.getEventId()));
        }
    }

    private int findOrderIndexIdByUserIdAndEventId(int userId, int eventId) {
        int i = 0;
        for (Order order : this.orderList) {
            if (order.getUserId() == userId && order.getEventId() == eventId) {
                return i;
            }
            i++;
        }
        return -1;
    }

    private void updateEventAvailableSeat(Event event, int newAvailableSeat) {
        String task = """
                UPDATE event SET available_seats = ? WHERE event_id = ?;
                """;
        orderData.executeUpdateCommand(task, newAvailableSeat, event.getEventId());
    }

    private boolean updateUserBalance(User user, double newBalance) {
        String task = """
                UPDATE users SET user_balance = ? WHERE user_id = ?;
                """;

        orderData.executeUpdateCommand(task, newBalance, user.getUserID());
        return true;
    }

    private String getDateForAddingNewOrder(int orderID) {
        String cmd = "SELECT order_date FROM ordering_table WHERE ordering_id = ?;";
        try (ResultSet result = orderData.executeQueryCommand(cmd, orderID)) {
            if (result.next())
                return result.getString("order_date");
        } catch (Exception e) {
            System.out.println("[ERROR]" + e.getMessage());
        }
        return "Not Found";
    }
}
