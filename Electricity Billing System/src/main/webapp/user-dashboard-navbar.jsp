<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Dashboard</title>
<style type="text/css">

/*Navbar*/
* {
	padding: 0;
	margin: 0;
	text-decoration: none;
	box-sizing: border-box;
}

body {
	font-family: montserrat;
	background-image: linear-gradient(rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.4)),
		url(images/background.jpg);
	background-position: center;
}

nav {
	background: transparent;
	height: 50px;
	width: 100%;
}

.logo {
	color: white;
	font-size: 40px;
	line-height: 50px;
	font-weight: bold;
}

nav ul {
	float: right;
	margin-right: 20px;
}

nav ul li {
	display: inline-block;
	line-height: 50px;
	margin: 0 5px;
}

nav ul li a, .dropbtn {
	color: white;
	font-size: 20px;
	font-weight: bold;
	padding: 7px 13px;
	border-radius: 20px;
}

a:hover, .dropbtn:hover {
	background: #A1887F;
	transition: .5s;
}

.dropdown {
	position: relative;
	display: inline-block;
}

.dropdown-content {
	display: none;
	position: absolute;
	background: transparent;
	align-items: center;
}

.dropdown:hover  .dropdown-content {
	display: block;
}

.dropdown-content  a {
	color: black;
	display: block;
	border: 2px solid #ffffff;
	border-radius: 20px;
	background-color: #E0E0E0;
	margin-top: 10px;
	font-size: 14px;
}

.dropdown-content a:hover {
	background-color: #ddd;
}

</style>
</head>

<body>

	<nav>
		<img id="homeIcon" src="images/logo.png" width="40" height="32"
			alt="EBS" /> <a class="logo">EBS</a>
		<ul>
			<li><a href="user-dashboard.jsp" class="active-home">Home</a></li>

			<li><div class="dropdown">
					<label class="dropbtn" id="active-bill">Bill</label>
					<div class="dropdown-content">
						 <a href="UserDashboardServlet?link=view-bill">View Bill</a>
					</div>
				</div></li>
			
			<li><div class="dropdown">
					<label class="dropbtn" id="active-profile">Profile</label>
					<div class="dropdown-content" style="margin-left: 5px;">
						 <a  class="active-change-pwd" href="UserDashboardServlet?link=change-pwd">Change Password</a>
						 <a  class="active-change-pwd" href="UserDashboardServlet?link=logout">Logout</a>
					</div>
				</div></li>
			
		</ul>
	</nav>


</body>
</html>