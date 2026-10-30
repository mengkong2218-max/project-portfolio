package SimpleRacingGame.ImgAssistant;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Image;
import java.net.URL;

public class BlueCar extends JPanel {

    private final String FILE_PATH = "../Img/BlueCarImg.png";
    private int width = 100;
    private int height = 100;

    public BlueCar() {
        URL myFilePath = BlueCar.class.getResource(FILE_PATH);
        if (myFilePath == null) {
            JOptionPane.showMessageDialog(null, "Error: Can not find this Image at: " + FILE_PATH, "Image Not Found.",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        ImageIcon img = new ImageIcon(myFilePath);
        Image scaledImg = img.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon myImg = new ImageIcon(scaledImg);

        JLabel imgLabel = new JLabel(myImg);
        this.setPreferredSize(new Dimension(width, height));
        this.add(imgLabel);
        this.setOpaque(false);
    }
}
