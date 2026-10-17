package bank_system.UI.LoginPage;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class PasswordPanel extends JPanel {
    private JPasswordField passwordInput;

    public PasswordPanel() {
        this.setLayout(new GridLayout(2, 1));

        JLabel passwordLabel = new JLabel();
        passwordLabel.setText("Password: ");
        passwordLabel.setFont(new Font(null, Font.BOLD, 15));

        passwordInput = new JPasswordField();
        passwordInput.setFont(new Font(null, Font.PLAIN, 20));

        this.add(passwordLabel);
        this.add(passwordInput);
    }

    public String getPasswordInput() {
        return String.valueOf(passwordInput.getPassword());
    }
}
