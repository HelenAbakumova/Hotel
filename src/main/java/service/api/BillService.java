package service.api;

import entity.Bid;
import entity.Bill;
import entity.BillStatus;

import java.util.List;

public interface BillService {
    int create(Bid bid);

    Bill getBill(int id);

    void changeBillStatus(String id, BillStatus status);

    List<Bill> getBillsForUser(String email);

    List<Bill> get(BillStatus paid);
}
