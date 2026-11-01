package SimpleRacingGame.UI;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import SimpleRacingGame.ToolBox.ColorMap;

public class GameWindow {
    public static final int gameWindow_X = 1280;
    public static final int gameWindow_Y = 720;

    public GameWindow() {
        List<String> colors = new ArrayList<>(List.of("Red", "Blue", "Green", "Grey", "Yellow", "Purple"));
        JFrame gameWindow = new JFrame();
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setResizable(false);
        gameWindow.setLayout(new GridLayout(colors.size(), 1));
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setSize(gameWindow_X, gameWindow_Y);

        for (String color : colors) {
            CarPanel colorCar = new CarPanel("../Img/" + color + "CarImg.png");
            colorCar.setBackground(ColorMap.getColor(color));
            colorCar.startMoving();
            colorCar.displayCar();
            gameWindow.add(colorCar);
        }
        gameWindow.setVisible(true);
    }

}
