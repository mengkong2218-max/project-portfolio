package Users;

import Database.DataSQL;

import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;

public class UsersMag {
    private Map<String, User> usersMag = new LinkedHashMap<>();
    private DataSQL fileData;
    private int lastAccID;

    public UsersMag(DataSQL data) {
        this.fileData = data;
        try (ResultSet result = this.fileData.loadData("users")) {
            while (result.next()) {
                lastAccID = result.getInt("user_id");
                String name = result.getString("user_name");
                String gmail = result.getString("user_gmail");
                usersMag.put(gmail, new User(lastAccID, name, gmail));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printALLUsers() {
        if (this.usersMag.isEmpty()) {
            System.out.println("--- No User To Display ---");
            return;
        }
        System.out.println("| ID | Name | Gmail |");
        for (User user : usersMag.values()) {
            user.printUserData();
        }
    }

    public void addNewUser(User user) {
        if (checkAccountExistByEmail(user.getUserGmail())) {
            System.out.println("Can not Continue this process.\nThis user is already exist.");
            return;
        }
        String cmd = "INSERT INTO users (user_name, user_gmail) VALUES (?, ?);";
        fileData.executeUpdateCommand(cmd, user.getName(), user.getUserGmail());
        if (fileData.isExecuteSuccessfully()) {
            lastAccID++;
            user.setUserID(lastAccID);
            usersMag.put(user.getUserGmail(), user);
        }
    }

    public void deleteUserByEmail(String email) {
        if (!checkAccountExistByEmail(email)) {
            System.out.println("This acc is not exist.");
            return;
        }
        String cmd = "DELETE FROM users WHERE user_gmail = ?;";
        fileData.executeUpdateCommand(cmd, email);
        if (fileData.isExecuteSuccessfully()) {
            usersMag.remove(email);
        }
    }

    public void changeUsernameByEmail(String email, String username) {
        if (!checkAccountExistByEmail(email)) {
            System.out.println("This acc is not exist.");
            return;
        }
        String cmd = "UPDATE users SET user_name = ? WHERE user_gmail = ?";
        fileData.executeUpdateCommand(cmd, username, email);
        if (fileData.isExecuteSuccessfully()) {
            usersMag.get(email).setUserName(username);
            System.out.println("Update Username Successfully.");
        }
    }

    private boolean checkAccountExistByEmail(String email) {
        return usersMag.containsKey(email);
    }

}
