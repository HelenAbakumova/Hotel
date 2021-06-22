<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
<html>
<header >
    <div class="overlay">
        <span><a href="login">Sign In</a></span>
        <span><a href="registration">Registration</a></span>
        <span><a href="room">Room</a></span>
        <span><a href="user">User</a></span>
        <span><a href="pay.jsp">My bills</a></span>
        <span><a href="<%=request.getContextPath()%>/LogoutServlet">Logout</a></span>
    </div>
</header>
</html>