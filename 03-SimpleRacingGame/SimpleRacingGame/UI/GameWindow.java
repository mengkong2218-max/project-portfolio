package SimpleRacingGame.UI;

import java.awt.Color;

import javax.swing.JFrame;

public class GameWindow {
    public static final int gameWindow_X = 1280;
    public static final int gameWindow_Y = 720;

    public GameWindow() {
        JFrame gameWindow = new JFrame();
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setResizable(false);
        gameWindow.setLayout(null);
        gameWindow.setSize(gameWindow_X, gameWindow_Y);

        CarPanel blueCar = new CarPanel("../Img/BlueCarImg.png");
        blueCar.setBackground(Color.BLUE);
        blueCar.setBounds(0, 0, gameWindow_X, 200);
        System.out.println(blueCar.getWidth() + " : " + blueCar.getHeight());

        blueCar.setCarPosition(gameWindow_X / 2, gameWindow_Y / 2);
        blueCar.displayCar();

        gameWindow.add(blueCar);

        gameWindow.setVisible(true);
    }

}
