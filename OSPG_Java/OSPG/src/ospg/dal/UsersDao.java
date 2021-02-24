package ospg.dal;
import ospg.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;





public class UsersDao {
  
	protected ConnectionManager connectionManager;
	private static UsersDao instance = null;
	
	protected UsersDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static UsersDao getInstance() {
		if(instance == null) {
			instance = new UsersDao();
		}
		return instance;
	}

	public Users create(Users user) throws SQLException {
		String insertUser = "INSERT INTO Users(password,first_name,last_name,email,phone,type) VALUES (?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertUser, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, user.getPassword());
			insertStmt.setString(2, user.getFirst_name());
			insertStmt.setString(3, user.getLast_name());
			insertStmt.setString(4, user.getEmail());
			insertStmt.setString(5, user.getPhone());
			insertStmt.setString(6, user.getType());
			
			insertStmt.executeUpdate();
			connection.commit();
			resultKey = insertStmt.getGeneratedKeys();
			int userId = -1;
			if(resultKey.next()) {
				userId = resultKey.getInt(1);
				System.out.println(userId);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			user.setUser_id(userId);
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
			if(resultKey != null) {
				resultKey.close();
			}
		}
	}

	

public Users getUserByUserId(int user_id) throws SQLException {
	String selectUser = "SELECT User_id,password,first_name,last_name,email,phone,type FROM Users WHERE User_id = ?;";
	Connection connection = null;
	PreparedStatement selectStmt = null;
	ResultSet results = null;
	try {
		connection = connectionManager.getConnection();
		selectStmt = connection.prepareStatement(selectUser);
		selectStmt.setInt(1, user_id);
		results = selectStmt.executeQuery();
		if(results.next()) {
			int resultUserId = results.getInt("User_id");
			String password = results.getString("password");
			String firstName = results.getString("first_name");
			String lastName = results.getString("last_name");
			String email = results.getString("email");
			String phone = results.getString("phone");
			String type = results.getString("type");
			
			Users user = new Users (resultUserId,password,firstName,lastName,email,phone,type);
			return user;
			}
	} catch (SQLException e) {
		e.printStackTrace();
		throw e;
	} finally {
		if(connection != null) {
			connection.close();
		}
		if(selectStmt != null) {
			selectStmt.close();
		}
		if(results != null) {
			results.close();
		}
	}
	return null;
}
	
public Users updateUser(String password, Users user) throws SQLException {
	String updateUser = "UPDATE Users SET password=? WHERE User_id=?;";
	Connection connection = null;
	PreparedStatement updateStmt = null;
	try {
		connection = connectionManager.getConnection();
		updateStmt = connection.prepareStatement(updateUser);
		updateStmt.setString(1, password);
		updateStmt.setInt(2, user.getUser_id());
		updateStmt.executeUpdate();
		user.setPassword(password);
	
		return user;
	} catch (SQLException e) {
		e.printStackTrace();
		throw e;
	} finally {
		if(connection != null) {
			connection.close();
		}
		if(updateStmt != null) {
			updateStmt.close();
		}
	}
}


public Users delete(Users user) throws SQLException {
	String deleteUser = "DELETE FROM Users WHERE User_id=?;";
	Connection connection = null;
	PreparedStatement deleteStmt = null;
	try {
		connection = connectionManager.getConnection();
		deleteStmt = connection.prepareStatement(deleteUser);
		deleteStmt.setInt(1, user.getUser_id());
		int affectedRows = deleteStmt.executeUpdate();
		if (affectedRows == 0) {
			throw new SQLException("No records available to delete for UserId=" + user.getUser_id());
		}
   return null;
} catch (SQLException e) {
	e.printStackTrace();
	throw e;
} finally {
	if(connection != null) {
		connection.close();
	}
	if(deleteStmt != null) {
		deleteStmt.close();
	}
}
}

}
