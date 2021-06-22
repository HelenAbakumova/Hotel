package repository;

import entity.Bid;
import entity.RoomCategory;
import repository.api.BidRepository;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BidRepositoryImpl implements BidRepository {
    public static final String INSERT_ORDER = "insert into bid(email, capacity, category, arrival, departure) values (?,?,?,?,?)";
    public static final String PARAM_DATE_FORMAT = "yyyy-MM-dd";

    /**
     * The Constant DELETE_BID_BY_ID.
     */
    public static final String DELETE_ORDER_BY_ID = "DELETE FROM bid WHERE id = ?";
    public static final String UPDATE_BID_ROOM_NUMBER = "UPDATE bid SET room_number = ? WHERE id = ? ";


    @Override
    public int createBid(Connection connection, Bid bid) {
        PreparedStatement preparedStatement;
        DateFormat format = new SimpleDateFormat(PARAM_DATE_FORMAT);

        int id = 0;
        try {
            preparedStatement = connection.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, bid.getEmail());
            preparedStatement.setInt(2, bid.getRoomId());
            preparedStatement.setString(3, bid.getCapacity());
            preparedStatement.setString(4, bid.getCategory().toString());
            preparedStatement.setDate(5, Date.valueOf(format.format(bid.getArrival())));
            preparedStatement.setDate(6, Date.valueOf(format.format(bid.getDeparture())));

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            //TODO throw new YourCustomException
            e.printStackTrace(); // remove
        }
        return id;
    }

    @Override
    public List<Bid> getAllBids(Connection connection) {
        Statement statement;
        List<Bid> getAll = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM bid;");
            while (resultSet.next()) {
                getAll.add(extractBid(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getAll;
    }

    Bid extractBid(ResultSet resultSet) throws SQLException {
        Bid bid = new Bid();
        if (resultSet != null) {
            bid.setId(resultSet.getInt("id"));
            bid.setRoomId(resultSet.getInt("room_number"));
            bid.setEmail(resultSet.getString("email"));
            bid.setCapacity(resultSet.getString("capacity"));
            bid.setCategory(RoomCategory.valueOf(resultSet.getString("category")));
            bid.setArrival(Date.valueOf(resultSet.getString("arrival")));
            bid.setDeparture(Date.valueOf(resultSet.getString("departure")));
        }
        return bid;
    }

    @Override
    public List<Bid> getBidForUser(String email, Connection connection) {
        PreparedStatement preparedStatement;
        List<Bid> bid = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM hotel.bid \n" +
                    "left join bill \n" +
                    "on id=bid_bill" +
                    " where email = ?" +
                    "and id_bill is null;");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                bid.add(extractBid(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bid;
    }

    @Override
    public void updateBid(String roomNumber, int bidId, Connection connection) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(UPDATE_BID_ROOM_NUMBER);
            preparedStatement.setString(1, roomNumber);
            preparedStatement.setInt(2, bidId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Bid get(Connection connection, int id) {
        PreparedStatement preparedStatement;
        Bid bid = new Bid();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM hotel.bid where id = ?;");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                bid = extractBid(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bid;
    }

    @Override
    public void deleteBidById(Connection connection, int idBid) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(DELETE_ORDER_BY_ID);
            preparedStatement.setInt(1, idBid);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int createBidUser(Connection connection, Bid bid) {
        PreparedStatement preparedStatement;
        DateFormat format = new SimpleDateFormat(PARAM_DATE_FORMAT);

        int id = 0;
        try {
            preparedStatement = connection.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, bid.getEmail());
            preparedStatement.setString(2, bid.getCapacity());
            preparedStatement.setString(3, bid.getCategory().toString());
            preparedStatement.setDate(4, Date.valueOf(format.format(bid.getArrival())));
            preparedStatement.setDate(5, Date.valueOf(format.format(bid.getDeparture())));

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            //TODO throw new YourCustomException
            e.printStackTrace(); // remove
        }
        return id;
    }
}
