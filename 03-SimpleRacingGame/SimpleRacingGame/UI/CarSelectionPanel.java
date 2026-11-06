package SimpleRacingGame.UI;

import SimpleRacingGame.ImgAssistant.ImageLoader;
import SimpleRacingGame.ToolBox.ColorMap;

import java.awt.BorderLayout;
import java.awt.Color;
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

        this.item = GameWindow.colors.get(0);

        JComboBox<String> choices = new JComboBox<>();
        choices.setBounds(middleInX(200), 50, 200, 50);
        choices.setFont(new Font(null, Font.BOLD, 20));
        for (String item : GameWindow.colors) {
            choices.addItem(item);
        }

        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new BorderLayout());
        displayPanel.setBounds(middleInX(300), 150, 300, 150);
        displayPanel.setBackground(Color.BLACK);
        displayPanel.setOpaque(true);
        displayPanel.setBackground(ColorMap.getColor(this.item));

        JPanel initPanel = ImageLoader.loadImageAsPanel("../Img/" + this.item + "CarImg.png");
        displayPanel.add(initPanel, BorderLayout.CENTER);

        choices.addActionListener(e -> {
            displayPanel.removeAll();
            this.item = String.valueOf(choices.getSelectedItem());

            JPanel nextPanel = ImageLoader.loadImageAsPanel("../Img/" + this.item + "CarImg.png");
            displayPanel.add(nextPanel, BorderLayout.CENTER);
            displayPanel.revalidate();
            displayPanel.repaint();
        });

        JButton getCarButton = new JButton("OK");
        getCarButton.setFont(new Font(null, Font.BOLD, 20));
        getCarButton.setBounds(middleInX(100), 350, 100, 50);
        getCarButton.addActionListener(e -> {
            this.isClicked = true;
            gw.setPickedItem(this.item);
            gw.setGameStarted(isClicked);
            frame.dispose();
            gw.startTheGame();
        });

        this.add(choices);
        this.add(displayPanel);
        this.add(getCarButton);
    }

    private int middleInX(int size) {
        return (getWidth() - size) / 2;
    }

}
