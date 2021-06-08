package entity;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Bill {
    private long id;
    private BillStatus status;
    private Order order;
    private double totalPrice;
    private Date billDate;

    public Bill() {

    }

    public Bill(long id, BillStatus status, Order order, double totalPrice, Date billDate, LocalDate start, LocalDate end, double price) {
        this.id = id;
        this.status = status;
        this.order = order;
        //this.billDate = LocalDateTime.now();
        long days = Duration.between(start.atStartOfDay(), end.atStartOfDay()).toDays();
        this.totalPrice = price * days;
    }

//    public Bill(LocalDate start, LocalDate end, double price) {
//        billDate = LocalDateTime.now();
//        long days = Duration.between(start.atStartOfDay(), end.atStartOfDay()).toDays();
//        double totalPrice = price * days;
//        this.totalPrice = totalPrice;
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BillStatus getStatus() {
        return status;
    }

    public void setStatus(BillStatus status) {
        this.status = status;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }
}
