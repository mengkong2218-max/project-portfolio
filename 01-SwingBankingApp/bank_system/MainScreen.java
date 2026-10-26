package bank_system;

import java.util.Scanner;

public class MainScreen {
    public static void startApp(Account myAcc, Scanner scanner, AccManagement accLists) {
        while (true) {
            helper.mainMenu(myAcc.getUsername(), myAcc.getBalance());
            System.out.print("+ Enter Option: ");
            int option = Integer.parseInt(scanner.nextLine().trim());
            if (option == 0)
                return;
            else {
                switch (option) {
                    case 1:
                        System.out.println("======= Deposit =======");
                        System.out.print("+ Enter Amount: ");
                        double depositAmount = Double.parseDouble(scanner.nextLine().trim());
                        myAcc.deposit(depositAmount);
                        accLists.saveChanges();
                        break;

                    case 2:
                        System.out.println("======= Withdraw =======");
                        System.out.print("- Enter Amount: ");
                        double withdrawAmount = Double.parseDouble(scanner.nextLine().trim());
                        myAcc.withdraw(withdrawAmount);
                        accLists.saveChanges();
                        break;

                    case 3:
                        System.out.println("========= Transfer =========");
                        System.out.print("+ Enter Username: ");
                        String targetUser = scanner.nextLine();
                        Account targetAcc = accLists.getAccountByUsername(targetUser);
                        if (targetAcc == null) {
                            System.out.println("User '" + targetUser + "' was not Found!");
                            break;
                        }
                        if (targetAcc.equals(myAcc)) {
                            System.out
                                    .println("I don't think You need to transfer your money to your own bank account.");
                            break;
                        }
                        System.out.print("+ Enter Amount: ");
                        double transferAmount = Double.parseDouble(scanner.nextLine().trim());
                        myAcc.transfer(targetAcc, transferAmount);
                        accLists.saveChanges();
                        break;

                    default:
                        System.out.println("!!! Invalid Input !!!");
                        break;
                }
            }
        }
    }
}
