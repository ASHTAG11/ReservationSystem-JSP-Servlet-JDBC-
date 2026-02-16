<%@ page import="com.wipro.reservation.bean.ReservationBean" %>

<html>
<body>

<h2>Reservation Details</h2>

<%
ReservationBean bean =
(ReservationBean)request.getAttribute("bean");

String msg =
(String)request.getAttribute("msg");

if(bean == null){
%>

<h3><%= msg %></h3>

<%
}else{
%>

<table border="1">

<tr><td>ID</td><td><%= bean.getRecordId() %></td></tr>
<tr><td>Name</td><td><%= bean.getCustomerName() %></td></tr>
<tr><td>Type</td><td><%= bean.getBookingType() %></td></tr>
<tr><td>Date</td><td><%= bean.getBookingDate() %></td></tr>
<tr><td>Confirmation</td><td><%= bean.getConfirmationNo() %></td></tr>
<tr><td>Amount</td><td><%= bean.getAmount() %></td></tr>
<tr><td>Remarks</td><td><%= bean.getRemarks() %></td></tr>

</table>

<%
}
%>

<br>
<a href="menu.html">Back</a>

</body>
</html>
