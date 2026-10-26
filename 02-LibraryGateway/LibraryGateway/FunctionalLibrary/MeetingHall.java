package LibraryGateway.FunctionalLibrary;

public class MeetingHall extends LibraryRoom {

    public MeetingHall(String roomLabel, int capacity) {
        super(roomLabel, capacity);
    }

    @Override
    public void displayFullRoom() {
        System.out.print(this.getRoomLabel() + " -----------");
        for (int i = 0; i < this.getCapacity(); i++) {
            String status = String.valueOf(i + 1);
            if (this.getStudyDesk().get(i).isTaken())
                status = "x";
            if (i == 0 || i == this.getCapacity() - 1) {
                System.out.println("     [" + status + "]");
                continue;
            }
            if (i < 9)
                System.out.print("[" + status + "] ");
            else
                System.out.print("[" + status + "]");
            if (i % 2 != 0)
                System.out.print(" |=| ");
            else
                System.out.println();
        }
        System.out.println();
    }
}
