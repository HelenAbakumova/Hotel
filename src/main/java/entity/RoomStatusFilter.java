package entity;


import java.sql.Date;

public class RoomStatusFilter {
    int id;
    Date arrival;
    Date departure;
    RoomStatus roomStatus;

    public RoomStatusFilter(int id, Date arrival, Date departure, RoomStatus roomStatus) {
        this.id = id;
        this.arrival = arrival;
        this.departure = departure;
        this.roomStatus = roomStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getArrival() {
        return arrival;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }

    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    public RoomStatus getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(RoomStatus roomStatus) {
        this.roomStatus = roomStatus;
    }
}
