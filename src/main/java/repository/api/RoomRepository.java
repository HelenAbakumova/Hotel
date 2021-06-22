package repository.api;

import entity.Room;
import entity.RoomParameter;
import exception.RepositoryException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface RoomRepository {
    void changeRoomStatus(Connection connection, String roomId, String status,String  arrival, String  departure);

    List<Room> selectionOfRoom(Connection connection, RoomParameter roomParameter) throws SQLException, RepositoryException;

    List<Room> getAllRooms(Connection connection);

    Room getRoom(Connection connection, int roomId) throws SQLException;

    List<Room> filterRooms(Connection connection, List<Room> rooms, RoomParameter roomParameter) throws SQLException;
}
