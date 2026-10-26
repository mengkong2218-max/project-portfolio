package bank_system.UI.AccPage.Withdraw;

// import java.awt.ComponentOrientation;
// import java.awt.Font;

// import javax.swing.JButton;
import javax.swing.JFrame;
// import javax.swing.JLabel;
// import javax.swing.JTextField;
import javax.swing.JOptionPane;

import bank_system.AccManagement;
import bank_system.Account;
import bank_system.UI.Numpad.Numpad;

public class WithdrawWindow {
    private static JFrame withdrawFrame = new JFrame();
    // private static JTextField textBox;

    public WithdrawWindow(Account myAcc, AccManagement accMag, Runnable onComplete) {
        // withdrawFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // withdrawFrame.setSize(400, 450);
        // withdrawFrame.setLayout(null);
        // withdrawFrame.setResizable(false);
        // withdrawFrame.setTitle("Withdraw Window Display");

        // JLabel textLabel = new JLabel();
        // textLabel.setText("Set Amount: ");
        // textLabel.setFont(new Font(null, Font.PLAIN, 16));
        // textLabel.setBounds(50, 40, 100, 40);

        // textBox = new JTextField();
        // textBox.setBounds(65, 85, 270, 35);
        // textBox.setFont(new Font(null, Font.PLAIN, 18));
        // textBox.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        // JButton goButton = new JButton();
        // goButton.setText("->");
        // goButton.setFocusable(false);
        // goButton.setFont(new Font(null, Font.PLAIN, 20));
        // goButton.addActionListener(e -> {
        // try {
        // double amount = Double.parseDouble(textBox.getText());
        // myAcc.withdraw(amount);

        // if (onComplete != null) {
        // onComplete.run();
        // }

        // textBox.setText("");
        // withdrawFrame.dispose();
        // } catch (NumberFormatException ex) {
        // System.out.println("Invalid amount entered.");
        // }
        // });

        Numpad numpadDisplay = new Numpad("Withdraw Window Display", amountStr -> {
            try {
                double amount = Double.parseDouble(amountStr);

                if (!myAcc.withdraw(amount))
                    JOptionPane.showMessageDialog(null, "Check Your Amount\nIs Your Amount too large, or too low?",
                            "Something Wrong", JOptionPane.ERROR_MESSAGE);

                if (onComplete != null) {
                    onComplete.run();
                    accMag.saveChanges();
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid amount.");
            }
        });

        // double amount = numpadDisplay.getAmount();
        // myAcc.withdraw(amount);
        // if (onComplete != null)
        // onComplete.run();

        withdrawFrame.add(numpadDisplay);
        withdrawFrame.setVisible(true);
    }
}
