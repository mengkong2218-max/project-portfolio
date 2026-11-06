package SimpleRacingGame.UI;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import SimpleRacingGame.ImgAssistant.ImageLoader;

public class CarResultPanel extends JPanel {

    public CarResultPanel(String filePath, GameWindow game, JFrame frame, String result) {
        this.setLayout(null);
        this.setSize(500, 500);

        JLabel text = new JLabel("Winner");
        text.setBounds(middleInX(150), 50, 150, 100);
        text.setFont(new Font(null, Font.BOLD, 30));

        JPanel img = ImageLoader.loadImageAsPanel(filePath);
        img.setBounds(middleInX(200), 150, 200, 100);
        this.add(img);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(middleInX(150), 300, 150, 60);
        buttonPanel.setLayout(new BorderLayout());

        JButton conButton = new JButton("Continue");
        conButton.setFont(new Font(null, Font.BOLD, 20));
        buttonPanel.add(conButton, BorderLayout.CENTER);
        conButton.setFocusable(false);

        JButton okayButton = new JButton("Okay");
        okayButton.setFont(new Font(null, Font.BOLD, 20));
        okayButton.setFocusable(false);

        conButton.addActionListener(e -> {
            this.remove(img);
            text.setText(result);
            this.repaint();
            JPanel newImg = ImageLoader.loadImageAsPanel("../Img/" + game.getPickedItem()
                    + "CarImg.png");
            newImg.setBounds(middleInX(200), 150, 200, 100);
            this.add(newImg);
            buttonPanel.add(okayButton, BorderLayout.CENTER);
            buttonPanel.remove(conButton);
            this.revalidate();
            this.repaint();
        });

        okayButton.addActionListener(e -> {
            frame.dispose();
            game.exitTheGame();
            new StartAgain();
        });
        this.add(text);
        this.add(buttonPanel);
    }

    private int middleInX(int size) {
        return (getWidth() - size) / 2;
    }

}
