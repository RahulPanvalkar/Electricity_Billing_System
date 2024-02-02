<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>About</title>
<style>
a.active-about {
	background: #b6c7d4;
	transition: .5s;
}

body {
	font-family: Arial, sans-serif;
	background-image: linear-gradient(rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.4)),
		url(images/logo.png);
}

.container {
	max-width: 800px;
	margin: 0 auto;
	padding: 40px;
	background-color: #fff;
	border-radius: 5px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.container h1 {
	text-align: center;
	margin-bottom: 30px;
}

.container p {
	line-height: 1.6;
	margin-bottom: 20px;
}

.container .highlight {
	background-color: #eaf6ff;
	padding: 10px;
	border-radius: 4px;
}

.container .text-center {
	text-align: center;
}

.container .quote {
	font-style: italic;
	font-size: 18px;
	margin-bottom: 30px;
}

.container .mission {
	font-weight: bold;
	font-size: 22px;
	margin-bottom: 20px;
}
</style>
</head>
<body>

	<%@ include file="Normal_Navbar.jsp"%>

	<div class="container">
		<h1>About Us</h1>
		<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam
			sed nulla lacinia, tristique dui et, consequat nisi. Nullam tempor
			nibh vel tellus volutpat, vitae venenatis tortor dignissim. Sed
			iaculis nunc ac orci feugiat aliquet.</p>
		<p>Suspendisse semper vulputate neque, nec iaculis quam tempor
			vel. Curabitur ut elementum felis. Donec congue felis vel felis
			volutpat tristique. Vestibulum lacinia elit vel mauris gravida, vitae
			elementum mi semper. In hac habitasse platea dictumst. Sed rhoncus
			turpis id quam blandit varius. Nam feugiat condimentum diam sed
			congue.</p>
		<p class="highlight">We are dedicated to providing excellent
			services to our customers and strive to deliver the best user
			experience.</p>
		<div class="quote">"Quality and customer satisfaction are at the
			core of our values."</div>
		<p class="mission">Our Mission</p>
		<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam
			sed nulla lacinia, tristique dui et, consequat nisi. Nullam tempor
			nibh vel tellus volutpat, vitae venenatis tortor dignissim. Sed
			iaculis nunc ac orci feugiat aliquet. Suspendisse semper vulputate
			neque, nec iaculis quam tempor vel.</p>
		<p>Curabitur ut elementum felis. Donec congue felis vel felis
			volutpat tristique. Vestibulum lacinia elit vel mauris gravida, vitae
			elementum mi semper. In hac habitasse platea dictumst. Sed rhoncus
			turpis id quam blandit varius. Nam feugiat condimentum diam sed
			congue.</p>
		<div class="text-center">
			<img src="images/logo.png" alt="Company Logo" width="100">
		</div>
	</div>
</body>
</html>
