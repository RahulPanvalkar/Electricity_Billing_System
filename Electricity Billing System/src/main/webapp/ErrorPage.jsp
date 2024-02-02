<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isErrorPage="true" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
        }

        .container {
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            color: #d43f3a;
            text-decoration: underline;
        }

        p {
            margin-top: 20px;
            font-size: 20px;
        }

        .back-btn {
            display: inline-block;
            padding: 7px 14px;
            background-color: #000;
            color: #fff;
            text-decoration: none;
            border-radius: 3px;
            margin-top: 10px;
            font-weight:bold;
        }
    </style>
</head>
<body>
    <%@ include file="default-navbar.jsp" %>
    
    <div class="container">
        <h1>Error</h1>

		 <c:if test="${not empty exception}">
		    <div class="container">
		        <p><strong>Exception:</strong> ${exception}</p>
		        <p><strong>Message:</strong> ${exception.message}</p>
		        <p><strong>Stack Trace:</strong></p>
		        <pre>${exception.stackTrace}</pre>
		    </div>
		</c:if>
		
		<c:if test="${empty exception}">
		    <div class="container">
		        <p>Something went wrong, please try again later.</p>
		    </div>
		</c:if>

        <p>
            <a href="javascript:history.back()" class="back-btn">Go Back</a>
        </p>
    </div>
</body>

</html>
