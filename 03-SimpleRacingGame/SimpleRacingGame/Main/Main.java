package SimpleRacingGame.Main;

import javax.swing.SwingUtilities;

import SimpleRacingGame.UI.GameWindow;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameWindow myGame = new GameWindow();
            myGame.start();
        });
    }
}