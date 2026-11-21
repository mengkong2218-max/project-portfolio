package Validation;

import java.util.Scanner;

public class Validation {

    private static final Scanner input = new Scanner(System.in);

    public static int getPositiveIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (input.hasNextInt()) {
                int value = input.nextInt();
                if (value >= 0)
                    return value;
            }
            input.next();
            System.out.println("[Invalid]: Not a valid Input. Try Again.");
        }
    }

    public static double getPositiveDoubleInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (input.hasNextDouble()) {
                double value = input.nextDouble();
                if (value >= 0.0)
                    return value;
            }
            input.next();
            System.out.println("[Invalid]: Not a valid Input. Try Again.");
        }
    }
}
