package SimpleRacingGame.UI;

import javax.swing.JFrame;

import SimpleRacingGame.ImgAssistant.BlueCar;

public class GameWindow {
    public static final int gameWindow_X = 1280;
    public static final int gameWindow_Y = 720;

    public GameWindow() {
        JFrame gameWindow = new JFrame();
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setResizable(false);
        gameWindow.setSize(gameWindow_X, gameWindow_Y);
        gameWindow.add(new BlueCar());
        gameWindow.setVisible(true);
    }

}
