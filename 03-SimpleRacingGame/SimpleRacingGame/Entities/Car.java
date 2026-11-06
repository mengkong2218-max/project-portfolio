package SimpleRacingGame.Entities;

import java.awt.image.BufferedImage;

import SimpleRacingGame.ImgAssistant.ImageLoader;

public class Car {

    private BufferedImage myCarImg;
    private int carWidth = 120;
    private int carHeight = 70;
    private double carSpeed = 5;
    private int carX = 0;
    private String carName;

    public Car(String filePath) {
        this.myCarImg = ImageLoader.loadMyImage(filePath);
        this.carName = "My Car";
    }

    public BufferedImage getMyCarImg() {
        return this.myCarImg;
    }

    public void setCarX(int x) {
        this.carX = x;
    }

    public int getCarX() {
        return this.carX;
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

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

}