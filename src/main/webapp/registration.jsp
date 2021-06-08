<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<title>Register</title>
<script>
function validate()
{
     var name = ${registrationUser.name};
     var email = ${registrationUser.email};
     var login = ${registrationUser.login};
     var password = ${registrationUser.password};
     var rePassword= ${registrationUser.rePassword};

     if (name==null || name=="")
     {
     alert("Name can't be blank");
     return false;
     }
     else if (email==null || email=="")
     {
     alert("Email can't be blank");
     return false;
     }
     else if (login==null || login=="")
     {
     alert("Login can't be blank");
     return false;
     }
     else if(password.length<6)
     {
     alert("Password must be at least 6 characters long.");
     return false;
     }
     else if (password!=conpassword)
     {
     alert("Confirm Password should match with the Password");
     return false;
     }
 }


</script>
</head>
<body>
<center><h2>Java Registration application using MVC and MySQL </h2></center>
    <form name="form" action="" method="post" onsubmit="return validate()">
        <table align="center">
         <tr>
         <td>Name</td>
         <td><input type="text" name="name" /></td>
         </tr>
         <tr>
         <td>Email</td>
         <td><input type="text" name="email" /></td>
         </tr>
         <tr>
         <td>Login</td>
         <td><input type="text" name="login" /></td>
         </tr>
         <tr>
         <td>Password</td>
         <td><input type="password" name="password" /></td>
         </tr>
         <tr>
         <td>Confirm Password</td>
         <td><input type="password" name="conpassword" /></td>
         </tr>
         <tr>
         <td><%=(request.getAttribute("errMessage") == null) ? ""
         : request.getAttribute("errMessage")%></td>
         </tr>
         <tr>
         <td></td>
         <td><input type="submit" value="Register"></input><input
         type="reset" value="Reset"></input></td>
         </tr>
        </table>
    </form>
</body>
</html>