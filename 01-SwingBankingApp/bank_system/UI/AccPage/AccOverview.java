package bank_system.UI.AccPage;

import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import bank_system.AccManagement;
import bank_system.Account;
import bank_system.UI.AccPage.Deposit.DepositWindow;
import bank_system.UI.AccPage.Transfer.TransferWindow;
import bank_system.UI.AccPage.Withdraw.WithdrawWindow;

public class AccOverview extends JPanel {

    public AccOverview(Account myAcc, AccManagement accMag) {
        this.setLayout(null);

        BankLogo logo = new BankLogo();
        logo.setBounds(0, 0, 300, 75);
        this.add(logo);

        AccBalance accBal = new AccBalance(myAcc);
        accBal.setBounds(30, 90, 366, 100);

        // Register this account's UI with AccManagement so other windows can update it
        accMag.registerListener(myAcc.getUsername(), () -> {
            accBal.refreshBalance();
        });

        // Automatically unregister when this panel is removed/closed
        this.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("ancestor".equals(evt.getPropertyName()) && evt.getNewValue() == null) {
                    accMag.unregisterListener(myAcc.getUsername());
                }
            }
        });

        JButton withdrawBut = new JButton("Withdraw");
        withdrawBut.setBounds(45, 220, 200, 50);
        withdrawBut.setFocusable(false);
        withdrawBut.setFont(new Font(null, Font.PLAIN, 16));
        withdrawBut.addActionListener(e -> {
            new WithdrawWindow(myAcc, accMag, () -> {
                accBal.refreshBalance();
            });
        });

        JButton depositBut = new JButton("Deposit");
        depositBut.setBounds(45, 300, 200, 50);
        depositBut.setFocusable(false);
        depositBut.setFont(new Font(null, Font.PLAIN, 16));
        depositBut.addActionListener(e -> {
            new DepositWindow(myAcc, accMag, () -> {
                accBal.refreshBalance();
            });
        });

        JButton transferBut = new JButton("Transfer");
        transferBut.setBounds(45, 380, 200, 50);
        transferBut.setFocusable(false);
        transferBut.setFont(new Font(null, Font.PLAIN, 16));
        transferBut.addActionListener(e -> {
            new TransferWindow(myAcc, accMag, () -> {
                accBal.refreshBalance();
            });
        });

        this.add(accBal);
        this.add(withdrawBut);
        this.add(depositBut);
        this.add(transferBut);
    }
}
