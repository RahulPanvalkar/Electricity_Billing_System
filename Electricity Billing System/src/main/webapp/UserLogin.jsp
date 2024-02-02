<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Login</title>
<style>
a .#active-user {
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

.form-group input[type="text"], .form-group input[type="password"] {
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
	<%@ include file="Normal_Navbar.jsp"%>
	
	<div class="container">

		<h1>User Login</h1>
		<form action="UserLoginServlet" method="post">
			<div class="form-group">
				<label for="userId">UserId</label> <input type="text" id="username" name="userId" 
				placeholder="Enter your userId/Email" 	required  maxlength="50">
			</div>
			<div class="form-group">
				<label for="password">Password</label> <input type="password"	id="password" 
				name="password" placeholder="Enter your password" required maxlength="20">
			</div>
			<div class="form-group">
				<button type="submit">Login</button>
			</div>
			<div class="error">
				<span>${error}</span>
			</div>
			<% session.removeAttribute("error"); %>
			<div class="text-center">
				<a href="ForgetPassword.jsp">Forgot password?</a>
			</div>
		</form>
	</div>
</body>
</html>