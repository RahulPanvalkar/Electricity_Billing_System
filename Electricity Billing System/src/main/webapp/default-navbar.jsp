<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Default Navbar</title>
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

</style>
</head>

<body>

	<nav>
		<img id="homeIcon" src="images/logo.png" width="40" height="32"
			alt="EBS" /> <a class="logo" href="Home.jsp">EBS</a>
	</nav>

</body>
</html>