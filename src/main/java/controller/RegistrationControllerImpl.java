package controller;

import controller.api.RegistrationController;
import entity.RegistrationUser;
import repository.UserRepositoryImpl;
import repository.api.UserRepository;
import service.api.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/registration")
public class RegistrationControllerImpl extends HttpServlet implements RegistrationController {
    ServletContext servletContext;
    UserService userService;
    UserRepository userRepository;
    FormParser formParser = new FormParser();

    @Override
    public void init() throws ServletException {
        servletContext = getServletContext();
        userService = (UserService) servletContext.getAttribute("userService");
        super.init();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/registration.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RegistrationUser registrationUser = formParser.register(req);
        String line = userService.registration(registrationUser);

        if(line.equals("SUCCESS"))
        {
            req.getRequestDispatcher("/home.jsp").forward(req, resp);
        }
        else
        {
            req.setAttribute("errMessage", line);
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

}
