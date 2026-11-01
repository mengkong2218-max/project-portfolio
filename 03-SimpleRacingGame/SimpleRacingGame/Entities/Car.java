package SimpleRacingGame.Entities;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import SimpleRacingGame.ImgAssistant.ImageLoader;

public class Car extends ImageLoader {

    private BufferedImage myCarImg;
    private int carWidth = 120;
    private int carHeight = 70;

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

    public int getCarWidth() {
        return carWidth;
    }

    public int getCarHeight() {
        return carHeight;
    }

}