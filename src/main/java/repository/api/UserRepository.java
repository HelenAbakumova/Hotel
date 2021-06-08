package repository.api;

import entity.LoginUser;
import entity.RegistrationUser;
import entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserRepository {
    List<User> getAllUser(Connection connection, LoginUser loginUser) throws SQLException;
    String getUser (Connection connection, LoginUser loginUser) throws SQLException;
    String registerUser(Connection connection, RegistrationUser registrationUser);
    String authenticateUser(Connection connection, LoginUser loginUser);
    List<User> getUserByID(Connection connection, LoginUser loginUser);
}
