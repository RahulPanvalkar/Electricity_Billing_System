package com.ebs.servlets;

import java.io.IOException;
import java.sql.Connection;
import com.ebs.dao.BillDao;
import com.ebs.dao.ConsumerDao;
import com.ebs.entities.Bill;
import com.ebs.utility.DBConnectionManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/QuickBillPaymentServlet")
public class QuickBillPaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String consNumber = request.getParameter("ca-number");
		System.out.println("Consumer No : " + consNumber);
		
		ConsumerDao consumerDao = new ConsumerDao();
		boolean isConsumerValid = consumerDao.validateConsumer(consNumber);
		System.out.println("isConsumerValid : "+isConsumerValid);
		
		if (isConsumerValid) {
			Connection con = DBConnectionManager.getConnection();
			BillDao bdao = new BillDao(con);
			Bill bill = bdao.getBill(consNumber);

			if (bill != null) {
				System.out.println(bill);
				session.setAttribute("currentBill", bill);
			}
			response.sendRedirect("PendingBill.jsp");
			
		} else {
			session.setAttribute("error", "Invalid Consumer Number!");
			response.sendRedirect("Home.jsp");
		}

	}
}
