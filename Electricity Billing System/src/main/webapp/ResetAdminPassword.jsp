<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Verification</title>
<style>
a.active-admin {
	background: #b6c7d4;
	transition: .5s;
}

body {
	font-family: Arial, sans-serif;
	background-color: #f2f2f2;
}

.container {
	max-width: 400px;
	margin: 25px 500px;
	padding: 40px;
	background-color: #fff;
	border-radius: 50px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.container h1 {
	text-align: center;
	margin-bottom: 30px;
}

.container input[type="text"],  .container input[type="number"], 
	 .containter label{
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
	<%@ include file="Normal_Navbar.jsp"%>
	
	<div class="container">
		<h1>Admin Verification</h1>
		<form action="ResetAdminPasswordServlet" method="post">
			
			<label>UserID</label>
			<input type="number" name="id" placeholder="Enter your UserID" required
					  maxlength="10">
			
			<h2 style="padding-bottom: 10px;">Security Questions</h2>
			
			<label>Enter Your Birth Place</label>
			<input type="text" name="birth-place" placeholder="Enter birth place here"	required
					  maxlength="30">
					
			<label>Enter Your Favorite Teacher's Name</label> 
			<input type="text"	name="teacher-name" placeholder="Enter teacher's name here" required
					  maxlength="30">
					
			<label>Enter Your Favorite Sport</label>
			<input type="text"	name="sport" placeholder="Enter name of a sport here" required
					  maxlength="30">
			
			<button type="submit">Submit</button>
			
			<c:if test="${error != null }" >
				<div class="error">
					<span>${error}</span>
				</div>
			<% session.removeAttribute("error"); %>
			</c:if>
		
		</form>
		
	</div>
</body>
</html>
