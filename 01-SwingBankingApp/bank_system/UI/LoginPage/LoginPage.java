package bank_system.UI.LoginPage;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Font;

import bank_system.AccManagement;
import bank_system.Account;
import bank_system.UI.MyMainLogo;
import bank_system.UI.AccPage.MainUiAcc;

public class LoginPage extends JPanel {

    private UsernamePanel username;
    private PasswordPanel password;
    private AccManagement accMag;

    public LoginPage(AccManagement accMag) {
        this.accMag = accMag;
        this.setLayout(null);

        int wLogo = 200;
        int hLogo = wLogo;
        MyMainLogo myLogo = new MyMainLogo(wLogo, hLogo);
        myLogo.setBounds(113, 40, wLogo, hLogo); // x, y, width, height
        this.add(myLogo);

        username = new UsernamePanel();
        username.setBounds(63, 220, 300, 75);
        this.add(username);

        password = new PasswordPanel();
        password.setBounds(63, 300, 300, 75);
        this.add(password);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(163, 400, 100, 40);
        loginButton.setFont(new Font(null, Font.BOLD, 15));
        loginButton.setFocusable(false);
        loginButton.addActionListener(e -> handleLogin());
        this.add(loginButton);

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBounds(163, 450, 100, 40);
        signUpButton.setFont(new Font(null, Font.BOLD, 15));
        signUpButton.setFocusable(false);
        signUpButton.addActionListener(e -> handlesignUp());
        this.add(signUpButton);
    }

    private void handleLogin() {
        String name = username.getUserName();
        String pwd = password.getPasswordInput();

        Account myAcc = accMag.getIntoAccount(name, pwd);

        if (myAcc != null) {
            new MainUiAcc(myAcc, accMag);

            java.awt.Window loginWin = javax.swing.SwingUtilities.getWindowAncestor(this);
            if (loginWin != null)
                loginWin.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Error: Can not find this Account", "Login Failed",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handlesignUp() {
        String name = username.getUserName();
        String pwd = password.getPasswordInput();

        Account myAcc = accMag.getIntoAccount(name, pwd);

        if (myAcc != null) {
            JOptionPane.showMessageDialog(null, "Error: This account is already Exist.", "Sign Up Failed",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            accMag.createAccount(name, pwd);
            JOptionPane.showMessageDialog(null, "Successfully: You can Login to Your Account", "Sign Up Successfully",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}