package service.api;

import entity.LoginUser;
import entity.RegistrationUser;
import entity.User;

import java.util.List;

public interface UserService {
    String login(LoginUser loginUser);

    String registration(RegistrationUser registrationUser);

    String getRole(LoginUser loginUser);
}
