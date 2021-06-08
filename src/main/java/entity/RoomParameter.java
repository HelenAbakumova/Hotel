package entity;

import java.util.List;

public class RoomParameter {

    private String capacity;
    private String priceFrom;
    private String priceTo;
    private List<String> type;
    private List <String> status;
    private String imgName;

    public RoomParameter() {
    }

    public RoomParameter(String capacity, String priceFrom, String priceTo, List<String> type, List<String> status, String imgName) {
        this.capacity = capacity;
        this.priceFrom = priceFrom;
        this.priceTo = priceTo;
        this.type = type;
        this.status = status;
        this.imgName = imgName;
    }

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

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public List<String> getStatus() {
        return status;
    }

    public void setStatus(List<String> status) {
        this.status = status;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    @Override
    public String toString() {
        return "RoomParameter{" +
                "capacity='" + capacity + '\'' +
                ", priceFrom='" + priceFrom + '\'' +
                ", priceTo='" + priceTo + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", imgName='" + imgName + '\'' +
                '}';
    }
}
