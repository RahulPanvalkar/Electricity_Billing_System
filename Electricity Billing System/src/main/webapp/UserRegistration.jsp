<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Registration</title>
<style>
#active-login {
	background: #b6c7d4;
	transition: .5s;
}

body {
	font-family: Arial, sans-serif;
	background-color: #f2f2f2;
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

 .container input[type="email"],.container input[type="password"], 
 .container input[type="number"],.container input[type="text"] {
	width: 100%;
	padding: 10px;
	margin-bottom: 20px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
}

 .container input[type="checkbox"]{
 	align-self: flex-start;
 }

.container button {
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

.container p {
	font-size: 16px;
	color: #000;
	text-align: center;
	margin-top: 20px;
}

</style>

<link rel="stylesheet" href="style2.css">

 <script src="script.js"></script>
<script>
    var alertMessage = "<%= session.getAttribute("error") %>";
   	console.log("AlertMessage : "+alertMessage);
    if (alertMessage && alertMessage !== "null") {
    	if(alertMessage === "User Registration Successfull"){
	        alert(alertMessage); 
	        window.location.href = "UserLogin.jsp"; 
    	}
    }
	</script>
</head>
<body>

	<!-- navbar-->
	<%@ include file="Normal_Navbar.jsp"%>

	<div class="container">
		<h1>User Registration</h1>
		<form id= "myForm" action="UserRegistrationServlet" method="post">

			<input type="text" name="full-name" placeholder="Full Name"
				required  maxlength="100"> 
			
			<input type="email" name="email" placeholder="Email Address" 
			required  maxlength="100">
			
			<input type="text"  name ="mobile" placeholder="Mobile Number" 
			required  maxlength="10">
			
			<input type="text"  placeholder="Address" name="address" 
			required  maxlength="200"> 
			
			<input	type="password"  name="password" placeholder="Password" onBlur="validatePassword()" 
			required  maxlength="20">
			
			<div style="margin-bottom: 20px; margin-top: -10px">
			<input type="checkbox" id="showPassword" onClick="showEnteredPassword()" >
			<label for="showPassword">Show Password</label>
			</div>
			
			<button type="submit" >Register</button>
			
			<c:choose>
				<c:when test="${error == 'User Registration Successfull'}">
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
		<p>
			Already have an account? <a href="UserLogin.jsp">Login here</a>
		</p>
	</div>
	
	<script>
	//function to show password
	function showEnteredPassword() {
	    var passwordField = document.getElementsByName("password")[0];
	    var showPasswordCheckbox = document.getElementById("showPassword");
		
	    console.log("isChecked : ",showPasswordCheckbox.checked);
		
	    if (showPasswordCheckbox.checked) {
	      passwordField.type = "text";
	    } else {
	      passwordField.type = "password";
	    }
	  }
	
	// function to validate password
	function validatePassword(){
		var password = document.getElementsByName("password")[0].value;
		
		if( !password || password.length < 8 ){
			alert("Password must be at least 8 characters long");
			return;
		} else {
			 var regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()_+])[A-Za-z\d!@#$%^&*()_+]{8,}$/;
	         if (!regex.test(password)) {
	                alert("Password must contain at least one capital letter, one small letter, one number, and one special character (!@#$%^&*()_+).");
	        } 
		}
	}
	</script>
	
</body>
</html>
