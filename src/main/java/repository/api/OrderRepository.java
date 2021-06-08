package repository.api;

import entity.Order;
import entity.OrderStatus;

import java.sql.Connection;
import java.util.ArrayList;

public interface OrderRepository {
    void createOrder(Connection connection, Order order);
    ArrayList<Order> findAllOrders(Connection connection, Order order, RoomRepository roomRepository, UserRepository userRepository);
    void deleteOrderById(Connection connection, int idOrder);
    void changeOrderStatusById(Connection connection, int id, OrderStatus status);
    Order findOrderById(Connection connection, int id, RoomRepository roomRepository, UserRepository userRepository, Order order);
}
