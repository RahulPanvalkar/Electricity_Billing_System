package com.ebs.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;

import com.ebs.dao.BillDao;
import com.ebs.entities.Bill;
import com.ebs.utility.DBConnectionManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/PaymentPageServlet")
public class PaymentPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String cardExpiryDate = request.getParameter("expiryDate");

		LocalDate expiryDate = LocalDate.parse(cardExpiryDate);
		LocalDate currentDate = LocalDate.now();
		System.out.println("currentDate : "+currentDate+" & expiryDate : "+expiryDate);
		if (currentDate.isAfter(expiryDate)) {
			session.setAttribute("error", "Payment Failed, Expired Card Used");
			response.sendRedirect("PaymentPage.jsp");
			return;
		}
		
		Bill bill = (Bill) session.getAttribute("currentBill");
		
		String consNumber = bill.getConsumerNum();
		String meterNum = bill.getMeterNum();
		System.out.println("consNumber : "+consNumber+" & meterNum : "+meterNum);
		Connection con = DBConnectionManager.getConnection();
		BillDao bDao = new BillDao(con);
		boolean isUpdated = bDao.updateBillStatus(consNumber,meterNum);
		if (isUpdated) {
			session.setAttribute("error", "Payment Successfull");
			response.sendRedirect("PaymentPage.jsp");
		} else {
			session.setAttribute("error", "Payment Failed, please try again");
			response.sendRedirect("PaymentPage.jsp");
		}
		
	}
}
