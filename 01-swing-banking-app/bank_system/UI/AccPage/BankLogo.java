package bank_system.UI.AccPage;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;

import bank_system.UI.MyMainLogo;

public class BankLogo extends JPanel {
    public BankLogo() {
        this.setLayout(null);
        int logoSize = 60;

        MyMainLogo logo = new MyMainLogo(logoSize, logoSize);
        logo.setBounds(20, 10, logoSize, logoSize);
        this.add(logo);

        JLabel vBank = new JLabel();
        vBank.setText("Virtual Bank");
        vBank.setFont(new Font("Courier New", Font.BOLD, 17));
        vBank.setBounds(80, 20, 250, 30);
        this.add(vBank);

        JLabel subLabel = new JLabel();
        subLabel.setText("Your Trusted Bank.");
        subLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
        subLabel.setBounds(80, 37, 250, 30);
        this.add(subLabel);
    }
}
