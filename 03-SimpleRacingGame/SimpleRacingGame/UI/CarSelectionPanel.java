package SimpleRacingGame.UI;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class CarSelectionPanel extends JPanel {
    public CarSelectionPanel() {
        this.setSize(500, 500);
        this.setLayout(null);

        JComboBox<String> choices = new JComboBox<>();
        choices.setBounds(middleInX(200), 100, 200, 50);
        choices.setFont(new Font(null, Font.BOLD, 20));
        for (String item : GameWindow.colors) {
            choices.addItem(item);
        }

        JButton getCarButton = new JButton("OK");
        getCarButton.setBounds(middleInX(100), 300, 100, 50);

        this.add(choices);
        this.add(getCarButton);
    }

    private int middleInX(int size) {
        return (getWidth() - size) / 2;
    }
}
