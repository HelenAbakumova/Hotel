package entity.dto;

import entity.RoomCategory;

import java.util.Date;

public class BidDto {
    private int id;
    private int roomId;
    private String email;
    private String capacity;
    private RoomCategory category;
    private Date startDate;
    private Date endDate;

    public BidDto(int id, String email, String capacity, RoomCategory category, Date startDate, Date endDate) {
        this.id = id;
        this.email = email;
        this.capacity = capacity;
        this.category = category;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public BidDto() {
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "BidDto{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", capacity='" + capacity + '\'' +
                ", category=" + category +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
