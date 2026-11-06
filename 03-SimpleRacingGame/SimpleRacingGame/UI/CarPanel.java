package SimpleRacingGame.UI;

import SimpleRacingGame.Entities.Car;

import SimpleRacingGame.ToolBox.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarPanel extends JPanel {

    private Car myCar;
    private JLabel lapLabel;
    private int lapCount;
    private int lapLabelWidth, lapLabelHeight;
    private Timer timer;
    private GameWindow game;
    private String filePath;

    public CarPanel(String filePath, GameWindow game) {
        this.filePath = filePath;
        this.game = game;
        this.setLayout(null);
        myCar = new Car(filePath);
        lapLabel = new JLabel();

        lapLabel.setText(String.valueOf(lapCount));
        lapLabel.setFont(new Font(null, Font.BOLD, 40));
        lapLabel.setForeground(Color.WHITE);

        this.add(lapLabel);

    }

    public void drivingTheCar(int x) {
        myCar.setCarX(x);
        repaint();
    }

    public void displayingTheCar() {
        timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (game.isRaceOverYet()) {
                    drivingTheCar(0);
                    timer.stop();
                    return;
                }

                int max = 30, min = 5;
                int carPosX = myCar.getCarX() + Random.inRange(min, max);
                drivingTheCar(carPosX);
                if (carPosX >= getWidth()) {
                    carPosX = 0;
                    drivingTheCar(carPosX);
                    lapCount++;

                    if (reachLapToFinsih(6)) {
                        game.raceIsOver();
                        getTheResult();
                    }
                }

                lapLabelWidth = getWidth() * 5 / 100;
                lapLabelHeight = getHeight();
                lapLabel.setBounds(GameWindow.gameWindow_X - lapLabelWidth, 0, lapLabelWidth, lapLabelHeight);
                lapLabel.setText(String.valueOf(lapCount));
            }
        });

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (myCar != null && myCar.getMyCarImg() != null) {
            int y = (getHeight() - myCar.getCarHeight()) / 2;
            g.drawImage(myCar.getMyCarImg(), myCar.getCarX(), y, myCar.getCarWidth(), myCar.getCarHeight(), this);
        }
    }

    private void getTheResult() {
        JFrame resultFrame = new JFrame();
        resultFrame.setSize(500, 500);
        resultFrame.setResizable(false);
        resultFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String result = "You Lose";
        if (myCar.getCarName().equals(game.getPickedItem()))
            result = "You Win";
        CarResultPanel resultPanel = new CarResultPanel(filePath, game, resultFrame, result);
        resultFrame.add(resultPanel);
        resultFrame.setVisible(true);
    }

    public void startTheGame() {
        timer.start();
    }

    private boolean reachLapToFinsih(int lapSizePreffered) {
        if (this.lapCount >= lapSizePreffered) {
            return true;
        }
        return false;

    }

    public String getCarName() {
        return myCar.getCarName();
    }

    public void setCarName(String carName) {
        myCar.setCarName(carName);
    }
}