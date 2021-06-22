<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
<html>
<head>
    <title>Home Page</title>
    <%@ include file="userHeader.jsp" %>
</head>
<body>
<div id="rooms and facets" class="grid-container">
    <table class="table">
        <caption>
            <h2>Our Rooms</h2>
        </caption>
        <tr>
            <th>Category</th>
            <th>Capacity</th>
            <th>Price</th>
            <th>Image</th>
            <th>Book room</th>
        </tr>
        <c:forEach var="room" items="${roomList}">
            <tr>
                <td>${room.category}</td>
                <td>${room.capacity}</td>
                <td>${room.price}</td>
                <td>${room.imgName}</td>
                <td>
                    <label>
                        <input type="radio" name="room_id" value="${room.id}" form="booking"/>
                    </label>Book
                </td>
            </tr>
        </c:forEach>
    </table>
    <div id="sorting and limit" class="grid-child">
        <span>Sort By</span>
        <label>
            <select name="sort" form="filter">
                <option>Cheap to expensive</option>
                <option>Expensive to cheap</option>
                <option>Capacity</option>
                <option>Category</option>
            </select>
        </label>
        <span>Enter count on pager</span>
        <label>
            <select name="limit" form="filter">
                <option value="5">5</option>
                <option value="10">10</option>
                <option value="50">50</option>
            </select>
        </label>
        <div class="grid-child">
            <div id="filtering" class="grid-container">
                <form action="room" method="GET" id="filter">
                    <label>
                        <input type="date" name="arrival"
                               value="2021-06-16"
                               min="2021-01-01" max="2021-12-31">
                    </label>
                    <label>
                        <input type="date" name="departure"
                               value="2021-06-18"
                               min="2021-01-01" max="2021-12-31">
                    </label>
                    <div>
                        <label>Capacity <input type="text" name="capacity"></label>
                    </div>
                    <div>
                        <label>From <input type="text" name="priceFrom" value="0"></label> <label>To <input type="text"
                                                                                                            name="priceTo"
                                                                                                            value="1300"></label>
                    </div>
                    <div>
                        <label>Room categories</label>
                        <table class="table">
                            <input type="checkbox" name="room_category" value="Economy"> Economy
                            <input type="checkbox" name="room_category" value="Standard"> Standard
                            <input type="checkbox" name="room_category" value="Suite"> Suite
                        </table>
                    </div>
                    <input type="submit" value=" Submit ">
                </form>
            </div>
        </div>
    </div>
    <%--    <c:forEach var="page" items="${pages}">--%>
    <%--        <a href="room?${sessionScope.query}&page=${page}">${page}</a>--%>
    <%--    </c:forEach>--%>

</div>
<form action="bid" method="POST" id="booking">
    <input type="hidden" name="action" value="book"/>
    <label for="user-arrival">Arrival:</label>
    <input type="date" id="user-arrival" name="arrival"
           value="2021-06-16"
           min="2021-01-01" max="2021-12-31">
    <label for="user-departure">Departure:</label>
    <input type="date" id="user-departure" name="departure"
           value="2021-06-18"
           min="2021-01-01" max="2021-12-31">
    <button class="ui-button" type="submit" title="Book">Book</button>
</form>
<form name="form" action="bid" method="post">
    <label>Create bid</label>
    <br><br>
    Category: <label>
    <input type="radio" name="room_category" value="ECONOMY" checked/>
</label>Economy
    <label>
        <input type="radio" name="room_category" value="STANDARD"/>
    </label>Standard
    <label>
        <input type="radio" name="room_category" value="SUITE"/>
    </label>Suite
    <br><br>
    Capacity:
    <label>
        <input type="radio" name="capacity" value="1" checked/>
    </label>1
    <label>
        <input type="radio" name="capacity" value="2" checked/>
    </label>2
    <label>
        <input type="radio" name="capacity" value="3" checked/>
    </label>3
    <label>
        <input type="radio" name="capacity" value="4" checked/>
    </label>4
    <br><br>
    <label for="start">Arrival:</label>
    <input type="date" id="start" name="arrival"
           value="2021-06-16"
           min="2021-01-01" max="2021-12-31">
    <label for="end">Departure:</label>
    <input type="date" id="end" name="departure"
           value="2021-06-18"
           min="2021-01-01" max="2021-12-31">
    <input type="hidden" name="action" value="create"/>
    <input type="submit" name="action" value="Apply"/>
</form>
</body>
<%@ include file="footer.jsp" %>
</html>