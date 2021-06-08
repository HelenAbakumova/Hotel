<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Home Page</title>
</head>
<body>
 <center><h2>Home Page</h2></center>
 <b>User Registration Successful</b>
 <br></br>
 <b>Please <a href="http://localhost:8090/hotel/login">log-in</a> to continue.</b>
Welcome <%=request.getAttribute("userName") %>
<div style="text-align: right"><a href="http://localhost:8090/hotel/login">Logout</a></div>
</body>
</html>