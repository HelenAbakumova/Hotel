package repository;

import entity.LoginUser;
import entity.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import repository.api.UserRepository;

import javax.management.relation.RoleNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private static final Logger LOGGER = LogManager.getLogger(UserRepositoryImpl.class);
    private static final String SQL_LOGIN_USER = "SELECT * FROM hotel.users INNER JOIN hotel.roles on hotel.users.role_id = hotel.roles.id where hotel.users.email = ?;";


    @Override
    public List<User> getAllUser(Connection connection, LoginUser loginUser) {
        Statement statement;
        List<User> getUser = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM hotel.users");
            while (resultSet.next()) {
                getUser.add(extractUser(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getUser;
    }

    @Override
    public User getUser(String email, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_LOGIN_USER);
        statement.setString(1, email);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            User user = extractUser(rs);
            rs.close();
            return user;
        }
        return null;
    }


    private User extractUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        if (resultSet != null) {
            user.setId(resultSet.getInt("id"));
            user.setEmail(resultSet.getString("email"));
            user.setName(resultSet.getString("name"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));
        }
        return user;
    }

    @Override
    public int getRoleIdByName(String roleName, Connection connection) throws SQLException, RoleNotFoundException {
        PreparedStatement statement = connection.prepareStatement("SELECT id FROM hotel.roles where role_name = ?;");
        statement.setString(1, roleName);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("id");
            rs.close();
            return id;
        }
        throw new RoleNotFoundException("Role not found" + roleName);
    }

    @Override
    public boolean registerUser(Connection connection, User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("insert into users(name, login, email, password,role_id) values (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getLogin());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getPassword());
        try {
            preparedStatement.setInt(5, getRoleIdByName(user.getRole(), connection));
        } catch (RoleNotFoundException e) {
            e.printStackTrace();
        }
        if (preparedStatement.executeUpdate() > 0) {
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public User getByEmail(Connection connection, String email) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_LOGIN_USER);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            try {
                User user = extractUser(resultSet);
                resultSet.close();
                return user;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
