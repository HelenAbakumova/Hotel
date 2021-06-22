package controller;

import entity.*;
import exception.WrongDateException;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

public class FormParser {

    private static final String SORT = "sort";
    private static final String DEFAULT_SORT = "Cheap to expensive";
    private static final int DEFAULT_LIMIT = 5;
    private static final int DEFAULT_PAGE = 1;

    public RoomParameter filterPriceSeparate(HttpServletRequest request) {
        RoomParameter roomParameter = new RoomParameter();
        Map<String, String[]> map = request.getParameterMap();

        String[] roomCategories = map.get("room_category");

        String priceFrom = request.getParameter("priceFrom");
        String priceTo = request.getParameter("priceTo");
        String capacity = request.getParameter("capacity");
        Date arrival;
        if (request.getParameter("arrival") != null) {
            arrival = Date.valueOf(request.getParameter("arrival"));
        } else {
            arrival = new Date(Calendar.getInstance().getTime().getTime());
        }

        Date departure;
        if (request.getParameter("departure") != null) {
            departure = Date.valueOf(request.getParameter("departure"));
        } else {
            departure = new java.sql.Date(arrival.getTime() + 24 * 60 * 60 * 1000);
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
            List<RoomCategory> roomCategory = Arrays.stream(roomCategories)
                    .map(RoomCategory::valueOf)// перегоняем каждую строку в объект перечисления
                    .collect(Collectors.toList());//собираем все в список
            if (roomCategory != null && roomCategory.equals("")) {
                roomParameter.setRoomCategory(null);
            } else {
                roomParameter.setRoomCategory(roomCategory);
            }
        }

        String sort = request.getParameter(SORT);
        roomParameter.setSort(Objects.nonNull(sort) ? sort : DEFAULT_SORT);
        if (arrival.before(new Date(Calendar.getInstance().getTime().getTime()))){
            throw new WrongDateException("wrong date");
        }
        roomParameter.setArrival(arrival);
        roomParameter.setDeparture(departure);
        roomParameter.setStatus(Collections.singletonList(RoomStatus.FREE));
        return roomParameter;
    }

    public LoginUser parseLoginForm(HttpServletRequest request) {
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


    public RegistrationUser parseRegistrationForm(HttpServletRequest req) {

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String rePassword = req.getParameter("rePassword");

        RegistrationUser registrationUser = new RegistrationUser();
        registrationUser.setRoleName("User");
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
        if (rePassword != null) {
            registrationUser.setRePassword(rePassword);
        }
        return registrationUser;
    }

    public Bid parseBidForm(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        //TODO add check if user already signed in
        if (user != null) {
            Bid bid = new Bid();
            bid.setEmail(user.getEmail());
            bid.setCapacity(request.getParameter("capacity"));
            bid.setCategory(RoomCategory.valueOf(request.getParameter("room_category")));
            bid.setArrival(Date.valueOf(request.getParameter("arrival")));
            bid.setDeparture(Date.valueOf(request.getParameter("departure")));

            return bid;
        } else {
            return null;
        }
    }

    public Bid parseFormFromBidUser(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        //TODO add check if user already signed in
        if (user != null) {
            Bid bid = new Bid();
            bid.setEmail(user.getEmail());
            bid.setRoomId(Integer.parseInt(request.getParameter("room_id")));
            bid.setArrival(Date.valueOf(request.getParameter("arrival")));
            bid.setDeparture(Date.valueOf(request.getParameter("departure")));
            return bid;
        } else {
            return null;
        }
    }

    public Bid bidUserForm(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        //TODO add check if user already signed in
        if (user != null) {
            Bid bid = new Bid();
            bid.setEmail(user.getEmail());
            bid.setCategory(RoomCategory.valueOf(request.getParameter("room_category")));
            bid.setCapacity(request.getParameter("capacity"));
            bid.setArrival(Date.valueOf(request.getParameter("arrival")));
            bid.setDeparture(Date.valueOf(request.getParameter("departure")));
            return bid;
        } else {
            return null;
        }
    }

    public Bill parseBill(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        //TODO add check if user already signed in
        if (user != null) {
            Bill bill = new Bill();
            bill.setBid(bill.getBid());
            return bill;
        } else {
            return null;
        }
    }
    public Bid bidofUsersForm(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        //TODO add check if user already signed in
        if (user != null) {
            Bid bid = new Bid();
            bid.setEmail(user.getEmail());
            bid.setCategory(RoomCategory.valueOf(request.getParameter("room_number")));
            bid.setArrival(Date.valueOf(request.getParameter("arrival")));
            bid.setDeparture(Date.valueOf(request.getParameter("departure")));
            return bid;
        } else {
            return null;
        }
    }

}
