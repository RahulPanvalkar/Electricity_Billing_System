<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!doctype html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Form</title>
<link rel="stylesheet" href="style1.css">

<style type="text/css">
a.active-home {
	background: #b6c7d4;
	transition: .5s;
}

.popup-overlay {
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.5);
	display: none;
	justify-content: center;
	align-items: center;
	z-index: 9999;
}

.popup-content {
	background-color: #fff;
	padding: 20px;
	border-radius: 5px;
	max-width: 80%;
	max-height: 80%;
	overflow: auto;
	text-align: center;
}

.centered-text {
	text-align: center;
	color: red;
	margin: 10px
}
</style>

 <script src="script.js"></script>

</head>
<body id="home-body">
	<!-- navbar-->
	<%@ include file="Normal_Navbar.jsp"%>

	<div class="home-container">
		<h1 id="quick-bill-header">Quick Bill Payment</h1>
		<form action="QuickBillPaymentServlet" method="post">
			<label id="Ca-Number-label">CA Number</label>
			<div class="input-group">
				<input type="number" id="ca-number" name="ca-number"
					placeholder="Enter Consumer Number" required maxlength="8"> <a
					id="sample-bill" onclick="showPopup()">Sample Bill</a>
			</div>

			<div class="captcha-group">
				<input type="checkbox" id="captcha" name="captcha" required>
				<label id="captcha" for="captcha">I'm not a robot</label>
			</div>
			<br>

			<button id="submit-button" type="submit">Proceed</button>
			<div class="centered-text">
				<span>${error}</span>
			</div>
			<% session.removeAttribute("error"); %>
		</form>
	</div>
	
	<!-- Popup -->
	<div class="popup-overlay" id="popup" onclick="hidePopup()">
		<div class="popup-content">
			<img src="images/sample-bill.jpg" width="600" height="600"
				alt="Sample Bill">
		</div>
	</div>
	
</body>
</html>
