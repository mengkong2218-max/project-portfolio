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
    private String pickedItem;
    private boolean isRaceOver = false;
    private boolean gameStarted = false;
    JFrame gameWindow;
    JLayeredPane windowLinked;
    JPanel gameRunningPanel;
    CarSelectionPanel carOption;
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
            colorCar.setCarName(color);
            cars.add(colorCar);
        }
        gameWindow.add(gameRunningPanel);
    }

    public void start() {
        gameWindow.setVisible(true);
        this.displayTheCar();
        this.carSelectionWindow();
    }

    private void carSelectionWindow() {
        JFrame window = new JFrame();
        window.setSize(500, 500);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        carOption = new CarSelectionPanel(this, window);
        window.add(carOption);
        window.setVisible(true);
    }

    public boolean isRaceOverYet() {
        return this.isRaceOver;
    }

    public void raceIsOver() {
        if (!this.isRaceOverYet())
            this.isRaceOver = true;
    }

    public void startTheGame() {
        for (CarPanel car : cars)
            car.startTheGame();
    }

    private void displayTheCar() {
        for (CarPanel car : cars) {
            car.displayingTheCar();
            gameRunningPanel.add(car);
        }
    }

    public void setPickedItem(String pickedItem) {
        this.pickedItem = pickedItem;
    }

    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }

    public String getPickedItem() {
        return pickedItem;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public void exitTheGame() {
        gameWindow.setVisible(false);
        gameWindow.dispose();
    }

}
