package service.util;

import entity.Room;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class RoomUtils {
    private RoomUtils() {
    }

    public final static Map<String, Comparator<Room>> COMPARATORS = new HashMap<>();

    static {
        COMPARATORS.put("Cheap to expensive", Comparator.comparing(Room::getPrice));
        COMPARATORS.put("Expensive to cheap", Comparator.comparing(Room::getPrice).reversed());
        COMPARATORS.put("Capacity", Comparator.comparing(Room::getCapacity));
        COMPARATORS.put("Status", Comparator.comparing(Room::getStatus));
        COMPARATORS.put("Category", Comparator.comparing(Room::getCategory));
    }

}
