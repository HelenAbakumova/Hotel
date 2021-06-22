package repository;

import entity.Bill;
import entity.BillStatus;
import exception.RepositoryException;
import repository.api.BillRepository;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BillRepositoryImpl implements BillRepository {

    public static final String PARAM_BILL_ID = "id_bill";
    public static final String PARAM_BILL_STATUS = "status";
    public static final String PARAM_BILL_DATE = "billDate";
    public static final String INSERT_BILL = "INSERT INTO bill (status,bid_bill,bill_date) VALUES(?,?,?)";
    private static final String FIND_ALL_BILLS_FOR_USER = "SELECT * FROM bill INNER JOIN bid on bid_bill=id where email=?";
    public static final String FIND_USER_BILLS = "SELECT * FROM bill INNER JOIN orders_selection ON bill.id_order = orders_selection.id_order WHERE id_user = ?";
    private static final String UPDATE_BILL_STATUS = "UPDATE bill SET status = ? WHERE id_bill = ?";
    private static final String FIND_BILL_BY_ID = "SELECT * FROM hotel.bill INNER JOIN hotel.bid on hotel.bill.bid_bill = hotel.bid.id where id_bill = ?;";

    BidRepositoryImpl bidRepository;

    public BillRepositoryImpl(BidRepositoryImpl bidRepository) {
        this.bidRepository = bidRepository;
    }

    @Override
    public int createBill(Connection connection, Bill bill) throws RepositoryException {
        PreparedStatement preparedStatement;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        int id = 0;
        try {
            preparedStatement = connection.prepareStatement(INSERT_BILL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, bill.getStatus().toString());
            preparedStatement.setInt(2, bill.getBid().getId());
            preparedStatement.setString(3, format.format(bill.getBillDate()));
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RepositoryException();
        }
        return id;
    }

    @Override
    public Bill get(Connection connection, int idBill) {
        PreparedStatement preparedStatement;
        Bill bill = new Bill();
        try {
            preparedStatement = connection.prepareStatement(FIND_BILL_BY_ID);
            preparedStatement.setInt(1, idBill);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                bill = extractBill(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bill;
    }

    @Override
    public ArrayList<Bill> findAllBills(Connection connection, String email) {
        PreparedStatement preparedStatement;
        ArrayList<Bill> billList = new ArrayList<>();
        Bill bill;
        try {
            preparedStatement = connection.prepareStatement(FIND_ALL_BILLS_FOR_USER);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                bill = extractBill(resultSet);
                billList.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return billList;
    }

    @Override
    public void changeBillStatusById(Connection connection, String idBill, BillStatus status) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(UPDATE_BILL_STATUS);
            preparedStatement.setString(1, status.name());
            preparedStatement.setString(2, idBill);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Bill> findUserBills(Connection connection, int idUser) {
        PreparedStatement preparedStatement;
        List<Bill> billList = new ArrayList();
        Bill bill;
        try {
            preparedStatement = connection.prepareStatement(FIND_USER_BILLS);
            preparedStatement.setInt(1, idUser);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                bill = extractBill(resultSet);
                billList.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return billList;
    }

    private Bill extractBill(ResultSet rs) throws SQLException {
        Bill bill = new Bill();
        if (Objects.nonNull(rs)) {
            bill.setId(rs.getInt("id_bill"));
            bill.setStatus(BillStatus.valueOf(rs.getString("status")));
            bill.setBid(bidRepository.extractBid(rs));
        }
        return bill;
    }
}
