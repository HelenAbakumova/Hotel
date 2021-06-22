package controller;

import controller.api.UserController;
import entity.Bid;
import entity.User;
import service.api.BidService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/user")
public class UserControllerImpl extends HttpServlet implements UserController {
    ServletContext servletContext;
    BidService bidService;


    @Override
    public void init() throws ServletException {
        servletContext = getServletContext();
        bidService = (BidService) servletContext.getAttribute("bidService");
        super.init();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        List<Bid> bidList = bidService.getBidsForUser(user.getEmail());

        request.setAttribute("bidList", bidList);
        request.getRequestDispatcher("/user.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        super.doPost(req, resp);
    }
}
