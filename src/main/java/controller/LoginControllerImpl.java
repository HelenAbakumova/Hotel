package controller;

import controller.api.LoginController;
import entity.LoginUser;
import entity.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import service.api.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet("/login")
public class LoginControllerImpl extends HttpServlet implements LoginController {
    private static final Logger LOGGER = LogManager.getLogger(LoginControllerImpl.class);
    public static final String USER_SERVICE = "user service";
    public static final String USER_VALIDATOR = "user validator";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String POST_IN_LOGIN_CONTROLLER_OCCURED = "POST in login controller occured";
    public static final String INCORRECT_CREDS_ERR = "wrong creds";
    public static final String USER_NOT_EXISTS = "user not exists";
    public static final String WRONG_CREDS = "wrong creds";
    public static final String SPACE = "";
    public static final String LOGIN = "login";
    public static final String USER = "user";
    public static final String ERRORS = "errors";
    public static final String BEAN = "bean";
    ServletContext servletContext;
    UserService userService;

    UserValidator userValidator = new UserValidator();

    @Override
    public void init() throws ServletException {
        servletContext = getServletContext();
        userService = (UserService) servletContext.getAttribute("userService");
        LOGGER.info("INFO in login controller");
        LOGGER.trace("TRACE in login controller");
        LOGGER.warn("WARN in login controller");
        super.init();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        request.setAttribute(ERRORS, session.getAttribute(ERRORS));
        request.setAttribute(BEAN, session.getAttribute(BEAN));
        request.getRequestDispatcher("login.jsp").forward(request, response); //TODO redirect to own profile page
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginUser loginForm = new LoginUser(req.getParameter(EMAIL), req.getParameter(PASSWORD));
        Map<String, String> errors = userValidator.validateLoginForm(loginForm);

        LOGGER.debug(POST_IN_LOGIN_CONTROLLER_OCCURED);

        HttpSession session = req.getSession();
        if (!userService.exists(loginForm.getEmail())) {
            errors.put(INCORRECT_CREDS_ERR, USER_NOT_EXISTS);
        }
        if (!userService.login(loginForm.getEmail(), loginForm.getPassword())) {
            errors.put(INCORRECT_CREDS_ERR, WRONG_CREDS);
        }
        if (!errors.isEmpty()) {
            loginForm.setPassword(SPACE);
            if (session != null) {
                session.setAttribute(BEAN, loginForm);
                session.setAttribute(ERRORS, errors);
                resp.sendRedirect(LOGIN);
            }
        } else {
            User user = userService.getUser(loginForm.getEmail());
            if (session != null) {
                session.setAttribute(USER, user);
                session.setMaxInactiveInterval(15000);
                resp.sendRedirect("room");
                session.removeAttribute(BEAN);
                session.removeAttribute(ERRORS);
            }
        }
    }
}
