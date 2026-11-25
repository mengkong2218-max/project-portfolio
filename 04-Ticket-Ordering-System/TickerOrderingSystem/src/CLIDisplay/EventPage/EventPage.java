package CLIDisplay.EventPage;

import CLIDisplay.MainMenu;
import Events.Event;
import Users.User;
import Validation.Validation;
import exceptions.AppException;
import exceptions.UserNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class EventPage {
    private static final Map<Integer, String> eventSpecifications = new HashMap<>();

    static {
        eventSpecifications.put(1, "event_name");
        eventSpecifications.put(2, "total_seats");
        eventSpecifications.put(3, "available_seats");
        eventSpecifications.put(4, "ticket_price");
        eventSpecifications.put(5, "event_open_day");
        eventSpecifications.put(6, "event_close_day");
    }

    public static void renderEvent(Event event) {
        while (true) {
            eventMenu();
            int option = Validation.getPositiveIntInput("Enter Option: ");
            switch (option) {
                case 1:
                    try {
                        MainMenu.controlOrder.displayEventParticipation(event);
                    } catch (UserNotFoundException e) {
                        System.out.println(e);
                    }
                    break;
                case 2:
                    updateEvent(event);
                    break;
                case 3:
                    addMember(event);
                    break;
                case 4:
                    cancelMember(event);
                    break;
                case 5:
                    eventDetail(event);
                    break;
                case 6:
                    if (deleteEvent(event)) return;
                    break;
                case 7:
                    return;
                default:
                    System.out.println("[Invalid]: Option [" + option + "] is not found.");
            }
        }
    }

    private static void eventMenu() {
        System.out.println("""
                /============== Event Menu ==============\\
                |           1. Display Member            |
                |           2. Update Event              |
                |           3. Add Member                |
                |           4. Cancel Member             |
                |           5. Event Detail              |
                |           6. Remove Event              |
                |           7. Exit.                     |
                \\========================================/
                """);
    }

    private static void addMember(Event event) {
        Validation.clearInputBufferBeforeStringInput();
        String userEmail = Validation.stringInput("Enter User's Email: ");
        int userTicket = Validation.getPositiveIntInput("Enter User's Ticket: ");
        User user = MainMenu.controlUser.returnUserByEmail(userEmail);
        if (user == null) {
            System.out.println("[Failed]: No user with this email: " + userEmail);
            return;
        }
        try {
            MainMenu.controlOrder.userOrderTicket(user, event.getEventId(), userTicket);
        } catch (AppException e) {
            System.out.println(e);
        }
    }

    private static void cancelMember(Event event) {
        Validation.clearInputBufferBeforeStringInput();
        String userEmail = Validation.stringInput("Enter User's Email: ");
        User user = MainMenu.controlUser.returnUserByEmail(userEmail);
        if (user == null) {
            System.out.println("[Failed]: No user with this email: " + userEmail);
            return;
        }
        try {
            MainMenu.controlOrder.userCancelOrderTicket(user, event.getEventName());
        } catch (AppException e) {
            System.out.println(e);
        }
    }

    private static void eventDetail(Event event) {
        event.printEventDetail();
    }

    private static boolean deleteEvent(Event event) {
        System.out.println("Are You Sure: You wanted to delete this event?");
        System.out.print(" (y/n) ==> ");
        Validation.clearInputBufferBeforeStringInput();
        String ans = Validation.stringInput("");
        try {
            if (Validation.setOfYes.contains(ans.toLowerCase())) {
                MainMenu.controlEvent.removeEvent(event.getEventName());
                return true;
            }
        } catch (AppException e) {
            System.out.println(e);
        }
        return false;
    }

    private static void updateEvent(Event event) {
        System.out.println("""
                /============= Update =============\\
                |         1. Event Name            |
                |         2. Total Seats           |
                |         3. Available Seat        |
                |         4. Ticket Price          |
                |         5. Open Day              |
                |         6. Close Day             |
                |         7. Exit.                 |
                \\==================================/
                """);
        int option = Validation.getPositiveIntInput("Choose Option: ");
        try {

            switch (option) {
                case 1:
                    Validation.clearInputBufferBeforeStringInput();
                    String newEventName = Validation.stringInput("Enter New Event Name: ");
                    MainMenu.controlEvent.updateEvent(event.getEventName(), eventSpecifications.get(1), newEventName);
                    break;
                case 2:
                    int newTotalSeat = Validation.getPositiveIntInput("Enter New Total Seats: ");
                    MainMenu.controlEvent.updateEvent(event.getEventName(), eventSpecifications.get(2), newTotalSeat);
                    break;
                case 3:
                    int newAvailableSeat = Validation.getPositiveIntInput("Enter New Available Seats: ");
                    MainMenu.controlEvent.updateEvent(event.getEventName(), eventSpecifications.get(3), newAvailableSeat);
                    break;
                case 4:
                    double newTicketPrice = Validation.getPositiveDoubleInput("Enter New Ticket Price: ");
                    MainMenu.controlEvent.updateEvent(event.getEventName(), eventSpecifications.get(4), newTicketPrice);
                    break;
                case 5:
                    Validation.clearInputBufferBeforeStringInput();
                    String newOpeningDay = Validation.stringInput("Enter New Opening Day: ");
                    MainMenu.controlEvent.updateEvent(event.getEventName(), eventSpecifications.get(5), newOpeningDay);
                    break;
                case 6:
                    Validation.clearInputBufferBeforeStringInput();
                    String newClosingDay = Validation.stringInput("Enter New Closing Day: ");
                    MainMenu.controlEvent.updateEvent(event.getEventName(), eventSpecifications.get(6), newClosingDay);
                    break;
                case 7:
                    return;
                default:
                    System.out.println("[Invalid]: Not an available Option.");
            }
        } catch (AppException e) {
            System.out.println(e);
        }
    }

}
