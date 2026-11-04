package SimpleRacingGame.UI;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import SimpleRacingGame.ToolBox.ColorMap;

public class GameWindow {
    public static final int gameWindow_X = 1280;
    public static final int gameWindow_Y = 720;
    public static List<String> colors = new ArrayList<>(List.of("Red", "Blue", "Green", "Grey", "Yellow", "Orange"));
    private boolean isRaceOver = false;
    JFrame gameWindow;
    JLayeredPane windowLinked;
    JPanel gameRunningPanel;
    private List<CarPanel> cars = new ArrayList<>();

    public GameWindow() {
        gameWindow = new JFrame();
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setResizable(false);
        gameWindow.setSize(gameWindow_X, gameWindow_Y);

        windowLinked = new JLayeredPane();
        gameRunningPanel = new JPanel();
        gameRunningPanel.setLayout(new GridLayout(colors.size(), 1));

        for (String color : colors) {
            CarPanel colorCar = new CarPanel("../Img/" + color + "CarImg.png", this);
            colorCar.setBackground(ColorMap.getColor(color));
            colorCar.setCarName(color + " Car");
            cars.add(colorCar);
        }
        gameWindow.add(gameRunningPanel);
        gameWindow.setVisible(true);
    }

    public void start() {
        this.displayTheCar();
        this.carSelectionWindow();
    }

    private void carSelectionWindow() {
        JFrame window = new JFrame();
        window.setSize(500, 500);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(new CarSelectionPanel());
        window.setVisible(true);
    }

    public boolean isRaceOverYet() {
        return this.isRaceOver;
    }

    public void raceIsOver() {
        if (!this.isRaceOverYet())
            this.isRaceOver = true;
    }

    private void startTheGame() {
        for (CarPanel car : cars)
            car.startTheGame();
    }

    private void displayTheCar() {
        for (CarPanel car : cars) {
            car.displayingTheCar();
            gameRunningPanel.add(car);
        }
    }
}
