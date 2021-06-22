package entity;

import java.sql.Timestamp;
import java.util.Date;

public class Bill {
    private long id;
    private BillStatus status;
    private Bid bid;
    private Date billDate;


    public Bill(long id, BillStatus status, Bid bid) {
        this.id = id;
        this.status = status;
        this.bid = bid;
        this.billDate = new Timestamp(System.currentTimeMillis());
    }

    public Bill() {

    }

    public Bill(BillStatus status, Bid bid) {
        this.status = status;
        this.bid = bid;
        this.billDate = new Timestamp(System.currentTimeMillis());
    }

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

    public Bid getBid() {
        return bid;
    }

    public void setBid(Bid bid) {
        this.bid = bid;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", status=" + status +
                ", bid=" + bid +
                ", billDate=" + billDate +
                '}';
    }
}
