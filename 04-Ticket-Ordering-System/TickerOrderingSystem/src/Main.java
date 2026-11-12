import Database.DataSQL;

public class Main {
    public static void main(String[] args) {
        DataSQL myData = new DataSQL();
        if (myData.isConnectedToFile())
            System.out.println("It's Working.");
        else System.out.println("No, it isn't.");
    }
}