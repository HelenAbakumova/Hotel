package service;

import db.JDBCManager;
import entity.RoomParameter;
import entity.Room;
import repository.api.RoomRepository;
import service.api.RoomService;

import java.util.List;

public class RoomServiceImpl implements RoomService {
    RoomRepository roomRepository;
    JDBCManager jdbcManager;

    public RoomServiceImpl(RoomRepository roomRepository, JDBCManager jdbcManager) {
        this.roomRepository = roomRepository;
        this.jdbcManager = jdbcManager;
    }

//    @Override
//    public List<Room> getRoom(RoomParameter roomParameter) {
//        return jdbcManager.doInTransaction(connection -> {
//            try {
//                return roomRepository.findAllRoom(connection, roomParameter);
//            } catch (Exception e) {
//                e.printStackTrace();
//                return null;
//            }
//        });
//    }

    @Override
    public List<Room> selectionOfRoom(RoomParameter roomParameter){
        return jdbcManager.doInTransaction(connection -> {
            try {
                return roomRepository.selectionOfRoom(connection, roomParameter);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
    }

    public List<Room> getAllRooms() {
        return jdbcManager.doInTransaction(connection -> {
            try {
                return roomRepository.getAllRooms(connection);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
    }

}

