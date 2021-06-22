<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"><html>
<head>
    <title>Home Page</title>
    <%@ include file="userHeader.jsp" %>
</head>
<body>
<div align="center">
    <table border="1" cellpadding="5">
        <caption>
            <h2>My bids</h2>
        </caption>
        <tr>
            <th>Capacity</th>
            <th>Category</th>
            <th>Arrival</th>
            <th>Departure</th>
            <th>Total price</th>
            <th>Confirm booking</th>
        </tr>
        <c:forEach var="bid" items="${bidList}">
            <tr>
                <td>${bid.capacity}</td>
                <td>${bid.category}</td>
                <td>${bid.arrival}</td>
                <td>${bid.departure}</td>
                <td>${bid.totalPrice}</td>
                <td><input type="radio" name="bid_id" value="${bid.id}" form="management"/>Confirm booking</td>
            </tr>
        </c:forEach>
    </table>
    <form action="bill" method="POST" id="management">
        <input type="hidden" name="action" value="Confirm booking"/>
        <button type="submit" title="Submit">Confirm booking</button>
        </table>
    </form>
</div>
</body>
<%@ include file="footer.jsp" %>
</html>