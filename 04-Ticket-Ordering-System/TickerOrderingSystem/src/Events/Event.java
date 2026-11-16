package Events;

import java.lang.classfile.instruction.SwitchCase;

public class Event {
    private int event_id;
    private String event_name;
    private int total_seats;
    private int available_seats;
    private double ticket_price;
    private String event_start_date;
    private String event_end_date;

    public Event() {
    }

    public Event(int id, String name, int totalSeats, int availableSeat, double price, String startDate, String endDate) {
        this.event_id = id;
        this.event_name = name;
        this.total_seats = totalSeats;
        this.available_seats = availableSeat;
        this.ticket_price = price;
        this.event_start_date = startDate;
        this.event_end_date = endDate;
    }

    public void setOneAnyFunctionOfEvent(String whatToUpdate, Object thingNeedToChange) {
        switch (whatToUpdate) {
            case "event_name":
                this.setEventName(String.valueOf(thingNeedToChange));
                break;
            case "total_seats":
                this.setTotalSeats(Integer.parseInt(thingNeedToChange.toString()));
                break;
            case "available_seats":
                this.setAvailableSeats(Integer.parseInt(thingNeedToChange.toString()));
                break;
            case "ticket_price":
                this.setTicketPrice(Double.parseDouble(thingNeedToChange.toString()));
                break;
            case "event_open_day":
                this.setEventStartDate(String.valueOf(thingNeedToChange));
                break;
            case "event_close_day":
                this.setEventEndDate(String.valueOf(thingNeedToChange));
                break;
        }
    }

    public void printEventDetail() {
        System.out.println("| " + this.event_id + " | " + this.event_name + " | " + this.total_seats + " | " + this.available_seats + " | " + this.ticket_price + " | " + this.event_start_date + " | " + this.event_end_date);
    }

    public void setEventStartDate(String startDate) {
        this.event_start_date = startDate;
    }

    public String getEventStartDate() {
        return this.event_start_date;
    }

    public void setEventEndDate(String endDate) {
        this.event_end_date = endDate;
    }

    public String getEventEndDate() {
        return this.event_end_date;
    }

    public boolean isEnoughSeat(int seats) {
        return (available_seats - seats) >= 0;
    }

    public int getEventId() {
        return event_id;
    }

    public void setEventId(int event_id) {
        this.event_id = event_id;
    }

    public String getEventName() {
        return event_name;
    }

    public void setEventName(String event_name) {
        this.event_name = event_name;
    }

    public int getTotalSeats() {
        return total_seats;
    }

    public void setTotalSeats(int total_seats) {
        this.total_seats = total_seats;
    }

    public int getAvailableSeats() {
        return available_seats;
    }

    public void setAvailableSeats(int available_seats) {
        this.available_seats = available_seats;
    }

    public double getTicketPrice() {
        return ticket_price;
    }

    public void setTicketPrice(double ticket_price) {
        this.ticket_price = ticket_price;
    }
}
