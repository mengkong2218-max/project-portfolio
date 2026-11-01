package SimpleRacingGame.UI;

import SimpleRacingGame.Entities.Car;

import javax.swing.JLabel;
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

    public CarPanel(String filePath) {
        this.setLayout(null);
        myCar = new Car(filePath);
        carImg = myCar.getMyImageAsJPanelObject();
        carImg.setOpaque(false);
        carImg.setSize(myCar.getCarWidth(), myCar.getCarHeight());
        lapLabel = new JLabel();
    }

    public void drivingTheCar(int x) {
        this.carPosX = x;
        this.carPosY = (this.getHeight() - myCar.getCarHeight()) / 2;
        carImg.setLocation(carPosX, carPosY);
    }

    public void addLapLabel() {
        lapLabel.setText(String.valueOf(lapCount));
        lapLabel.setBounds(GameWindow.gameWindow_X - lapLabelWidth, 0, lapLabelWidth, lapLabelHeight);
        lapLabel.setFont(new Font(null, Font.BOLD, 40));
        lapLabel.setForeground(Color.WHITE);
        // System.out.println("\r Lap label: " + this.getWidth() * (int) 0.1 + " : " +
        // this.getHeight());
        System.out.print("\r " + lapLabelWidth + " : " + lapLabelHeight);
        this.add(lapLabel);
    }

    public void displayCar() {
        this.add(carImg);
    }

    public void startMoving() {
        Timer timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int max = 30, min = 5;
                carPosX += (int) (Math.random() * (max - min + 1)) + 1;
                drivingTheCar(carPosX);
                if (carPosX >= getWidth()) {
                    carPosX = 0;
                    lapCount++;
                }

                lapLabelWidth = getWidth() * 5 / 100;
                lapLabelHeight = getHeight();
                // System.out.println("Car Position: ( " + carPosX + " , " + carPosY + " ) ");
                carImg.repaint();
                addLapLabel();
            }
        });
        timer.start();
    }

}