package SimpleRacingGame.ImgAssistant;

import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public abstract class ImageLoader {

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

    public abstract JPanel getMyImageAsJPanelObject();
}