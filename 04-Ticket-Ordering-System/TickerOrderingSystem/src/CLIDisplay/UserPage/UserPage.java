package CLIDisplay.UserPage;

import CLIDisplay.MainMenu;
import Users.User;
import Validation.Validation;

import java.util.Locale;

public class UserPage {
    public static void renderUser(User user) {
        while (true) {
            displayMenu(user);
            int choice = Validation.getPositiveIntInput("Enter Choice: ");

            switch (choice) {
                case 1:
                    depositProcess(user);
                    break;
                case 2:
                    withdrawProcess(user);
                    break;
                case 3:
                    changeUsername(user);
                    break;
                case 4:
                    addOrder(user);
                    break;
                case 5:
                    cancelOrder(user);
                    break;
                case 6:
                    printMyOrder(user);
                    break;
                case 7:
                    boolean weDeletedIt = deleteThisUser(user);
                    if (weDeletedIt) return;
                    break;
                case 8:
                    return;
                default:
                    System.out.println("Choice [" + choice + "] is Not Available in Current Task.");
            }
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
                |    6. Display My Order     |
                |    7. Delete User          |
                |    8. Exit.                |
                \\============================/
                
                """);
    }

    private static void depositProcess(User user) {
        double amount = Validation.getPositiveDoubleInput("Enter Amount To Deposit: ");
        MainMenu.controlUser.depositBalanceToUser(user, amount);
    }

    private static void withdrawProcess(User user) {
        double amount = Validation.getPositiveDoubleInput("Enter Amount To Withdraw: ");
        MainMenu.controlUser.withdrawBalanceFromUser(user, amount);
    }

    private static void changeUsername(User user) {
        Validation.clearInputBufferBeforeStringInput();
        String newUsername = Validation.stringInput("Enter New Username: ");
        MainMenu.controlUser.changeUsername(user, newUsername);
    }

    private static void addOrder(User user) {
        Validation.clearInputBufferBeforeStringInput();
        MainMenu.controlEvent.listAllEvent();
        int eventId = Validation.getPositiveIntInput("Enter Event ID: ");
        int quantity = Validation.getPositiveIntInput("Enter Event Ticket Quantity: ");
        MainMenu.controlOrder.userOrderTicket(user, eventId, quantity);
    }

    private static void cancelOrder(User user) {
        Validation.clearInputBufferBeforeStringInput();
        printMyOrder(user);
        String eventName = Validation.stringInput("Enter Event Name: ");
        MainMenu.controlOrder.userCancelOrderTicket(user, eventName);
    }

    private static void printMyOrder(User user) {
        MainMenu.controlOrder.printAllUserOrdered(user);
    }

    private static boolean deleteThisUser(User user) {
        System.out.println("Are You Sure: You wanted to delete this user?");
        System.out.print(" (y/n) ==> ");
        Validation.clearInputBufferBeforeStringInput();
        String ans = Validation.stringInput("");
        if (Validation.setOfYes.contains(ans.toLowerCase())) {
            MainMenu.controlUser.deleteUser(user);
            return true;
        }
        return false;
    }
}
