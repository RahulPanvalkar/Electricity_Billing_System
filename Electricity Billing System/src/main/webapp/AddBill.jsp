<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
	
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@page import="com.ebs.entities.CostPerUnit"%>
	<%@page import="java.util.Map"%>
	<%@page import="java.util.HashMap"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Bill</title>
<style>
#active-bill {
	background: #b6c7d4;
	transition: .5s;
}

body {
	font-family: Arial, sans-serif;
	background-color: #f2f2f2;
}

.container {
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
	.container input[type="date"], .containter label, .container select {
	width: 100%;
	padding: 10px;
	margin-bottom: 20px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
}

.container button[type="submit"] {
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
	font-size: 14px;
	color: #000;
	text-align: center;
	margin-top: 20px;
}

option {
	 line-height: 2;
}
</style>

<link rel="stylesheet" href="style2.css">

 <script src="script.js"></script>

</head>
<body>

	<!-- navbar-->
	<%@ include file="admin-dashboard-navbar.jsp"%>

	<div class="container">
	<h1>Add New Bill</h1>
	<form action="AddBillServlet" method="post">

		<label>Consumer Number</label>
		<input type="text" name="cons-number" id="consumerNo" placeholder="Enter Consumer Number" required 
		onblur="validateConsumerNo()" maxlength="8">
		
		<label>Meter Number</label>
		<select name="meter-number" id="meterNumberDropdown" onChange="setPreviousBalance()" required ></select>

		<label>Bill For Month</label>
		<select id="bill-month" name="month" required>
			<option value="January">January</option>
			<option value="February">February</option>
			<option value="March">March</option>
			<option value="April">April</option>
			<option value="May">May</option>
			<option value="June">June</option>
			<option value="July">July</option>
			<option value="August">August</option>
			<option value="September">September</option>
			<option value="October">October</option>
			<option value="November">November</option>
			<option value="December">December</option>
		</select>

		<label>Current Reading</label>
		<input type="number" name="cur-reading" placeholder="Enter Current Reading" required   maxlength="8">

		<label>Previous Reading</label>
		<input type="number" name="prev-reading" placeholder="Enter Previous Reading" required   maxlength="8">

		<label>Total Unit</label>
		<input type="number" id="units" name="units" placeholder="Total Units" required   maxlength="8" readonly>
		
		<label>Previous Balance</label>
		<input type="number" id="prev-balance" name="prev-balance" placeholder="Privious Balance" readonly
		step="0.01" required oninput="checkInput(this)" maxlength="10">

		<input type="hidden"  id="current-amount"  name="current-amount">

		<label>Final Amount</label>
		<input type="number" id="final-amount" name="final-amount" placeholder="Final Amount" 
		step="0.01" required oninput="checkInput(this)" maxlength="10" readonly>

		<label>Due Date</label>
		<input type="date" name="due-date" id="due-date" placeholder="Due Date" required>

		<button type="submit" >Submit</button>

		<c:choose>
			<c:when test="${error == 'Bill generated successfully'}">
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

<%
    CostPerUnit cost = (CostPerUnit) session.getAttribute("cost");

    // Create a HashMap to store the cost values
    Map<String, Double> costMap = new HashMap<>();
    costMap.put("unitsZeroToHundred", cost.getUnitsZeroToHundred());
    costMap.put("unitsOneHundredOneToThreeHundred", cost.getUnitsOneHundredOneToThreeHundred());
    costMap.put("unitsThreeHundredOneToFiveHundred", cost.getUnitsThreeHundredOneToFiveHundred());
    costMap.put("unitsFiveHundredOneAndAbove", cost.getUnitsFiveHundredOneAndAbove());
%>
	
<script>
    // Get the current date
    let currentDate = new Date();
     console.log("currentDate : ",currentDate);

   	let currentMonthIndex = currentDate.getMonth();
   	console.log("currentMonthIndex : ",currentMonthIndex);
    // Set the selected attribute for the corresponding option
    document.getElementById('bill-month').options[currentMonthIndex].selected = true;
    
    let onlyCurrentDate = currentDate.toISOString().split('T')[0];
	console.log("onlyCurrentDate : ",onlyCurrentDate);
	
    // Set the minimum attribute of the due date input to the current date
    let dueDate = document.getElementById('due-date');
    dueDate.setAttribute('min', onlyCurrentDate);
    dueDate.value = onlyCurrentDate;
    
</script>

<script>
	
	//Attach the costMap variable to the window object
	window.costMap = new Map();
	
	// Populate the costMap with values from the HashMap
	<% for (Map.Entry<String, Double> entry : costMap.entrySet()) { %>
	    window.costMap.set('<%= entry.getKey() %>', <%= entry.getValue() %>);
	<% } %>
	
	// Attach the calculateBillAmount function to the input fields' input event
	document.getElementsByName('cur-reading')[0].addEventListener('blur', calculateBillAmount);
	document.getElementsByName('prev-reading')[0].addEventListener('blur', calculateBillAmount);
	document.getElementsByName('prev-balance')[0].addEventListener('blur', calculateBillAmount);
	document.getElementsByName('meter-number')[0].addEventListener('blur', calculateBillAmount);
</script>

<script>
let paymentData;
// function to validate consumer no and set meterNo based on consumerNo
function validateConsumerNo() {
    let consumerNo = document.getElementById("consumerNo").value;
    console.log("Inside validateConsumerNo => consumerNo : ", consumerNo);

    if (!consumerNo || consumerNo.length < 8) {
        alert("Invalid consumer number!");
        return;
    }

    let regex = /^\d+$/;

    if (!regex.test(consumerNo)) {
        alert("Invalid consumer no!");
        return;
    }

    fetch("AddBillServlet?consumerNo=" + consumerNo)
        .then(response => {
            if (!response.ok) {
                throw new Error("HTTP error! Status: " + response.status);
            }
            return response.json();
        })
        .then(data => {
            console.log("Data from server:", data);

            const dropdown = document.getElementById("meterNumberDropdown");
            dropdown.innerHTML = "";

            if (Object.keys(data).length > 0) {
            	paymentData = data;
                for (const meterNumber in data) {
                	console.log("meterNo : ",meterNumber);
                    const option = document.createElement("option");
                    option.text = meterNumber;
                    option.value = meterNumber;
                    dropdown.add(option);
                }
                // setting previous balance value
                let firstEntry = Object.entries(data)[0];
                let prevBalance = document.getElementById("prev-balance");
               	prevBalance.value = firstEntry[1];
            } else {
                alert("Invalid consumer number!");
            }

        })
        .catch(error => {
            console.error("Error:", error);
            alert("An error occurred while fetching data from the server.");
        });
}

function setPreviousBalance() {
    let data = paymentData;

    if (Object.keys(data).length > 0) {
        let meterNoFieldValue = document.getElementById("meterNumberDropdown").value;
        console.log("meterNoValue : ", meterNoFieldValue);
        let prevBalance = document.getElementById("prev-balance");
        let found = false;

        for (const meterNumber in data) {
            const value = data[meterNumber];
            console.log("Key:", meterNumber, "Value:", value);

            if (meterNumber === meterNoFieldValue) {
                prevBalance.value = value;
                found = true;
                break; 
            }
        }

        if (!found) {
            console.log("No previous balance found for the selected meterNo.");
            prevBalance.value = 0.0;  
        }
    } else {
        console.log("Invalid Data!");
    }
}

</script>

</body>
</html>
