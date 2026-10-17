package bank_system.UI.AccPage;

import bank_system.AccManagement;
import bank_system.Account;

import javax.swing.JFrame;

public class MainUiAcc extends JFrame {
    public MainUiAcc(Account myAcc, AccManagement accMag) {
        this.setSize(426, 820);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Virtual Bank - " + myAcc.getUsername());
        this.setLocationRelativeTo(null);

        AccOverview overviewPanel = new AccOverview(myAcc, accMag);
        this.add(overviewPanel);

        this.setVisible(true);
    }
}
