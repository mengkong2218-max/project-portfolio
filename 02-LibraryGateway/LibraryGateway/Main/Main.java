package LibraryGateway.Main;

import LibraryGateway.FunctionalLibrary.ComputerRoom;
import LibraryGateway.FunctionalLibrary.LibraryRoom;
import LibraryGateway.FunctionalLibrary.MeetingHall;
import LibraryGateway.Patron.Patron;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        LibraryRoom room = new MeetingHall("Meeting Hall", 30);
        room.displayFullRoom();
    }
}