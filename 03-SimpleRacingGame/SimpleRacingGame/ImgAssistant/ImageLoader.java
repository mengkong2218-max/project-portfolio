package SimpleRacingGame.ImgAssistant;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.swing.JPanel;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class ImageLoader {

    public static BufferedImage loadMyImage(String filePath) {

        try {
            InputStream myImg = ImageLoader.class.getResourceAsStream(filePath);
            if (myImg == null) {
                JOptionPane.showMessageDialog(null, "[Error]: Can not Found this Image.", "File Not Found",
                        JOptionPane.ERROR_MESSAGE);
                return null;
            }
            return ImageIO.read(myImg);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "[Error]: " + e, "Something Went Wrong.", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public static JPanel loadImageAsPanel(String filePath) {
        BufferedImage myImg = ImageLoader.loadMyImage(filePath);
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (myImg != null) {
                    g.drawImage(myImg, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
    }
}