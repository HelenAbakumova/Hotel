package controller;

import entity.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class FormParser {

    public RoomParameter filterPriceSeparate(HttpServletRequest request) {
        RoomParameter roomParameter = new RoomParameter();
        Map<String, String[]> map = request.getParameterMap();

        String[] roomCategories = map.get("room_categories");
        String[] roomStatuses = map.get("room_status");

        System.err.println(map);

        String priceFrom = request.getParameter("priceFrom");
        String priceTo = request.getParameter("priceTo");
        String capacity = request.getParameter("capacity");

        if (roomStatuses != null) {
            List<String> roomStatus = Arrays.asList(roomStatuses);
            if (roomStatus != null && roomStatus.equals("")) {
                roomParameter.setStatus(null);
            } else {
                roomParameter.setStatus(roomStatus);
            }
        }
        if (capacity != null && capacity.equals("")) {
            roomParameter.setCapacity(null);
        } else {
            roomParameter.setCapacity(capacity);
        }
        if (priceFrom != null && priceFrom.equals("")) {
            roomParameter.setPriceTo(null);
            roomParameter.setPriceFrom(null);
        } else {
            roomParameter.setPriceFrom(request.getParameter("price"));
            if (priceFrom != null) {
                roomParameter.setPriceFrom(priceFrom);
                roomParameter.setPriceTo(priceTo);
            }
        }
        if (roomCategories != null) {
            List<String> roomCategory = Arrays.asList(roomCategories);
            if (roomCategory != null && roomCategory.equals("")) {
                roomParameter.setType(null);
            } else {
                roomParameter.setType(roomCategory);
            }
        }
        return roomParameter;
    }

    public LoginUser findUser(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        LoginUser loginUser = new LoginUser();
        if (email != null) {
            loginUser.setEmail(email);
        }
        if (password != null) {
            loginUser.setPassword(password);
        }
        return loginUser;
    }

    public RegistrationUser register(HttpServletRequest req) {
        //Copying all the input parameters in to local variables
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        RegistrationUser registrationUser = new RegistrationUser();

        if (name != null) {
            registrationUser.setName(name);
        }
        if (email != null) {
            registrationUser.setEmail(email);
        }
        if (login != null) {
            registrationUser.setLogin(login);
        }
        if (password != null) {
            registrationUser.setPassword(password);
        }
        return registrationUser;
    }


}
