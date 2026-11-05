package SimpleRacingGame.UI;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CarSelectionPanel extends JPanel {
    private String item;
    private boolean isClicked = false;

    public CarSelectionPanel(GameWindow gw, JFrame frame) {
        this.setSize(500, 500);
        this.setLayout(null);

        JComboBox<String> choices = new JComboBox<>();
        choices.setBounds(middleInX(200), 100, 200, 50);
        choices.setFont(new Font(null, Font.BOLD, 20));
        for (String item : GameWindow.colors) {
            choices.addItem(item);
        }

        this.item = GameWindow.colors.get(0);
        choices.addActionListener(e -> {
            this.item = String.valueOf(choices.getSelectedItem());
        });

        JButton getCarButton = new JButton("OK");
        getCarButton.setFont(new Font(null, Font.BOLD, 20));
        getCarButton.setBounds(middleInX(100), 300, 100, 50);
        getCarButton.addActionListener(e -> {
            this.isClicked = true;
            gw.setPickedItem(this.item);
            gw.setGameStarted(isClicked);
            frame.dispose();
            gw.startTheGame();
            System.out.println(gw.getPickedItem() + " : " + gw.isGameStarted());
            // System.out.println(this.getSelectedItem() + " : " + this.isClicked());
        });

        this.add(choices);
        this.add(getCarButton);
    }

    private int middleInX(int size) {
        return (getWidth() - size) / 2;
    }
}
