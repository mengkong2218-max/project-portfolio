package Events;

import Database.DataSQL;

import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.Map;

public class EventsManager {
    private Map<String, Event> eventsMan = new LinkedHashMap<>();
    private DataSQL eventsData;
    private int lastEventId;
    private static final Set<String> EVENT_COLUMN = Set.of("event_name", "total_seats", "available_seats", "ticket_price", "event_open_day", "event_close_day");

    public EventsManager(DataSQL eventsData) {
        this.eventsData = eventsData;
        try (ResultSet result = this.eventsData.loadData("event")) {
            while (result.next()) {
                lastEventId = result.getInt("event_id");
                String name = result.getString("event_name");
                int totalSeat = result.getInt("total_seats");
                int availableSeat = result.getInt("available_seats");
                double price = result.getDouble("ticket_price");
                String startDate = result.getString("event_open_day");
                String endDate = result.getString("event_close_day");
                eventsMan.put(name, new Event(lastEventId, name, totalSeat, availableSeat, price, startDate, endDate));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listAllEvent() {
        if (this.eventsMan.isEmpty()) {
            System.out.println(" --- No Event Available here --- ");
            return;
        }
        System.out.println("| ID | Name | Total Seat | Available Seat | Ticket Price | Start Date | End Date |");
        for (Event event : eventsMan.values()) {
            event.printEventDetail();
        }
    }

    public void addNewEvent(Event event) {
        if (isEventExist(event.getEventName())) {
            System.out.println("Sorry, This event is already exist.");
            return;
        }
        String cmd = """ 
                INSERT INTO event 
                (event_name, total_seats, available_seats, ticket_price, event_open_day, event_close_day)
                VALUES (?, ?, ?, ?, ?, ?);
                """;
        int getId = eventsData.executeInsert(cmd, event.getEventName(), event.getTotalSeats(), event.getAvailableSeats(), event.getTicketPrice(), event.getEventStartDate(), event.getEventEndDate());
        if (getId != -1) {
            lastEventId = getId;
            event.setEventId(lastEventId);
            eventsMan.put(event.getEventName(), event);
        }
    }

    public void updateEvent(String eventName, String whatToUpdate, Object newValue) {
        if (!isEventExist(eventName)) {
            System.out.println("Sorry, This event is not exist.");
            return;
        }
        if (!EVENT_COLUMN.contains(whatToUpdate)) {
            System.out.println("Sorry, We can not perform this action.");
            return;
        }
        String task = "UPDATE event SET " + whatToUpdate + " = ? WHERE event_name = ?;";
        eventsData.executeUpdateCommand(task, newValue, eventName);
        if (eventsData.isExecuteSuccessfully()) {
            eventsMan.get(eventName).setOneAnyFunctionOfEvent(whatToUpdate, newValue);
        }
    }

    private boolean isEventExist(String evt) {
        return eventsMan.containsKey(evt);
    }
}
