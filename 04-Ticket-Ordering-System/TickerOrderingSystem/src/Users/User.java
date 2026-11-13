package Users;

public class User {
    private String userName;
    private String userGmail;
    private int userID;

    public User() {}
    public User(String userName, String userGmail) {
        this.userName = userName;
        this.userGmail = userGmail;
    }
    public User(int userID, String userName, String userGmail) {
        this.userID = userID;
        this.userName = userName;
        this.userGmail = userGmail;
    }

    public void printUserData() {
        System.out.println("| " + this.userID + " | " + this.userName + " | " + this.userGmail + " |");
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
    public void setUserID(int id) {
        this.userID = id;
    }
    public void setUserName(String name) {
        this.userName = name;
    }
    public void setUserGmail(String gmail) {
        this.userGmail = gmail;
    }
}
