package controller;

import controller.api.UserController;
import service.api.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserControllerImpl extends HttpServlet implements UserController {
    ServletContext servletContext;
    UserService userService;
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
       // LoginUser loginUser = formParser.findUser(req);
        //LoginUser loginUser = formParser.findUser(req);

       // List<User> user = userService.login(loginUser);
        //req.setAttribute("Users", user);
        super.doPost(req, resp);
    }
}
