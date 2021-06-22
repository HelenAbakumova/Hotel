<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
    <%@ include file="userHeader.jsp" %>
    <title>Payment Page</title>
</head>
<body>
<div align="center" class="grid-child">
<table border="1" cellpadding="5">
    <caption>
        <h2>My bills</h2>
    </caption>
    <tr>
        <th>Capacity</th>
        <th>Category</th>
        <th>Arrival</th>
        <th>Departure</th>
        <th>Total price</th>
        <th>Bill status</th>
        <th>Choose</th>
    </tr>
    <c:forEach var="bill" items="${billList}">
        <tr>
            <td>${bill.bid.capacity}</td>
            <td>${bill.bid.category}</td>
            <td>${bill.bid.arrival}</td>
            <td>${bill.bid.departure}</td>
            <td>${bill.bid.totalPrice}</td>
            <td>${bill.status}</td>
            <td><input type="radio" name="billId" value="${bill.id}" form="management"/>Confirm paying</td>
        </tr>
    </c:forEach>
</table>
<form action="bill" method="POST" id="management">
    <input type="hidden" name="action" value="Pay"/>
    <button type="submit" title="Submit">Pay</button>
</form>
</div>
</body>
<%@ include file="footer.jsp" %>
</html>