package com.ebs.dao;

import java.io.Serializable;
import java.sql.*;

import com.ebs.entities.AnswersOfQuestions;

public class SecurityQuestionsDao  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Connection con;
	
	public SecurityQuestionsDao(Connection con) {
		this.con = con;
	}
	
	//method to get answers
	public AnswersOfQuestions getAnswers(int id) {
		AnswersOfQuestions answers= null;
		String query = "Select * from SecurityQuestions where Id=?";
		try(PreparedStatement ps = con.prepareStatement(query);) {
			ps.setInt(1, id);
			System.out.println("Query : [" + ps.toString() + "]");

			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					int userId = rs.getInt("Id");
					String birthPlace = rs.getString("Birth_Place");
					String teacher = rs.getString("Teachers_Name");
					String sport = rs.getString("Sport");

					answers = new AnswersOfQuestions(userId, birthPlace, teacher, sport);
				}
			}
		} catch (Exception e) {
			System.out.println("Exception occured in getAnswers..");
			e.printStackTrace();
		}
		
		return answers;
	}
	
}