package SimpleRacingGame.UI;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class StartAgain {
    public StartAgain() {
        GameWindow game = new GameWindow();
        JFrame frame = new JFrame();
        frame.setSize(500, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setResizable(true);

        JLabel text = new JLabel("Play Again?", SwingConstants.CENTER);
        text.setBounds((frame.getWidth() - 200) / 2, 50, 200, 50);
        text.setFont(new Font(null, Font.BOLD, 30));

        JButton yesButton = new JButton("Yes");
        yesButton.setBounds(100, 150, 100, 50);
        yesButton.setFocusable(false);
        yesButton.setFont(new Font(null, Font.BOLD, 25));
        yesButton.addActionListener(e -> {
            frame.dispose();
            game.start();
        });

        JButton noButton = new JButton("No");
        noButton.setBounds(250, 150, 100, 50);
        noButton.setFocusable(false);
        noButton.setFont(new Font(null, Font.BOLD, 25));
        noButton.addActionListener(e -> {

            frame.dispose();
            game.exitTheGame();
        });

        frame.add(text);
        frame.add(yesButton);
        frame.add(noButton);
        frame.setVisible(true);
    }
}
