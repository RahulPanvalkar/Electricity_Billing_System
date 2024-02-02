<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.ebs.entities.Admin"%>
<%@page import="com.ebs.entities.Consumer"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Consumers</title>
<style>
#active-customer {
	background: #b6c7d4;
	transition: .5s;
}

.container {
	max-width: 900px;
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

<body>
  <%@ include file="admin-dashboard-navbar.jsp"%>

  <div class="container">
    
    <h1>View Consumers</h1>

    <div class="search-box">
     	<input type="text" id="searchText" placeholder="Search by ConsumerNum / Name" style="width:250px" oninput="searchData(0, 1, 'consumerTable')" />
    </div>

    <table border="1" id="consumerTable">
			<tr>
				<th>Consumer Number</th>
				<th>Full Name</th>
				<th>Email Id</th>
				<th>Mobile No</th>
				<th>Address</th>
				<th>Action</th>
			</tr>
      
      <c:if test="${empty consumers}">
        <tr>
          <td colspan="10"><span class="no-data-row">No Data Available for now</span></td>
        </tr>
      </c:if>
      
      <c:set var="count" value="0" />
      <c:set var="showRemaining" value="false" />
      
      <c:forEach var="consumer" items="${consumers}">
        <c:choose>
          <c:when test="${count < 5}">
            <!-- Display the first 5 consumers -->
            <tr>
              <td><c:out value="${consumer.consumerNum}" /></td>
              <td><c:out value="${consumer.consumerName}" /></td>
              <td><c:out value="${consumer.email}" /></td>
              <td><c:out value="${consumer.mobileNo}" /></td>
              <td><c:out value="${consumer.address}" /></td>
             <td><a href="#" onclick="removeConsumer('${consumer.consumerNum.toString()}', '${consumer.consumerName}')">Remove</a></td>
            </tr>
            <c:set var="count" value="${count + 1}" />
          </c:when>
          <c:otherwise>
            <!-- If more than 5 consumers, set flag to show remaining -->
            <c:set var="showRemaining" value="true" />
            <tr class="hidden-row" style="display: none;">
              <td><c:out value="${consumer.consumerNum}" /></td>
              <td><c:out value="${consumer.consumerName}" /></td>
              <td><c:out value="${consumer.email}" /></td>
              <td><c:out value="${consumer.mobileNo}" /></td>
              <td><c:out value="${consumer.address}" /></td>
              <td><a href="#" onclick="removeConsumer('${consumer.consumerNum.toString()}', '${consumer.consumerName}')">Remove</a></td>
            </tr>
          </c:otherwise>
        </c:choose>
      </c:forEach>

      <c:if test="${showRemaining}">
        <tr id="view-more-row">
          <td colspan="10"><label id="view-more-label" onclick="toggleRows()">View More</label></td>
        </tr>
      </c:if>
      
    </table>
  </div>

<script>

	function removeConsumer(consumerNum, consumerName) {
		console.log("inside removeConsumer..");
		let confirmValue = confirm("Are you sure ? All the details of " + consumerName + " will be permanantly deleted");
		console.log("confirmValue : " + confirmValue);
		if (confirmValue) {
			callServlet("consumerNo", consumerNum);
		}
	}

</script>


</body>
</html>