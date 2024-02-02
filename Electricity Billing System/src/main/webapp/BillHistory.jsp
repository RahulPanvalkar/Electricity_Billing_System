<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bill History</title>
<style>
#active-bill {
	background: #b6c7d4;
	transition: .5s;
}

.container {
	max-width: 1100px;
	margin: 60px auto;
	padding: 20px;
	background-color: #fff;
	border-radius: 5px;
}

.container h1 {
	text-align: center;
	margin-bottom: 20px;
}

table {
	width: 100%;
	border-collapse: collapse;
}

th, td {
	padding: 6px;
	text-align: center;
}

th {
	background-color: #ccc;
}

.no-data-row {
	color: #DD2C00;
	text-align: center;
	margin: 10px;
}

tr:nth-child(even) {
	background-color: #f9f9f9;
}

.search-box {
	margin-bottom: 20px;
	text-align: right;
}

.search-box input[type="text"] {
	padding: 6px 12px;
	border: 1px solid #ccc;
	border-radius: 4px;
}

.search-box button {
	padding: 6px 12px;
	border-radius: 4px;
	background-color: #4CAF50;
	color: white;
	border: none;
	cursor: pointer;
}

.search-box button:hover {
	background-color: #45a049;
}

#view-more-label:hover{
    border: 1px solid #ccc;
    border-radius: 4px;
    color: #fff;
    padding: 2px;
    background-color: grey;
}
</style>

<script src="script.js"></script>

</head>
<body>
	<%@ include file="user-dashboard-navbar.jsp"%>

	<div class="container">
		<h1>Bill History</h1>

		<div class="search-box">
			<input type="text" id="searchText" placeholder="Search by BillNum / ConsumerNum" style="width:250px" oninput="searchData(1, 0, 'billTable')"/>
		</div>

		<table border="1" id="billTable">
			<tr>
				<th>Bill No</th>
				<th>Consumer Number</th>
				<th>Meter Number</th>
				<th>Bill For Month</th>
				<th>Current Reading</th>
				<th>Previous Reading</th>
				<th>Total Units</th>	
				<th>Previous Balance</th>
				<th>Current Amount</th>
				<th>Total Amount</th>
				<th>Due Date</th>
				<th>Payment Date</th>
				<th>Status</th>
			</tr>

			<c:if test="${empty allBills}">
				<tr>
					<td colspan="12"><span class="no-data-row">No Data Available</span></td>
				</tr>
			</c:if>

			<c:set var="count" value="0" />
			<c:set var="showRemaining" value="false" />

			<c:forEach var="bill" items="${allBills}" varStatus="loop">
				<c:choose>
					<c:when test="${count < 5}">
						<tr>
							<td><c:out value="${bill.billNum}" /></td>
							<td><c:out value="${bill.consumerNum}" /></td>
							<td><c:out value="${bill.meterNum}" /></td>
							<td><c:out value="${bill.month}" /></td>
							<td><c:out value="${bill.currentReading}" /></td>
							<td><c:out value="${bill.previousReading}" /></td>
							<td><c:out value="${bill.totalUnits}" /></td>
							<td><c:out value="${bill.prevBalance}" /></td>
							<td><c:out value="${bill.currentAmount}" /></td>
							<td><c:out value="${bill.totalAmount}" /></td>
							<td width="80"><fmt:formatDate value="${bill.dueDate}" pattern="dd/MM/yy" /></td>
							<td><fmt:formatDate value="${bill.paymentDate}" pattern="dd-MM-yy" /></td>
							<td><c:out value="${bill.status}" /></td>
						</tr>
						 <c:set var="count" value="${count + 1}" />
					</c:when>
					<c:otherwise>
						<c:set var="showRemaining" value="true" />
						<tr class="hidden-row" style="display: none;">
							<td><c:out value="${bill.billNum}" /></td>
							<td><c:out value="${bill.consumerNum}" /></td>
							<td><c:out value="${bill.meterNum}" /></td>
							<td><c:out value="${bill.month}" /></td>
							<td><c:out value="${bill.currentReading}" /></td>
							<td><c:out value="${bill.previousReading}" /></td>
							<td><c:out value="${bill.prevBalance}" /></td>
							<td><c:out value="${bill.totalUnits}" /></td>
							<td><c:out value="${bill.currentAmount}" /></td>
							<td><c:out value="${bill.totalAmount}" /></td>
							<td width="80"><fmt:formatDate value="${bill.dueDate}" pattern="dd/MM/yy" /></td>
							<td><fmt:formatDate value="${bill.paymentDate}" pattern="dd/MM/yy" /></td>
							<td><c:out value="${bill.status}" /></td>
						</tr>
					</c:otherwise>
				</c:choose>
			</c:forEach>

			<c:if test="${showRemaining}">
				<tr id="view-more-row">
					<td colspan="16"><label id="view-more-label" onclick="toggleRows()">View More</label></td>
				</tr>
			</c:if>
		</table>
	</div>
	
</body>
</html>
