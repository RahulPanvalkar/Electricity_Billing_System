package com.ebs.entities;

public class Consumer {
	private String consumerNum;
	private String consumerName;
	private String mobileNo;
	private String email;
	private String address;
	private String password;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Consumer() { }
	
	public Consumer(String consumerNum, String consumerName, String mob, String email, String password, String address) {
		this.consumerNum = consumerNum;
		this.consumerName = consumerName;
		this.mobileNo = mob;
		this.email = email;
		this.password = password;
		this.address = address;
	}
	
	public Consumer(String consumerNum, String fullName, String mob, String email, String address) {
		this.consumerNum = consumerNum;
		this.consumerName = fullName;
		this.mobileNo = mob;
		this.email = email;
		this.address = address;
	}
	
	public Consumer(String fullName, String mob, String email, String address) {
		this.consumerName = fullName;
		this.mobileNo = mob;
		this.email = email;
		this.address = address;
	}
	
	public String getConsumerNum() {
		return consumerNum;
	}

	public void setConsumerNum(String consumerNum) {
		this.consumerNum = consumerNum;
	}

	public String getConsumerName() {
		return consumerName;
	}

	public void setConsumerName(String fullName) {
		this.consumerName = fullName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String location) {
		this.address = location;
	}


	@Override
	public String toString() {
		return "Consumer [consumerNum=" + consumerNum + ", fullName=" + consumerName + ", mobileNo=" + mobileNo + ", email=" + email
				+ ", address=" + address + "\n";
	}
	
	
}
