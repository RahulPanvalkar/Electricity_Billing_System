package com.ebs.dao;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;

import com.ebs.entities.Bill;
import com.ebs.utility.DBConnectionManager;

public class BillDao implements Serializable {
	private static final long serialVersionUID = 1L;

	private Connection con;

	public BillDao(Connection con) {
		this.con = con;
	}

	// method to get pending Bill data
	public Bill getBill(String consNumber) {
		Bill bill = null;
		String query = "Select * from BillDetails where Consumer_Num=? and Status='Pending'";
		try(PreparedStatement ps = con.prepareStatement(query);) {
			ps.setString(1, consNumber);
			System.out.println("Query : [" + ps.toString()+"]");

			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					bill = new Bill();

					bill.setBillNum(rs.getString("Bill_No"));
					bill.setConsumerNum(rs.getString("Consumer_Num"));
					bill.setMeterNum(rs.getString("Meter_Num"));
					bill.setMonth(rs.getString("Month"));
					bill.setCurrentReading(rs.getInt("Current_Reading"));
					bill.setPreviousReading(rs.getInt("Previous_Reading"));
					bill.setTotalUnits(rs.getInt("Total_Units"));
					bill.setPrevBalance(rs.getDouble("Previous_Balance"));
					bill.setCurrentAmount(rs.getDouble("Current_Amount"));
					bill.setTotalAmount(rs.getDouble("Total_Amount"));
					bill.setDueDate(rs.getDate("Due_Date"));
					bill.setPaymentDate(rs.getDate("Payment_Date"));
					bill.setStatus(rs.getString("Status"));
				} else {
					System.out.println("Pending Bill not found..");
				}
			}
		} catch (Exception e) {
			System.out.println("Exception occured in getBill.." );
			e.printStackTrace();
		} finally {
			DBConnectionManager.closeConnection(con);
		}
		return bill;
	}
	
	// method to get consumers bill history
	public ArrayList<Bill> getBillHistory(String consNumber) {
		ArrayList<Bill> allBills = new ArrayList<>();

		String query = "Select * from BillDetails where Consumer_Num=?";
		try(PreparedStatement ps = con.prepareStatement(query);) {
			ps.setString(1, consNumber);
			System.out.println("Query : [" + ps.toString() +"]");

			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					Bill bill = null;

					String billNum = rs.getString("Bill_No");
					String consumerNum = rs.getString("Consumer_Num");
					String meterNum = rs.getString("Meter_Num");
					String month = rs.getString("Month");
					int currentReading = rs.getInt("Current_Reading");
					int previousReading = rs.getInt("Previous_Reading");
					int totalUnits = rs.getInt("Total_Units");
					double prevBalance = rs.getDouble("Previous_Balance");
					double currentAmount = rs.getDouble("Current_Amount");
					double totalAmount = rs.getDouble("Total_Amount");
					Date dueDate = rs.getDate("Due_Date");
					Date paymentDate = rs.getDate("Payment_Date");
					String status = rs.getString("Status");

					bill = new Bill(billNum, consumerNum, meterNum, month, currentReading, previousReading, totalUnits,
							prevBalance,currentAmount, totalAmount, dueDate, paymentDate, status);
					allBills.add(bill);
				}
			}
		} catch (Exception e) {
			System.out.println("Exception occured in getBillHistory..");
			e.printStackTrace();
		} finally {
			DBConnectionManager.closeConnection(con);
		}

		return allBills;
	}

	// method to update bill status
	public boolean updateBillStatus(String consNumber, String meterNum) {
		java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
		System.out.println("currentDate : [" + currentDate +"]");
		boolean statusUpdated = false;
		String query = "UPDATE BillDetails SET Status = 'Paid', Payment_Date = ? WHERE Consumer_Num=? AND Meter_Num=?";
		try(PreparedStatement ps = con.prepareStatement(query);) {
			ps.setDate(1, currentDate);
			ps.setString(2, consNumber);
			ps.setString(3, meterNum);
			System.out.println("Query : [" + ps.toString() +"]");
			
			int rowCount = ps.executeUpdate();
			System.out.println("rowCount : "+rowCount);
			if (rowCount == 1) {
				statusUpdated = true;
			}

		} catch (Exception e) {
			System.out.println("Exception occured in updateBillStatus.."); 
			e.printStackTrace();
		} finally {
			DBConnectionManager.closeConnection(con);
		}
		return statusUpdated;
	}

//method to get all Bills
	public ArrayList<Bill> getAllBills() {
		ArrayList<Bill> allBills = new ArrayList<>();

		String query = "select * from BillDetails";
		try(PreparedStatement ps = con.prepareStatement(query);) {
			System.out.println("Query : [" + ps.toString() +"]");

			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					Bill bill = null;

					String billNum = rs.getString("Bill_No");
					String consumerNum = rs.getString("Consumer_Num");
					String meterNum = rs.getString("Meter_Num");
					String month = rs.getString("Month");
					int currentReading = rs.getInt("Current_Reading");
					int previousReading = rs.getInt("Previous_Reading");
					int totalUnits = rs.getInt("Total_Units");
					double prevBalance = rs.getDouble("Previous_Balance");
					double currentAmount = rs.getDouble("Current_Amount");
					double totalAmount = rs.getDouble("Total_Amount");
					Date dueDate = rs.getDate("Due_Date");
					Date paymentDate = rs.getDate("Payment_Date");
					String status = rs.getString("Status");

					bill = new Bill(billNum, consumerNum, meterNum, month, currentReading, previousReading, totalUnits,
							prevBalance, currentAmount, totalAmount, dueDate, paymentDate, status);
					allBills.add(bill);
				}
			}
		} catch (Exception e) {
			System.out.println("Exception occured in getAllBills..");
			e.printStackTrace();
		} finally {
			DBConnectionManager.closeConnection(con);
		}

		return allBills;
	}

	// method to add bill
	public boolean addBill(Bill bill) {
		boolean isAdded = false;
		
		String query = "INSERT INTO BillDetails(Consumer_Num,Month,Current_Reading,Previous_Reading,"
				+ "Total_Units,Current_Amount,Total_Amount,Due_Date,Payment_Date,Status) VALUES (?,?,?,?,?,?,?,?,?,?)";
		
		try (PreparedStatement ps = con.prepareStatement(query);){
			ps.setString(1, bill.getConsumerNum());
			ps.setString(2, bill.getMonth());
			ps.setInt(3, bill.getCurrentReading());
			ps.setInt(4, bill.getPreviousReading());
			ps.setInt(5, bill.getTotalUnits());
			ps.setDouble(6, bill.getCurrentAmount());
			ps.setDouble(7, bill.getTotalAmount());
			ps.setDate(8, bill.getDueDate());
			ps.setDate(9, null);
			ps.setString(10, bill.getStatus());
			System.out.println("Query : [" + ps.toString()+"]");
			
			int rowCount = ps.executeUpdate();
			System.out.println("rowCount : "+rowCount);
			if (rowCount == 1) {
				isAdded = true;
			}
		} catch (Exception e) {
			System.out.println("Exception occured in addBill..");
			e.printStackTrace();
		} finally {
			DBConnectionManager.closeConnection(con);
		}

		return isAdded;
	}
	
	//method to get PendingPayment List
	public ArrayList<Double> getPendingPaymentList(ArrayList<String> meterList) {
		ArrayList<Double> pendingPaymentList = new ArrayList<>();
		for (int i = 0; i < meterList.size(); i++) {
			String query = "SELECT Total_Amount FROM BillDetails WHERE meter_num = ? AND status='Unpaid' ";

			try (PreparedStatement ps = con.prepareStatement(query)) {
				ps.setString(1, meterList.get(i));
				System.out.println("Query : [" + ps.toString() + "]");

				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {
						Double pendingAmount = rs.getDouble("Total_Amount");
						System.out.println("PendingAmount : "+pendingAmount);
						pendingPaymentList.add(pendingAmount);
					}else {
						pendingPaymentList.add(0.0);
					}
				}
			} catch (Exception e) {
				System.out.println("SQLException occured in getMeterNumbersMap..");
				e.printStackTrace();
			}
		}
		return pendingPaymentList;
	}
	// method to remove a bill
	public boolean removeBill(String billNo) {
		boolean isRemoved = false;
		String query = "DELETE FROM BillDetails WHERE Bill_No = ?";
		try(PreparedStatement ps = con.prepareStatement(query);) {
			ps.setString(1, billNo);
			System.out.println("Query : [" + ps.toString()+"]");
			
			int rowCount = ps.executeUpdate();
			System.out.println("rowCount : "+rowCount);
			if (rowCount == 1) {
				isRemoved = true;
			}

		} catch (Exception e) {
			System.out.println("Exception occured in removeBill..");
			e.printStackTrace();
		} finally {
			DBConnectionManager.closeConnection(con);
		}

		return isRemoved;
	}
	

}
