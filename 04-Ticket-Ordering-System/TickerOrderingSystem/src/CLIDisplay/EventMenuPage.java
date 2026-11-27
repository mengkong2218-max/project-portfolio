package CLIDisplay;

import CLIDisplay.EventPage.EventPage;
import Events.Event;
import Validation.Validation;
import exceptions.AppException;

import java.util.Map;

public class EventMenuPage {
    public static void eventMode() {
        while (true) {
            Map<Integer, Event> eventList = eventMenu();
            taskToDo();
            int option = Validation.getPositiveIntInput("> Enter Option: ");
            switch (option) {
                case 1:
                    Event event = selectEventFrom(eventList);
                    if (event != null)
                        EventPage.renderEvent(event);
                    break;
                case 2:
                    addNewEvent();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("[Invalid]: Option '" + option + "' not Found.");
            }
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

    private static Event selectEventFrom(Map<Integer, Event> eventList) {
        if (eventList.isEmpty()) {
            return null;
        }
        int getIndex = Validation.getPositiveIntInput("Enter Event No: ");
        if (eventList.containsKey(getIndex)) return eventList.get(getIndex);
        return null;
    }

    private static void addNewEvent() {
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
        try {
            MainMenu.controlEvent.addNewEvent(newEvent);
        } catch (AppException e) {
            System.out.println(e.getMessage());
        }
    }
}
