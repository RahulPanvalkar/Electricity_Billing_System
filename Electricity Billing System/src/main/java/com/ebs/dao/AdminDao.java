package com.ebs.dao;

import java.io.Serializable;
import java.sql.*;

import com.ebs.entities.Admin;
import com.ebs.entities.User;
import com.ebs.utility.DBConnectionManager;

public class AdminDao  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Connection con;
	
	public AdminDao(Connection con) {
		this.con = con;
	}
	
	//method to get admin data
	public Admin getAdmin(int id, String password) {
		Admin admin = null;
		String query = "Select * from AdminDetails where Id=? and Password=?";
		try (PreparedStatement ps = con.prepareStatement(query);) {
			ps.setInt(1, id);
			ps.setString(2, password);

			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					admin = new Admin();
					admin.setId(rs.getInt("Id"));
					admin.setFirstName(rs.getString("First_Name"));
					admin.setLastName(rs.getString("Last_Name"));
					admin.setPassword(rs.getString("Password"));
					admin.setEmail(rs.getString("Email"));
					admin.setMobNumber(rs.getString("Mob_Number"));
					admin.setLocation(rs.getString("Location"));
				}
			}
		} catch (Exception e) {
			System.out.println("Exception occured in getAdmin.. " );
			e.printStackTrace();
		} finally {
			DBConnectionManager.closeConnection(con);
		}

		return admin;
	}
	
	//method to get a User
		public User getUser(Admin admin) {
			User User = null;
			System.out.println("AdminId : " + admin.getId());
			String query = "SELECT * FROM AdminDetails WHERE Id = ?";
			
			try (PreparedStatement ps = con.prepareStatement(query);) {
				ps.setInt(1, admin.getId());
				System.out.println("Query : [" + ps.toString() + "]");

				try (ResultSet rs = ps.executeQuery();) {
					if (rs.next()) {
						int id = rs.getInt("Id");
						String fullName = rs.getString("First_Name") + " " + rs.getString("Last_Name");
						String email = rs.getString("Email");
						String mob = rs.getString("Mob_Number");
						String location = rs.getString("Location");

						User = new User(id, fullName, mob, email, location);
					}
				}
			} catch (Exception e) {
				System.out.println("Exception occured in getUser... ");
				e.printStackTrace();
			} finally {
				DBConnectionManager.closeConnection(con);
			}

			return User;
		}
		
		// update password
		public boolean updatePassword(int id, String password) {
			boolean hasUpdated = false;
			
			String query = "UPDATE AdminDetails SET Password = ? WHERE Id = ?";
			try(PreparedStatement ps = con.prepareStatement(query);) {
				ps.setString(1, password);
				ps.setInt(2, id);
				System.out.println("Query : [" + ps.toString() + "]");
				
				int rowCount = ps.executeUpdate();
				System.out.println("rowCount : "+rowCount);
				if (rowCount == 1) {
					System.out.println("Admin password updated!");
					hasUpdated = true;
				}
				
			} catch (Exception e) {
				System.out.println("Exception occured in updatePassword.." );
				e.printStackTrace();
			} finally {
				DBConnectionManager.closeConnection(con);
			}
			
			return hasUpdated; 
		}
	
		// method to update Admin Details
		public boolean updatedAdminDetails(User user) {
			
			if (user == null) {
				return false;
			}
			String fullName = user.getFullName();

			 //Split the full name into first name and last name
			String[] nameParts = fullName.split(" ");
			String firstName = nameParts[0];
			String lastName = nameParts[1];

			boolean hasUpdated = false;
			String query = "UPDATE AdminDetails SET Email = ?, First_Name = ?, Last_Name = ?, Mob_Number = ? WHERE Id = ?";
			try(PreparedStatement ps = con.prepareStatement(query);) {
				ps.setString(1, user.getEmail());
				ps.setString(2, firstName);
				ps.setString(3, lastName);
				ps.setString(4, user.getMob());
				ps.setInt(5, user.getAdminId());
				System.out.println("Query : [" + ps.toString() + "]");
				
				int rowCount = ps.executeUpdate();
				if (rowCount == 1) {
					System.out.println("Admin details updated!");
					hasUpdated = true;
				}
			} catch (Exception e) {
				System.out.println("Exception occured in updatePassword.. " );
				e.printStackTrace();
			} 

			return hasUpdated;
		}
		
		// get admin password
public String getAdminPassword(int id) {
			String password = "";
			String query = "SELECT Password from AdminDetails WHERE Id = ?";
			try(PreparedStatement ps = con.prepareStatement(query);) {
				ps.setInt(1, id);
				System.out.println("Query : [" + ps.toString() + "]");
				
				try (ResultSet rs = ps.executeQuery();) {
					if (rs.next()) {
						password = rs.getString("Password");
					}
				}
			} catch (Exception e) {
				System.out.println("Exception occured in getAdminPassword.. " );
				e.printStackTrace();
			} 

			return password;
		}
		

}