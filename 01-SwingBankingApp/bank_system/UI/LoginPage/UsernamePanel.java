package bank_system.UI.LoginPage;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.GridLayout;

public class UsernamePanel extends JPanel {
    private JTextField usernameInput;

    public UsernamePanel() {
        this.setLayout(new GridLayout(2, 1));

        JLabel usernameLabel = new JLabel();
        usernameLabel.setText("Username: ");
        usernameLabel.setFont(new Font(null, Font.BOLD, 15));

        usernameInput = new JTextField(20);
        usernameInput.setFont(new Font(null, Font.PLAIN, 16));

        this.add(usernameLabel);
        this.add(usernameInput);
    }

    public String getUserName() {
        return usernameInput.getText().trim();
    }
}
