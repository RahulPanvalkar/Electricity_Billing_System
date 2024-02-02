<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Cost Per Unit</title>
<style>
a.active-update-cost {
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

.form-group1 {
	margin-bottom: 20px;
}

.form-group1 label {
	display: block;
	margin-bottom: 5px;
	font-size: 13px;
	font-weight: bold;
}

.form-group1 input[type="text"], .form-group1 input[type="number"] {
	width: 100%;
	padding: 10px;
	border-radius: 4px;
	border: 1px solid #ccc;
}

.form-group1 button {
	width: 100%;
	padding: 10px;
	background-color: #000000;
	border: none;
	color: #fff;
	font-weight: bold;
	cursor: pointer;
	border-radius: 4px;
}

.form-group1 button:hover {
	background-color: #00BCD4;
}

.form-group1 .text-center {
	text-align: center;
}
</style>

<link rel="stylesheet" href="style2.css">

<script src="script.js"></script>

</head>
<body>

	<!-- navbar-->
	<%@ include file="admin-dashboard-navbar.jsp"%>

	<script>
        function fetchData() {
        	fetch("UpdateCostServlet")
            .then(response => {
                if (!response.ok) {
                    throw new Error("HTTP error! Status: " + response.status);
                }
                return response.json();
            })
            .then(data => {
                console.log("Data from server:", data);

                if (data.length !== 0) {
                    let elements = document.getElementsByTagName("input");
                    for (let i = 0; i < elements.length; i++) {
                    	elements[i].placeholder = data[i];
                    }
                }
            })
            .catch(error => {
                console.error("Error:", error);
                alert("An error occurred while fetching data from the server.");
            });
        }

     // Call fetchData function when the page loads
        document.addEventListener('DOMContentLoaded', function() {
            fetchData();
        });
    </script>

	<div class="container">

		<h1>Update Cost</h1>
		<form action="UpdateCostServlet" method="post" id="updateCostForm">
			<div class="form-group1">
				<label for="units">Units 0-100</label> <input type="number"
					step="0.01" required oninput="checkInput(this)" maxlength="6"
					name="zero-to-hun">
			</div>
			<div class="form-group1">
				<label for="units">Units 101-300</label> <input type="number"
					step="0.01" required oninput="checkInput(this)" maxlength="6"
					name="hun-to-three">
			</div>
			<div class="form-group1">
				<label for="units">Units 301-500</label> <input type="number"
					step="0.01" required oninput="checkInput(this)" maxlength="6"
					name="three-to-five">
			</div>
			<div class="form-group1">
				<label for="units">Units 501 and above</label> <input type="number"
					step="0.01" required oninput="checkInput(this)" maxlength="6"
					name="greater-than-500">
			</div>
			<div class="form-group1">
				<button type="button" onClick="costValidation()">Update Cost</button>
			</div>

			<c:choose>
				<c:when test="${error == 'Cost Successfully Updated'}">
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
	</div>

	<script>
	
	function costValidation() {
	    console.log("inside costValidation...");
	    let elements = document.getElementsByTagName("input");
	    let isFieldEmpty = false;
	    let isCostChanged = false;
	    let issues = [];

	    for (let i = 0; i < elements.length; i++) {
	        let value = elements[i].value;
	        let placeholder = elements[i].placeholder;
	        console.log("value: " + value + " & placeholder: " + placeholder);
	        
	        if (!value) {
	            isFieldEmpty = true;
	        } else if (value !== placeholder) {
	            isCostChanged = true;
	        }
	    }

	    if (isFieldEmpty) {
	        issues.push("One or more fields is empty!");
	    }

	    if (isCostChanged) {
	        document.getElementById("updateCostForm").submit();
	    } else if (!isFieldEmpty) {
	        issues.push("No changes made in cost!");
	    }

	    if (issues.length > 0) {
	        alert(issues.join("\n"));
	    }
	}

	</script>
	
</body>
</html>