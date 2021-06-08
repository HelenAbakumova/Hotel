package repository.api;

import entity.Bill;
import entity.BillStatus;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public interface BillRepository {
    Bill findBillById(Connection connection, int idBill);
    void createBill(Connection connection, Bill bill);
    ArrayList<Bill> findAllBills(Connection connection);
    void changeBillStatusById(Connection connection, int idBill, BillStatus status);
    List<Bill> findUserBills(Connection connection, int idUser);


}
