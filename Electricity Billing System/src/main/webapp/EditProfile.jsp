<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@page import="com.ebs.entities.User"%>
	<%@page import="com.ebs.entities.Admin"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Profile</title>
<style>
a .#active-profile {
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
	text-align: center;
	margin-bottom: 30px;
}

.form-group {
	margin-bottom: 20px;
}

.form-group label {
	display: block;
	margin-bottom: 5px;
	font-weight: bold;
}

.form-group input[type="text"], .form-group input[type="email"]
, .form-group input[type="number"] {
	width: 100%;
	padding: 10px;
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
	cursor: pointer;
	border-radius: 4px;
}

.form-group button:hover {
	background-color: #00BCD4;
}

.text-center {
	text-align: center;
	margin: 10px;
}


</style>

<link rel="stylesheet" href="style2.css">

 <script src="script.js"></script>

</head>
<body>


	<!-- navbar-->
	<c:choose>
		<c:when test="${ consumer != null }">
			<c:import url="user-dashboard-navbar.jsp"/>
			<c:set var="userType" value="consumer" />
		</c:when>
		<c:when test="${admin != null }">
			<c:import url="admin-dashboard-navbar.jsp"/>
			<c:set var="userType" value="admin" />
		</c:when>
		<c:otherwise>
			<c:import url="default-navbar.jsp"/>
		</c:otherwise>
	</c:choose>  
	
	<div class="container">

		<h1>Edit Profile</h1>
		<form id="myForm"  action="EditProfileServlet?userType=${userType}" method="post">
			<div class="form-group">
				<label>Full Name</label> 
				<input type="text"	 name="full-name" value="${user.fullName }"
					required   maxlength="30">
			</div>
			
			<div class="form-group">
				<label>Email</label> 
				<input type="email"	value="${user.email }"  name="email" 
					required   maxlength="50">
			</div>
			
			<div class="form-group">
				<label>Mobile Number</label> 
				<input type="number" value="${user.mob }" name="mobile" 
					required   maxlength="10">
			</div>
			
			<div class="form-group">
				<button type="button" onclick="checkUpdate()">Update</button>
			</div>
			
			<c:choose>
			<c:when test="${error == 'Profile has been updated'}">
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
	
	 <script>
        let originalNameValue = document.getElementsByName("full-name")[0].value;
        let originalEmailValue = document.getElementsByName("email")[0].value;
        let originalMobileValue = document.getElementsByName("mobile")[0].value;

        function checkUpdate() {
        	let currentNameValue = document.getElementsByName("full-name")[0].value;
            let currentEmailValue = document.getElementsByName("email")[0].value;
            let currentMobileValue = document.getElementsByName("mobile")[0].value;
            
            if (currentNameValue !== originalNameValue || currentEmailValue !== originalEmailValue || currentMobileValue !== originalMobileValue) {
				document.getElementById("myForm").submit();
            } else {
				let error = document.getElementsByClassName("error")[0];
				error.innerText = "No changes detected";
            }
        }
    </script>
	
</body>
</html>