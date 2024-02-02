package com.ebs.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ebs.dao.BillDao;
import com.ebs.dao.ConnectionDao;
import com.ebs.dao.ConsumerDao;
import com.ebs.entities.Bill;
import com.ebs.entities.Consumer;
import com.ebs.entities.EConnection;
import com.ebs.utility.DBConnectionManager;

@WebServlet("/RemoveDataServlet")
public class RemoveDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//System.out.println("Full URL: " + request.getRequestURL() + "?" + request.getQueryString());
	    
	    String key = request.getParameter("key");
	    String value = request.getParameter("value");
	    System.out.println("key: [" + key + "] & value: [" + value + "]");
		
		HttpSession session = request.getSession();
		
		if (key != null && key.equals("billNo") && value != null) {
			ArrayList<Bill> allBills = removeBill(value);
			
			if (allBills != null) {
				session.setAttribute("allBills", allBills);
				response.sendRedirect("ViewBills.jsp");
			} else {
				response.sendRedirect("ViewBills.jsp");
			}
			
		} else if (key != null && key.equals("consumerNo") && value != null) {
			ArrayList<Consumer> consumers = removeConsumer(value);
			
			if (consumers != null) {
				session.setAttribute("consumers", consumers);
				response.sendRedirect("ViewConsumers.jsp");
			} else {
				response.sendRedirect("ViewConsumers.jsp");
			}
			
		} else if (key != null && key.equals("meterNo") && value != null) {
			ArrayList<EConnection> allConnections = removeConnection(value);
			
			if (allConnections != null) {
				session.setAttribute("connections", allConnections);
				response.sendRedirect("ViewConnections.jsp");
			} else {
				response.sendRedirect("ViewConnections.jsp");
			}
		}
		
	}
	
	
	private ArrayList<Bill> removeBill(String billNo) {
		Connection con = DBConnectionManager.getConnection();
		BillDao bDao = new BillDao(con);
		ArrayList<Bill> allBills = null;
		boolean isRemoved = bDao.removeBill(billNo);

		if (isRemoved) {
			allBills = bDao.getAllBills();
		}
		System.out.println("All bills : ["+allBills+"]"); 
		return allBills;
	}
	
	private ArrayList<Consumer> removeConsumer(String consumerNo) {
		Connection con = DBConnectionManager.getConnection();
		ConsumerDao userDao = new ConsumerDao(con);
		ConnectionDao conDao = new ConnectionDao(con);
		ArrayList<Consumer> consumers = null;
		try {
			con.setAutoCommit(false);

			boolean isConsumerRemoved = userDao.removeConsumer(consumerNo);
			boolean isConnectionRemoved = conDao.removeConnection(consumerNo);
			System.out.println("isConsumerRemoved : "+isConsumerRemoved+" & isConnectionRemoved : "+isConnectionRemoved);
			
			if (isConsumerRemoved &&  isConnectionRemoved) {
				con.commit();
				con.setAutoCommit(true);
				consumers = userDao.getAllConsumers();
			}
			System.out.println("All consumers : ["+consumers+"]");
			
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		return consumers;
	}

	private ArrayList<EConnection> removeConnection(String meterNo) {
		Connection con = DBConnectionManager.getConnection();
		ConnectionDao conDao = new ConnectionDao(con);
		ArrayList<EConnection> allConnections = null;
		boolean isRemoved = conDao.removeConnection(meterNo);

		if (isRemoved) {
			allConnections = conDao.getAllConnections();
		}
		System.out.println("All connections : ["+allConnections+"]");
		return allConnections;
	}
	
}
