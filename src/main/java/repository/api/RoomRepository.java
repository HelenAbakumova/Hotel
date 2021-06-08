package repository.api;

import entity.RoomParameter;
import entity.RoomStatus;
import entity.Room;
import entity.RoomType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface RoomRepository {
    void changeRoomStatus(Connection connection, int id, RoomStatus status);

    //   Room findRoomById(Connection connection, RoomParameter roomParameter);
    // List<Room> findAllRoom(Connection connection, RoomParameter roomParameter);
    List<Room> selectionOfRoom(Connection connection, RoomParameter roomParameter) throws SQLException;

    List<Room> getAllRooms(Connection connection);


}
