package SimpleRacingGame.UI;

import java.awt.Color;

import javax.swing.JFrame;

import SimpleRacingGame.ImgAssistant.ImageGetter;

public class GameWindow {
    public static final int gameWindow_X = 1280;
    public static final int gameWindow_Y = 720;

    public GameWindow() {
        JFrame gameWindow = new JFrame();
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setResizable(false);
        gameWindow.setLayout(null);
        gameWindow.setSize(gameWindow_X, gameWindow_Y);

        ImageGetter blueCar = new ImageGetter("../Img/BlueCarImg.png", 100, 100);
        // blueCar.rotateAngle(90);
        blueCar.setBounds(0, 0, 200, 200);
        blueCar.setBackground(Color.BLUE);
        gameWindow.add(blueCar);

        ImageGetter redCar = new ImageGetter("../Img/RedCarImg.png", 100,
                100);
        // redCar.rotateAngle(90);
        redCar.setBounds(0, 300, 200, 200);
        redCar.setBackground(Color.RED);
        gameWindow.add(redCar);
        gameWindow.setVisible(true);
    }

}
