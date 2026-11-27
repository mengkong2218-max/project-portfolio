package CLIDisplay;

import Validation.Validation;

public class OrderPage {
    public static void displayOrderList() {
        System.out.println("| ============== Order List =============== |");
        MainMenu.controlOrder.displayListOfOrder();
        System.out.println("| ========================================= |");
        Validation.stringInput("Press Enter to Exit...");
        System.out.println();
    }
}
