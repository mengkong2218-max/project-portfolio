package SimpleRacingGame.Main;

// import java.awt.BorderLayout;
// import java.util.jar.JarEntry;

// import javax.swing.JFrame;
import javax.swing.SwingUtilities;

// import SimpleRacingGame.UI.CarResultPanel;
import SimpleRacingGame.UI.GameWindow;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameWindow game = new GameWindow();
            game.start();
            // JFrame frame = new JFrame();
            // frame.setSize(500, 500);
            // frame.setLayout(new BorderLayout());
            // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // CarResultPanel resultPanel = new CarResultPanel("../Img/RedCarImg.png");
            // frame.add(resultPanel, BorderLayout.CENTER);
            // frame.setVisible(true);
        });
    }
}