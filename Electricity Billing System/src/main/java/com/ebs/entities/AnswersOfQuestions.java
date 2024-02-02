package com.ebs.entities;

public class AnswersOfQuestions {
	private int id;
	private String birthPlace; 
	private String teachersName;
	private String sport;

	public AnswersOfQuestions(int id, String birthPlace, String teachersName, String sport) {
		this.id = id;
		this.birthPlace = birthPlace;
		this.teachersName = teachersName;
		this.sport = sport;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public String getTeachersName() {
		return teachersName;
	}

	public void setTeachersName(String teachersName) {
		this.teachersName = teachersName;
	}

	public String getSport() {
		return sport;
	}

	public void setSport(String sport) {
		this.sport = sport;
	}
	
}
