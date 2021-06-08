package controller;

import entity.LoginUser;
import entity.User;
import repository.UserRepositoryImpl;
import repository.api.UserRepository;
import service.UserServiceImpl;
import service.api.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserValidator {


    public String getRole(HttpServletRequest request, UserService userService) {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        LoginUser loginUser = new LoginUser();

        loginUser.setEmail(email);
        loginUser.setPassword(password);

        try {
            String userValidate = userService.getRole(loginUser);

            if (userValidate.equals("Admin_Role")) {
                return "Admin";
            } else if (userValidate.equals("User_Role")) {
                return "User";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error in UserValidator method getRole";
    }
}