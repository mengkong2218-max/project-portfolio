package Users;

import Database.DataSQL;
import exceptions.EmailDuplicatedException;
import exceptions.NotEnoughBalanceException;
import exceptions.UserNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
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

    public Map<Integer, User> displayUsernameAsList() {
        int no = 1;
        Map<Integer, User> userList = new HashMap<>();
        if (this.usersMag.isEmpty()) {
            System.out.println("Sorry!!! There is not a single user available here.");
            return userList;
        }
        for (User user : usersMag.values()) {
            userList.put(no, user);
            String nameOrder = "|  " + no + ". ";
            String name = user.getName();
            String fullSpace = "|=================================|";
            int spaceLeft = fullSpace.length() - (name.length() + nameOrder.length());
            System.out.print(nameOrder + name);
            for (int i = 0; i < spaceLeft; i++) {
                if (i == (spaceLeft - 1)) System.out.print("|\n");
                else System.out.print(" ");
            }
            no++;
        }
        return userList;
    }

    public void addNewUser(User user) {
        if (checkAccountExistByEmail(user.getUserGmail())) {
            throw new EmailDuplicatedException("[Failed]: Can not register on a gmail that is already exist:" + user.getUserGmail());
        }
        String cmd = "INSERT INTO users (user_name, user_gmail, user_balance) VALUES (?, ?, ?);";
        int getID = fileData.executeInsert(cmd, user.getName(), user.getUserGmail(), user.getUserBalance());
        if (getID != -1) {
            lastAccID = getID;
            user.setUserID(lastAccID);
            usersMag.put(user.getUserGmail(), user);
            usersMagInt.put(user.getUserID(), user);
            System.out.println("[Successfully]: New User Added.");
        }
    }

    public void deleteUser(User user) {
        if (!checkAccountExistByEmail(user.getUserGmail())) {
            throw new UserNotFoundException("[Failed]: Account with this email '" + user.getUserGmail() + "' was not found in System.");
        }
        int getID = user.getUserID();
        String cmd = "DELETE FROM users WHERE user_id = ?;";
        fileData.executeUpdateCommand(cmd, getID);
        if (fileData.isExecuteSuccessfully()) {
            usersMag.remove(user.getUserGmail());
            usersMagInt.remove(getID);
            System.out.println("[Successfully]: This user is already deleted.");
        }
    }

    public void changeUsername(User user, String newUsername) {
        if (!checkAccountExistByEmail(user.getUserGmail())) {
            throw new UserNotFoundException("[Failed]: Account with this email '" + user.getUserGmail() + "' was not found in System.");
        }
        if (user.getName().equals(newUsername)) {
            System.out.println("You Typed the Same USERNAME.");
            return;
        }
        int user_id = user.getUserID();
        String cmd = "UPDATE users SET user_name = ? WHERE user_id = ?";
        fileData.executeUpdateCommand(cmd, newUsername, user_id);
        if (fileData.isExecuteSuccessfully()) {
            user.setUserName(newUsername);
            System.out.println("Update Username Successfully.");
        }
    }

    public void depositBalanceToUser(User user,double amount) {
        if (!checkAccountExistByEmail(user.getUserGmail()))
            throw new UserNotFoundException("[Failed]: Account with this email '" + user.getUserGmail() + "' was not found in System.");
        if (amount <= 0)
            throw new NotEnoughBalanceException("[Failed]: Not Enough Amount to process deposit.");
        double newBalance = user.getUserBalance() + amount;
        transactionBalance(user, newBalance, "Deposit");
    }

    public void withdrawBalanceFromUser(User user, double amount) {
        if (!checkAccountExistByEmail(user.getUserGmail()))
            throw new UserNotFoundException("[Failed]: Account with this email '" + user.getUserGmail() + "' was not found in System.");
        if (amount <= 0 || user.getUserBalance() <= amount)
            throw new NotEnoughBalanceException("[Failed]: Not Enough Amount to process Withdraw.");
        double newBalance = user.getUserBalance() - amount;
        transactionBalance(user, newBalance, "Withdraw");
    }

    private void transactionBalance(User user, double newBalance, String taskPerform) {
        String task = """
                UPDATE users SET user_balance = ? WHERE user_id = ?;
                """;
        fileData.executeUpdateCommand(task, newBalance, user.getUserID());
        if (fileData.isExecuteSuccessfully()) {
            user.setUserBalance(newBalance);
            System.out.println("[" + taskPerform + "]: Successfully");
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
