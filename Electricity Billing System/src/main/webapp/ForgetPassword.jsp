<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Forget Password</title>
<style>
#active-login {
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

.form-group input[type="number"] {
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

.form-group .text-center {
	text-align: center;
}

</style>

<link rel="stylesheet" href="style2.css">

 <script src="script.js"></script>

</head>
<body>

	<!-- navbar-->
	<%@ include file="Normal_Navbar.jsp"%>
	
	<div class="container">

		<h1>User Verification</h1>
		<form action="ForgetPasswordServlet" method="post">
			<div class="form-group">
				<label for="ca-number">CA Number</label> 
				<input type="number"	name="conNumber"
					placeholder="Enter your consumer number" required   maxlength="10">
			</div>
			<div class="form-group">
				<label for="mob-number">Mobile Number</label> 
				<input type="number" name="mobNumber"
					placeholder="Enter your mobile number" required   maxlength="10">
			</div>
			<div class="form-group">
				<button type="submit">Submit</button>
			</div>
			<div class="error">
            <span>${error}</span>
        	</div>
        	<% session.removeAttribute("error"); %>
		</form>
	</div>

</body>
</html>