<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
   <head>
      <title>hotel</title>
      <title>Hotel</title>
   </head>
   <body>
   <div align="center">
   <table border="1" cellpadding="5">
     <caption><h2>Our Rooms</h2></caption>
         <tr>
                <th>Category</th>
                <th>Capacity</th>
                <th>Price</th>
                <th>Room Status</th>
                <th>Image</th>
         </tr>
         <c:forEach var="room" items="${roomList}">
            <tr>
               <td>${room.category}</td>
               <td>${room.capacity}</td>
               <td>${room.price}</td>
               <td>${room.status}</td>
               <td>${room.imgName}</td>
            </tr>
         </c:forEach>
      </table>
      <form action="room" method="GET" id="filter">
        <div align="left">

        <label>Room Status</label>
        <table border="1" cellpadding="5">
        <input type="checkbox" name="room_status" value="free" >Free<BR>
        <input type="checkbox" name="room_status" value="booked" >Booked<BR>
        <input type="checkbox" name="room_status" value="occupied" >Occupied<BR>
        <input type="checkbox" name="room_status" value="unavailable" >Unavailable<BR>

        <label>Number of persons</label>
        <div class="filter-interval">
        <label>Enter of number <input type="text" name="capacity" ></label>
        </div>

        <label>Get price</label>
               <div class="filter-interval">
                 <label>From <input type="text" name="priceFrom" ></label>
                 <label>To <input type="text" name="priceTo" ></label>
               </div>
             </div>
             <div align="left">
                       <label>Room categories</label>
                      <table border="1" cellpadding="5">
                          <input type="checkbox" name="room_categories" value="Economy" >Economy<BR>
                          <input type="checkbox" name="room_categories" value="Standard" >Standard<BR>
                          <input type="checkbox" name="room_categories" value="Suite" >Suite<BR>
                        </form>
                       </table>
      </form>
     </table>
               <input type="submit" value=" Нажми меня ">

   </body>
</html>

