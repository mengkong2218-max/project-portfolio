package CLIDisplay;

import CLIDisplay.UserPage.UserPage;
import Users.User;
import Validation.Validation;

import java.util.Map;

public class UserMenuPage {

    public static void userMode() {
        while (true) {
            Map<Integer, User> userList = displayAllAvailableUser();
            userSelectionMode();
            int taskNum = Validation.getPositiveIntInput("Enter Task: ");
            switch (taskNum) {
                case 1:
                    User user = selectUserFrom(userList);
                    UserPage.renderUser(user);
                    break;
                case 2:
                    createNewUser();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("This activity is not valid at all.");

            }
        }
    }

    private static Map<Integer, User> displayAllAvailableUser() {
        System.out.println("|============> Users <============|");
        Map<Integer, User> userList = MainMenu.controlUser.displayUsernameAsList();
        System.out.println("|=================================|");
        return userList;
    }

    private static void userSelectionMode() {
        System.out.println("""
                | 1. Check in on User.
                | 2. Add new User.
                | 3. Exit.
                """);
    }

    private static User selectUserFrom(Map<Integer, User> userList) {
        int getIndex = Validation.getPositiveIntInput("Enter User No: ");
        return userList.get(getIndex);
    }

    private static void createNewUser() {
        Validation.clearInputBufferBeforeStringInput();
        String name = Validation.stringInput("Enter Your Name: ");
        String gmail = Validation.stringInput("Enter Your Email: ");
        double balance = Validation.getPositiveDoubleInput("Enter Your Balance: ");
        User user = new User();
        user.setUserName(name);
        user.setUserBalance(balance);
        user.setUserGmail(gmail);
        MainMenu.controlUser.addNewUser(user);
    }
}
