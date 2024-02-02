package com.ebs.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ebs.dao.ConnectionDao;
import com.ebs.dao.ConsumerDao;
import com.ebs.entities.EConnection;
import com.ebs.utility.DBConnectionManager;

@WebServlet("/AddConnectionServlet")
public class AddConnectionServlet extends HttpServlet { 
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("Inside AddConnectionServlet => doPost...");
		String consNumber = request.getParameter("ca-number");
		String meterNumber = request.getParameter("meter-number");
		String fullName = request.getParameter("name");
		String mob = request.getParameter("mob-number");
		String address = request.getParameter("address");
		String startDate = request.getParameter("start-date");
		String connType = request.getParameter("connectionType");
		
		System.out.println( "consNumber : "+consNumber+" & Date : "+startDate);
		Date date = Date.valueOf(startDate);
		
		EConnection connection = new EConnection(consNumber,meterNumber,fullName,mob,address,date,connType);
		
		HttpSession session = request.getSession();
		
		Connection con = DBConnectionManager.getConnection();
		ConnectionDao cdao = new ConnectionDao(con);
		
		boolean isAdded = cdao.addConnection(connection);
		
		if(isAdded){ 
			session.setAttribute("error","Connection Created Successfully" );
			response.sendRedirect("AddConnection.jsp");
		} else {
			session.setAttribute("error", "Error Occurred, please try again");
			response.sendRedirect("AddConnection.jsp");
		}
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside AddConnectionServlet => doGet...");
		response.setContentType("application/json");
		
		 // Get the consumerNo from the request parameter
        String consumerNo = request.getParameter("consumerNo");
        System.out.println("consumerNo : "+consumerNo);
        
        if (consumerNo == null) {
        	try (PrintWriter out = response.getWriter()) {
                out.print("{}");
            }
		}
        
        Connection con = DBConnectionManager.getConnection();
        ConsumerDao consumerDao = new ConsumerDao(con);
        HashMap<String, String> dataMap = consumerDao.getConsumerNoMap(consumerNo);
        StringBuilder builder = new StringBuilder("{");
        boolean first = true;
        for(String key : dataMap.keySet()) {
        	if (!first) {
        		builder.append(", ");
        	}
        	builder.append("\"").append(key).append("\":\"").append(dataMap.get(key)).append("\"");
        	first = false;
        }
        builder.append("}");
        String jsonResponse = builder.toString();
        System.out.println("JsonResponse : "+jsonResponse);

        // Get the PrintWriter object to write the JSON response
        try (PrintWriter out = response.getWriter()) {
            out.print(jsonResponse);
        }
	}
}
