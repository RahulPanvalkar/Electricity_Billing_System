package com.ebs.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ebs.dao.AdminDao;
import com.ebs.dao.ConsumerDao;
import com.ebs.entities.Admin;
import com.ebs.entities.Consumer;
import com.ebs.entities.User;
import com.ebs.utility.DBConnectionManager;

@WebServlet("/EditProfileServlet")
public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String fullName = request.getParameter("full-name");
		String email = request.getParameter("email");
		String mob = request.getParameter("mobile");
		
		System.out.println("fullName : "+fullName+"& email : "+email+" & mob : "+mob);
		HttpSession session = request.getSession();
		
		User updatedUser  = null;
		if (fullName != null && email != null  && mob != null) {
			updatedUser = (User) session.getAttribute("user");
			updatedUser .setFullName(fullName);
			updatedUser .setEmail(email);
			updatedUser .setMob(mob);
		}
		Connection con = DBConnectionManager.getConnection();
		
		String userType = request.getParameter("userType");
		System.out.println("userType : "+userType);
		
		if (userType.equals("admin")) {
			AdminDao aDao = new AdminDao(con);
			
			boolean hasUpdated = aDao.updatedAdminDetails(updatedUser );
			
			Admin admin = (Admin) session.getAttribute("admin"); 
			updatedUser  = aDao.getUser(admin);
			if (hasUpdated && updatedUser  != null) {
				session.setAttribute("user", updatedUser );
				session.setAttribute("error", "Profile has been updated");
				response.sendRedirect("EditProfile.jsp");
			} else {
				session.setAttribute("error", "Error Occurred, please try again");
				response.sendRedirect("EditProfile.jsp");
			}
		}
		
		// check if it's consumer
		if (userType.equals("consumer")) {
			ConsumerDao consumerDao = new ConsumerDao(con);
			
			boolean hasUpdated = consumerDao.updateConsumerDetails(updatedUser );
			
			Consumer consumer = (Consumer) session.getAttribute("consumer"); 
			updatedUser  = consumerDao.getUser(consumer);
			if (hasUpdated && consumer != null) {
				session.setAttribute("user", updatedUser );
				session.setAttribute("error", "Profile has been updated");
				response.sendRedirect("EditProfile.jsp");
			} else {
				session.setAttribute("error", "Error Occurred, please try again");
				response.sendRedirect("EditProfile.jsp");
			}
		}

	}
}
