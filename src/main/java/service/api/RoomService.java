package service.api;

import entity.Room;
import entity.RoomParameter;

import java.util.List;

public interface RoomService {

    List<Room> selectionOfRoom(RoomParameter roomParameter);

    List<Room> getAllRooms();

    void changeRoomStatus(String id, String status,  String arrival, String departure);

    Room getRoom(int roomId);

    List<Room> filterRooms(List<Room> rooms, RoomParameter roomParameter);
}
