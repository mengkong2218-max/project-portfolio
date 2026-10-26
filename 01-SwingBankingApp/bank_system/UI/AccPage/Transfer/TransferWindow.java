package bank_system.UI.AccPage.Transfer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import bank_system.AccManagement;
import bank_system.Account;
import bank_system.UI.Numpad.Numpad;

import java.awt.Font;

public class TransferWindow extends JFrame {

    public TransferWindow(Account myAcc, AccManagement accMag, Runnable onComplete) {
        this.setLayout(null);
        this.setSize(450, 350);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Transfer Window");

        JLabel textLabel = new JLabel();
        textLabel.setText("Enter Account Name: ");
        textLabel.setFont(new Font(null, Font.BOLD, 18));
        textLabel.setBounds(60, 45, 200, 75);

        JTextField textBox = new JTextField();
        textBox.setFont(new Font(null, Font.PLAIN, 17));
        textBox.setBounds(100, 115, 250, 40);

        JButton checkAccButton = new JButton("Next");
        checkAccButton.setFont(new Font(null, Font.BOLD, 17));
        checkAccButton.setBounds(175, 180, 100, 40);
        checkAccButton.setFocusable(false);
        checkAccButton.addActionListener(e -> {
            String username = textBox.getText().trim();
            Account targetAcc = accMag.getAccountByUsername(username);

            if (targetAcc == null) {
                JOptionPane.showMessageDialog(null, "Not Found: " + username, "Login failed.",
                        JOptionPane.ERROR_MESSAGE);
                textBox.setText("");
                return;
            }

            this.dispose();

            new Numpad("Transfer Money Section", amountStr -> {
                try {
                    double amount = Double.parseDouble(amountStr);

                    if (!myAcc.transfer(targetAcc, amount)) {
                        JOptionPane.showMessageDialog(null, "Can not Process this action.", "Failed",
                                JOptionPane.ERROR_MESSAGE);
                    }

                    if (onComplete != null) {
                        onComplete.run();
                        accMag.notifyUser(targetAcc.getUsername());
                        accMag.saveChanges();

                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Amount!!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
        });

        this.add(textLabel);
        this.add(textBox);
        this.add(checkAccButton);
        this.setVisible(true);
    }

}
