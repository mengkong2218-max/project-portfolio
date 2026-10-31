package SimpleRacingGame.ImgAssistant;

import java.io.IOException;
import java.io.InputStream;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class ImageLoader {
    BufferedImage image;

    public ImageLoader(String filePath) {
        try (InputStream fileImg = ImageLoader.class.getResourceAsStream(filePath)) {
            if (fileImg == null) {
                JOptionPane.showMessageDialog(null, "[Error]: Can not found Item in this path.", "File Not Found",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            image = ImageIO.read(fileImg);

        } catch (IOException e) {

            JOptionPane.showMessageDialog(null, "[Error]: " + e.getMessage(), "File Not Found",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void showImage(Graphics2D g2d, int x, int y, int width, int height) {
        g2d.drawImage(image, x, y, width, height, null);
    }

    public void showImage(Graphics2D g2d, int x, int y, int width, int height, double angle) {
        AffineTransform oldImg = g2d.getTransform();
        double angleInRadains = Math.toRadians(angle);
        g2d.translate(x + (width / 2.0), y + (height / 2.0));
        g2d.rotate(angleInRadains);

        showImage(g2d, -x / 2, -y / 2, width, height);
        g2d.setTransform(oldImg);
    }
}