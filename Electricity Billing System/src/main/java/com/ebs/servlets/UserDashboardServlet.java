package com.ebs.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ebs.dao.AdminDao;
import com.ebs.dao.BillDao;
import com.ebs.dao.ConsumerDao;
import com.ebs.entities.Admin;
import com.ebs.entities.Bill;
import com.ebs.entities.Consumer;
import com.ebs.entities.User;
import com.ebs.utility.DBConnectionManager;

@WebServlet("/UserDashboardServlet")
public class UserDashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("Inside UserDashboardServlet => doGet..");
		
		String link = request.getParameter("link");
		Connection con = DBConnectionManager.getConnection();
		System.out.println("Link : "+link);
		if (link == null) {
			response.sendRedirect("admin-dashboard.jsp");
			return;
		}

		HttpSession session = request.getSession();
		Consumer consumer = (Consumer) session.getAttribute("consumer");

		// view pending bill
		if (link.equals("view-bill")) {
			BillDao bdao = new BillDao(con);
			Bill bill = bdao.getBill(consumer.getConsumerNum());
			if (bill != null) {
				session.setAttribute("currentBill", bill);
				response.sendRedirect("PendingBill.jsp");
			} else {
				response.sendRedirect("user-dashboard.jsp");
			}
		}

		// view bill history
		if (link.equals("bill-history")) {
			BillDao bdao = new BillDao(con);
			ArrayList<Bill> allBills = bdao.getBillHistory(consumer.getConsumerNum());
			if (allBills != null && !allBills.isEmpty()) {
				session.setAttribute("allBills", allBills);
				response.sendRedirect("BillHistory.jsp");
			} else {
				response.sendRedirect("ErrorPage.jsp");
			}
		}
		
		// change password
		else if (link.equals("change-pwd")) {
		    session.setAttribute("consumerNum", consumer.getConsumerNum()); 
		    response.sendRedirect("ChangePassword.jsp?reqType=Change");
		}

		// view profile
		else if (link.equals("view-profile")) {
			if (consumer == null) {
				request.getRequestDispatcher("ErrorPage.jsp").forward(request, response);
				return;
			}
			ConsumerDao conDao = new ConsumerDao(con);
			User user = conDao.getUser(consumer); 
			System.out.println(user);
			if (user != null) {
				session.setAttribute("user", user);
				response.sendRedirect("Profile.jsp");
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
