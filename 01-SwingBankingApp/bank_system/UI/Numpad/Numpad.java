package bank_system.UI.Numpad;

import java.awt.Font;
import java.awt.GridLayout;
import java.util.function.Consumer;
import java.awt.ComponentOrientation;
import java.util.function.Consumer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Numpad extends JFrame {

    // public Numpad(JTextField textBox) {
    // this.setBounds(50, 150, 200, 200);
    // this.setLayout(new GridLayout(4, 3, 20, 20));
    // for (int i = 1; i <= 9; i++) {
    // JButton button = new JButton();
    // button.setText(String.valueOf(i));
    // button.setFocusable(false);
    // button.setFont(new Font(null, Font.PLAIN, 20));
    // button.addActionListener(e -> {
    // JButton clickedButton = (JButton) e.getSource();
    // String getNumber = clickedButton.getText();
    // textBox.setText(textBox.getText() + getNumber);
    // });
    // this.add(button);
    // }

    // JButton delButton = new JButton();
    // delButton.setText("D");
    // delButton.setFocusable(false);
    // delButton.setFont(new Font(null, Font.PLAIN, 20));
    // delButton.addActionListener(e -> {
    // String currentText = textBox.getText();
    // if (currentText != null && currentText.length() > 1) {
    // String newText = currentText.substring(0, currentText.length() - 1);
    // textBox.setText(newText);
    // } else {
    // textBox.setText("");
    // }
    // });

    // this.add(delButton);

    // JButton Zerobutton = new JButton();
    // Zerobutton.setText("0");
    // Zerobutton.setFocusable(false);
    // Zerobutton.setFont(new Font(null, Font.PLAIN, 20));
    // Zerobutton.addActionListener(e -> {
    // JButton clickedButton = (JButton) e.getSource();
    // String getNumber = clickedButton.getText();
    // textBox.setText(textBox.getText() + getNumber);
    // });
    // this.add(Zerobutton);

    // JButton goButton = new JButton();
    // goButton.setText("->");
    // goButton.setFocusable(false);
    // goButton.setFont(new Font(null, Font.PLAIN, 20));
    // goButton.addActionListener(e -> {
    // JFrame newWin = new JFrame();
    // newWin.setLayout(new GridLayout(1, 1));
    // newWin.setSize(300, 300);
    // newWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // newWin.setResizable(false);

    // JLabel theAmount = new JLabel();
    // theAmount.setText(textBox.getText());
    // theAmount.setFont(new Font(null, Font.BOLD, 20));
    // newWin.add(theAmount);

    // newWin.setVisible(true);
    // });
    // this.add(goButton);
    // }

    private JTextField textBox;
    private String amount;

    public Numpad(String title, Consumer<String> onSubmit) {
        this.setLayout(null);
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 450);
        this.setResizable(false);

        JLabel textLabel = new JLabel();
        textLabel.setText("Enter Your Amount: ");
        textLabel.setFont(new Font(null, Font.PLAIN, 16));
        textLabel.setBounds(50, 40, 150, 40);
        this.add(textLabel);

        textBox = new JTextField();
        textBox.setBounds(65, 85, 270, 35);
        textBox.setFont(new Font(null, Font.PLAIN, 18));
        textBox.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        this.add(textBox);

        JPanel numpadBox = new JPanel();
        numpadBox.setBounds(90, 150, 200, 200);
        numpadBox.setLayout(new GridLayout(4, 3, 20, 20));
        for (int i = 1; i <= 9; i++) {
            JButton button = new JButton();
            button.setText(String.valueOf(i));
            button.setFocusable(false);
            button.setFont(new Font(null, Font.PLAIN, 20));
            button.addActionListener(e -> {
                JButton clickedButton = (JButton) e.getSource();
                String getNumber = clickedButton.getText();
                textBox.setText(textBox.getText() + getNumber);
            });
            numpadBox.add(button);
        }

        JButton delButton = new JButton();
        delButton.setText("D");
        delButton.setFocusable(false);
        delButton.setFont(new Font(null, Font.PLAIN, 20));
        delButton.addActionListener(e -> {
            String currentText = textBox.getText();
            if (currentText != null && currentText.length() > 1) {
                String newText = currentText.substring(0, currentText.length() - 1);
                textBox.setText(newText);
            } else {
                textBox.setText("");
            }
        });

        numpadBox.add(delButton);

        JButton Zerobutton = new JButton();
        Zerobutton.setText("0");
        Zerobutton.setFocusable(false);
        Zerobutton.setFont(new Font(null, Font.PLAIN, 20));
        Zerobutton.addActionListener(e -> {
            JButton clickedButton = (JButton) e.getSource();
            String getNumber = clickedButton.getText();
            textBox.setText(textBox.getText() + getNumber);
        });
        numpadBox.add(Zerobutton);

        JButton goButton = new JButton();
        goButton.setText("->");
        goButton.setFocusable(false);
        goButton.setFont(new Font(null, Font.PLAIN, 20));
        goButton.addActionListener(e -> {
            this.amount = textBox.getText().trim();

            if (this.amount != null && !this.amount.isEmpty()) {
                onSubmit.accept(this.amount);
                textBox.setText("");
                this.dispose();
            }
        });
        numpadBox.add(goButton);

        this.add(numpadBox);
        this.setVisible(true);
    }
}
