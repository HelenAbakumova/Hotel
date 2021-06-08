package controller.api;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface LoginController {
    void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
    void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    void init() throws ServletException;

}
