package repository;

import entity.RoomParameter;
import entity.RoomStatus;
import entity.Room;
import repository.api.RoomRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RoomRepositoryImpl implements RoomRepository {

    public static final String PARAM_ID = "room_id";
    public static final String PARAM_STATUS_ROOM = "room_status";
    public static final String PARAM_NUMBER_BEDS = "capacity";
    public static final String PARAM_COST_PER_DAY = "price";
    public static final String PARAM_CATEGORY = "room_categories";

    public static final String FIND_ROOM_LIST = "SELECT * FROM `room` INNER JOIN roominformation ON room.id_roominfo = roominformation.id_roominfo";

    public static final String FIND_ROOM_BY_ID = "SELECT * FROM hotel.room INNER JOIN room_categories ON room_categories = room_categories.room_categories_id WHERE room_id=?";

    public static final String UPDATE_ROOM_STATUS = "UPDATE room SET status = ? WHERE room_id = ? ";
    public static final String SELECT_FROM_HOTEL_ROOM = "SELECT * FROM hotel.room";

//    @Override
//    public List<Room> findAllRoom(Connection connection, RoomParameter roomParameter){
//        List<Room> roomList = new ArrayList<Room>();
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        try {
//            preparedStatement = connection.prepareStatement(FIND_ROOM_LIST);
//            resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                room.setId(Integer.parseInt(resultSet.getString(PARAM_ID)));
//                room.setStatus(RoomStatus.valueOf(resultSet.getString(PARAM_STATUS_ROOM)));
//                room.setType(RoomType.valueOf(resultSet.getString(PARAM_CATEGORY)));
//                room.setPrice(Integer.parseInt(resultSet.getString(PARAM_COST_PER_DAY)));
//                roomList.add(room);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return roomList;
//    }

    @Override
    public List<Room> getAllRooms(Connection connection) {
        Statement statement;
        List<Room> getAll = new ArrayList<>();
        if (Objects.nonNull(getAllRooms(connection))) {
        }
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
//        @Override
//        public Room findRoomById (Connection connection, Room room){
//            PreparedStatement preparedStatement = null;
//            ResultSet resultSet = null;
//            try {
//                preparedStatement = connection.prepareStatement(FIND_ROOM_BY_ID);
//                preparedStatement.setInt(1, room.getId());
//                resultSet = preparedStatement.executeQuery();
//                if (resultSet.next()) {
//                    room.setId(Integer.parseInt(resultSet.getString(PARAM_ID)));
//                    room.setStatus(RoomStatus.valueOf(resultSet.getString(PARAM_STATUS_ROOM)));
//                    room.setCapacity(Integer.parseInt(resultSet.getString(PARAM_NUMBER_BEDS)));
//                    room.setType(RoomType.valueOf(resultSet.getString(PARAM_CATEGORY)));
//                    room.setPrice(Integer.parseInt(resultSet.getString(PARAM_COST_PER_DAY)));
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            return room;
//        }

    @Override
    public void changeRoomStatus(Connection connection, int id, RoomStatus status) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(UPDATE_ROOM_STATUS);
            preparedStatement.setString(1, status.toString());
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private String buildQuery(RoomParameter roomParameter) {
        StringBuilder query = new StringBuilder(SELECT_FROM_HOTEL_ROOM);
        List<String> roomCategories = roomParameter.getType();
        List<String> roomStatuses = roomParameter.getStatus();

        if (Objects.nonNull(roomCategories)) {
            compareQuery(query);
            query.append(" room_categories in ('");
            for (int i = 0; i < roomCategories.size(); i++) {
                query.append(roomCategories.get(i)).append("'");
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
        if (Objects.nonNull(roomStatuses)) {
            compareQuery(query);
            query.append(" room_status in ('");
            for (int a = 0; a < roomStatuses.size(); a++) {
                query.append(roomStatuses.get(a)).append("'");
                if (roomStatuses.size() - 1 != a) {
                    query.append(",'");
                }
            }
            query.append(")");
        }
        if (Objects.nonNull(roomParameter.getPriceFrom())) {
            compareQuery(query);
            if (roomParameter.getPriceTo() != null){
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

    private StringBuilder compareQuery(StringBuilder compareQuery) {
        if (compareQuery.toString().equals(SELECT_FROM_HOTEL_ROOM)) {
            compareQuery.append(" where");
        } else {
            compareQuery.append(" and");
        }
        return compareQuery;
    }

    @Override
    public List<Room> selectionOfRoom(Connection connection, RoomParameter roomParameter) throws SQLException {
        Statement statement;
        List<Room> roomArrayList = new ArrayList<>();
        System.out.println("selectionOfTours one");
        statement = connection.createStatement();
        ResultSet resultSet;
        String query = buildQuery(roomParameter);
        resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            try {
                roomArrayList.add(extractRoom(resultSet));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return roomArrayList;
    }

    private Room extractRoom(ResultSet rs) throws SQLException {
        Room room = new Room();
        if (rs != null) {
            room.setId(rs.getInt("room_id"));
            room.setCapacity(rs.getString("capacity"));
            room.setCategory(rs.getString("room_categories"));
            room.setPrice(Double.parseDouble(rs.getString("price")));
            room.setStatus(rs.getString("room_status"));

        }
        return room;
    }

}


