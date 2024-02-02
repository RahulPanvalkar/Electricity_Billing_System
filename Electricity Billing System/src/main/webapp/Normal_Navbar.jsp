<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Navigation Bar</title>
<link rel="stylesheet" href="style1.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<nav>

		<img id="homeIcon" src="images/logo.png" width="40" height="32"
			alt="EBS" /> <label class="logo">EBS</label>
		<ul>
			<li><a href="Home.jsp" class="active-home" href="#">Home</a></li>
			<li><a href="AdminLogin.jsp" class="active-admin">Admin</a></li>
			
			<li><div class="dropdown">
					<label class="dropbtn" id="active-login">Login/Register</label>
					<div class="dropdown-content1">
						<a  class ="active-user" style="font-size: 14px; display: inline;" href="UserLogin.jsp">Login</a> 
						<a  class ="active-user" style="font-size: 14px; display: inline;" href="UserRegistration.jsp">Register</a>
					</div>
				</div></li>

			<li><a href="About.jsp" class="active-about">About</a></li>
		</ul>
	</nav>

	<section></section>
</body>
</html>