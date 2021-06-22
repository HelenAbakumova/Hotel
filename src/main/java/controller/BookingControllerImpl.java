package controller;

import service.api.BookingService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

@WebServlet("/book")
public class BookingControllerImpl extends HttpServlet {
    ServletContext context;
    BookingService bookingService;
    FormParser formParser = new FormParser();

    @Override
    public void init() throws ServletException {
        context = getServletContext();
        bookingService = (BookingService) context.getAttribute("booking");
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        for (Map.Entry<String, String[]> entry : req.getParameterMap().entrySet()) {
            String s = entry.getKey();
            String[] strings = entry.getValue();
            System.out.println(s + " "+ Arrays.toString(strings));
        }
        super.doPost(req, resp);
    }
}
