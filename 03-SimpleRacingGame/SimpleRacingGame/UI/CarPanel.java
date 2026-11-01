package SimpleRacingGame.UI;

import SimpleRacingGame.Entities.Car;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class CarPanel extends JPanel {

    private Car myCar;
    private JPanel carImg;
    private int posX, posY;
    private double carSpeed = 10;

    public CarPanel(String filePath) {
        this.setLayout(null);
        myCar = new Car(filePath);
        carImg = myCar.getMyImageAsJPanelObject();
        carImg.setOpaque(false);
        carImg.setSize(myCar.getCarWidth(), myCar.getCarHeight());
    }

    public void setCarPosition(int x, int y) {
        this.posX = x;
        this.posY = y;
        carImg.setLocation(posX, posY);
    }

    public void displayCar() {
        this.add(carImg);
    }

    public void startMoving() {
        Timer timer = new Timer(35, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                posX += carSpeed;
                setCarPosition(posX, posY);
                if (posX >= GameWindow.gameWindow_X)
                    posX = 0;

                carImg.repaint();
            }
        });
        timer.start();
    }

    public void setCarSpeed(double carSpeed) {
        this.carSpeed = carSpeed;
    }

}