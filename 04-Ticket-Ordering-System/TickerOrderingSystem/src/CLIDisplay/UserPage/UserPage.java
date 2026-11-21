package CLIDisplay.UserPage;

import Users.User;
import Validation.Validation;

public class UserPage {
    public static void renderUser(User user) {
        displayMenu(user);
        int choice = Validation.getPositiveIntInput("Enter Choice: ");

        switch (choice) {
            case 1:
        }
    }

    private static void displayMenu(User user) {
        System.out.println("| ========== > User Data");
        user.printUserData();

        System.out.println("""
                
                /=========== Menu ===========\\
                |    1. Deposit Balance      |
                |    2. Withdraw Balance     |
                |    3. Change Username      |
                |    4. Add Order            |
                |    5. Cancel Order         |
                |    6. Display Order        |
                |    7. Delete User          |
                |    8. Exit.                |
                \\============================/
                
                """);
    }
}
