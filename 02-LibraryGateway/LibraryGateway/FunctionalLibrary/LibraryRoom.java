package LibraryGateway.FunctionalLibrary;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import LibraryGateway.Patron.Patron;

public class LibraryRoom {
    private int capacity;
    private String roomLabel;
    private List<StudyDesk> studyDesk = new ArrayList<>();

    public LibraryRoom(String roomLabel, int capacity) {
        this.capacity = capacity;
        this.roomLabel = roomLabel;
        for (int i = 0; i < this.capacity; i++) {
            studyDesk.add(new StudyDesk(i + 1));
        }
    }

    public void displayFullRoom() {
        System.out.print(this.roomLabel);
        for (int i = 0; i < capacity; i++) {
            String status = String.valueOf(i + 1);
            if (i % 10 == 0)
                System.out.print("\n");
            if (studyDesk.get(i).isTaken())
                status = "x";
            if (i < 10)
                System.out.print("[" + status + "]   ");
            else
                System.out.print("[" + status + "]  ");
        }
        System.out.println();
    }

    public void setSpecificDeskToBeTaken(int deskNumber) {
        this.checkBoundaryOfCapacity(deskNumber);
        studyDesk.get(deskNumber - 1).setTaken(true);
    }

    public void askingPatronForDeskNumber(Scanner scanner, Patron patron) {
        this.displayFullRoom();
        System.out.println(" + Enter Your Prefer Desk Number: ");
        int deskNumber = scanner.nextInt();

        if (!checkBoundaryOfCapacity(deskNumber)) {
            System.out.println("Sorry! We can not proceed this process.");
            System.out.println("We can not set this desk number [" + deskNumber + "] to be taken.");
            return;
        }

        if (checkDeskIfItWasAvailable(deskNumber)) {
            System.out.println("This number is not available Right Now.");
            return;
        }
        this.assignPatronToDeskNumber(patron, deskNumber);
        patron.getInLibrary();
    }

    private boolean checkBoundaryOfCapacity(int deskNumber) {
        return deskNumber >= 1 && deskNumber <= capacity;
    }

    private boolean checkDeskIfItWasAvailable(int deskNumber) {
        return studyDesk.get(deskNumber - 1).isTaken();
    }

    private void assignPatronToDeskNumber(Patron patron, int deskNumber) {
        studyDesk.get(deskNumber - 1).assignPatron(patron);
    }

    public String getRoomLabel() {
        return roomLabel;
    }

    public void setRoomLabel(String roomLabel) {
        this.roomLabel = roomLabel;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<StudyDesk> getStudyDesk() {
        return studyDesk;
    }

    public void setStudyDesk(List<StudyDesk> studyDesk) {
        this.studyDesk = studyDesk;
    }

}
