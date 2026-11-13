package Users;

import Database.DataSQL;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsersMag {
    private List<User> users = new ArrayList<>();
    private DataSQL usersData;

    public UsersMag(DataSQL data) {
        this.usersData = data;
        try (ResultSet result = this.usersData.loadData("users")) {
            while(result.next()) {
                System.out.println("Lool");
                int id = result.getInt("user_id");
                String name = result.getString("user_name");
                String gmail = result.getString("user_gmail");
                users.add(new User(id, name, gmail));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printALLUsers() {
        if (this.users.isEmpty()) {
            System.out.println("--- No User To Display ---");
            return;
        }
        System.out.println("| ID | Name | Gmail |");
        this.users.forEach(user -> user.printUserData());
    }

    public void addNewUser(User user) {
        
    }

}
