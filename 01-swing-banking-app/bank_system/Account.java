package bank_system;

public class Account {

    private int id;
    private String username;
    private double balance;
    private String password;

    public Account(String username, String password, double balance) {
        this.username = username;
        this.balance = balance;
        this.password = password;
    }

    public Account() {
        this.username = "Guest";
        this.balance = 0;
        this.password = "1234";
    }

    public boolean withdraw(double amount) {
        if (helper.amountLessThanZeroCheck(amount))
            return false;
        if (helper.amountLargerThanBalance(this.balance, amount))
            return false;

        this.balance -= amount;
        helper.accountState(this.username, this.balance);
        return true;
    }

    public boolean deposit(double amount) {
        if (helper.amountLessThanZeroCheck(amount))
            return false;
        this.balance += amount;
        helper.accountState(this.username, this.balance);
        return true;
    }

    public boolean transfer(Account targetAcc, double amount) {
        if (helper.amountLessThanZeroCheck(amount))
            return false;
        if (helper.amountLargerThanBalance(this.balance, amount))
            return false;

        this.setBalance(this.balance - amount);
        targetAcc.setBalance(targetAcc.balance + amount);

        System.out.println("==== Transfer Successfully ===");
        helper.accountState(this.username, this.balance);
        helper.accountState(targetAcc.username, targetAcc.balance);
        return true;
    }

    public void setID(int id) {
        this.id = id;
    }

    public void setUsername(String newName) {
        this.username = newName;
    }

    public void setBalance(double newBalance) {
        this.balance = newBalance;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public int getID() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public double getBalance() {
        return this.balance;
    }

    public String getPassword() {
        return this.password;
    }
}