package controller;

import entity.Bid;
import entity.Room;
import service.api.BidService;
import service.api.RoomService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/bid")
public class BidController extends HttpServlet {
    ServletContext context;
    BidService bidService;
    RoomService roomService;

    FormParser formParser = new FormParser();

    @Override
    public void init() throws ServletException {
        context = getServletContext();
        bidService = (BidService) context.getAttribute("bidService");
        roomService = (RoomService) context.getAttribute("roomService");
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Bid> bids = bidService.getAll();
        List<Room> rooms = roomService.getAllRooms();

        req.setAttribute("bidList", bids);
        req.setAttribute("roomList", rooms);
        System.out.println("controller bid method do get");
        req.getRequestDispatcher("/admin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("action").equals("update")) {
            String bidId = req.getParameter("bid_id");
            String roomNumber = req.getParameter("room_number");
            String arrival = req.getParameter("arrival");
            String departure = req.getParameter("departure");


            roomService.changeRoomStatus(roomNumber, "BOOKED", arrival, departure);
            bidService.updateBid(bidId, Integer.parseInt(req.getParameter("bid_id")));
            bidService.getBidsForUser(req.getParameter("email"));

            req.getRequestDispatcher("/user.jsp").forward(req, resp);

        } else if (req.getParameter("action").equals("book")) {
            bidService.create(formParser.parseFormFromBidUser(req));
            req.getRequestDispatcher("/user.jsp").forward(req, resp);
        } else if (req.getParameter("action").equals("create")) {
            bidService.createUserBid(formParser.bidUserForm(req));
        } else {
            bidService.create(formParser.parseBidForm(req));
        }
    }
}
