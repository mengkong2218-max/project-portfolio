package bank_system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AccManagement {

    ArrayList<Account> accounts = new ArrayList<>();
    private static int primary_ID;

    public AccManagement() {
        this.accounts = convertData.loadAccounts();

        for (Account acc : accounts) {
            if (acc.getID() > primary_ID) {
                primary_ID = acc.getID();
            }
        }
    }

    public void saveChanges() {
        convertData.saveAccounts(this.accounts);
    }

    public void createAccount(String username, String password) {
        if (helper.checkAccount(accounts, username, password)) {
            System.out.println("This Username is already Exist.\nYou need to choose another username.");
            return;
        }
        primary_ID++;
        Account new_account = new Account(username, password, 0.0);
        new_account.setID(primary_ID);
        accounts.add(new_account);
        saveChanges();
    }

    public void deleteAccountByID(int id) {
        Account acc = helper.getAccByID(accounts, id);
        if (acc == null) {
            System.out.println("We can not user with this ID: " + id);
            return;
        }
        accounts.remove(acc);
        saveChanges();
    }

    public void deleteAccountByUsername(String username) {
        Account acc = helper.getAccByUsername(accounts, username);
        if (acc == null) {
            System.out.println("We can not user with this Username: " + username);
            return;
        }
        accounts.remove(acc);
        saveChanges();
    }

    public Account getAccountByID(int id) {
        return helper.getAccByID(accounts, id);
    }

    public Account getAccountByUsername(String username) {
        return helper.getAccByUsername(accounts, username);
    }

    public Account getIntoAccount(String username, String password) {
        Account acc = helper.getAccByUsername(accounts, username);
        if (acc != null && acc.getPassword().equals(password)) {
            return acc;
        }
        System.out.println("Can not find this Username: " + username);
        return null;
    }

    private static Map<String, Runnable> activeListeners = new HashMap<>();

    public void registerListener(String username, Runnable onRefresh) {
        activeListeners.put(username, onRefresh);
    }

    public void unregisterListener(String username) {
        activeListeners.remove(username);
    }

    public void notifyUser(String username) {
        Runnable listener = activeListeners.get(username);
        if (listener != null) {
            listener.run(); // Triggers the target user's UI refresh!
        }
    }
}
