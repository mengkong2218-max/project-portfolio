import CLIDisplay.MainMenu;
import Database.DataSQL;
import Events.EventsManager;
import Ordering.Ordering;
import Users.User;
import Users.UsersMag;

import java.util.Scanner;

public class Main {
    private static final DataSQL myData = new DataSQL();
    public static final UsersMag controlUser = new UsersMag(myData);
    public static final EventsManager controlEvent = new EventsManager(myData);
    public static final Ordering controlOrder = new Ordering(myData, controlUser, controlEvent);
    public static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            MainMenu.display();
            System.out.print("Choose an Option: ");
            int option = input.nextInt();

            switch (option) {
                case 1:
                    System.out.println("User");
                    break;
                case 2:
                    System.out.println("Event");
                    break;
                case 3:
                    System.out.println("Order");
                    break;
                case 4:
                    System.out.println("Thank You!!!");
                    return;
                default:
                    System.out.println("Undefined behaviour of Option.");
            }
        }
    }
}
