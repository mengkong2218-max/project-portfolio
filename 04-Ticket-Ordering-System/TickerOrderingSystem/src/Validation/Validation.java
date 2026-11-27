package Validation;

import java.util.Scanner;
import java.util.Set;

public class Validation {

    public static final Set<String> setOfYes = Set.of("y", "yes");
    private static final Scanner input = new Scanner(System.in);

    public static int getPositiveIntInput(String prompt) {
        while (true) {
            String numInput = stringInput(prompt).trim();
            try {
                int value = Integer.parseInt(numInput);
                if (value >= 0) return value;
                System.out.println("[Invalid]: Not a valid Input. Try Again.");
            } catch (NumberFormatException e) {
                System.out.println(e);
            }
        }
    }

    public static double getPositiveDoubleInput(String prompt) {
        while (true) {
            String numInput = stringInput(prompt).trim();
            try {
                double value = Double.parseDouble(numInput);
                if (value >= 0) return value;
                System.out.println("[Invalid]: Not a valid Input. Try Again.");
            } catch (NumberFormatException e) {
                System.out.println(e);
            }
        }
    }

    public static String stringInput(String prompt) {
        System.out.print(prompt);
        return input.nextLine();
    }
}
