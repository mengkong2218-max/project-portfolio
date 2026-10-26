package LibraryGateway.Patron;

import java.time.LocalDateTime;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Patron {

    private String personalID;
    private String name;
    private int age;
    private char gender;
    private int deskNumber;
    private String entryTimeFormatted;
    private String exitTimeFormatted;
    private boolean inLibrary;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy | HH:mm:ss");
    LocalDateTime enterTime;
    LocalDateTime exitTime;

    public Patron() {
        this.inLibrary = false;
    }

    public void getInLibrary() {
        enterTime = LocalDateTime.now();
        entryTimeFormatted = enterTime.format(formatter);
        exitTimeFormatted = "not yet.";
        this.inLibrary = true;
    }

    public void showInfo() {
        System.out.println("Patron's Information -----");
        System.out.println("Patron's ID         : " + this.personalID);
        System.out.println("Patron's Name       : " + this.name);
        System.out.println("Patron's Age        : " + this.age);
        System.out.println("Patron's Gender     : " + this.gender);
        System.out.println("Patron's Entry Time : " + this.entryTimeFormatted);
        System.out.println("Patron's Exit Time  : " + this.exitTimeFormatted);
        if (this.exitTime != null) {
            System.out.println("Patron's Duration: " + this.getDurationFormatted());
        }
    }

    public void askingInfo(Scanner scanner) {
        System.out.println("---------- INFORMATION ----------");
        System.out.print(" + Enter Your ID: ");
        this.personalID = scanner.nextLine();
        System.out.print(" + Enter Your Name: ");
        this.name = scanner.nextLine();
        System.out.print(" + Enter Your Age: ");
        this.age = scanner.nextInt();
        System.out.print(" + Enter Your Gender: ");
        this.gender = scanner.next().charAt(0);
    }

    public String getEntryDateInfo() {
        return this.entryTimeFormatted;
    }

    public void getOutofLibrary() {
        if (!inLibrary)
            return;
        exitTime = LocalDateTime.now();
        exitTimeFormatted = exitTime.format(formatter);
    }

    private Duration getDuration() {
        return Duration.between(enterTime, exitTime);
    }

    public String getDurationFormatted() {
        return getDuration().toHours() + ":" + getDuration().toMinutes() + ":" + getDuration().toSeconds();
    }

    public int getDeskNumber() {
        return deskNumber;
    }

    public void setDeskNumber(int deskNumber) {
        this.deskNumber = deskNumber;
    }

    public String getPersonalID() {
        return personalID;
    }

    public void setPersonalID(String personalID) {
        this.personalID = personalID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

}