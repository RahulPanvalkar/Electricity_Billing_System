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
import com.ebs.utility.DBConnectionManager;

@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Inside ChangePasswordServlet => doPost..");
		Connection con = DBConnectionManager.getConnection();
		HttpSession session = request.getSession();
		Admin admin = (Admin) session.getAttribute("admin");
		Consumer consumer = (Consumer) session.getAttribute("consumer");
		System.out.println("Admin : "+admin);
		System.out.println("Consumer : "+consumer);
		
		String reqType = request.getParameter("reqType");
		System.out.println("reqType : "+reqType);
		if (reqType.equals("Change")) {
			String oldPass = request.getParameter("old-password");
			String oldPassword ="";
			
			if (oldPass != null && !oldPass.trim().isEmpty() && admin != null) {
				AdminDao adminDao = new AdminDao(con);
				oldPassword = adminDao.getAdminPassword(admin.getId());
			} else if (oldPass != null && !oldPass.trim().isEmpty() && consumer != null)  {
				ConsumerDao consumerDao = new ConsumerDao(con);
				oldPassword = consumerDao.getConsumerPassword(consumer.getConsumerNum());
			} 
			
			if (oldPassword.trim().isEmpty() || !oldPass.equals(oldPassword)) {
				session.setAttribute("error", "Something went wrong, please try again");
				response.sendRedirect("ChangePassword.jsp?reqType=Change");
				return;
			}
		}
		
		String pass1 = request.getParameter("password1");
		String pass2 = request.getParameter("password2");
		
		if (pass1 == null || pass2 == null) {
			session.setAttribute("error", "Something went wrong,please try again..");
			response.sendRedirect("ChangePassword.jsp?reqType="+reqType);
			return;
		}

		if (admin != null) {
			AdminDao aDao = new AdminDao(con);
			boolean hasReset = aDao.updatePassword(admin.getId(), pass1);

			if (hasReset) {
				session.setAttribute("error", "Password successfully updated");
				response.sendRedirect("ChangePassword.jsp?reqType="+reqType);
			} else {
				session.setAttribute("error", "Error occurred, please try again");
				response.sendRedirect("ChangePassword.jsp?reqType="+reqType);
			}
		}

		else {
			ConsumerDao udao = new ConsumerDao(con);

			String consumerNum = (String) session.getAttribute("consumerNum");
			System.out.println("consumerNum : " + consumerNum);
			boolean hasReset = udao.resetPassword(consumerNum, pass1);

			if (hasReset) {
				session.setAttribute("error", "Password successfully updated");
				response.sendRedirect("ChangePassword.jsp?reqType="+reqType);
			} else {
				session.setAttribute("error", "Error occurred, please try again");
				response.sendRedirect("ChangePassword.jsp?reqType="+reqType);
			}
		}
		
	}
}
