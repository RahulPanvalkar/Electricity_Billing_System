<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.ebs.entities.Admin"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Dashboard</title>
<style type="text/css">
a.active-home {
	background: #b6c7d4;
	transition: .5s;
}

.links-section {
			display: flex;
			justify-content: center;
			margin-top: 20px;
		}

		.link {
			margin: 150px 10px;
			width: 200px;
			height: 150px;
			background-color: #f0f0f0;
			border: 1px solid #ccc;
			border-radius: 4px;
			display: flex;
			align-items: center;
			justify-content: center;
			border-radius: 20px;
		}

		.link a {
			text-decoration: none;
			color: #000;
			font-weight: bold;
			font-size: 20px;
			padding: 20px;
			border-radius: 20px;
		}

		.link a:hover {
			background-color: #ccc;
		}
		
</style>
</head>

<body>
	
	<!-- navbar -->
	<%@ include file="admin-dashboard-navbar.jsp"%>

	<!-- Links section -->
	<div class="links-section">
		<div class="link">
			<a href="AdminDashboardServlet?link=view-consumers">View Consumers</a>
		</div>
		<div class="link">
			<a href="AdminDashboardServlet?link=view-connections">View Connections</a>
		</div>
		<div class="link">
			<a href="AdminDashboardServlet?link=view-bills">View Bills</a>
		</div>
	</div>

</body>
</html>
