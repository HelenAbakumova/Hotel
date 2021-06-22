<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
   <head>
      <%@ include file="adminHeader.jsp" %>
      <title>Admin Page</title>
   </head>
   <body>
      <div align="left">
         <table border="1" cellpadding="5">
            <caption>
               <h2>Bids</h2>
            </caption>
            <tr>
               <th>Capacity</th>
               <th>Category</th>
               <th>Arrival</th>
               <th>Departure</th>
               <th>Decision</th>
               <th>Select number of room</th>
            </tr>
            <c:forEach var="bid" items="${bidList}">
               <tr>
                  <td>${bid.capacity}</td>
                  <td>${bid.category}</td>
                  <td>${bid.arrival}</td>
                  <td>${bid.departure}</td>
                  <td style="display:none;">
                     <input type="text" name="bid_id" value=${bid.id} form="management"/>
                  </td>
                  <td style="display:none;">
                      <input type="text" name="email" value=${bid.email} form="management"/>
                   </td>
                  <td>
                    <select name="decision" form ="management">
                  <option>approve</option>
                  <option>reject</option>
                  <option selected="no decision">no decision</option>
                    </select>
                  </td>
                  <td>
                     <select name="room_number" form ="management">
                        <c:forEach var="room" items="${roomList}">
                           <option>${room.id}</option>
                        </c:forEach>
                     </select>
                  </td>
            </c:forEach>
            </tr>
         </table>
         <form action="bid" method="POST" id="management">
         <input type="hidden" name="action" value="update"/>

         <button type="submit" title="Submit">Submit</button>
         </form>
      </div>
      <div align="right">
      <table border="1" cellpadding="5">
      <caption>
         <h2>Our Rooms</h2>
      </caption>
      <tr>
         <th>Number of room</th>
         <th>Category</th>
         <th>Capacity</th>
         <th>Status</th>
      </tr>
      <c:forEach var="room" items="${roomList}">
         <tr>
            <td>${room.id}</td>
            <td>${room.category}</td>
            <td>${room.capacity}</td>
            <td>${fn:toUpperCase(room.status)}</td>
         </tr>
      </c:forEach>

      <div align="right">
            <table border="1" cellpadding="5">
            <caption>
               <h2>Bids of Users</h2>
            </caption>
            <tr>
               <th>Room number</th>
               <th>Arrival</th>
               <th>Departure</th>
               <th>Status</th>
            </tr>
            <c:forEach var="rooms" items="${rooms}">
               <tr>
                  <td>${rooms.id}</td>
                  <td>${rooms.arrival}</td>
                  <td>${rooms.departure}</td>
                  <td>${fn:toUpperCase(rooms.status)}</td>
               </tr>
            </c:forEach>

   </body>
</html>