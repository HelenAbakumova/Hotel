package entity;

import java.util.Date;

public class Bid {
    private int id;
    private int roomId;
    private String email;
    private String capacity;
    private RoomCategory category;
    private Date arrival;
    private Date departure;
    private int totalPrice;
    private Date time;


    public Bid() {

    }

    public Bid(int id, int roomId, String email, String capacity, RoomCategory category, Date arrival, Date departure, int totalPrice, Date time) {
        this.id = id;
        this.roomId = roomId;
        this.email = email;
        this.capacity = capacity;
        this.category = category;
        this.arrival = arrival;
        this.departure = departure;
        this.totalPrice = totalPrice;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public RoomCategory getCategory() {
        return category;
    }

    public void setCategory(RoomCategory category) {
        this.category = category;
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

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Bid{" +
                "id=" + id +
                ", roomId=" + roomId +
                ", email='" + email + '\'' +
                ", capacity='" + capacity + '\'' +
                ", category=" + category +
                ", arrival=" + arrival +
                ", departure=" + departure +
                ", totalPrice=" + totalPrice +
                ", time=" + time +
                '}';
    }
}
