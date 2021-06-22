package service;

import db.JDBCManager;
import entity.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import repository.UserRepositoryImpl;
import repository.api.UserRepository;
import service.api.UserService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.Function;

public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserRepositoryImpl.class);
    UserRepository userRepository;
    JDBCManager jdbcManager;

    public UserServiceImpl(UserRepository userRepository, JDBCManager jdbcManager) {
        this.userRepository = userRepository;
        this.jdbcManager = jdbcManager;
    }

    public UserServiceImpl() {

    }

    @Override
    public boolean login(String email, String password) {
        return jdbcManager.doInTransaction(new Function<Connection, Boolean>() {
            @Override
            public Boolean apply(Connection connection) {
                User user = null;
                boolean result = false;
                try {
                    user = userRepository.getUser(email, connection);
                } catch (SQLException e) {
                    LOGGER.warn(e.getMessage());
                }
                if (user != null && user.getPassword().equals(password)) {
                    result = true;
                }
                return result;
            }
        });
    }

    @Override
    public boolean registration(User user) {
        return jdbcManager.doInTransaction(connection -> {
            boolean res = false;
            try {
                if (userRepository.registerUser(connection, user)) {
                    res = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return res;
        });
    }

    @Override
    public User getByEmail(String email) {
        return jdbcManager.doInTransaction(connection -> {
            try {
                return userRepository.getByEmail(connection, email);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
    }

    @Override
    public boolean exists(String email) {
        return jdbcManager.doInTransaction(new Function<Connection, Boolean>() {
            @Override
            public Boolean apply(Connection connection) {
                return UserServiceImpl.this.getUser(email) != null;
            }
        });
    }

    @Override
    public User getUser(String email) {
        return jdbcManager.doInTransaction(connection -> {
            User user = null;
            try {
                user = userRepository.getUser(email, connection);
            } catch (SQLException e) {
                LOGGER.warn(e.getMessage());
            }
            return user;
        });
    }

}
