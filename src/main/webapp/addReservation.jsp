<html>
<body>

<h2>Add Reservation</h2>

<form action="MainServlet" method="post">

<input type="hidden" name="operation" value="newRecord">

Name: <input type="text" name="customerName"><br><br>

Type: <input type="text" name="bookingType"><br><br>

Date: <input type="date" name="bookingDate"><br><br>

Confirmation No: <input type="text" name="confirmationNo"><br><br>

Amount: <input type="text" name="amount"><br><br>

Remarks: <input type="text" name="remarks"><br><br>

<input type="submit" value="Add">

</form>

</body>
</html>
