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

@WebServlet("/AddConsumerServlet")
public class AddConsumerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Inside AddConsumerServlet => doPost..");
		
		String fullName = request.getParameter("name");
		String email = request.getParameter("email");
		String mob = request.getParameter("mob-number");
		String address = request.getParameter("address");

		System.out.println("fullName : ["+fullName+"]"+"email : ["+email+"]"+"mob : ["+mob+"]"+"address : ["+address+"]");
		Consumer consumer = new Consumer(fullName, mob, email, address);

		HttpSession session = request.getSession();

		Connection con = DBConnectionManager.getConnection();
		ConsumerDao consumerDao = new ConsumerDao(con);
		boolean isAdded = consumerDao.addConsumer(consumer);

		if (isAdded) {
			session.setAttribute("error", "Consumer Added Successfully");
			response.sendRedirect("AddConsumer.jsp");
		} else {
			session.setAttribute("error", "Error Occurred, please try again");
			response.sendRedirect("AddConsumer.jsp");
		}
		
	}
	
}
