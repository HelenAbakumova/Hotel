package service;

import db.JDBCManager;
import entity.Room;
import entity.RoomParameter;
import repository.api.RoomRepository;
import service.api.RoomService;
import service.util.RoomUtils;

import java.util.List;
import java.util.stream.Collectors;

public class RoomServiceImpl implements RoomService {
    RoomRepository roomRepository;
    JDBCManager jdbcManager;

    public RoomServiceImpl(RoomRepository roomRepository, JDBCManager jdbcManager) {
        this.roomRepository = roomRepository;
        this.jdbcManager = jdbcManager;
    }

    @Override
    public void changeRoomStatus(String id, String status, String arrival, String departure) {
        jdbcManager.doInTransaction(connection -> {
            try {
                roomRepository.changeRoomStatus(connection, id, status, arrival, departure);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    @Override
    public Room getRoom(int roomId) {
        return jdbcManager.doInTransaction(connection -> {
            try {
                return roomRepository.getRoom(connection, roomId);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
    }

    @Override
    public List<Room> selectionOfRoom(RoomParameter roomParameter) {
        return jdbcManager.doInTransaction(connection -> {
            try {
                return roomRepository.selectionOfRoom(connection, roomParameter)
                        .stream()
                        .sorted(RoomUtils.COMPARATORS.get(roomParameter.getSort()))
                        .collect(Collectors.toList());
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

    @Override
    public List<Room> filterRooms(List<Room> rooms, RoomParameter roomParameter) {
        return jdbcManager.doInTransaction(connection -> {
            try {
                return roomRepository.filterRooms(connection, rooms, roomParameter);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
    }


}

