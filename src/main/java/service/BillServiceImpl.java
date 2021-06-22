package service;

import db.JDBCManager;
import entity.Bid;
import entity.Bill;
import entity.BillStatus;
import repository.api.BillRepository;
import service.api.BillService;

import java.util.List;

public class BillServiceImpl implements BillService {
    private final BillRepository billRepository;
    private final JDBCManager jdbcManager;

    public BillServiceImpl(BillRepository billRepository, JDBCManager jdbcManager) {
        this.billRepository = billRepository;
        this.jdbcManager = jdbcManager;
    }

    @Override
    public int create(Bid bid) {
        Bill bill = new Bill(BillStatus.UNPAID, bid);
        return jdbcManager.doInTransaction(connection -> {
            try {
                return billRepository.createBill(connection, bill);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
    }

    public Bill getBill(int id) {
        return jdbcManager.doInTransaction(connection -> {
            try {
                return billRepository.get(connection, id);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
    }

    @Override
    public void changeBillStatus(String id, BillStatus status) {
        jdbcManager.doInTransaction(connection -> {
            try {
                billRepository.changeBillStatusById(connection, id, status);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    @Override
    public List<Bill> getBillsForUser(String email) {
        return jdbcManager.doInTransaction(connection -> {
            try {
                return billRepository.findAllBills(connection, email);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
    }

    @Override
    public List<Bill> get(BillStatus paid) {
        return null;
    }
}
