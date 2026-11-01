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

        blueCar.setCarPosition(0, 50);
        blueCar.startMoving();
        blueCar.displayCar();
        blueCar.setCarSpeed(5);
        gameWindow.add(blueCar);

        CarPanel redCar = new CarPanel("../Img/RedCarImg.png");
        redCar.setBackground(Color.RED);
        redCar.setBounds(0, 300, gameWindow_X, 200);
        System.out.println(redCar.getWidth() + " : " + redCar.getHeight());

        redCar.setCarPosition(0, 50);
        redCar.startMoving();
        redCar.displayCar();
        redCar.setCarSpeed(5);
        gameWindow.add(redCar);

        gameWindow.setVisible(true);
    }

}
