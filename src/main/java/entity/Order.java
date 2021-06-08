package entity;

import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;

public class Order {
    private int id;
    private LoginUser owner;
    private int requirementCapacity;
    private RoomType requirementType;
    private double totalPrice;
    private OrderStatus status;
    private Date dateIn;
    private Date dateOut;
    private Room room;

    public Order(int id, int requirementCapacity, RoomType requirementType, double totalPrice, OrderStatus status, Date dateIn, Date dateOut, LoginUser owner, Room room) {
        this.id = id;
        this.requirementCapacity = requirementCapacity;
        this.requirementType = requirementType;
        this.totalPrice = totalPrice;
        this.status = status;
        this.dateIn = dateIn;
        this.dateOut = dateOut;
        this.owner = owner;
        this.room = room;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRequirementCapacity() {
        return requirementCapacity;
    }

    public void setRequirementCapacity(int requirementCapacity) {
        this.requirementCapacity = requirementCapacity;
    }

    public RoomType getRequirementType() {
        return requirementType;
    }

    public void setRequirementType(RoomType requirementType) {
        this.requirementType = requirementType;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Date getDateIn() {
        return dateIn;
    }

    public void setDateIn(Date dateIn) {
        this.dateIn = dateIn;
    }

    public Date getDateOut() {
        return dateOut;
    }

    public void setDateOut(Date dateOut) {
        this.dateOut = dateOut;
    }

    public LoginUser getOwner() {
        return owner;
    }

    public void setOwner(LoginUser owner) {
        this.owner = owner;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
//
//    public void calcTotalPrice() {
//        long days = Duration.between(dateIn.atStartOfDay(), dateOut.atStartOfDay()).toDays();
//        this.totalPrice = room.getPrice() * days;
//    }

}
