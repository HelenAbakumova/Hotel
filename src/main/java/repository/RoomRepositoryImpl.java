package repository;

import entity.*;
import exception.RepositoryException;
import repository.api.RoomRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RoomRepositoryImpl implements RoomRepository {

    public static final String PARAM_ID = "room_id";

    public static final String PARAM_NUMBER_BEDS = "capacity";
    public static final String PARAM_COST_PER_DAY = "price";
    public static final String PARAM_CATEGORY = "room_category";

    public static final String FIND_ROOM_BY_ID = "SELECT * FROM hotel.room WHERE room_id=?";

    public static final String UPDATE_ROOM_STATUS = "UPDATE hotel.concatenated_table SET room_status = ? WHERE room_id = ? ";
    public static final String SELECT_FROM_HOTEL_ROOM = "SELECT * FROM room ";
    public static final String SELECT_FROM_CONCATENATED_TABLE = "select * from concatenated_table";

    @Override
    public List<Room> getAllRooms(Connection connection) {
        Statement statement;
        List<Room> getAll = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_FROM_HOTEL_ROOM);
            while (resultSet.next()) {
                getAll.add(extractRoom(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getAll;
    }

    @Override
    public Room getRoom(Connection connection, int roomId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_ROOM_BY_ID);
        preparedStatement.setInt(1, roomId);
        ResultSet resultSet = preparedStatement.executeQuery();
        Room room = new Room();

        if (resultSet.next()) {
            try {
                room = extractRoom(resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return room;
    }

    @Override
    public void changeRoomStatus(Connection connection, String roomId, String status, String  arrival, String  departure) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(UPDATE_ROOM_STATUS);
            preparedStatement.setString(1, status);
            preparedStatement.setString(2, roomId);
            preparedStatement.setString(3, arrival);
            preparedStatement.setString(4, departure);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Room> selectionOfRoom(Connection connection, RoomParameter roomParameter) throws SQLException, RepositoryException {
        Statement statement;
        List<Room> roomArrayList = new ArrayList<>();
        statement = connection.createStatement();
        ResultSet resultSet;
        String query = buildQuery(roomParameter);
        resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            try {
                roomArrayList.add(extractRoom(resultSet));
            } catch (SQLException e) {
               throw new RepositoryException();
            }
        }

        return roomArrayList;
    }

    @Override
    public List<Room> filterRooms(Connection connection, List<Room> rooms, RoomParameter roomParameter) throws SQLException {
        Statement statement;

        List<RoomStatusFilter> list = new ArrayList<>();
        statement = connection.createStatement();
        ResultSet resultSet;
        resultSet = statement.executeQuery(SELECT_FROM_CONCATENATED_TABLE);
        while (resultSet.next()) {
            try {
                list.add(new RoomStatusFilter(resultSet.getInt("room_id"),
                        resultSet.getDate("date_from"),
                        resultSet.getDate("date_to"),
                        RoomStatus.valueOf(resultSet.getString("room_status"))
                ));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        List<Integer> roomsToRemove = new ArrayList<>();
        for (Room room : rooms) {
            for (RoomStatusFilter filter : list) {
                if (room.getId() == filter.getId()) {
                    if (checkOverlap(roomParameter.getArrival(), roomParameter.getDeparture(), filter.getArrival(), filter.getDeparture())) {
                        roomsToRemove.add(room.getId());
                    }
                }
            }
        }
        for (int id : roomsToRemove) {
            rooms.remove(id);
        }
        return rooms;
    }

    private boolean checkOverlap(Date arrivalFromUser, Date departureFromUser, Date arrivalFromDb, Date departureFromDb) {
        return arrivalFromUser.before(departureFromDb) && arrivalFromDb.before(departureFromUser);
    }


    private String buildQuery(RoomParameter roomParameter) {
        StringBuilder query = new StringBuilder(SELECT_FROM_HOTEL_ROOM);
        List<RoomCategory> roomCategories = roomParameter.getRoomCategory();

        if (Objects.nonNull(roomCategories)) {
            compareQuery(query);
            query.append(" room_category in ('");
            for (int i = 0; i < roomCategories.size(); i++) {
                query.append(roomCategories.get(i).toString()).append("'");
                if (roomCategories.size() - 1 != i) {
                    query.append(",'");
                }
            }
            query.append(")");
        }
        if (Objects.nonNull(roomParameter.getCapacity())) {
            compareQuery(query);
            query.append(" capacity ='").append(roomParameter.getCapacity()).append("'");
        }

        if (Objects.nonNull(roomParameter.getPriceFrom())) {
            compareQuery(query);
            if (roomParameter.getPriceTo() != null) {
                query.append(" price > ").append(roomParameter.getPriceFrom());
            }
        }
        if (Objects.nonNull(roomParameter.getPriceTo())) {
            compareQuery(query);
            query.append(" price < ").append(roomParameter.getPriceTo());
        }
        System.out.println("QUERY: " + query);
        return query.toString();
    }

    private Room extractRoom(ResultSet rs) throws SQLException {
        Room room = new Room();
        if (rs != null) {
            room.setId(rs.getInt("room_id"));
            room.setCapacity(rs.getString("capacity"));
            room.setCategory(RoomCategory.valueOf(rs.getString("room_category")));
            room.setPrice(Integer.parseInt(rs.getString("price")));
        }
        return room;
    }

    private StringBuilder compareQuery(StringBuilder compareQuery) {
        if (compareQuery.toString().equals(SELECT_FROM_HOTEL_ROOM)) {
            compareQuery.append(" where");
        } else {
            compareQuery.append(" and");
        }
        return compareQuery;
    }

}


