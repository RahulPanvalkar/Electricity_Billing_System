package com.ebs.dao;

import java.sql.*;
import java.util.ArrayList;

import com.ebs.entities.EConnection;
import com.ebs.utility.DBConnectionManager;

public class ConnectionDao {

	private Connection con;

	public ConnectionDao(Connection con) {
		this.con = con;
	}

	// method to get all Connections
	public ArrayList<EConnection> getAllConnections() {
		ArrayList<EConnection> eConnections = new ArrayList<>();

		String query = "select * from ConnectionDetails";
		try (PreparedStatement ps = con.prepareStatement(query);) {
			System.out.println("Query : [" + ps.toString() + "]");

			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					String consumerNum = rs.getString("Consumer_Num");
					String meter = rs.getString("Meter_Num");
					String fullName = rs.getString("Full_Name");
					String mob = rs.getString("Mob_Number");
					String address = rs.getString("Address");
					Date startDate = rs.getDate("Start_Date");
					String connType = rs.getString("Type");

					eConnections.add(new EConnection(consumerNum, meter, fullName, mob, address, startDate, connType));
				}
			}
		} catch (Exception e) {
			System.out.println("Exception occured in getAllConnections..");
			e.printStackTrace();
		} finally {
			DBConnectionManager.closeConnection(con);
		}

		return eConnections;
	}

	// method to check if connection is already present is table 
	public boolean checkConnection(String consNum, String meterNo) {
		String query = "SELECT * FROM ConnectionDetails WHERE Consumer_Num=? and Meter_num = ?";
	    try ( PreparedStatement ps = con.prepareStatement(query)) {
	        ps.setString(1, consNum);
	        ps.setString(2, meterNo);
	        System.out.println("Query : [" + ps.toString() + "]");

	        try (ResultSet rs = ps.executeQuery()) {
	            return rs.next();
	        }

	    } catch (Exception e) {
	    	System.out.println("Exception occured in checkConnection..");
	    	e.printStackTrace();
	    } finally {
			DBConnectionManager.closeConnection(con);
		}

	    return false;
	}

	// method to add connection
	public boolean addConnection(EConnection eCon) {
		System.out.println("Inside ConnectionDao => addConnection..");
		boolean isAdded = false;

		boolean isConnectionPresent = checkConnection(eCon.getConsumerNum(), eCon.getMeter());
		System.out.println("isConnectionPresent : " + isConnectionPresent);
		if (isConnectionPresent) {
			System.out.println("Connection with same MeterNo already exist..");
			return false;
		}

		String query = "Insert into ConnectionDetails values (?,?,?,?,?,?,?)";
		try (PreparedStatement ps = con.prepareStatement(query);) {
			ps.setString(1, eCon.getConsumerNum());
			ps.setString(2, eCon.getMeter());
			ps.setString(3, eCon.getFullName());
			ps.setString(4, eCon.getMob());
			ps.setString(5, eCon.getAddress());
			ps.setDate(6, eCon.getStartDate());
			ps.setString(7, eCon.getConnType());
			System.out.println("Query : [" + ps.toString() + "]");

			int updatedRows = ps.executeUpdate();
			System.out.println("updateCount : " + updatedRows);

			if (updatedRows == 1) {
				isAdded = true;
			}

		} catch (Exception e) {
			System.out.println("Exception occured in addConnection..");
			e.printStackTrace();
		} finally {
			DBConnectionManager.closeConnection(con);
		}
		System.out.println("is Connection Added : "+isAdded);
		return isAdded;
	}

	public boolean removeConnection(String meterNo) {
		boolean isRemoved = false;
		String selectQuery = "SELECT * FROM ConnectionDetails WHERE Meter_Num= ?"; 
		try (PreparedStatement pStatement = con.prepareStatement(selectQuery)) {
			pStatement.setString(1, meterNo);
			System.out.println("Query : [" + pStatement.toString() + "]");
			
			try (ResultSet rs = pStatement.executeQuery()){
				if (rs.next()) {
					String query = "DELETE FROM ConnectionDetails WHERE Meter_Num = ?";
					PreparedStatement ps = con.prepareStatement(query);
					ps.setString(1, meterNo);
					System.out.println("Query : [" + ps.toString()+"]");
					
					if (ps.executeUpdate() > 0) {
						isRemoved = true;
					}
				}else {
					System.out.println("Connection not found..");
					isRemoved = true;
				}
			} 
			
		} catch (Exception e) {
			System.out.println("Exception occured in removeConnection..");
			e.printStackTrace();
		} finally {
			DBConnectionManager.closeConnection(con);
		}

		return isRemoved;
	
	}

	// method to get a meter numbers
	public ArrayList<String> getMeterNumbersList(String consumerNo) {
		ArrayList<String> meterNoList = new ArrayList<>();

		String query = "Select Meter_Num from ConnectionDetails where Consumer_Num=?";

		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, consumerNo);
			System.out.println("Query : [" + ps.toString() + "]");

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					String meterNo = rs.getString("Meter_Num");
					meterNoList.add(meterNo);
				}
			}
		} catch (Exception e) {
			System.out.println("Exception occured in getMeterNumbersMap..");
			e.printStackTrace();
		} 

		return meterNoList;
	}

	
}