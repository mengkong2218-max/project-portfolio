package Users;

import Database.DataSQL;
import exceptions.EmailDuplicatedException;
import exceptions.UserNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class UsersMag {
    private final Map<String, User> usersMag = new LinkedHashMap<>();
    private final Map<Integer, User> usersMagInt = new LinkedHashMap<>();
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
                User user = new User(lastAccID, name, gmail, balance);
                usersMag.put(gmail, user);
                usersMagInt.put(lastAccID, user);
            }
        } catch (SQLException e) {
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
            throw new EmailDuplicatedException("[Failed]: Can not register on a gmail that is already exist:" + user.getUserGmail());
        }
        String cmd = "INSERT INTO users (user_name, user_gmail) VALUES (?, ?);";
        int getID = fileData.executeInsert(cmd, user.getName(), user.getUserGmail());
        if (getID != -1) {
            lastAccID = getID;
            user.setUserID(lastAccID);
            usersMag.put(user.getUserGmail(), user);
            usersMagInt.put(user.getUserID(), user);
        }
    }

    public void deleteUserByEmail(String email) {
        if (!checkAccountExistByEmail(email)) {
            throw new UserNotFoundException("[Failed]: Account with this email '" + email + "' was not found in System.");
        }
        int getID = usersMag.get(email).getUserID();
        String cmd = "DELETE FROM users WHERE user_id = ?;";
        fileData.executeUpdateCommand(cmd, getID);
        if (fileData.isExecuteSuccessfully()) {
            usersMag.remove(email);
            usersMagInt.remove(getID);
        }
    }

    public void changeUsernameByEmail(String email, String username) {
        if (!checkAccountExistByEmail(email)) {
            throw new UserNotFoundException("[Failed]: Account with this email '" + email + "' was not found in System.");
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

    public User returnUserByInt(int id) {
        return usersMagInt.get(id);
    }
}
