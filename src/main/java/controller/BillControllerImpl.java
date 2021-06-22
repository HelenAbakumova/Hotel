package controller;

import entity.Bid;
import entity.BillStatus;
import entity.User;
import service.api.BidService;
import service.api.BillService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/bill")
public class BillControllerImpl extends HttpServlet {
    ServletContext context;
    private BillService billService;
    private BidService bidService;

    @Override
    public void init() throws ServletException {
        context = getServletContext();
        bidService = (BidService) context.getAttribute("bidService");
        billService = (BillService) context.getAttribute("billService");
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        req.setAttribute("billList", billService.getBillsForUser(user.getEmail()));
        req.getRequestDispatcher("/pay.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("action").equals("Confirm booking")) {
            String bidNumber = req.getParameter("bid_id");

            Bid bid = bidService.get(Integer.parseInt(bidNumber));
            billService.create(bid);

        } else if (req.getParameter("action").equals("Pay")) {
            billService.changeBillStatus(req.getParameter("billId"), BillStatus.PAID);
        }
        resp.sendRedirect("bill");
    }
}
