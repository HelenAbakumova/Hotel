<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
   <head>
      <title>Home Page</title>
      <%@ include file="userHeader.jsp" %>

   </head>
   <body>
   <div align="center">
   <table border="1" cellpadding="5">
     <caption><h2>My bids</h2></caption>
         <tr>
                <th>Category</th>
                <th>Capacity</th>
                <th>Total price</th>

         </tr>
         <c:forEach var="bid" items="${bidList}">
            <tr>
               <td>${bid.category}</td>
               <td>${bid.capacity}</td>
               <td>${bid.price}</td>
               <td>
               <input type="radio" name="bid_id" value="apply" form="management" />Apply booking
               <input type="radio" name="bid_id" value="reject" form="management" />Reject booking
               </td>
            </tr>
         </c:forEach>
         </table>
            <form action="bid" method="POST" id="management">
            <button type="submit" title="Submit">Submit</button>
      </table>
         </body>
           <%@ include file="footer.jsp" %>
      </html>