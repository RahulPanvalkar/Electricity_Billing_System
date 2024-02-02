package com.ebs.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ebs.dao.AdminDao;
import com.ebs.dao.BillDao;
import com.ebs.dao.ConnectionDao;
import com.ebs.dao.ConsumerDao;
import com.ebs.dao.CostPerUnitDao;
import com.ebs.entities.Admin;
import com.ebs.entities.Bill;
import com.ebs.entities.Consumer;
import com.ebs.entities.CostPerUnit;
import com.ebs.entities.EConnection;
import com.ebs.entities.User;
import com.ebs.utility.DBConnectionManager;

@WebServlet("/AdminDashboardServlet")
public class AdminDashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String link = request.getParameter("link");
		System.out.println("Link : ["+link+"]");
		Connection con = DBConnectionManager.getConnection();

		if (link == null) {
			response.sendRedirect("admin-dashboard.jsp");
			return;
		}

		// view all consumers
		if (link.equals("view-consumers")) {
			ConsumerDao uDao = new ConsumerDao(con);
			ArrayList<Consumer> consumers = uDao.getAllConsumers();
			Collections.sort(consumers, Comparator.comparing(Consumer::getConsumerNum).reversed());
			if (consumers != null) {
				session.setAttribute("consumers", consumers);
				response.sendRedirect("ViewConsumers.jsp");
			} else {
				response.sendRedirect("ViewConsumers.jsp");
			}
		}
  
		// view all connections
		else if (link.equals("view-connections")) {
			ConnectionDao connDao = new ConnectionDao(con);
			ArrayList<EConnection> eConnections = connDao.getAllConnections();
			Collections.sort(eConnections, Comparator.comparing(EConnection::getConsumerNum).reversed());

			if (eConnections != null) {
				session.setAttribute("connections", eConnections);
				response.sendRedirect("ViewConnections.jsp");
			} else {
				response.sendRedirect("ViewConnections.jsp");
			}
		}

		// view all bills
		else if (link.equals("view-bills")) {
			BillDao bDao = new BillDao(con);
			ArrayList<Bill> allBills = bDao.getAllBills();
			Collections.sort(allBills, Comparator.comparing(Bill::getBillNum).reversed());
			if (allBills != null) {
				session.setAttribute("allBills", allBills);
				response.sendRedirect("ViewBills.jsp");
			} else {
				response.sendRedirect("ViewBills.jsp");
			}
		}

		// view profile
		else if (link.equals("view-profile")) {
			Admin admin = (Admin) session.getAttribute("admin");

			if (admin == null) {
				request.getRequestDispatcher("ErrorPage.jsp").forward(request, response);
				return;
			}

			AdminDao aDao = new AdminDao(con);
			User user = aDao.getUser(admin);
			if (user != null) {
				session.setAttribute("user", user);
				response.sendRedirect("Profile.jsp");
			} else {
				response.sendRedirect("ErrorPage.jsp");
			}
		}

		// generate bill
		else if (link.equals("add-bills")) {
			CostPerUnitDao costDao = new CostPerUnitDao(con); 
			CostPerUnit cost = costDao.getCost();

			if (cost != null) {
				session.setAttribute("username","Admin");
				session.setAttribute("cost", cost);
				response.sendRedirect("AddBill.jsp");
			} else {
				response.sendRedirect("ErrorPage.jsp");
			}
		}

		// change password
		else if (link.equals("change-pwd")) {
			response.sendRedirect("ChangePassword.jsp?reqType=Change");
		}

		// logout
		else if (link.equals("logout")) {
			session = request.getSession(false);
			if (session != null) {
				session.invalidate();
			} else {
				response.sendRedirect("ErrorPage.jsp");
			}

			response.sendRedirect("Home.jsp");
		}
	}
}
