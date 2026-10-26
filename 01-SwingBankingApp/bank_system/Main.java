package bank_system;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AccManagement accounts = new AccManagement();
        while (true) {
            Account myAcc;
            helper.starterScreen();
            System.out.print("+ Enter: ");
            int starterChoice = Integer.parseInt(scanner.nextLine().trim());

            if (starterChoice == 0)
                break;
            else {
                switch (starterChoice) {
                    case 1:
                        myAcc = helper.loginScreen(scanner, accounts);
                        if (myAcc == null)
                            break;
                        MainScreen.startApp(myAcc, scanner, accounts);
                        break;
                    case 2:
                        helper.signUpScreen(scanner, accounts);
                        break;
                    default:
                        System.out.println("!!! Invalid Input !!!");
                        break;
                }
            }
        }

        scanner.close();
    }

}
