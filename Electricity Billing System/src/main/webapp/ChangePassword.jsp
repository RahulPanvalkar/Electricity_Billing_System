<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style>
#active-profile {
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
	margin: 40px 500px;
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

.form-group input[type="text"],
.form-group input[type="password"] {
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
	<c:choose>
    <c:when test="${consumer != null}">
        <%@ include file="user-dashboard-navbar.jsp" %>
    </c:when>
    <c:when test="${admin != null}">
        <%@ include file="admin-dashboard-navbar.jsp" %>
    </c:when>
    <c:otherwise>
        <%@ include file="default-navbar.jsp"%> 
    </c:otherwise>
	</c:choose>
	
	<div class="container">

		<form action="ChangePasswordServlet" method="post">
		<c:if test="${param.reqType == 'Reset'}">
		    <title>Reset Password</title>
		    <h1>Reset Password</h1>
		</c:if>
		<c:if test="${param.reqType == 'Change'}">
    		<title>Change Password</title>
    		<h1>Change Password</h1>
		    <div class="form-group">
		        <input type="password" id="old-password" name="old-password"
		            placeholder="Enter Old Password" required maxlength="20">
		    </div>
		</c:if>

			<div class="form-group">
				<input type="password" id="password1" name="password1"
					placeholder="Enter New Password" required maxlength="20">
			</div>
			
			<div class="form-group">
				<input type="password" id="password2" name="password2" 
				onBlur="validatePasswords()" placeholder="Re-Enter Password" required maxlength="20">
			</div>
			<div style="margin-bottom: 20px; margin-top: -10px">
			<input type="checkbox" id="showPassword" onClick="showEnteredPassword()" >
			<label for="showPassword">Show Passwords</label>
			</div>
			<div class="form-group">
				<button type="submit" onClick="return validatePasswords()">Reset</button>
			</div>
			<input type="hidden" name="reqType" value="${param.reqType}">
			<c:choose>
				<c:when test="${error == 'Password successfully updated'}">
					<div class="message">
						<span>${error}</span>
					</div>
				</c:when>
				<c:otherwise>
					<div class="error">
						<span id="errorSpan">${error}</span>
					</div>
				</c:otherwise>
			</c:choose>
			
		</form>
	</div>

	<script>
	//function to validate passwords
		function validatePasswords() {
		    console.log("Inside validatePasswords..");

		    let pass1 = document.getElementById("password1").value;
		    let pass2 = document.getElementById("password2").value;
		    let oldPass = document.getElementById("old-password").value;
		    console.log("OldPass: " + oldPass + ", pass1: " + pass1 + ", pass2: " + pass2);

		    let errorSpan = document.getElementById("errorSpan");
		    let reqType = "${param.reqType}";
		    console.log("reqType: " + reqType);

		    let returnValue = false;

		    if (reqType === "Change") {
		    	var regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()_+])[A-Za-z\d!@#$%^&*()_+]{8,}$/;
		        if (!oldPass || !pass1 || !pass2 || pass1.length < 8 || pass2.length < 8) {
		            errorSpan.innerHTML = "Password must be at least 8 characters long";
		        } else if (pass1 !== pass2) {
		            errorSpan.innerHTML = "New passwords do not match!";
		        } else if (oldPass === pass1) {
		            errorSpan.innerHTML = "Old password and new password cannot be the same!";
		        } else if (!regex.test(pass1)){
					errorSpan.innerHTML = "";
					alert("Password must contain at least one capital letter, one small letter, one number, and one special character (!@#$%^&*()_+).");
				} else {
		            returnValue = true; // Validation passed
		        }
		    } else if (reqType === "Reset") {
		    	var regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()_+])[A-Za-z\d!@#$%^&*()_+]{8,}$/;
		        if (!pass1 || !pass2 || pass1.length < 8 || pass2.length < 8) {
		            errorSpan.innerHTML = "Password must be at least 8 characters long";
		        } else if (pass1 !== pass2) {
		            errorSpan.innerHTML = "New passwords do not match!";
		        } else if (!regex.test(pass1)){
					errorSpan.innerHTML = "";
					alert("Password must contain at least one capital letter, one small letter, one number, and one special character (!@#$%^&*()_+).");
				} else {
		            returnValue = true; // Validation passed
		        }
		    }
		    console.log("returnValue: " + returnValue);
		    return returnValue;
		}

		//function to show password
		function showEnteredPassword() {
			let pass1 = document.getElementById("password1");
			let pass2 = document.getElementById("password2");
			let oldPass = document.getElementById("old-password");
			var showPasswordCheckbox = document.getElementById("showPassword");

			console.log("isChecked : ", showPasswordCheckbox.checked);

			if (showPasswordCheckbox.checked) {
				pass1.type = "text";
				pass2.type = "text";
				oldPass.type = "text";
			} else {
				pass1.type = "password";
				pass2.type = "password";
				oldPass.type = "password";
			}
		}
	</script>

	<script>
	    var alertMessage = "<%= session.getAttribute("error") %>";
    	console.log("AlertMessage : "+alertMessage);
	    if (alertMessage && alertMessage !== "null") {
	        alert(alertMessage); 
	        window.location.href = "AdminDashboardServlet?link=logout"; 
	    }
	</script>

</body>
</html>



