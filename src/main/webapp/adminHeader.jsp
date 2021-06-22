<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
   <title>HEADER</title>
</head>
    <body>
        <div class="container" style="margin-top: 80px;" align='center'>
        <span><a href="login">Sign In</a></span>
        <span><a href="registration">Registration</a></span>
        <span><a href="bid">Bid</a></span>
        <span><a href="admin">Admin</a></span>
        <span><a href="<%=request.getContextPath()%>/LogoutServlet">Logout</a></div></span>
        </div>
     </body>
</html>