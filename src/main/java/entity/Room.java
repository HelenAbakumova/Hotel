package entity;

public class Room {
    private int id;
    private String capacity;
    private int price;
    private RoomCategory category;
    private RoomStatus status;
    private String imgName;

    public Room() {
    }

    public Room(int id, String capacity, int price, RoomCategory roomCategory, RoomStatus status, String imgName) {
        this.id = id;
        this.capacity = capacity;
        this.price = price;
        this.category = roomCategory;
        this.status = status;
        this.imgName = imgName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public RoomCategory getCategory() {
        return category;
    }

    public void setCategory(RoomCategory category) {
        this.category = category;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
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
        return "Room{" +
                "id=" + id +
                ", capacity='" + capacity + '\'' +
                ", price=" + price +
                ", roomCategory=" + category +
                ", status=" + status +
                ", imgName='" + imgName + '\'' +
                '}';
    }
}
