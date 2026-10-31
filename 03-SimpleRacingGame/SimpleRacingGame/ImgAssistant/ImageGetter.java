package SimpleRacingGame.ImgAssistant;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class ImageGetter extends JPanel {
    private int imageWidth;
    private int imageHeight;
    private double angle = 0;
    private String filePath;
    private ImageLoader getImg;

    public ImageGetter(String filePath) {
        this.filePath = filePath;
        getImg = new ImageLoader(this.filePath);
    }

    public ImageGetter(String filePath, int imgWidth, int imgHeight) {
        this.filePath = filePath;
        getImg = new ImageLoader(this.filePath);
        this.imageWidth = imgWidth;
        this.imageHeight = imgHeight;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int imgX = (getWidth() - this.imageWidth) / 2;
        int imgY = (getHeight() - this.imageHeight) / 2;
        getImg.showImage(g2d, imgX, imgY, imageWidth, imageHeight, this.angle);
    }

    public void rotateAngle(double angle) {
        this.angle = angle;
    }
}
