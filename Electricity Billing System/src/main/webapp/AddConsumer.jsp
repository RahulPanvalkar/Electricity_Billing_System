<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Customer</title>
<style>
#active-customer {
	background: #b6c7d4;
	transition: .5s;
}

body {
	font-family: Arial, sans-serif;
	background-color: #f2f2f2;
}

.container {
	min-width: 400px;
	margin: 20px 500px;
	padding: 40px;
	background-color: #fff;
	border-radius: 50px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.container h1 {
	text-align: center;
	margin-bottom: 30px;
}

.container input[type="text"], .container input[type="email"],
.containter label, .container select{
	width: 100%; 
	padding: 10px;
	margin-bottom: 20px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
}

.container button[type="submit"] {
	width: 100%;
	padding: 10px;
	background-color: #000000;
	color: #fff;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

.container button[type="submit"]:hover {
	background-color: #00BCD4;
}

</style>

<link rel="stylesheet" href="style2.css">

 <script src="script.js"></script>

</head>
<body>

	<!-- navbar-->
	<%@ include file="admin-dashboard-navbar.jsp"%>

	<div class="container">
		<h1>Add New Consumer</h1>
		<form action="AddConsumerServlet" method="post">
			
			<label for="connectionType">Full Name</label>
			<input type="text" name="name" placeholder="Enter name here" required
					  maxlength="50">
			<label for="connectionType">Email Id</label>
			<input type="email" name="email" placeholder="Enter email here" required
					  maxlength="50">
			<label for="connectionType">Mobile Number</label>
			<input type="text" name="mob-number" placeholder="Enter mobile number here"	required
					  maxlength="10">
			<label for="connectionType">Address</label> 
			<input type="text"	name="address" placeholder="Ex : 123, Main Apt, Andheri West, Mumbai, 400001" required
					  maxlength="100">
			
			<button type="submit">Add Consumer</button>
			
			<c:choose>
				<c:when test="${error == 'Consumer Added Successfully'}">
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
