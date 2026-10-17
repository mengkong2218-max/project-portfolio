package bank_system.UI;

import javax.swing.JFrame;

import bank_system.AccManagement;
import bank_system.UI.LoginPage.LoginPage;

public class MainFrame extends JFrame {

    // private static AccManagement accMag = new AccManagement();

    // public static void main(String[] args) {
    // JFrame mainFrame = new JFrame();

    // mainFrame.setSize(426, 820);
    // mainFrame.setResizable(false);
    // mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // mainFrame.add(new LoginPage(accMag));

    // mainFrame.setLocationRelativeTo(null);
    // mainFrame.setVisible(true);
    // }

    private AccManagement accMag;

    public MainFrame(AccManagement accMag) {
        this.accMag = accMag;
        this.setSize(426, 820);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(new LoginPage(this.accMag));

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
