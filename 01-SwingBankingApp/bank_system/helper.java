package bank_system;

import java.util.ArrayList;
import java.util.Scanner;

public class helper {
    // Main helper
    public static void starterScreen() {
        System.out.println("===== LIBank App =====");
        System.out.println("|      1. Login      |");
        System.out.println("|     2. Sign Up     |");
        System.out.println("|      0. Exit       |");
        System.out.println("======================");
    }

    public static Account loginScreen(Scanner scanner, AccManagement accs) {
        String username, password;
        System.out.println("========== LIBank Login ==========");
        System.out.print("| + Enter Username: ");
        username = scanner.nextLine().trim();
        System.out.print("| + Enter Password: ");
        password = scanner.nextLine().trim();
        return accs.getIntoAccount(username, password);
    }

    public static void signUpScreen(Scanner scanner, AccManagement accs) {
        String username, password;
        System.out.println("========== LIBank Sign Up ==========");
        System.out.print("| + Enter Username: ");
        username = scanner.nextLine().trim();
        System.out.print("| + Enter Password: ");
        password = scanner.nextLine().trim();
        accs.createAccount(username, password);
    }

    public static void mainMenu(String name, double balance) {
        accountState(name, balance);
        System.out.println("\n |  1. Deposit");
        System.out.println(" |  2. Withdraw");
        System.out.println(" |  3. Transfer");
        System.out.println(" |  0. Exit.");
    }

    // Account Helper
    public static void accountState(String name, double balance) {
        System.out.println();
        System.out.println(" | Username: " + name);
        System.out.println(" | Balance: " + balance);
        System.out.println();
    }

    public static boolean amountLessThanZeroCheck(double amount, String prompt) {
        if (amount <= 0) {
            System.out.println(prompt);
            return true;
        }
        return false;
    }

    public static boolean amountLessThanZeroCheck(double amount) {
        return amountLessThanZeroCheck(amount, "We can't process amount less than 0.");
    }

    public static boolean amountLargerThanBalance(double balance, double amount, String prompt) {
        if (amount > balance) {
            System.out.println(prompt);
            return true;
        }
        return false;
    }

    public static boolean amountLargerThanBalance(double balance, double amount) {
        return amountLargerThanBalance(balance, amount, "Amount is larger than Balance.");
    }

    // Account Management
    public static boolean findAccByID(ArrayList<Account> accs, int id) {
        for (Account acc : accs) {
            if (acc.getID() == id)
                return true;
        }
        return false;
    }

    public static boolean checkAccount(ArrayList<Account> accs, String username, String password) {
        for (Account acc : accs) {
            if (acc.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public static Account getAccByID(ArrayList<Account> accs, int id) {
        for (Account acc : accs) {
            if (acc.getID() == id)
                return acc;
        }
        return null;
    }

    public static Account getAccByUsername(ArrayList<Account> accs, String username) {
        for (Account acc : accs) {
            if (acc.getUsername().equals(username))
                return acc;
        }
        return null;
    }
}