package SimpleRacingGame.UI;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import SimpleRacingGame.Entities.Car;
import SimpleRacingGame.ToolBox.ColorMap;

public class GameWindow {
    public static final int gameWindow_X = 1280;
    public static final int gameWindow_Y = 720;
    public static List<String> colors = new ArrayList<>(List.of("Red", "Blue", "Green", "Grey", "Yellow", "Orange"));
    private String pickedItem;
    private boolean isRaceOver = false;
    private boolean gameStarted = false;
    private JFrame gameWindow;
    private JLayeredPane gameRunningPanel;
    private CarSelectionPanel carOption;
    private List<CarPanel> cars = new ArrayList<>();
    private JPanel showCarPanel;

    public GameWindow() {
        gameWindow = new JFrame();
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setResizable(false);
        gameWindow.setLayout(null);
        gameWindow.setSize(gameWindow_X, gameWindow_Y);

        gameRunningPanel = new JLayeredPane();
        gameRunningPanel.setBounds(0, 0, gameWindow_X, gameWindow_Y);
        ;

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
        carOption = new CarSelectionPanel(this, gameRunningPanel);
        carOption.setBounds(390, 110, 500, 450);
        gameRunningPanel.add(carOption, Integer.valueOf(1));
    }

    private void displayTheCar() {
        showCarPanel = new JPanel();
        showCarPanel.setLayout(new GridLayout(colors.size(), 1));
        showCarPanel.setBounds(0, -10, GameWindow.gameWindow_X, GameWindow.gameWindow_Y);
        for (CarPanel car : cars) {
            car.displayingTheCar();
            showCarPanel.add(car);
        }
        gameRunningPanel.add(showCarPanel, Integer.valueOf(0));
    }

    public void getTheResult(String filePath, Car myCar) {

        String result = "You Lose";
        if (myCar.getCarName().equals(this.getPickedItem()))
            result = "You Win";
        CarResultPanel resultPanel = new CarResultPanel(filePath, this, gameRunningPanel, result);
        resultPanel.setBounds(390, 110, 500, 450);
        gameRunningPanel.add(resultPanel, Integer.valueOf(1));
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
}
