package com.ebs.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ebs.dao.CostPerUnitDao;
import com.ebs.entities.CostPerUnit;
import com.ebs.utility.DBConnectionManager;

@WebServlet("/UpdateCostServlet")
public class UpdateCostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();

			double unitsZeroToHundred = Double.parseDouble(request.getParameter("zero-to-hun"));
			double unitsOneHundredOneToThreeHundred = Double.parseDouble(request.getParameter("hun-to-three"));
			double unitsThreeHundredOneToFiveHundred = Double.parseDouble(request.getParameter("three-to-five"));
			double unitsFiveHundredOneAndAbove = Double.parseDouble(request.getParameter("greater-than-500"));

			Connection con = DBConnectionManager.getConnection();
			CostPerUnitDao costDao = new CostPerUnitDao(con);

			CostPerUnit cost = new CostPerUnit(unitsZeroToHundred, unitsOneHundredOneToThreeHundred,
					unitsThreeHundredOneToFiveHundred, unitsFiveHundredOneAndAbove);

			boolean hasUpdated = costDao.updateCost(cost);

			if (hasUpdated) {
				session.setAttribute("error", "Cost Successfully Updated");
				response.sendRedirect("UpdateCost.jsp");
			} else {
				session.setAttribute("error", "Error Occurred, please try again");
				response.sendRedirect("UpdateCost.jsp");
			}
		} catch (Exception e) {
			System.out.println("Exception occurred in UpdateCostServlet => doPost: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// to get current cost data
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    System.out.println("Inside UpdateCostServlet => doGet...");
	    response.setContentType("application/json");

	    try (Connection con = DBConnectionManager.getConnection()) {
	        CostPerUnitDao costDao = new CostPerUnitDao(con);
	        CostPerUnit costPerUnit = costDao.getCost();

	        System.out.println("costPerUnit : " + costPerUnit);

	        // Prepare the JSON response
	        StringBuilder jsonResponse = new StringBuilder("[");
	        if (costPerUnit != null) {
	            appendCostEntry(jsonResponse, costPerUnit.getUnitsZeroToHundred());
	            appendCostEntry(jsonResponse, costPerUnit.getUnitsOneHundredOneToThreeHundred());
	            appendCostEntry(jsonResponse, costPerUnit.getUnitsThreeHundredOneToFiveHundred());
	            appendCostEntry(jsonResponse, costPerUnit.getUnitsFiveHundredOneAndAbove());
	        }
	        jsonResponse.append("]");

	        System.out.println("JsonResponse: " + jsonResponse);

	        // Get the PrintWriter object to write the JSON response
	        try (PrintWriter out = response.getWriter()) {
	            out.print(jsonResponse.toString());
	        }

	    } catch (Exception e) {
	        System.out.println("Exception occurred in UpdateCostServlet => doGet: " + e.getMessage());
	        e.printStackTrace();
	    }
	}

	// to append a cost entry to the JSON response
	private void appendCostEntry(StringBuilder json, double value) {
	    if (json.charAt(json.length() - 1) != '[') {
	        json.append(",");
	    }
	    json.append("\"").append(value).append("\"");
	}

	
}
