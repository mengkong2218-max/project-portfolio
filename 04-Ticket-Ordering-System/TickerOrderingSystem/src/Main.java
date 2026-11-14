import Database.DataSQL;
import Events.Event;
import Events.EventsManager;

public class Main {
    private static DataSQL myData = new DataSQL();
    public static void main(String[] args) {
        EventsManager eventsTable = new EventsManager(myData);
        eventsTable.listAllEvent();

        eventsTable.updateEvent("OCIC", "total_seats", 2022);
        eventsTable.listAllEvent();
    }
}