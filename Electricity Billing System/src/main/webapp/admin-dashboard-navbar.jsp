<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page import="com.ebs.entities.Admin" %>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Dashboard</title>
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
	display: inline;
}

.dropdown-content {
	display: none;
	position: absolute;
	background: transparent;
}

.dropdown:hover  .dropdown-content {
	display: block;
}

.dropdown-content  a {
	color: black;
	font-size: 12px;
	padding: 0 5px;
	display: block;
	border: 2px solid #ffffff;
	border-radius: 20px;
	background-color: #E0E0E0;
	margin-top: 5px;
	text-align: center;
}

.dropdown-content a:hover {
	background-color: #A1887F;
	color:white;
}

</style>
</head>

<body>

	<nav>
		<img id="homeIcon" src="images/logo.png" width="40" height="32"
			alt="EBS" /> <a class="logo" href="Home.jsp">EBS</a>
		<ul>
			<li><a href="admin-dashboard.jsp" class="active-home">Home</a></li>

			<li><div class="dropdown">
					<label class="dropbtn" id="active-customer" onclick="dropdownClicked('Customer')">Customer</label>
					<div id="Customer" class="dropdown-content"  style="margin-left:10px;">
						<a href="AddConsumer.jsp">Add	Consumer</a> 
						 <a href="AdminDashboardServlet?link=view-consumers">View	Consumer</a>
					</div>
				</div></li>

			<li><div class="dropdown" >
					<label class="dropbtn" id="active-connection" onclick="dropdownClicked('Connection')">Connection</label>
					<div id="Connection" class="dropdown-content" style="margin-left:10px;">
						<a href="AddConnection.jsp">Add Connection</a> 
						<a  href="AdminDashboardServlet?link=view-connections">View Connection</a>
					</div>
				</div></li>

			<li><div class="dropdown" >
					<label class="dropbtn" id="active-bill" onclick="dropdownClicked('Bill')">Bill</label>
					<div  id="Bill" class="dropdown-content" >
						<a  href="AdminDashboardServlet?link=add-bills">Add Bill</a> 
						<a  href="AdminDashboardServlet?link=view-bills">View Bill</a>
					</div>
				</div></li>
			
			<li><a href="UpdateCost.jsp" class="active-update-cost">Update Cost</a></li>
			
			<li><div class="dropdown">
					<label class="dropbtn" id="active-profile" onclick="dropdownClicked('Profile')">Profile</label>
					<div  id="Profile" class="dropdown-content">
						<a style="font-size: 12px;" href="AdminDashboardServlet?link=view-profile">View Profile</a> 
							<a style="font-size: 12px;" href="AdminDashboardServlet?link=change-pwd">Change Password</a>
							<a style="font-size: 12px;" href="AdminDashboardServlet?link=logout">LogOut</a>
					</div>
				</div></li>
		</ul>
	</nav>

<script type="text/javascript">

// General function to handle dropdown clicks
function dropdownClicked(targetId) {
    console.log("Dropdown clicked for : " + targetId);
    // Hide all dropdown contents
    hideAllDropdowns();
    // Display the clicked dropdown content
    document.getElementById(targetId).style.display = "block";
}

// Function to hide all dropdown contents
function hideAllDropdowns() {
    var dropdownContents = document.getElementsByClassName("dropdown-content");
    for (var i = 0; i < dropdownContents.length; i++) {
        dropdownContents[i].style.display = "none";
    }
}
</script>

</body>
</html>