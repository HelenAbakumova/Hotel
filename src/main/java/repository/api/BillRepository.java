package repository.api;

import entity.Bill;
import entity.BillStatus;
import exception.RepositoryException;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public interface BillRepository {
    Bill get(Connection connection, int idBill);

    ArrayList<Bill> findAllBills(Connection connection, String email);

    void changeBillStatusById(Connection connection, String idBill, BillStatus status);

    List<Bill> findUserBills(Connection connection, int idUser);

    int createBill(Connection connection, Bill bill) throws RepositoryException;
}
