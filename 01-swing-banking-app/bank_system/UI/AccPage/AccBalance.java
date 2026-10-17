package bank_system.UI.AccPage;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import bank_system.Account;

public class AccBalance extends JPanel {

    private Account accRef;
    private JLabel balanceLabel;

    public AccBalance(Account myAcc) {
        this.accRef = myAcc;
        this.setLayout(new GridLayout(2, 1, 20, 0));
        JLabel nameLabel = new JLabel();
        nameLabel.setText("     Name: " + myAcc.getUsername());
        nameLabel.setFont(new Font(null, Font.PLAIN, 18));
        this.add(nameLabel);

        balanceLabel = new JLabel();
        refreshBalance();
        balanceLabel.setFont(new Font(null, Font.PLAIN, 18));
        this.add(balanceLabel);

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    }

    public void refreshBalance() {
        balanceLabel.setText("      Balance: " + accRef.getBalance() + "$");
    }
}
