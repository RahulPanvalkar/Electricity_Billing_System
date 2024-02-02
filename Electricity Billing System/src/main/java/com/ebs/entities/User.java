package com.ebs.entities;

public class User {
	private String consumerNum;
	private String fullName;
	private String mob;
	private String email;
	private String address;
	private int adminId;
	
	// for Consumer
	public User(String consumerNum, String fullName, String mob, String email,String address) {
		this.consumerNum = consumerNum;
		this.fullName = fullName;
		this.mob = mob;
		this.email = email;
		this.address = address;
	}
	
	// for Admin
	public User(int adminId, String fullName, String mob, String email, String address) {
		this.adminId = adminId;
		this.fullName = fullName;
		this.mob = mob;
		this.email = email;
		this.address = address;
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

	public String getMob() {
		return mob;
	}

	public void setMob(String mob) {
		this.mob = mob;
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

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int id) {
		this.adminId = id;
	}

	@Override
	public String toString() {
		return "User : [consumerNum=" + consumerNum + ", fullName=" + fullName + ", mob=" + mob + ", email=" + email
				+ ", location=" + address + ", adminId=" +adminId + "]\n";
	}
	
	
}
