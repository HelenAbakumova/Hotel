package repository;

import entity.LoginUser;
import entity.RegistrationUser;
import entity.User;
import repository.api.UserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserRepositoryImpl implements UserRepository {

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
    public List<User> getUserByID(Connection connection, LoginUser loginUser) {
        Statement statement;
        List<User> getUser = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM hotel.users WHERE email =?");
            while (resultSet.next()) {
                getUser.add(extractUser(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getUser;
    }

    public String getUser(Connection connection, LoginUser loginUser) throws SQLException {
        String email = loginUser.getEmail();
        String password = loginUser.getPassword();
        Statement statement = null;
        ResultSet resultSet = null;
        String emailDB = "";
        String passwordDB = "";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select email,password from hotel.users");
            while (resultSet.next()) {
                emailDB = resultSet.getString("email");
                passwordDB = resultSet.getString("password");
                if (email.equals(emailDB) && password.equals(passwordDB)) {
                    return "SUCCESS";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Invalid user credentials";
    }

    private User extractUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        if (resultSet != null) {
            user.setEmail(resultSet.getString("email"));
            user.setName(resultSet.getString("name"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));
        }
        return user;
    }

    public String registerUser(Connection connection, RegistrationUser registrationUser) {

        String name = registrationUser.getName();
        String email = registrationUser.getEmail();
        String login = registrationUser.getLogin();
        String password = registrationUser.getPassword();

        PreparedStatement preparedStatement = null;
        try {
            String query = "insert into users(name, login, email, password) values (?,?,?,?)"; //Insert user details into the table 'USERS'
            preparedStatement = connection.prepareStatement(query); //Making use of prepared statements here to insert bunch of data
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, login);
            preparedStatement.setString(4, password);

            int i = preparedStatement.executeUpdate();

            if (i != 0)  //Just to ensure data has been inserted into the database
                return "SUCCESS";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Oops.. Something went wrong there..!";  // On failure, send a message from here.
    }

    public String authenticateUser(Connection connection, LoginUser loginUser) {
        String email = loginUser.getEmail();
        String password = loginUser.getPassword();
        Statement statement = null;
        ResultSet resultSet = null;
        String emailDB = "";
        String passwordDB = "";
        String roleDB = "";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT email, password, role_name FROM hotel.users, hotel.roles;");
            while (resultSet.next()) {
                emailDB = resultSet.getString("email");
                passwordDB = resultSet.getString("password");
                roleDB = resultSet.getString("role_name");
                if (email.equals(emailDB) && password.equals(passwordDB) && roleDB.equals("Admin")) {
                    return "Admin_Role";
                } else if (email.equals(emailDB) && password.equals(passwordDB) && roleDB.equals("User")) {
                    return "User_Role";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Invalid user credentials";
    }
}
