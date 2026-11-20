package Users;

import Ordering.Ordering;

public class User {
    private String userName;
    private String userGmail;
    private double userBalance = 0.0;
    private int userID;

    public User() {}
    public User(String userName, String userGmail) {
        this.userName = userName;
        this.userGmail = userGmail;
    }
    public User(int userID, String userName, String userGmail, double userBalance) {
        this.userID = userID;
        this.userName = userName;
        this.userGmail = userGmail;
        this.userBalance = userBalance;
    }

    public User(String userName, String userGmail, double userBalance) {
        this.userName = userName;
        this.userGmail = userGmail;
        this.userBalance = userBalance;
    }

    public void printUserData() {
        System.out.println("| " + this.userID + " | " + this.userName + " | " + this.userGmail + " | " + this.userBalance + " |");
    }

    public boolean haveEnoughBalance(double ticketPrice) {
        return (this.userBalance - ticketPrice) >= 0.0;
    }

    public int getUserID() {
        return this.userID;
    }
    public String getName() {
        return this.userName;
    }
    public String getUserGmail() {
        return this.userGmail;
    }
    public double getUserBalance() {return  this.userBalance;}
    public void setUserID(int id) {
        this.userID = id;
    }
    public void setUserName(String name) {
        this.userName = name;
    }
    public void setUserGmail(String gmail) {
        this.userGmail = gmail;
    }
    public void setUserBalance(double userBalance) {this.userBalance = userBalance;}
}
