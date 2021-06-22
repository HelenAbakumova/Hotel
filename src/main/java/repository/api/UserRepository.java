package repository.api;

import entity.LoginUser;
import entity.User;

import javax.management.relation.RoleNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserRepository {
    List<User> getAllUser(Connection connection, LoginUser loginUser) throws SQLException;

    User getUser(String email, Connection connection) throws SQLException;

    int getRoleIdByName(String roleName, Connection connection) throws SQLException, RoleNotFoundException;

    User getByEmail(Connection connection, String email) throws SQLException;

    boolean registerUser(Connection connection, User user) throws SQLException;

}
