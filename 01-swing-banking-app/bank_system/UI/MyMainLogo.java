package bank_system.UI;

import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyMainLogo extends JPanel {

    public MyMainLogo(int width, int height) {
        ImageIcon oriImage = new ImageIcon("bank_system/UI/myLogo.png");

        Image scaledImage = oriImage.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon logoIcon = new ImageIcon(scaledImage);

        JLabel logoLabel = new JLabel(logoIcon);

        this.setPreferredSize(new Dimension(width, height));
        this.add(logoLabel);

        this.setOpaque(false);
    }
}