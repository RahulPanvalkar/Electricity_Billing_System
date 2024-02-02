package com.ebs.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ebs.dao.BillDao;
import com.ebs.dao.ConnectionDao;
import com.ebs.entities.Bill;
import com.ebs.utility.DBConnectionManager;

@WebServlet("/AddBillServlet")
public class AddBillServlet extends HttpServlet { 
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Inside AddBillServlet => doPost...");
		
		HttpSession session = request.getSession();
		
		String consNumber = request.getParameter("cons-number");
		String meterNum = request.getParameter("meter-number");
		String month = request.getParameter("month");
		String curReadingStr = request.getParameter("cur-reading");
		String prevReadingStr= request.getParameter("prev-reading");
		String totalUnitsStr = request.getParameter("units");
		String prevBalStr = request.getParameter("prev-balance");
		String currentAmountStr = request.getParameter("final-amount");
		String finalAmountStr = request.getParameter("final-amount");
		String dueDateStr = request.getParameter("due-date");
		String status = "Pending";
		
		//converting to appropriate type
		int curReading = Integer.parseInt(curReadingStr);		
		int prevReading = Integer.parseInt(prevReadingStr);		
		int totalUnits = Integer.parseInt(totalUnitsStr);	
		
		System.out.println("currentAmountStr : "+currentAmountStr);
		if (currentAmountStr == null || currentAmountStr.trim().isEmpty()) {
			session.setAttribute("error", "Error Occurred, please try again");
			response.sendRedirect("AddBill.jsp");
			return;
		}
		double prevBalance = Double.parseDouble(prevBalStr);
		double currentAmount = Double.parseDouble(currentAmountStr);		
		double finalAmount = Double.parseDouble(finalAmountStr);		
		Date dueDate = Date.valueOf(dueDateStr);
		System.out.println("currentAmount : "+currentAmount);
		Bill newBill = new Bill(consNumber,meterNum,month,curReading,prevReading,totalUnits, prevBalance, currentAmount, finalAmount,dueDate, null,status);
		
		Connection con = DBConnectionManager.getConnection();
		BillDao bDao = new BillDao(con);
		
		boolean isAdded = bDao.addBill(newBill); 
		
		if(isAdded){ 
			session.setAttribute("error","Bill generated successfully" );
			response.sendRedirect("AddBill.jsp");
		} else {
			session.setAttribute("error", "Error Occurred, please try again");
			response.sendRedirect("AddBill.jsp");
		}
		
	}
	
	// to get meter numbers in drop-down list
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside AddBillServlet => doGet...");
		response.setContentType("application/json");
		
		 // Get the consumerNo from the request parameter
        String consumerNo = request.getParameter("consumerNo");
        System.out.println("consumerNo : "+consumerNo);
        
        if (consumerNo == null) {
        	try (PrintWriter out = response.getWriter()) {
                out.print("{}");
            }
		}
        
        try (Connection con = DBConnectionManager.getConnection()) {
            ConnectionDao connectionDao = new ConnectionDao(con);
            ArrayList<String> meterNoList = connectionDao.getMeterNumbersList(consumerNo);
            BillDao billDao = new BillDao(con);
            ArrayList<Double> pendingPaymentList = billDao.getPendingPaymentList(meterNoList);
            System.out.println("meterNoListSize : "+meterNoList.size());
            System.out.println("pendingPaymentListSize : "+pendingPaymentList.size());
            
            if (meterNoList.size() == pendingPaymentList.size()) {
                StringBuilder builder = new StringBuilder("{");
                for (int i = 0; i < meterNoList.size(); i++) {
                    if (i > 0) {
                        builder.append(", ");
                    }
                    builder.append("\"").append(meterNoList.get(i)).append("\":\"").append(pendingPaymentList.get(i)).append("\"");
                }
                builder.append("}");

                String jsonResponse = builder.toString();
                System.out.println("JsonResponse: " + jsonResponse);

                // Get the PrintWriter object to write the JSON response
                try (PrintWriter out = response.getWriter()) {
                	out.print(jsonResponse);
                }
                
            }else {
            	try (PrintWriter out = response.getWriter()) {
                    out.print("{}");
                }
			}
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
        }


	}
}
