<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.ebs.entities.Admin"%>
<%@page import="com.ebs.entities.User"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profile</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f5f5f5;
}

.container {
	max-width: 450px;
	margin: 50px auto;
	padding: 20px;
	background-color: #fff;
	border-radius: 10px;
	text-align: center;
}

h1 {
	color: #337ab7;
	text-decoration: underline;
}

.profile-info {
	margin-top: 30px;
}

.profile-info h2 {
	color: #333;
}

.profile-info p {
	margin-top: 10px;
	color: #777;
}

.profile-info .label {
	font-weight: bold;
	font-size: 20px;
}

.profile-info .value {
	margin-left: 10px;
	font-size: 20px;
}

.profile-info .button {
	display: inline-block;
	padding: 10px 15px;
	background-color: #000;
	color: #fff;
	text-decoration: none;
	border-radius: 3px;
	margin-top: 40px;
	font-weight:bold;
}
</style>
</head>
<body>

	<!-- navbar-->
	<c:choose>
		<c:when test="${ consumer != null }">
			<c:import url="user-dashboard-navbar.jsp"/>
			<c:set var="userType" value="consumer" />
		</c:when>
		<c:when test="${admin != null }">
			<c:import url="admin-dashboard-navbar.jsp"/>
			<c:set var="userType" value="admin" />
		</c:when>
		<c:otherwise>
			<c:import url="default-navbar.jsp"/>
		</c:otherwise>
	</c:choose>  
	
	<c:out  value="${userType}"/>
	
	<div class="container">
		<h1>Profile</h1>
		<div class="profile-info">
			<h2>Personal Information</h2>
			<p>
				<span class="label">Name:</span> <span class="value"><c:out value="${user.fullName }" /></span>
			</p>
			
			<c:choose>
			<c:when test="${userType == 'consumer' }"> 
				<p>
				<span class="label">Consumer Number:</span> <span class="value"><c:out value="${user.consumerNum}" /></span>
				</p>
			</c:when>
			<c:otherwise>
				<p>
				<span class="label">Admin Id:</span> <span class="value"><c:out value="${user.adminId}" /></span>
				</p>
			</c:otherwise>
			</c:choose>

			<p>
				<span class="label">Phone:</span> <span class="value"><c:out value="${user.mob }" /></span>
			</p>
			<p>
				<span class="label">Email:</span> <span class="value"><c:out value="${user.email}" /></span>
			</p>
			
			<c:if test="${userType == 'admin' }">
			<p>
				<span class="label">Location:</span> <span class="value"><c:out value="${user.address}" /></span>
			</p>
			</c:if>
			
			<a href="ProfileServlet?link=${userType}" class="button">Edit Profile</a>
		</div>
	</div>
</body>
</html>
