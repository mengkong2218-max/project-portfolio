package SimpleRacingGame.Entities;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.Timer;

import SimpleRacingGame.ImgAssistant.ImageLoader;
import SimpleRacingGame.UI.GameWindow;

public class Car extends ImageLoader {

    private BufferedImage myCarImg;
    private int carWidth = 120;
    private int carHeight = 70;
    private double carSpeed = 5;
    private int carX = 0;

    public Car(String filePath) {
        this.myCarImg = ImageLoader.loadMyImage(filePath);
    }

    @Override
    public JPanel getMyImageAsJPanelObject() {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (myCarImg != null) {

                    int x = (getWidth() - carWidth) / 2;
                    int y = (getHeight() - carHeight) / 2;
                    g.drawImage(myCarImg, x, y, carWidth, carHeight, this);
                }
            }

            @Override
            public Dimension getPreferredSize() {
                if (myCarImg != null) {
                    return new Dimension(carWidth, carHeight);
                }
                return super.getPreferredSize();
            }
        };
    }

    public JPanel startMoving() {
        JPanel carObjPanel = this.getMyImageAsJPanelObject();
        Timer timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carX += carSpeed;
                if (carX != GameWindow.gameWindow_X) {
                    carSpeed = 0;
                    return;
                }
                carObjPanel.repaint();
            }
        });

        timer.start();
        return carObjPanel;
    }

    public int getCarWidth() {
        return carWidth;
    }

    public void setCarWith(int carWidth) {
        this.carWidth = carWidth;
    }

    public int getCarHeight() {
        return carHeight;
    }

    public void setCarHeight(int carHeight) {
        this.carHeight = carHeight;
    }

    public double getCarSpeed() {
        return this.carSpeed;
    }
}