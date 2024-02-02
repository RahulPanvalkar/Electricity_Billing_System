package com.ebs.dao;

import java.io.Serializable;
import java.sql.*;

import com.ebs.entities.CostPerUnit;
import com.ebs.utility.DBConnectionManager;

public class CostPerUnitDao  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Connection con;
	
	public CostPerUnitDao(Connection con) {
		this.con = con;
	}
	
	//method to get CostPerUnit data
	public CostPerUnit getCost() {
		CostPerUnit costPerUnit = null;
		String query = "Select * from CostPerUnit";
		try (PreparedStatement ps = con.prepareStatement(query);) {
			System.out.println("Query : [" + ps.toString()+"]");
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					costPerUnit = new CostPerUnit();

					costPerUnit.setUnitsZeroToHundred(rs.getDouble("0_to_100"));
					costPerUnit.setUnitsOneHundredOneToThreeHundred(rs.getDouble("101_to_300"));
					costPerUnit.setUnitsThreeHundredOneToFiveHundred(rs.getDouble("301_to_500"));
					costPerUnit.setUnitsFiveHundredOneAndAbove(rs.getDouble("501_and_above"));
				}
			}
		} catch (Exception e) {
			System.out.println("Exception occured in getCost..");
			e.printStackTrace();
		}
		
		return costPerUnit;
	}
	
	//method to update CostPerUnit
	public boolean updateCost(CostPerUnit cost) {
		boolean costUpdated = false;
		String query = "UPDATE CostPerUnit SET  0_to_100=?, 101_to_300=?,"
				+ "301_to_500=?, 501_and_above=? WHERE 0_to_100 = ?";

		CostPerUnit oldCost = getCost();
		if (oldCost == null) {
			query = "INSERT INTO CostPerUnit VALUES (?,?,?,?)";
		}

		try (PreparedStatement ps = con.prepareStatement(query);) {
			ps.setDouble(1, cost.getUnitsZeroToHundred());
			ps.setDouble(2, cost.getUnitsOneHundredOneToThreeHundred());
			ps.setDouble(3, cost.getUnitsThreeHundredOneToFiveHundred());
			ps.setDouble(4, cost.getUnitsFiveHundredOneAndAbove());

			if (oldCost != null) {
				System.out.println("add new param..");
				ps.setDouble(5, oldCost.getUnitsZeroToHundred());
			}
			System.out.println("Query : [" + ps.toString() + "]");

			int rowCount = ps.executeUpdate();
			System.out.println("rowCount : [" + rowCount + "]");
			if (rowCount == 1) {
				costUpdated = true;
			}
		} catch (Exception e) {
			System.out.println("Exception occured in updateCost..");
			e.printStackTrace();
		} finally {
			DBConnectionManager.closeConnection(con);
		}

		return costUpdated;
	}
	
}