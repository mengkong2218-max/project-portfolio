package LibraryGateway.Main;

import LibraryGateway.FunctionalLibrary.LibraryRoom;
import LibraryGateway.Patron.Patron;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LibraryRoom room = new LibraryRoom(30);
        Patron hiso = new Patron();
        hiso.askingInfo(scanner);

        room.askingPatronForDeskNumber(scanner, hiso);
        room.displayFullRoom();

        try {
            Thread.sleep(123456);
        } catch (InterruptedException e) {
            System.err.println(e);
        }
        hiso.getOutofLibrary();
        hiso.showInfo();
        scanner.close();
    }
}