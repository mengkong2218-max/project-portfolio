package SimpleRacingGame.UI;

import SimpleRacingGame.Entities.Car;
import javax.swing.JPanel;

public class CarPanel extends JPanel {

    private Car myCar;
    private JPanel carImg;

    public CarPanel(String filePath) {
        this.setLayout(null);
        myCar = new Car(filePath);
        carImg = myCar.getMyImageAsJPanelObject();
        carImg.setOpaque(false);
    }

    public void setCarPosition(int x, int y) {
        carImg.setBounds(x, y, myCar.getCarWidth(), myCar.getCarHeight());
        System.out.println(carImg.getWidth() + " : Car : " + carImg.getHeight());
    }

    public void displayCar() {
        this.add(carImg);
    }
}