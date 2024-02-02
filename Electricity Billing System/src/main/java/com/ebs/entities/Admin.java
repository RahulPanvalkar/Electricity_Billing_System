package com.ebs.entities;

public class Admin{
	
	private int id;
	private String firstName;
	private String lastName;
	private String password;
	private String mobNumber;
	private String email;
	private String location;
	
	public Admin() {	}
	
	public Admin(int id, String firstName, String lastName, String password, String mobNumber, String email,
			String location) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.mobNumber = mobNumber;
		this.email = email;
		this.location = location;
	}
	
	public Admin(String firstName, String lastName, String password, String mobNumber, String email,	String location) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.mobNumber = mobNumber;
		this.email = email;
		this.location = location;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobNumber() {
		return mobNumber;
	}

	public void setMobNumber(String mobNumber) {
		this.mobNumber = mobNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
}