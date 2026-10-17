package bank_system.UI;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;

import bank_system.AccManagement;

public class OpenWindow {

    private static AccManagement accMag = new AccManagement();

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(400, 300);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setTitle("S'ey krr ban");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton openBut = new JButton("Open THe Bank");
        openBut.setBounds(100, 105, 200, 50);
        openBut.setFocusable(false);
        openBut.setFont(new Font(null, Font.BOLD, 15));
        openBut.addActionListener(e -> {
            new MainFrame(accMag);
        });

        frame.add(openBut);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
