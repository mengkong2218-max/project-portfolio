import Database.DataSQL;
import Events.Event;
import Events.EventsManager;
import Ordering.Ordering;
import Users.User;
import Users.UsersMag;

public class Main {
    private static DataSQL myData = new DataSQL();
    public static void main(String[] args) {
        UsersMag userData = new UsersMag(myData);
        EventsManager eventData = new EventsManager(myData);
        Ordering order = new Ordering(myData);

        Event mpl = new Event();
        mpl.setEventName("MPL");
        mpl.setTotalSeats(100);
        mpl.setAvailableSeats(50);
        mpl.setTicketPrice(5);
        mpl.setEventStartDate("16/11/2026");
        mpl.setEventEndDate("20/11/2026");

        eventData.addNewEvent(mpl);
        order.displayListOfOrder();

        User me = userData.returnUserByEmail("mengkong2218@gmail.com");
        order.addNewOrder(me, mpl, 5);


        order.displayListOfOrder();

    }
}