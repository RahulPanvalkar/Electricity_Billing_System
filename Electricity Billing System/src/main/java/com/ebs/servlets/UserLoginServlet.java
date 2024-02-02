package com.ebs.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ebs.dao.ConsumerDao;
import com.ebs.entities.Consumer;
import com.ebs.utility.DBConnectionManager;

@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		String pass = request.getParameter("password");
		
		String credentialType;

		// Determine the credential type based on the input format (consumer number or email) 
		if (userId.matches("\\d+")) {
		    credentialType = "consumerNumber";
		} else {
		    credentialType = "email";
		} 
		System.out.println("CredentialType : "+credentialType);
		
		Connection con = DBConnectionManager.getConnection();
		ConsumerDao condao = new ConsumerDao(con);
		
	    Consumer consumer = condao.getConsumer(userId, pass,credentialType);
		
		HttpSession session = request.getSession();

		if (consumer != null) {
			session.setAttribute("consumer", consumer);
			response.sendRedirect("user-dashboard.jsp");
		} else {
			session.setAttribute("error", "Invalid UserID or Password"); 
			response.sendRedirect("UserLogin.jsp");
		}
		
	}
}
