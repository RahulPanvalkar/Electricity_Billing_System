<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Connection</title>
<style>
#active-connection {
	background: #b6c7d4;
	transition: .5s;
}

body {
	font-family: Arial, sans-serif;
	background-color: #f2f2f2;
}

.container {
	min-width: 400px;
	margin: 20px 450px;
	padding: 40px;
	background-color: #fff;
	border-radius: 50px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.container h1 {
	text-align: center;
	margin-bottom: 30px;
}

.container input[type="text"], .container input[type="email"],
	.container input[type="password"], .container input[type="number"], 
	.container input[type="date"], .containter label, .container select{
	width: 100%; 
	padding: 10px;
	margin-bottom: 20px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
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

.container button:hover {
	background-color: #00BCD4;
}

</style>

<link rel="stylesheet" href="style2.css">

 <script src="script.js"></script>

</head>
<body>

	<!-- navbar-->
	<%@ include file="admin-dashboard-navbar.jsp"%>

	<div class="container">
		<h1>Add New Connection</h1>
		<form id ="addConnectionForm" action="AddConnectionServlet" method="post">
			
			<label for="connectionType">Consumer Number</label>
			<input type="text" id="consumerNo" name="ca-number" placeholder="Enter consumer number here"	required
					onblur="validateConsumerNo()"   maxlength="8">
			<label for="connectionType">Meter Number</label>
			<input type="text" id="meter-number" name="meter-number" placeholder="Enter meter number here"	required maxlength="8" >
			<label for="connectionType">Full Name</label>
			<input type="text" name="name" placeholder="Enter name here" required maxlength="50">
			<label for="connectionType">Mobile Number</label>
			<input type="number" name="mob-number" placeholder="Enter mobile number here"	required
					  maxlength="10">
			<label for="connectionType">Address</label> 
			<input type="text"	name="address" placeholder="Ex : 123, Main Apt, Andheri West, Mumbai, 400001" required
					  maxlength="100">
			<label for="connectionType">Connection Start Date:</label>
			<input type="date" id="start-date" name="start-date" placeholder="Connection Start Date" required> 
			<label for="connectionType">Connection Type:</label>
        	<select id="connectionType" name="connectionType">
            <option value="Residential">Residential</option>
            <option value="Commercial">Commercial</option>
        	</select>
			
			<button type="button" onClick="validateMeterNo()">Add Connection</button>
			
			<c:choose>
				<c:when test="${error == 'Connection Created Successfully'}">
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
    // Get the current date
    let currentDate = new Date();
    currentDate = currentDate.toISOString().split('T')[0];
	console.log("currentDate : ",currentDate);
	
    // Set the maximum attribute of the start date input to the current date
    let startDate = document.getElementById('start-date'); 
    startDate.setAttribute('max', currentDate);
    startDate.value = currentDate;
    console.log("startDateValue : "+startDate.value)
    
    // function to validate consumer no and set name,mobNumber and address based on consumerNo
    function validateConsumerNo() {
    	if(meterNoAlertShown){
    		meterNoAlertShown = false;
    		return;
    	}
    	
        let consumerNo = document.getElementById("consumerNo").value;
        console.log("Inside validateConsumerNo => consumerNo : ", consumerNo);
        if (!consumerNo || consumerNo.length < 8) {
        	alert("Invalid consumer number!");
            let inputElements = document.getElementsByTagName("input");
            for (let i = 0; i < inputElements.length; i++) {
                inputElements[i].value = "";
            }
            return;
        }

        let regex = /^\d+$/;
        if (!regex.test(consumerNo)) {
            alert("Invalid consumer no!");
            return;
        }
        
        fetch("AddConnectionServlet?consumerNo=" + consumerNo)
        .then(response => {
            if (!response.ok) {
                throw new Error("HTTP error! Status: "+ response.status);
            }
            return response.json();
        })
        .then(data => {
            console.log("Data from server:", data);
            
            if(JSON.stringify(data) !== '{}'){
            let name = document.getElementsByName("name")[0];
            let mob = document.getElementsByName("mob-number")[0];
            let address = document.getElementsByName("address")[0];
            
            name.value = data.Full_Name;
            name.readOnly=true;
            mob.value = data.Mob_Number;
            mob.readOnly=true;
            address.value = data.Address;
            address.readOnly=true;
            
            }else{
            	alert("Invalid consumer number!")
            }
        })
        .catch(error => {
            console.error("Error:", error);
        });
        
    }
    
let meterNoAlertShown = false; // Flag to track if the meterNo alert has been shown
 // function to validate meter number 
 function validateMeterNo() {
     let meterNo = document.getElementById("meter-number").value;
     console.log("Inside validateMeterNo => meterNo : ", meterNo);

     if (!meterNo || meterNo.length < 8) {
         meterNoAlertShown = true;
         alert("Invalid meter no!");
         return;
     }

     let alphabetRegex = /^[a-zA-Z]+$/;
     let numericalRegex = /^\d+$/;
     let firstChar = meterNo.charAt(0);
     console.log("firstChar : ", firstChar);
     let remainingChars = meterNo.substring(1);
     console.log("remainingChars : ", remainingChars);

     if (!(alphabetRegex.test(firstChar) && numericalRegex.test(remainingChars))) {
    	 meterNoAlertShown = true; 
         alert("Invalid meter no!");
     } else {
         document.getElementById("addConnectionForm").submit();
     }
 }

    
</script>

</body>

  </html>
