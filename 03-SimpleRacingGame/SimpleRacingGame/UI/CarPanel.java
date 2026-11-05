package SimpleRacingGame.UI;

import SimpleRacingGame.Entities.Car;

import SimpleRacingGame.ToolBox.Random;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarPanel extends JPanel {

    private Car myCar;
    private JPanel carImg;
    private int carPosX, carPosY;
    private JLabel lapLabel;
    private int lapCount;
    private int lapLabelWidth, lapLabelHeight;
    private Timer timer;
    private GameWindow game;

    public CarPanel(String filePath, GameWindow game) {
        this.game = game;
        this.setLayout(null);
        myCar = new Car(filePath);
        carImg = myCar.getMyImageAsJPanelObject();
        carImg.setOpaque(false);
        carImg.setSize(myCar.getCarWidth(), myCar.getCarHeight());
        lapLabel = new JLabel();

        lapLabel.setText(String.valueOf(lapCount));
        lapLabel.setFont(new Font(null, Font.BOLD, 40));
        lapLabel.setForeground(Color.WHITE);

        this.add(lapLabel);
        this.add(carImg);

    }

    public void drivingTheCar(int x) {
        this.carPosX = x;
        this.carPosY = (this.getHeight() - myCar.getCarHeight()) / 2;
        carImg.setLocation(carPosX, carPosY);
    }

    public void displayingTheCar() {
        timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (game.isRaceOverYet()) {
                    drivingTheCar(0);
                    timer.stop();
                    game.exitTheGame();
                    new StartAgain();
                    return;
                }

                int max = 30, min = 5;
                carPosX += Random.inRange(min, max);
                drivingTheCar(carPosX);
                if (carPosX >= getWidth()) {
                    carPosX = 0;
                    lapCount++;

                    if (reachLapToFinsih(6)) {
                        game.raceIsOver();
                        getTheResult();
                    }
                }

                lapLabelWidth = getWidth() * 5 / 100;
                lapLabelHeight = getHeight();
                carImg.repaint();
                lapLabel.setBounds(GameWindow.gameWindow_X - lapLabelWidth, 0, lapLabelWidth, lapLabelHeight);
                lapLabel.setText(String.valueOf(lapCount));
            }
        });

    }

    private void getTheResult() {
        if (myCar.getCarName().equals(game.getPickedItem()))
            JOptionPane.showMessageDialog(null, myCar.getCarName() + " has won. You are the Winner", "Victory",
                    JOptionPane.INFORMATION_MESSAGE);
        else
            JOptionPane.showMessageDialog(null,
                    myCar.getCarName() + " has won. You Lost. [" + game.getPickedItem() + "]", "Defeat",
                    JOptionPane.ERROR_MESSAGE);
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