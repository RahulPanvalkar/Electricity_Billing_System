<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.ebs.entities.Bill"%>
<%@page import="com.ebs.entities.User"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pending Bills</title>
<style>
#active-bill{
	background: #b6c7d4;
	transition: .5s;
}
.container {
	max-width: 950px;
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
</style>
</head>
<body>

	<c:choose>
    <c:when test="${consumer != null}">
        <%@ include file="user-dashboard-navbar.jsp" %>
    </c:when>
    <c:otherwise>
        <%@ include file="default-navbar.jsp"%> 
    </c:otherwise>
	</c:choose>


	<div class="container">
		<h1>Pending Bills</h1>
		<table border="1">
			<tr>
				
				<th>Bill No</th>
				<th>Consumer Number</th>
				<th>Meter Number</th>
				<th>Bill For Month</th>
				<th>Total Units</th>
				<th>Current Bill Amount</th>
				<th>Previous Balance</th>
				<th>Total Amount</th>
				<th>Due Date</th>
				<th>Status</th>
				<th>Action</th>
				
			</tr>
			
			<c:choose>
			<c:when test="${currentBill == null}">
				<tr>
					<td colspan="12"><span class="no-data-row">Pending Bills Not Found</span></td>
				</tr>
			</c:when>
			
			<c:otherwise>
			<tr>
			
				<td><c:out value="${currentBill.billNum}" /></td>
				<td><c:out value="${currentBill.consumerNum}" /></td>
				<td><c:out value="${currentBill.meterNum}" /></td>
				<td><c:out value="${currentBill.month}" /></td>
				<td><c:out value="${currentBill.totalUnits}" /></td>
				<td><c:out value="${currentBill.currentAmount}" /></td>
				<td><c:out value="${currentBill.prevBalance}" /></td>
				<td><c:out value="${currentBill.totalAmount}" /></td>
				<td><c:out value="${currentBill.dueDate}" /></td>
				<td><c:out value="${currentBill.status}" /></td>
				<td><a href="PaymentPage.jsp?totalAmount=${currentBill.totalAmount}">Pay Now</a></td>
				
			</tr>
			</c:otherwise>
			</c:choose>
		</table>
	</div>
</body>
</html>
