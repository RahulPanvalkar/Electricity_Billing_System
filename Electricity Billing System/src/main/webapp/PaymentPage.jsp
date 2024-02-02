<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.ebs.entities.Bill" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment Page</title>
<style>
a.active-home {
	background: #b6c7d4;
	transition: .5s;
}

body {
	font-family: Arial, sans-serif;
	background-image: linear-gradient(rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.4)),
		url(images/logo.png);
}

.container {
	max-width: 400px;
	margin: 20px 500px;
	padding: 40px;
	background-color: #fff;
	border-radius: 50px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.container h1 {
	font-size: 27px;
	margin-bottom: 30px;
	border-bottom: 2px solid #ccc;
}

.form-group {
	margin-bottom: 20px;
}

.form-group label {
	display: block;
	margin-bottom: 5px;
	font-weight: bold;
}

.form-group input[type="text"] {
	width: 100%;
	padding: 5px;
	border-radius: 4px;
	border: 1px solid #ccc;
}

.form-group input[type="date"] {
	width: 100%;
	padding: 3.5px;
	border-radius: 4px;
	border: 1px solid #ccc;
}

.form-group button {
	width: 100%;
	padding: 10px;
	background-color: #000000;
	border: none;
	color: #fff;
	font-weight: bold;
	border-radius: 4px;
}

.form-group button:hover {
	background-color: #00BCD4;
}

.row {
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.col {
	flex: 1;
	margin-right: 20px;
}

.col:last-child {
	margin-right: 0;
}

.col label {
	display: block;
	margin-bottom: 5px;
	font-weight: bold;
}

</style>

<link rel="stylesheet" href="style2.css">

 <script src="script.js"></script>
 
 </head>
 
<body>

	<c:choose>
    <c:when test="${user != null}">
        <%@ include file="user-dashboard-navbar.jsp" %>
    </c:when>
    <c:otherwise>
        <%@ include file="default-navbar.jsp"%> 
    </c:otherwise>
	</c:choose>

	<div class="container">

		<h1>
			Payment Details <img alt="card" src="images/card.png" width="40"	height="40">
		</h1>
		<form action="PaymentPageServlet" method="post">

			<div class="form-group">
				<label>Card Number</label> 
				<input type="text" placeholder="Enter your card number" required maxlength="16">
			</div>

			<div class="form-group">
				<div class="row">
					<div class="col">
						<label>Expiration Date</label> <input type="date" name="expiryDate"
							placeholder="Expiration Date" required>
					</div>
					<div class="col">
						<label>CVC Code</label> 
						<input type="text"	placeholder="Enter cvc" required maxlength="3">
					</div>
				</div>
			</div>
			
			<div class="form-group">
			<%
				String totalAmount = request.getParameter("totalAmount") ;
				if(totalAmount == null){
					totalAmount = "";
				}
			%>
   					 <label style="font-size: 20px">Amount to Pay: <%=totalAmount %> &#8377;</label>
			</div>
			
			<div class="form-group">
				<button type="submit">Pay</button>
			</div>
			
			<c:choose>
				<c:when test="${error == 'Payment Successfull'}">
					<div class="message">
						<span>${error}</span>
					</div> 
				</c:when>
				<c:otherwise>
					<div class="error">
						<span>${error}</span>
					</div>
				</c:otherwise>
			</c:choose>
			
			<% session.removeAttribute("error"); %>
			
		</form>
	</div>
</body>
</html>