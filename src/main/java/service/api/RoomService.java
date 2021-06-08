package service.api;

import entity.RoomParameter;
import entity.Room;

import java.util.List;

public interface RoomService {
 //   List<Room> getRoom();
   List<Room> selectionOfRoom(RoomParameter roomParameter);
   List<Room> getAllRooms();

}
