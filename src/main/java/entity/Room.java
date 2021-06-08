package entity;

public class Room {
    private int id;
    private String capacity;
    private double price;
    private String category;
    private String status;
    private String imgName;

    public Room() {
    }

    public Room(int id, String capacity, double price, String category, String status, String imgName) {
        this.id = id;
        this.capacity = capacity;
        this.price = price;
        this.category = category;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
                ", type=" + category +
                ", status=" + status +
                ", imgName='" + imgName + '\'' +
                '}';
    }
}
