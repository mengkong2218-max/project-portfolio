package bank_system.UI.AccPage.Deposit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import bank_system.AccManagement;
import bank_system.Account;
import bank_system.UI.Numpad.Numpad;

public class DepositWindow {
    private static JFrame withdrawFrame = new JFrame();

    public DepositWindow(Account myAcc, AccManagement accMag, Runnable onComplete) {

        Numpad numpadDisplay = new Numpad("Deposit Window Display", amountStr -> {
            try {
                double amount = Double.parseDouble(amountStr);
                if (!myAcc.deposit(amount))
                    JOptionPane.showMessageDialog(null, "Error: Can not perform Amount less than, or Equal to Zero.",
                            "Something Wrong", JOptionPane.ERROR_MESSAGE);

                if (onComplete != null) {
                    onComplete.run();
                    accMag.saveChanges();
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid amount.");
            }
        });

        withdrawFrame.add(numpadDisplay);
        withdrawFrame.setVisible(true);
    }
}
