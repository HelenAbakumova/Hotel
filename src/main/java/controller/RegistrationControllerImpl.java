package controller;

import controller.api.RegistrationController;
import entity.RegistrationUser;
import entity.User;
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

@WebServlet("/registration")
public class RegistrationControllerImpl extends HttpServlet implements RegistrationController {
    ServletContext servletContext;
    UserService userService;
    FormParser formParser = new FormParser();
    UserValidator userValidator;
    public static final String ERRORS = "errors";
    public static final String PRINTABLE_CONSTANT_EMAIL_ERR = "PrintableConstant.EMAIL_ERR";
    public static final String PRINTABLE_CONSTANT_EMAIL_ALREADY_EXISTS_MSG = "PrintableConstant.EMAIL_ALREADY_EXISTS_MSG";
    public static final String SPACE = "";
    public static final String BEAN = "bean";

    @Override
    public void init() throws ServletException {
        servletContext = getServletContext();
        userService = (UserService) servletContext.getAttribute("userService");
        userValidator = (UserValidator) servletContext.getAttribute("userValidator");
        super.init();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/registration.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RegistrationUser registrationUser = formParser.parseRegistrationForm(req);
        Map<String, String> errors = userValidator.validateRegForm(registrationUser);

        HttpSession session = req.getSession();
        if (userService.exists(registrationUser.getEmail())) {
            errors.put(PRINTABLE_CONSTANT_EMAIL_ERR, PRINTABLE_CONSTANT_EMAIL_ALREADY_EXISTS_MSG);
        }
        if (!errors.isEmpty()) {
            registrationUser.setPassword(SPACE);
            if (session != null) {
                session.setAttribute(BEAN, registrationUser);
                session.setAttribute(ERRORS, errors);
                resp.sendRedirect("login");
            }
        } else {
            userService.registration(transformToDomain(registrationUser));
            resp.sendRedirect("room");
            session.removeAttribute(BEAN);
            session.removeAttribute(ERRORS);
        }

    }

    private User transformToDomain(RegistrationUser registrationUser) {
        return new User(registrationUser.getName(), registrationUser.getLogin(), registrationUser.getPassword(), registrationUser.getEmail(),
                 registrationUser.getRoleName());
    }

}
