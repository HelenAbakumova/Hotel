package repository;

import entity.LoginUser;
import entity.Order;
import entity.OrderStatus;
import org.graalvm.compiler.core.common.type.ArithmeticOpTable;
import repository.api.OrderRepository;
import repository.api.RoomRepository;
import repository.api.UserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {
    /** The Constant PARAM_DATE_FORMAT. */
    public static final String PARAM_DATE_FORMAT = "yyyy-MM-dd";

    /** The Constant PARAM_ID_ORDER. */
    public static final String PARAM_ID_ORDER = "id";

    /** The Constant PARAM_STATUS_ORDER. */
    public static final String PARAM_STATUS_ORDER = "status";

    /** The Constant PARAM_DATE_OF_ARRIVAL. */
    public static final String PARAM_DATE_OF_ARRIVAL = "dateIn";

    /** The Constant PARAM_DATE_OF_DEPARTURE. */
    public static final String PARAM_DATE_OF_DEPARTURE = "dateOut";

    /** The Constant PARAM_ID_USER. */
    public static final String PARAM_ID_USER = "user_id";

    /** The Constant PARAM_ID_ROOM. */
    public static final String PARAM_ID_ROOM = "id_room";

    /** The Constant INSERT_ORDER. */
    public static final String INSERT_ORDER = "insert into orders_selection(user_id, capacity_id, category_id, arrival, departure, room_id) values (?,?,?,?,?,?)";

    /** The Constant GET_ALL_ORDERS. */
    public static final String GET_ALL_ORDERS = "SELECT * FROM orders_selection";

    /** The Constant GET_USER_ORDERS. */
    public static final String GET_USER_ORDERS = "SELECT * FROM orders_selection WHERE user_id =?";

    /** The Constant DELETE_ORDER_BY_ID. */
    public static final String DELETE_ORDER_BY_ID = "DELETE FROM orders_selection WHERE id = ?";

    /** The Constant UPDATE_ORDER_STATUS. */
    public static final String UPDATE_ORDER_STATUS = "UPDATE orders_selection SET status = ? WHERE id =?";

    /** The Constant GET_ORDER_BY_ID. */
    public static final String GET_ORDER_BY_ID = "SELECT * FROM orders_selectionWHERE id = ?";


    public void createOrder(Connection connection, Order order) {
        PreparedStatement preparedStatement = null;
        DateFormat format = new SimpleDateFormat(PARAM_DATE_FORMAT);

        try {
            preparedStatement = connection.prepareStatement(INSERT_ORDER);
            preparedStatement.setString(1, order.getOwner().getEmail());
            preparedStatement.setInt(3, order.getRequirementCapacity());
            preparedStatement.setString(4, order.getRequirementType().toString());
            preparedStatement.setString(5, format.format(order.getDateIn()));
            preparedStatement.setString(6, format.format(order.getDateOut()));
            preparedStatement.setString(6, order.getRoom().toString());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public ArrayList<Order> findAllOrders(Connection connection, Order order, RoomRepository roomRepository, UserRepository userRepository){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Order> orderList = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(GET_ALL_ORDERS);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                order.setId(resultSet.getInt("id"));
                order.setStatus(OrderStatus.valueOf(resultSet.getString("status")));
               // order.setUser(userRepository.getUserById(resultSet.getInt(PARAM_ID_USER)));
                order.setDateIn(resultSet.getDate("arrival"));
                order.setDateOut(resultSet.getDate("departure"));
                //order.setRoom(roomRepository.findRoomById(resultSet.getInt("room_id")));
                orderList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

    @Override
    public void deleteOrderById(Connection connection, int idOrder){
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(DELETE_ORDER_BY_ID);
            preparedStatement.setInt(1, idOrder);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void changeOrderStatusById(Connection connection, int id, OrderStatus status){
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(UPDATE_ORDER_STATUS);
            preparedStatement.setString(1, status.toString());
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Order findOrderById(Connection connection, int id, RoomRepository roomRepository, UserRepository userRepository, Order order) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(GET_ORDER_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                order.setId(resultSet.getInt(PARAM_ID_ORDER));
                order.setDateIn(resultSet.getDate(PARAM_DATE_OF_ARRIVAL));
                order.setDateOut(resultSet.getDate(PARAM_DATE_OF_DEPARTURE));
                order.setStatus(OrderStatus.valueOf(resultSet.getString(PARAM_STATUS_ORDER)));
//                order.setOwner(userRepository.findUserById(resultSet.getInt(PARAM_ID_USER)));
//                order.setRoom(roomRepository.findRoomById(resultSet.getInt(PARAM_ID_ROOM)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

//    @Override
//    public List<Order> findUserOrders(Connection connection, int idUser) throws DAOException {
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        UserDAO userDAO = new UserDaoImpl();
//        RoomDAO roomDAO = new RoomDaoImpl();
//        List<Order> orderList = new ArrayList<Order>();
//        Connection connection = null;
//        try {
//            connection = getConnection();
//            preparedStatement = connection.prepareStatement(GET_USER_ORDERS);
//            preparedStatement.setInt(1, idUser);
//            resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                Order order = new Order();
//                order.setId(resultSet.getInt(PARAM_ID_ORDER));
//                order.setDescription(resultSet.getString(PARAM_DESCRIPTION));
//                order.setDateOrder(resultSet.getDate(PARAM_DATE_CREATING_ORDER));
//                order.setStatus(OrderStatus.valueOf(resultSet
//                        .getString(PARAM_STATUS_ORDER)));
//                order.setUser(userDAO.findUserById(resultSet
//                        .getInt(PARAM_ID_USER)));
//                order.setDateIn(resultSet.getDate(PARAM_DATE_OF_ARRIVAL));
//                order.setDateOut(resultSet.getDate(PARAM_DATE_OF_DEPARTURE));
//                order.setRoom(roomDAO.findRoomById(resultSet
//                        .getInt(PARAM_ID_ROOM)));
//                orderList.add(order);
//            }
//        } catch (SQLException e) {
//            throw new DAOException(e);
//        } finally {
//            closePreparedStatement(preparedStatement);
//            releaseConnection(connection);
//        }
//        return orderList;
//    }
}
