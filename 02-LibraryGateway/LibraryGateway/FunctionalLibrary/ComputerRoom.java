package LibraryGateway.FunctionalLibrary;

public class ComputerRoom extends LibraryRoom {
    public ComputerRoom(String roomLael, int capacity) {
        super(roomLael, capacity);
    }

    @Override
    public void displayFullRoom() {
        System.out.print(this.getRoomLabel() + " -----------");
        for (int i = 0; i < this.getCapacity(); i++) {
            String status = String.valueOf(i + 1);
            if (i % 5 == 0)
                System.out.print("\n");
            if (this.getStudyDesk().get(i).isTaken())
                status = "x";
            if (i < 10)
                System.out.print("[" + status + "]   ");
            else
                System.out.print("[" + status + "]  ");
        }
        System.out.println();
    }
}
