package repository;

import entity.Bill;
import entity.BillStatus;
import repository.api.BillRepository;
import repository.api.OrderRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BillRepositoryImpl implements BillRepository {

    public static final String PARAM_BILL_ID = "id_bill";
    public static final String PARAM_BILL_STATUS = "status";
    public static final String PARAM_BILL_TOTAL_PRICE = "totalPrice";
    public static final String PARAM_BILL_DATE = "billDate";
    public static final String PARAM_BILL_ORDER_ID = "order_bill";
    public static final String INSERT_BILL = "INSERT INTO bill (status,totalPrice,billDate,order_bill) VALUES(?,?,?,?)";
    private static final String FIND_ALL_BILLS = "SELECT * FROM bill";
    public static final String FIND_USER_BILLS = "SELECT * FROM bill INNER JOIN orders_selection ON bill.id_order = orders_selection.id_order WHERE id_user = ?";
    private static final String UPDATE_BILL_STATUS = "UPDATE bill SET status = ? WHERE id_bill = ?";
    private static final String FIND_BILL_BY_ID = "SELECT * FROM bill WHERE id_bill = ?";

    OrderRepository orderRepository = new OrderRepositoryImpl();

    @Override
    public Bill findBillById(Connection connection, int idBill) {
        Bill bill = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(FIND_BILL_BY_ID);
            preparedStatement.setInt(1, idBill);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                bill = new Bill();
                bill.setId(resultSet.getInt(PARAM_BILL_ID));
                bill.setBillDate(resultSet.getDate(PARAM_BILL_DATE));
                bill.setStatus(BillStatus.valueOf(resultSet.getString(PARAM_BILL_STATUS)));
                bill.setTotalPrice(resultSet.getInt(PARAM_BILL_TOTAL_PRICE));
                //bill.setOrder(orderRepository.findOrderById(resultSet.getInt(PARAM_BILL_ORDER_ID)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bill;
    }

    @Override
    public void createBill(Connection connection, Bill bill) {
        PreparedStatement preparedStatement = null;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {

            preparedStatement = connection.prepareStatement(INSERT_BILL);
            preparedStatement.setString(1, bill.getStatus().toString());
           // preparedStatement.setInt(2, bill.getTotalCost());
            preparedStatement.setString(3, format.format(bill.getBillDate())
                    .toString());
            //preparedStatement.setInt(4, bill.getOrder().getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public ArrayList<Bill> findAllBills(Connection connection){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Bill> billList = new ArrayList<Bill>();
        try {
            preparedStatement = connection.prepareStatement(FIND_ALL_BILLS);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Bill bill = new Bill();
                bill.setId(resultSet.getInt(PARAM_BILL_ID));
                bill.setStatus(BillStatus.valueOf(resultSet
                        .getString(PARAM_BILL_STATUS)));
                bill.setBillDate(resultSet.getDate(PARAM_BILL_DATE));
                bill.setTotalPrice(resultSet.getInt(PARAM_BILL_TOTAL_PRICE));
               // bill.setOrder(orderRepository.findOrderById(resultSet.getInt(PARAM_BILL_ORDER_ID)));
                billList.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return billList;
    }

    @Override
    public void changeBillStatusById(Connection connection, int idBill, BillStatus status) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(UPDATE_BILL_STATUS);
            preparedStatement.setString(1, status.toString());
            preparedStatement.setInt(2, idBill);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Bill> findUserBills(Connection connection, int idUser){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Bill> billList = new ArrayList<Bill>();

        try {
            preparedStatement = connection.prepareStatement(FIND_USER_BILLS);
            preparedStatement.setInt(1, idUser);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Bill bill = new Bill();
                bill.setId(resultSet.getInt(PARAM_BILL_ID));
                bill.setStatus(BillStatus.valueOf(resultSet
                        .getString(PARAM_BILL_STATUS)));
                bill.setBillDate(resultSet.getDate(PARAM_BILL_DATE));
                bill.setTotalPrice(resultSet.getInt(PARAM_BILL_TOTAL_PRICE));
                //bill.setOrder(orderRepository.findOrderById(resultSet.getInt(PARAM_BILL_ORDER_ID)));
                billList.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return billList;
    }
}
