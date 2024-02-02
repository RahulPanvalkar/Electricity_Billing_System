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
import com.ebs.entities.Admin;
import com.ebs.utility.DBConnectionManager;

@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		String idString = request.getParameter("userID");
		String pass = request.getParameter("password");
		
		int id = Integer.parseInt(idString);
		
		Connection con = DBConnectionManager.getConnection();
		AdminDao ad = new AdminDao(con);
		
		Admin admin =  ad.getAdmin(id, pass);
		
		if (admin != null) {
			session.setAttribute("admin", admin); 
			response.sendRedirect("admin-dashboard.jsp");
		} else {
			session.setAttribute("error", "Invalid UserID or Password");
			response.sendRedirect("AdminLogin.jsp");
			
		}	
	}
}
