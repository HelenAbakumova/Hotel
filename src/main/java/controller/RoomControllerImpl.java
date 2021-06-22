package controller;

import controller.api.RoomController;
import entity.Room;
import entity.RoomParameter;
import service.api.RoomService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/room")
public class RoomControllerImpl extends HttpServlet implements RoomController {
    private RoomService roomService;
    private FormParser formParser = new FormParser();

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        roomService = (RoomService) context.getAttribute("roomService");
        super.init();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RoomParameter roomParameter = formParser.filterPriceSeparate(request);
        System.out.println("request roomParameter " + roomParameter);


        List<Room> rooms = roomService.filterRooms(roomService.selectionOfRoom(roomParameter),roomParameter);

        request.setAttribute("roomList", rooms);

        request.getRequestDispatcher("/room.jsp").forward(request, response);
    }

}