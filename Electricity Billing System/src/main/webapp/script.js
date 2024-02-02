// Function to show the popup
function showPopup() {
	document.getElementById("popup").style.display = "flex";
}

// Function to hide the popup
function hidePopup() {
	document.getElementById("popup").style.display = "none";
}


// Function to allow only 2 digit after decimal
function checkInput(input) {
	if (input.value.length > input.maxLength) {
		input.value = input.value.slice(0, input.maxLength);
		input.value = parseFloat(input.value).toFixed(2);
	}
}

// Function to calculate bill amount
function calculateBillAmount() {
	let costMap = window.costMap;

	// Accessing values from the costMap
	let unitsZeroToHundred = costMap.get('unitsZeroToHundred');
	let unitsOneHundredOneToThreeHundred = costMap.get('unitsOneHundredOneToThreeHundred');
	let unitsThreeHundredOneToFiveHundred = costMap.get('unitsThreeHundredOneToFiveHundred');
	let unitsFiveHundredOneAndAbove = costMap.get('unitsFiveHundredOneAndAbove');

	console.log("Cost : [" + unitsZeroToHundred + "],[" + unitsOneHundredOneToThreeHundred + "]," +
		"[" + unitsThreeHundredOneToFiveHundred + "],[" + unitsFiveHundredOneAndAbove + "]");

	var curReading = parseInt(document.getElementsByName('cur-reading')[0].value);
	var prevReading = parseInt(document.getElementsByName('prev-reading')[0].value);

	// Calculate the total units
	var units = curReading - prevReading;

	// Calculate the bill amount
	var billAmount = 0;
	var finalAmount = 0;
	var tempUnits = units;

	while (tempUnits > 0) {
		if (tempUnits <= 100) {
			billAmount += tempUnits * unitsZeroToHundred;
			tempUnits = 0;
		} else if (tempUnits <= 300) {
			billAmount += 100 * unitsZeroToHundred + (tempUnits - 100) * unitsOneHundredOneToThreeHundred;
			tempUnits = 0;
		} else if (tempUnits <= 500) {
			billAmount += 100 * unitsZeroToHundred + 200 * unitsOneHundredOneToThreeHundred + (tempUnits - 300) * unitsThreeHundredOneToFiveHundred;
			tempUnits = 0;
		} else {
			billAmount += 100 * unitsZeroToHundred + 200 * unitsOneHundredOneToThreeHundred + 200 * unitsThreeHundredOneToFiveHundred + (tempUnits - 500) * unitsFiveHundredOneAndAbove;
			tempUnits = 0;
		}
	}
	console.log("current amount : ", billAmount);
	var prevBalance = parseInt(document.getElementsByName('prev-balance')[0].value);
	if (prevBalance) {
		console.log("prev bal : ", prevBalance);
		finalAmount = billAmount + prevBalance;
	} else {
		finalAmount = billAmount;
	}
	console.log("finalAmount : ", finalAmount);

	document.getElementById('units').value = units;//.toFixed(0);
	document.getElementById('current-amount').value = billAmount.toFixed(2);
	document.getElementById('final-amount').value = finalAmount.toFixed(2);
}

// validate dueDate
function validateDueDate() {
	var passwordInput = document.getElementById("password");
	var password = passwordInput.value;

	if (password.length < 8) {
		alert("Password must be at least 8 characters long");
		return false;
	}

	return true;
}

function toggleRows() {
	let hiddenRows = document.getElementsByClassName("hidden-row");
	let showMoreLabel = document.getElementById("view-more-label");

	for (let i = 0; i < hiddenRows.length; i++) {
		hiddenRows[i].style.display = (hiddenRows[i].style.display === "none") ? "" : "none";
	}

	// Toggle the label based on the visibility of hidden rows
	showMoreLabel.textContent = (hiddenRows[0].style.display !== "none") ? "View Less" : "View More";
}

// function to call RemoveDataServlet
function callServlet(key, value) {
	fetch("RemoveDataServlet?key=" + key + "&value=" + value)
		.then(response => {
			if (response.ok) {
				console.log("response is ok..");
				location.reload(true);
			}
		})
		.catch(error => {
			console.error('Exception occurs : ', error);
		});
}

// search function
function searchData(columnIdx1, columnIdx2, tableId) {
	console.log("search function is called");
	console.log("numericIdx : " + columnIdx1 + " & alphabetIdx : " + columnIdx2 + " & tableId : " + tableId);

	let input, type, table, tr, td, i, txtValue;
	input = document.getElementById("searchText").value;
	table = document.getElementById(tableId);
	tr = table.getElementsByTagName("tr");

	let viewMoreRow = document.getElementById("view-more-row");
	if (!input) {
		console.log("no-input")

		for (i = 0; i < tr.length; i++) {
			tr[i].style.display = "";
		}
		viewMoreRow.style.display = "";
		toggleRows()
		return;
	}
	if (/^\d+$/.test(input)) {
		type = "numeric";
		console.log("type is numeric");
	} else {
		type = "alphabetic";
		console.log("type is alphabetic");
	}

	// Loop through all table rows, and hide those that don't match the search query
	for (i = 0; i < tr.length; i++) {
		if (type === "numeric") {
			td = tr[i].getElementsByTagName("td")[columnIdx1]; // index of column in table
		} else {
			td = tr[i].getElementsByTagName("td")[columnIdx2]; // index of column in table
		}

		if (td) {
			txtValue = td.textContent || td.innerText;
			if (txtValue.toUpperCase().indexOf(input.toUpperCase()) > -1) {
				tr[i].style.display = "";
				console.log("ViewMoreRow1 : ", viewMoreRow);
				if (viewMoreRow)
					viewMoreRow.style.display = "";
			} else {
				tr[i].style.display = "none";
				console.log("ViewMoreRow2 : ", viewMoreRow);
				if (viewMoreRow)
					viewMoreRow.style.display = "none";
			}
		}
	}
}




