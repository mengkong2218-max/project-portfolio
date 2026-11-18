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
        Ordering order = new Ordering(myData, userData, eventData);
        User me = userData.returnUserByEmail("riyiusan@gmail.com");

        order.userCancelOrderTicket(me, "MPL");

    }
}