package controller;

import controller.api.LoginController;
import entity.LoginUser;
import entity.RegistrationUser;
import repository.api.UserRepository;
import service.api.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginControllerImpl extends HttpServlet implements LoginController {
    ServletContext servletContext;
    UserService userService;
    UserRepository userRepository;
    FormParser formParser = new FormParser();
    UserValidator userValidator = new UserValidator();

    @Override
    public void init() throws ServletException {
        servletContext = getServletContext();
        userService = (UserService) servletContext.getAttribute("userService");
        super.init();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginUser loginUser = formParser.findUser(req);
        String resultLoginUser = userService.login(loginUser);
        String email = req.getParameter("email");
        String loginUserForRole = userValidator.getRole(req, userService);


        if(resultLoginUser.equals("SUCCESS")){
            if(loginUserForRole.equals("Admin")){
                HttpSession session = req.getSession(); //Creating a session
                session.setAttribute("Admin", email); //setting session attribute
                req.setAttribute("email", email);

                req.getRequestDispatcher("/admin.jsp").forward(req, resp);
            }else if(loginUserForRole.equals("User")) {

                HttpSession session = req.getSession();
                session.setMaxInactiveInterval(10 * 60);
                session.setAttribute("User", email);
                req.setAttribute("email", email);

                req.getRequestDispatcher("/user.jsp").forward(req, resp);
            }
            //req.setAttribute("login", loginUser);
            //req.getRequestDispatcher("/home.jsp").forward(req, resp);
        }
        else{
            req.setAttribute("Invalid user credentials", resultLoginUser);
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}
