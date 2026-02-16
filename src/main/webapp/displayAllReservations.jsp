<%@ page import="java.util.*,com.wipro.reservation.bean.ReservationBean" %>

<html>
<body>

<h2>All Reservations</h2>

<%
List<ReservationBean> list =
(List<ReservationBean>)request.getAttribute("list");

String msg =
(String)request.getAttribute("msg");

if(list == null || list.isEmpty()){
%>

<h3><%= msg %></h3>

<%
}else{
%>

<table border="1">

<tr>
<th>ID</th>
<th>Name</th>
<th>Type</th>
<th>Date</th>
<th>Amount</th>
</tr>

<%
for(ReservationBean b : list){
%>

<tr>
<td><%= b.getRecordId() %></td>
<td><%= b.getCustomerName() %></td>
<td><%= b.getBookingType() %></td>
<td><%= b.getBookingDate() %></td>
<td><%= b.getAmount() %></td>
</tr>

<%
}
%>

</table>

<%
}
%>

<br>
<a href="menu.html">Back</a>

</body>
</html>
