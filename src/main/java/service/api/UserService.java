package service.api;

import entity.LoginUser;
import entity.RegistrationUser;
import entity.User;

public interface UserService {
    boolean login(String email, String password);

    boolean registration(User user);

    User getByEmail(String email);

    boolean exists(String email);

    User getUser(String email);
}
