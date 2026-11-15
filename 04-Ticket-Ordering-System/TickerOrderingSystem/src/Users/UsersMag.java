package Users;

import Database.DataSQL;

import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;

public class UsersMag {
    private final Map<String, User> usersMag = new LinkedHashMap<>();
    private final DataSQL fileData;
    private int lastAccID;

    public UsersMag(DataSQL data) {
        this.fileData = data;
        try (ResultSet result = this.fileData.loadData("users")) {
            while (result.next()) {
                lastAccID = result.getInt("user_id");
                String name = result.getString("user_name");
                String gmail = result.getString("user_gmail");
                double balance = result.getDouble("user_balance");
                usersMag.put(gmail, new User(lastAccID, name, gmail, balance));
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
        System.out.println("| ID | Name | Gmail | Balance |");
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
        int getID = fileData.executeInsert(cmd, user.getName(), user.getUserGmail());
        if (getID != -1) {
            lastAccID = getID;
            user.setUserID(lastAccID);
            usersMag.put(user.getUserGmail(), user);
        }
    }

    public void deleteUserByEmail(String email) {
        if (!checkAccountExistByEmail(email)) {
            System.out.println("This acc is not exist.");
            return;
        }
        int getID = usersMag.get(email).getUserID();
        String cmd = "DELETE FROM users WHERE user_id = ?;";
        fileData.executeUpdateCommand(cmd, getID);
        if (fileData.isExecuteSuccessfully()) {
            usersMag.remove(email);
        }
    }

    public void changeUsernameByEmail(String email, String username) {
        if (!checkAccountExistByEmail(email)) {
            System.out.println("This acc is not exist.");
            return;
        }
        if (usersMag.get(email).getName().equals(username)) {
            System.out.println("You Typed the Same USERNAME.");
            return;
        }
        int user_id = usersMag.get(email).getUserID();
        String cmd = "UPDATE users SET user_name = ? WHERE user_id = ?";
        fileData.executeUpdateCommand(cmd, username, user_id);
        if (fileData.isExecuteSuccessfully()) {
            usersMag.get(email).setUserName(username);
            System.out.println("Update Username Successfully.");
        }
    }

    public boolean checkAccountExistByEmail(String email) {
        return usersMag.containsKey(email);
    }

    public User returnUserByEmail(String email) {
        return usersMag.get(email);
    }

}
