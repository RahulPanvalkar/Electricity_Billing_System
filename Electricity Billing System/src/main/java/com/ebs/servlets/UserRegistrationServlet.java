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

@WebServlet("/UserRegistrationServlet")
public class UserRegistrationServlet extends HttpServlet { 
	private static final long serialVersionUID = 1L;
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String fullName = request.getParameter("full-name");
		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		String mobile = request.getParameter("mobile");
		String address = request.getParameter("address");
		
		Consumer user = new Consumer("",fullName,mobile,email,pass,address);
		
		HttpSession session = request.getSession();
		
		Connection con = DBConnectionManager.getConnection();
		ConsumerDao consumerDao = new ConsumerDao(con);
		boolean isAlreadyPresent = consumerDao.checkEmailAndMobile(email, mobile);
		if (isAlreadyPresent) {
			session.setAttribute("error", "Email Id or Mobile No already registered!");
			response.sendRedirect("UserRegistration.jsp");
			return;
		}
		
		boolean isAdded = consumerDao.addConsumer(user);
		if(isAdded){ 
			session.setAttribute("error","User Registration Successfull" );
			response.sendRedirect("UserRegistration.jsp");
		} else {
			session.setAttribute("error", "Error Occurred, please try again");
			response.sendRedirect("UserRegistration.jsp");
		}
		
	}
}
