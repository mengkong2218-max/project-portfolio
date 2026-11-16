package Events;

import Database.DataSQL;

import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.Map;

public class EventsManager {
    private final Map<String, Event> eventsMan = new LinkedHashMap<>();
    private final Map<Integer, Event> eventsManById = new LinkedHashMap<>();
    private final DataSQL eventsData;
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
                Event event = new Event(lastEventId, name, totalSeat, availableSeat, price, startDate, endDate);
                eventsMan.put(name, event);
                eventsManById.put(lastEventId, event);
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
            eventsManById.put(lastEventId, event);
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
        int event_id = getIdFromEventName(eventName);
        String task = "UPDATE event SET " + whatToUpdate + " = ? WHERE event_id = ?;";
        eventsData.executeUpdateCommand(task, newValue, event_id);
        if (eventsData.isExecuteSuccessfully()) {
            eventsMan.get(eventName).setOneAnyFunctionOfEvent(whatToUpdate, newValue);
            eventsManById.get(event_id).setOneAnyFunctionOfEvent(whatToUpdate, newValue);
        }
    }

    public void removeEvent(String eventName) {
        if (!isEventExist(eventName)) {
            System.out.println("Sorry! We don't have this event in our List.");
            return;
        }
        int getID = getIdFromEventName(eventName);
        String task = "DELETE FROM event WHERE event_id = ?;";
        this.eventsData.executeUpdateCommand(task, getID);
        if (this.eventsData.isExecuteSuccessfully()) {
            this.eventsMan.remove(eventName);
            this.eventsManById.remove(getID);
        }
    }

    private int getIdFromEventName(String eventName) {
        return this.eventsMan.get(eventName).getEventId();
    }

    public boolean isEventExist(String evt) {
        return eventsMan.containsKey(evt);
    }

    public Event getMyEventByEventName(String eventName) {
        return this.eventsMan.get(eventName);
    }

    public Event getEventByEventID(int id) {
        return this.eventsManById.get(id);
    }
}
