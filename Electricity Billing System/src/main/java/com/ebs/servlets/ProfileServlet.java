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

@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet { 
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String link = request.getParameter("link");
		Connection con = DBConnectionManager.getConnection();
		System.out.println(link);
		if (link == null) {
			String previousPage = request.getHeader("referer");
		    response.sendRedirect(previousPage);
		    return;
		}

		HttpSession session = request.getSession();

		// edit admin profile
		if (link.equals("admin")) {
			Admin admin = (Admin) session.getAttribute("admin"); 
			AdminDao aDao = new AdminDao(con);
			User user = aDao.getUser(admin);
			if (user != null) {
				session.setAttribute("user", user);
				response.sendRedirect("EditProfile.jsp");
			} else {
				response.sendRedirect("ErrorPage.jsp");
			}
		}

		// edit consumer profile
		else if (link.equals("consumer")) {
			Consumer consumer = (Consumer) session.getAttribute("consumer");
			ConsumerDao conDao = new ConsumerDao(con);
			User user = conDao.getUser(consumer);
			if (user != null) {
				session.setAttribute("user", user);
				response.sendRedirect("EditProfile.jsp");
			} else {
				response.sendRedirect("ErrorPage.jsp");
			}
		}
		
		// logout
		else if (link.equals("logout")) {
			session = request.getSession(false);
			if (session != null) {
				session.invalidate();
			}

			response.sendRedirect("Home.jsp");
		}
	}
}
