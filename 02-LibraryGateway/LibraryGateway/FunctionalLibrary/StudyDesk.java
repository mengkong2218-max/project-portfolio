package LibraryGateway.FunctionalLibrary;

import LibraryGateway.Patron.Patron;

public class StudyDesk {
    private int deskNumber;
    private boolean isTaken;
    private Patron patron;

    public StudyDesk(int deskNumber) {
        this.deskNumber = deskNumber;
        this.isTaken = false;
        this.patron = null;
    }

    public void assignPatron(Patron patron) {
        this.patron = patron;
        this.isTaken = true;
        this.patron.setDeskNumber(deskNumber);
    }

    public void freeTheDesk() {
        this.isTaken = false;
        this.patron = null;
    }

    public void getPatronInfo() {
        if (this.patron != null && this.isTaken != false) {
            this.patron.showInfo();
            return;
        }
        System.out.println("Sorry, We can not Find this Patron.");
    }

    public int getDeskNumber() {
        return deskNumber;
    }

    public void setDeskNumber(int deskNumber) {
        this.deskNumber = deskNumber;
    }

    public boolean isTaken() {
        return isTaken;
    }

    public void setTaken(boolean isTaken) {
        this.isTaken = isTaken;
    }

}
