package LibraryGateway.Main;

import LibraryGateway.FunctionalLibrary.ComputerRoom;
import LibraryGateway.FunctionalLibrary.LibraryRoom;
import LibraryGateway.Patron.Patron;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        LibraryRoom room = new ComputerRoom("Computer Hall", 30);
        room.displayFullRoom();
    }
}