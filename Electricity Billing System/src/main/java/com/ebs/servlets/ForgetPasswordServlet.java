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
import com.ebs.utility.DBConnectionManager;

@WebServlet("/ForgetPasswordServlet")
public class ForgetPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String consNumber = request.getParameter("conNumber");
		String mob = request.getParameter("mobNumber");
		
		Connection con = DBConnectionManager.getConnection();
		ConsumerDao udao = new ConsumerDao(con);
		
		boolean isValid = udao.validationByConsumerNumAndMobNumber(consNumber, mob);
		
		HttpSession session = request.getSession();
		
		if (isValid) {
			session.setAttribute("consumerNum", consNumber); 
//			request.setAttribute("reqType", "Reset");
//			request.getRequestDispatcher("ChangePassword.jsp").forward(request, response);
			response.sendRedirect("ChangePassword.jsp?reqType=Reset");
		} else {
			session.setAttribute("error", "Invalid credentials, please try again");
			response.sendRedirect("ForgetPassword.jsp");
		}
	}
}
