package com.ebs.entities;

import java.io.Serializable;
import java.sql.Date;

public class EConnection implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String consumerNum;
	private String meter;
	private String fullName;
	private String mob;
	private String address;
	private Date startDate;
	private String connType;
	
	
	
	public EConnection() {
		
	}

	public EConnection(String consumerNum, String meter, String fullName, String mob, String address, Date startDate,
			String connType) {
		this.consumerNum = consumerNum;
		this.fullName=fullName;
		this.meter = meter;
		this.mob = mob;
		this.address = address;
		this.startDate = startDate;
		this.connType = connType;
	}

	public String getConsumerNum() {
		return consumerNum;
	}

	public void setConsumerNum(String consumerNum) {
		this.consumerNum = consumerNum;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getMeter() {
		return meter;
	}

	public void setMeter(String meter) {
		this.meter = meter;
	}

	public String getMob() {
		return mob;
	}

	public void setMob(String mob) {
		this.mob = mob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getConnType() {
		return connType;
	}

	public void setConnType(String connType) {
		this.connType = connType;
	}
	

	@Override
	public String toString() {
		return "Connection : consumerNum = [" + consumerNum + "], meterNo = [" + meter + "], fullName = [" + fullName + "], mob = ["
				+ mob + "], address = [" + address + "], startDate = [" + startDate + "], connType = [" + connType + "] \n";
	}

	
}
