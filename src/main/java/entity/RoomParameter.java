package entity;

import java.sql.Date;
import java.util.List;

public class RoomParameter {

    private String capacity;
    private String priceFrom;
    private String priceTo;
    private List<RoomCategory> roomCategory;
    private List<RoomStatus> status;
    private String imgName;
    private String sort;
    private Date arrival;
    private Date departure;

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(String priceFrom) {
        this.priceFrom = priceFrom;
    }

    public String getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(String priceTo) {
        this.priceTo = priceTo;
    }

    public List<RoomCategory> getRoomCategory() {
        return roomCategory;
    }

    public void setRoomCategory(List<RoomCategory> roomCategory) {
        this.roomCategory = roomCategory;
    }

    public List<RoomStatus> getStatus() {
        return status;
    }

    public void setStatus(List<RoomStatus> status) {
        this.status = status;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
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

    @Override
    public String toString() {
        return "RoomParameter{" +
                "capacity='" + capacity + '\'' +
                ", priceFrom='" + priceFrom + '\'' +
                ", priceTo='" + priceTo + '\'' +
                ", type=" + roomCategory +
                ", status=" + status +
                ", imgName='" + imgName + '\'' +
                '}';
    }
}
