package service;

import db.JDBCManager;
import entity.LoginUser;
import entity.RegistrationUser;
import entity.User;
import repository.api.UserRepository;
import service.api.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    JDBCManager jdbcManager;

    public UserServiceImpl(UserRepository userRepository, JDBCManager jdbcManager) {
        this.userRepository = userRepository;
        this.jdbcManager = jdbcManager;
    }

    public UserServiceImpl() {

    }

    @Override
    public String login(LoginUser loginUser) {
        return jdbcManager.doInTransaction(connection -> {
            try {
                return userRepository.getUser(connection, loginUser);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
    }

    @Override
    public String registration(RegistrationUser registrationUser) {
        return jdbcManager.doInTransaction(connection -> {
            try {
                return userRepository.registerUser(connection, registrationUser);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
    }

    @Override
    public String getRole(LoginUser loginUser) {
        return jdbcManager.doInTransaction(connection -> {
            try {
                return userRepository.authenticateUser(connection, loginUser);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
    }


}
