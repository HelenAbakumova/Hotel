<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Order</title>
    <script>
        function validate() {
            var capacity = ${order.capacity};
            var category = ${order.category};
            var login = ${order.login};
            var arrived = ${order.arrived};
            var departure = ${order.departure};
            var room = ${order.room};
            var totalPrice = ${order.totalPrice};

            if (capacity == null || capacity == "") {
                alert("Requirement capacity can't be blank");
                return false;
            } else if (category == null || category == "") {
                alert("Requirement Type can't be blank");
                return false;
            } else if (login == null || login == "") {
                alert("Login can't be blank");
                return false;
            } else if (arrived == null || arrived == "" && departure == null || departure == "") {
                alert("Date in and date out can't be blank");
                return false;
            }
        }
    </script>
</head>
<body>
<<h2>Your application</h2>
<form name="form" action="orderAdmin.jsp" method="post" onsubmit="return validate()">

    Name: <input name="login"/>
    <br><br>
    Type: <input type="radio" name="category" value="Economy" checked/>Economy
    <input type="radio" name="category" value="Standard"/>Standard
    <input type="radio" name="category" value="Suite"/>Suite

    Capacity:
    <input type="radio" name="capacity" value="1" checked/>1
    <input type="radio" name="capacity" value="2" checked/>2
    <input type="radio" name="capacity" value="3" checked/>3
    <input type="radio" name="capacity" value="4" checked/>4
    <br><br>
    <table align="center">
        <tr>
            <td>Name</td>
            <td><input type="text" name="capacity"/></td>
        </tr>
        <tr>
            <td>Email</td>
            <td><input type="text" name="category"/></td>
        </tr>
        <tr>
            <td>Login</td>
            <td><input type="text" name="login"/></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="arrived"/></td>
        </tr>
        <tr>
            <td>Confirm Password</td>
            <td><input type="password" name="departure"/></td>
        </tr>
        <tr>
            <td><%=(request.getAttribute("errMessage") == null) ? ""
                    : request.getAttribute("errMessage")%>
            </td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Apply"></input><input
                    type="reset" value="Reset"></input></td>
        </tr>
    </table>
    <head>
        <meta charset="UTF-8">
        <title>Application Form</title>
    </head>
    <body>
    <form action="orderAdmin.jsp" method="POST">

        <input type="submit" value="Submit"/>
    </form>
    </body>
</form>
</html>
