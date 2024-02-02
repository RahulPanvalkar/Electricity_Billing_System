package com.ebs.entities;

import java.sql.Date;

public class Bill {
	private String billNum;
	private String consumerNum;
	private String meterNum;
	private String month;
	private int currentReading;
	private int previousReading;
	private int totalUnits;
	private double prevBalance;
	private double currentAmount;
	private double totalAmount;
	private Date dueDate; 
	private Date paymentDate;
	private String status;
	
	public Bill() {
		
	}
	
	public Bill(String billNum, String consumerNum, String meterNum, String month, int currentReading, int previousReading, int totalUnits,
			double prevBalance, double currentAmount, double totalAmount, Date dueDate, Date paymentDate, String status) {
		super();
		this.billNum = billNum;
		this.consumerNum = consumerNum;
		this.meterNum = meterNum;
		this.month = month;
		this.currentReading = currentReading;
		this.previousReading = previousReading;
		this.totalUnits = totalUnits;
		this.prevBalance = prevBalance;
		this.currentAmount = currentAmount;
		this.totalAmount = totalAmount;
		this.dueDate = dueDate;
		this.paymentDate = paymentDate;
		this.status = status;
	}


	public Bill(String consumerNum,String meterNum, String month, int currentReading, int previousReading, int totalUnits,
			double prevBalance,double currentAmount, double totalAmount, Date dueDate, Date paymentDate, String status) {
		super();
		this.consumerNum = consumerNum;
		this.meterNum = meterNum;
		this.month = month;
		this.currentReading = currentReading;
		this.previousReading = previousReading;
		this.totalUnits = totalUnits;
		this.prevBalance = prevBalance;
		this.currentAmount = currentAmount;
		this.totalAmount = totalAmount;
		this.dueDate = dueDate;
		this.paymentDate = paymentDate;
		this.status = status;
	}

	public double getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(double currentAmount) {
		this.currentAmount = currentAmount;
	}

	public String getBillNum() {
		return billNum;
	}

	public void setBillNum(String billNum) {
		this.billNum = billNum;
	}

	public String getConsumerNum() {
		return consumerNum;
	}

	public void setConsumerNum(String consumerNum) {
		this.consumerNum = consumerNum;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getCurrentReading() {
		return currentReading;
	}

	public void setCurrentReading(int currentReading) {
		this.currentReading = currentReading;
	}

	public int getPreviousReading() {
		return previousReading;
	}

	public void setPreviousReading(int previousReading) {
		this.previousReading = previousReading;
	}

	public int getTotalUnits() {
		return totalUnits;
	}

	public void setTotalUnits(int totalUnits) {
		this.totalUnits = totalUnits;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double amount) {
		this.totalAmount = amount;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getPrevBalance() {
		return prevBalance;
	}

	public void setPrevBalance(double prevBalance) {
		this.prevBalance = prevBalance;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	
	public String getMeterNum() {
		return meterNum;
	}

	public void setMeterNum(String meterNum) {
		this.meterNum = meterNum;
	}


	@Override
	public String toString() {
		return "Bill : billNum = [" + billNum + "], consumerNum = [" + consumerNum + "], month = [" + month + "], totalUnits = ["
				+ totalUnits + "], currentAmount = [" + currentAmount + "], totalAmount = [" + totalAmount + "], dueDate = [" + dueDate + "], PaymentDate = [" + paymentDate+ "], status = [" + status + "]";
	}

	
}
