package CLIDisplay;

import Events.Event;
import Validation.Validation;

import java.util.Map;

public class EventMenuPage {
    public static void eventMode() {
        Map<Integer, Event> eventList = eventMenu();
        taskToDo();
        int option = Validation.getPositiveIntInput("> Enter Option: ");
        switch (option) {
            case 1:
                System.out.println("Check Event ");
                break;
            case 2:
                addNewEvent();
                break;
            case 3:
                return;
            default:
                System.out.println("[Invalid]: Option '"+ option + "' not Found.");
        }
    }

    private static Map<Integer, Event> eventMenu() {
        System.out.println("| ============ Event ============ |");
        Map<Integer, Event> eventList = MainMenu.controlEvent.printEventAsListOfName();
        System.out.println("| =============================== |");
        return eventList;
    }

    private static void taskToDo() {
        System.out.println("""
                
                |  1. Check Event.
                |  2. Add New Event.
                |  3. Exit.
                
                """);
    }

    private static void addNewEvent() {
        Validation.clearInputBufferBeforeStringInput();
        String eventName = Validation.stringInput("Enter Event Name: ");
        int total_seats = Validation.getPositiveIntInput("Enter Event Total Seat: ");
        int available_seats = Validation.getPositiveIntInput("Enter Event Available Seat: ");
        double ticket_price = Validation.getPositiveDoubleInput("Enter Event Ticket Price: ");
        String opening_date = Validation.stringInput("Enter Event Open Date: ");
        String close_date = Validation.stringInput("Enter Event Close Date: ");
        Event newEvent = new Event();
        newEvent.setEventName(eventName);
        newEvent.setTotalSeats(total_seats);
        newEvent.setAvailableSeats(available_seats);
        newEvent.setTicketPrice(ticket_price);
        newEvent.setEventStartDate(opening_date);
        newEvent.setEventEndDate(close_date);
        MainMenu.controlEvent.addNewEvent(newEvent);
    }
}
