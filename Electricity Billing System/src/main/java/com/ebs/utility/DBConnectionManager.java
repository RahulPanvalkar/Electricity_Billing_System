package com.ebs.utility;

import java.sql.*;

public class DBConnectionManager {
	private static Connection con;

	public static Connection getConnection() {
		try {
			// Load the MySQL JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish the database connection
			String url = "jdbc:mysql://localhost:3306/EBS";

			con = DriverManager.getConnection(url, "root", "root");

			System.out.println("Connection Successful!");
		} catch (Exception e) {
			System.out.println("Exception occurred in getConnection => " + e);
		}

		return con;
	}
	
	public static void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
				System.out.println("Connection closed!");
			} catch (SQLException e) {
				System.out.println("SQLException occured while closing connection.. " );
				e.getStackTrace();
			}
		}
	}

}
