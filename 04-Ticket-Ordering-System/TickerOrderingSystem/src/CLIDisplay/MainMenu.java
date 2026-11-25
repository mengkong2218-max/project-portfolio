package CLIDisplay;

import Database.DataSQL;
import Events.EventsManager;
import Ordering.Ordering;
import Users.UsersMag;
import Validation.Validation;

public class MainMenu {
    private static final DataSQL myData = new DataSQL();
    public static final UsersMag controlUser = new UsersMag(myData);
    public static final EventsManager controlEvent = new EventsManager(myData);
    public static final Ordering controlOrder = new Ordering(myData, controlUser, controlEvent);

    public static void start() {
        while (true) {
            MainMenu.display();
            int option = Validation.getPositiveIntInput("Choose an Option: ");

            switch (option) {
                case 1:
                    UserMenuPage.userMode();
                    break;
                case 2:
                    EventMenuPage.eventMode();
                    break;
                case 3:
                    OrderPage.displayOrderList();
                    break;
                case 4:
                    System.out.println("Thank You!!!");
                    return;
                default:
                    System.out.println("Undefined behaviour of Option.");
            }
        }
    }

    private static void display() {
        System.out.println("""
                /------------> Main Menu <------------\\
                |              1. User                |
                |              2. Event               |
                |              3. Order               |
                |              4. Exit                |
                \\-------------------------------------/
                """);
    }
}
