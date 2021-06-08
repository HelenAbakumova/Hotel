package controller;

import controller.api.TourController;
import entity.RoomParameter;
import entity.RoomStatus;
import entity.Room;
import service.api.RoomService;

import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/room")
public class RoomControllerImpl extends HttpServlet implements TourController {
    ServletContext context;
    RoomService roomService;
    FormParser formParser = new FormParser();

    public RoomControllerImpl() throws NamingException {
    }

    @Override
    public void init() throws ServletException {
        context = getServletContext();
        roomService = (RoomService) context.getAttribute("roomService");
        super.init();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RoomParameter roomParameter = formParser.filterPriceSeparate(request);
        System.out.println("request roomParameter " + roomParameter);
        List<Room> rooms = roomService.selectionOfRoom(roomParameter);

        System.out.println("ROOMS ====>" + rooms);
        request.setAttribute("roomList", rooms);
        request.getRequestDispatcher("/room.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        RoomParameter roomParameter = formParser.filterPriceSeparate(request);
//        List<Room> rooms = roomService.selectionOfRoom(roomParameter);
//
//        System.out.println("ROOMS ====>  " + rooms);
//        request.setAttribute("roomList", rooms);
       request.getRequestDispatcher("/room.jsp").forward(request, response);
    }
}