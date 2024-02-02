package com.ebs.dao;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import com.ebs.entities.Consumer;
import com.ebs.entities.User;
import com.ebs.utility.DBConnectionManager;

public class ConsumerDao implements Serializable {
	private static final long serialVersionUID = 1L;

	private Connection con;

	public ConsumerDao() {	}
	public ConsumerDao(Connection con) {
		this.con = con;
	}

	// method to get a Consumer
	public Consumer getConsumer(String userId, String password, String credentialType) {
		System.out.println("Inside ConsumerDao => getConsumer..");
		String query = "Select * from ConsumerDetails where Email=? and Password=?";

		if (credentialType.equalsIgnoreCase("consumerNumber")) {
			query = "Select * from ConsumerDetails where Consumer_Num=? and Password=?";
		}

		Consumer consumer = null;
		try(PreparedStatement ps = con.prepareStatement(query);) {
			ps.setString(1, userId);
			ps.setString(2, password);
			System.out.println("Query : [" + ps.toString()+"]");

			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					consumer = new Consumer();

					consumer.setConsumerNum(rs.getString("Consumer_Num"));
					consumer.setConsumerName(rs.getString("Full_Name"));
					consumer.setEmail(rs.getString("Email"));
					consumer.setMobileNo(rs.getString("Mob_Number"));
					consumer.setAddress(rs.getString("Address"));
				}
			}
		} catch (Exception e) {
			System.out.println("Exception occured in getConsumer..");
			e.printStackTrace();
		} finally {
			DBConnectionManager.closeConnection(con);
		}

		return consumer;
	}

	// method to get Consumer by Consumer_num
	public Consumer getConsumer(String consumerNum) {

		Consumer Consumer = null;
		String query = "Select * from ConsumerDetails where Consumer_Num=?";
		try(PreparedStatement ps = con.prepareStatement(query);) {
			ps.setString(1, consumerNum);
			System.out.println("Query : [" + ps.toString()+"]");

			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					Consumer = new Consumer();

					Consumer.setConsumerNum(rs.getString("Consumer_Num"));
					Consumer.setConsumerName(rs.getString("Full_Name"));
					Consumer.setEmail(rs.getString("Email"));
					Consumer.setMobileNo(rs.getString("Mob_Number"));
					Consumer.setAddress(rs.getString("Address"));
				}
			}
		} catch (Exception e) {
			System.out.println("Exception occured in getConsumer..");
			e.printStackTrace();
		} finally {
			DBConnectionManager.closeConnection(con);
		}

		return Consumer;
	}

	// method to get Consumer by Consumer_Num and Mob_Number
	public boolean validationByConsumerNumAndMobNumber(String consNumber, String mobNumber) {
		boolean isValid = false;
		String query = "Select * from ConsumerDetails where Consumer_Num=? and Mob_number = ?";
		try (PreparedStatement ps = con.prepareStatement(query);) {
			ps.setString(1, consNumber);
			ps.setString(2, mobNumber);
			System.out.println("Query : [" + ps.toString() + "]");

			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					isValid = true;
				}
			}
		} catch (Exception e) {
			System.out.println("Exception occured in validationByConsumerNumAndMobNumber..");
			e.printStackTrace();
		} finally {
			DBConnectionManager.closeConnection(con);
		}

		return isValid;
	}

	// method to update consumer password
	public boolean resetPassword(String consNumber, String pass) {
		boolean hasUpdated = false;
		String query = "UPDATE ConsumerDetails SET Password=? WHERE Consumer_Num = ?";
		try(PreparedStatement ps = con.prepareStatement(query);) {
			ps.setString(1, pass);
			ps.setString(2, consNumber);
			System.out.println("Query : [" + ps.toString()+"]");
			
			int rowCount = ps.executeUpdate();
			if (rowCount == 1) {
				hasUpdated = true;
			}
			
		} catch (Exception e) {
			System.out.println("Exception occured in resetPassword..");
			e.printStackTrace();
		} finally {
			DBConnectionManager.closeConnection(con);
		}
		return hasUpdated;
	}

	//method to validate consumerNo
	public boolean validateConsumer(String consumerNum) {
	    String query = "SELECT * FROM ConsumerDetails WHERE Consumer_Num=?";
	    
	    try (Connection connection = DBConnectionManager.getConnection();
	         PreparedStatement ps = connection.prepareStatement(query)) {
	        ps.setString(1, consumerNum);
	        System.out.println("Query: [" + ps.toString() + "]");

	        try (ResultSet rs = ps.executeQuery()) {
	            return rs.next();
	        }
	    } catch (Exception e) {
	        System.out.println("Exception occurred in validateConsumer..");
	        e.printStackTrace();
	    } 

	    return false;
	}

	
	// method to get all consumers
	public ArrayList<Consumer> getAllConsumers() {
		ArrayList<Consumer> consumers = new ArrayList<>();

		String query = "select * from ConsumerDetails";
		try(PreparedStatement ps = con.prepareStatement(query);) {
			System.out.println("Query : [" + ps.toString()+"]");
			
			try(ResultSet rs = ps.executeQuery();){
				while (rs.next()) {
					String consumerNum = rs.getString("Consumer_Num");
					String consumerName = rs.getString("Full_Name");
					String mob = rs.getString("Mob_Number");
					String email = rs.getString("Email");
					String address = rs.getString("Address");

					consumers.add(new Consumer(consumerNum, consumerName, mob, email, address));
				}
			}
		} catch (Exception e) {
			System.out.println("Exception occured in getAllConsumers..");
			e.printStackTrace();
		} finally {
			DBConnectionManager.closeConnection(con);
		}

		return consumers;
	}

	// method to add Consumer
	public boolean addConsumer(Consumer consumer) {
		System.out.println("Inside addConsumer.. [" + consumer + "]");
		if (consumer == null) {
			return false;
		}
		boolean isAdded = false;

		String query = "INSERT INTO ConsumerDetails (Full_Name, Email, Mob_Number, Address, Password) VALUES (?,?,?,?,?)";
		try (PreparedStatement ps = con.prepareStatement(query);) {
			ps.setString(1, consumer.getConsumerName());
			ps.setString(2, consumer.getEmail());
			ps.setString(3, consumer.getMobileNo());
			ps.setString(4, consumer.getAddress());
			ps.setString(5, "Pass1234");
			System.out.println("Query : [" + ps.toString() + "]");

			int rowCount = ps.executeUpdate();
			System.out.println("rowCount : [" + rowCount + "]");
			if (rowCount == 1) {
				isAdded = true;
			}

		} catch (Exception e) {
			System.out.println("Exception occured in addConsumer..");
			e.printStackTrace();
		} finally {
			DBConnectionManager.closeConnection(con);
		}

		return isAdded;
	}

	// method to get a consumer
	public HashMap<String, String> getConsumerNoMap(String consumerNo) {
		HashMap<String, String> consumerDataMap = new HashMap<>();
		String query = "select * from ConsumerDetails where Consumer_Num=?";
		try (PreparedStatement ps = con.prepareStatement(query);) {
			ps.setString(1, consumerNo);
			System.out.println("Query : [" + ps.toString() + "]");

			try (ResultSet rs = ps.executeQuery();) {
				ResultSetMetaData metaData = rs.getMetaData();
				int count = metaData.getColumnCount();
				System.out.println("columnCount : " + count);

				if (rs.next()) {
					for (int i = 1; i <= count; i++) {
						String columnName = metaData.getColumnName(i);

						if (columnName.equalsIgnoreCase("Full_Name")) {
							String columnValue = rs.getString(i);
							consumerDataMap.put(columnName, columnValue);
						} else if (columnName.equalsIgnoreCase("Mob_Number")) {
							String columnValue = rs.getString(i);
							consumerDataMap.put(columnName, columnValue);
						} else if (columnName.equalsIgnoreCase("Address")) {
							String columnValue = rs.getString(i);
							consumerDataMap.put(columnName, columnValue);
						}
					}
				} else {
					System.out.println("No rows in the result set");
				}
			}
		} catch (Exception e) {
			System.out.println("Exception occured in getConsumerNoMap..");
			e.printStackTrace();
		} finally {
			DBConnectionManager.closeConnection(con);
		}

		return consumerDataMap;
	}

	//method to get a User
		public User getUser(Consumer consumer) {
			User User = null;
			String query = "select * from ConsumerDetails where Consumer_Num=?";
			try (PreparedStatement ps = con.prepareStatement(query);) {
				ps.setString(1, consumer.getConsumerNum());
				System.out.println("Query : [" + ps.toString() + "]");

				try (ResultSet rs = ps.executeQuery();) {
					if (rs.next()) {
						String consumerNum = rs.getString("Consumer_Num");
						String name = rs.getString("Full_Name");
						String email = rs.getString("Email");
						String mob = rs.getString("Mob_Number");
						String address = rs.getString("Address");

						User = new User(consumerNum, name, mob, email, address);
					}
				}
			} catch (Exception e) {
				System.out.println("Exception occured in getUser..");
				e.printStackTrace();
			} finally {
				DBConnectionManager.closeConnection(con);
			}

			return User;
		}
	
	// method to update Consumer
	public boolean updateConsumerDetails(User user) {
		if (user == null) {
			return false;
		}
		boolean hasUpdated = false;
		String query = "UPDATE ConsumerDetails SET Email = ?,  Full_Name = ?,   Mob_Number = ? WHERE Consumer_Num = ?";
		try(PreparedStatement ps = con.prepareStatement(query);) {
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getFullName());
			ps.setString(3, user.getMob());
			ps.setString(4, user.getConsumerNum());
			System.out.println("Query : [" + ps.toString()+"]");
			
			int rowCount = ps.executeUpdate();
			System.out.println("rowCount : [" + rowCount+"]");
			if (rowCount == 1) {
				hasUpdated = true;
			}
		} catch (Exception e) {
			System.out.println("Exception occured in updateConsumerDetails..");
			e.printStackTrace();
		} finally {
			DBConnectionManager.closeConnection(con);
		}

		return hasUpdated;
	}
	
	// method to remove Consumer
	public boolean removeConsumer(String consumerNo) {
		boolean isRemoved = false;
		try {
			String query = "DELETE FROM ConsumerDetails WHERE Consumer_Num = ?";
			
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, consumerNo);
			System.out.println("Query : [" + ps.toString()+"]");
			
			int rowCount = ps.executeUpdate();
			System.out.println("rowCount : [" + rowCount+"]");
			if (rowCount == 1) {
				isRemoved = true;
			}

		} catch (Exception e) {
			System.out.println("Exception occured in removeConsumer..");
			e.printStackTrace();
		} finally {
			DBConnectionManager.closeConnection(con);
		}

		return isRemoved;
	
	}
	
	// method to get consumer password
		public String getMobileNumber(String consumerNo) {
			String password = "";
			String query = "SELECT Password from ConsumerDetails WHERE Consumer_Num = ?";
			try(PreparedStatement ps = con.prepareStatement(query);) {
				ps.setString(1, consumerNo);
				System.out.println("Query : [" + ps.toString() + "]");
				
				try (ResultSet rs = ps.executeQuery();) {
					if (rs.next()) {
						password = rs.getString("Password");
					}
				}
			} catch (Exception e) {
				System.out.println("Exception occured in getMobileNumber.. " );
				e.printStackTrace();
			} 

			return password;
		}
	
	
	// method to get consumer password
	public String getConsumerPassword(String consumerNo) {
		String password = "";
		String query = "SELECT Password from ConsumerDetails WHERE Consumer_Num = ?";
		try(PreparedStatement ps = con.prepareStatement(query);) {
			ps.setString(1, consumerNo);
			System.out.println("Query : [" + ps.toString() + "]");
			
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					password = rs.getString("Password");
				}
			}
		} catch (Exception e) {
			System.out.println("Exception occured in getConsumerPassword.. " );
			e.printStackTrace();
		} 

		return password;
	}
	
	public boolean checkEmailAndMobile(String email, String mobile) {
		boolean isAlreadyPresent = false;
		try {
			String qString = "SELECT * FROM ConsumerDetails WHERE email=? OR mob_number=?";
			try (PreparedStatement pStatement = con.prepareStatement(qString);) {
				pStatement.setString(1, email);
				pStatement.setString(2, mobile);
				System.out.println("Query : [" + pStatement.toString() + "]");

				try (ResultSet rs = pStatement.executeQuery();) {
					if (rs.next()) {
						System.out.println("consumer found for email or mobile..");
						isAlreadyPresent = true;
					}
				}
			}

		} catch (Exception e) {
			System.out.println("Exception occured in updateConsumerDetails..");
			e.printStackTrace();
		}
		return isAlreadyPresent;
	}
}
