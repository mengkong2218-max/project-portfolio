import Database.DataSQL;
import Users.UsersMag;

public class Main {
    private static DataSQL myData = new DataSQL();
    public static void main(String[] args) {
        UsersMag usersTable = new UsersMag(myData);
        usersTable.printALLUsers();
        System.out.println("\n");
        usersTable.changeUsernameByEmail("mengkong2218@gmail.com", "Yeliang Gong");
        usersTable.printALLUsers();
    }
}