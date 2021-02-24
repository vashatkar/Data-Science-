package ospg.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ospg.model.*;



public class ZipcodeDao {

	protected ConnectionManager connectionManager;
	private static ZipcodeDao instance = null;
	
	
	public ZipcodeDao() {
		connectionManager = new ConnectionManager();
	}

	public static ZipcodeDao getInstance() {
		if(instance == null) {
			instance = new ZipcodeDao();
		}
		return instance;
	}
	
	
	public Zipcode create(Zipcode zpcode) throws SQLException {
		String insertZip =
				"INSERT INTO Zipcode(Zipcode,city,state,latitude,longitude) " +
				"VALUES(?,?,?,?,?);";
			Connection connection = null;
			PreparedStatement insertStmt = null;
			try {
				connection = connectionManager.getConnection();
				insertStmt = connection.prepareStatement(insertZip);
				insertStmt.setInt(1, zpcode.getZipcode());
				insertStmt.setString(2, zpcode.getCity());
				insertStmt.setString(3, zpcode.getState());
				insertStmt.setDouble(4, zpcode.getLatitude());
				insertStmt.setDouble(5, zpcode.getLongitude());
				insertStmt.executeUpdate();
				
				return zpcode;
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
				
			}
		}
	
	
	public void load() throws SQLException {
        String loadZip = "LOAD DATA INFILE ? INTO TABLE Zipcode\r\n" + 
        		"     FIELDS TERMINATED BY ',' ENCLOSED BY '\"'\r\n" + 
        		"     LINES TERMINATED BY '\\r\\n'\r\n" + 
        		"     IGNORE 1 LINES;";
        Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(loadZip);
			insertStmt.setString(1, "C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/zipcode.csv");
			insertStmt.executeUpdate();
			connection.commit();

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
		}
	}
	
	public Zipcode getZipCodeInfoByZipCode(int zipcode) throws SQLException {
		String selectZip = "SELECT * FROM Zipcode WHERE Zipcode = ?";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectZip);
			selectStmt.setInt(1, zipcode);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int Zipcode = results.getInt("Zipcode");
				String city = results.getString("city");
				String state = results.getString("state");
				double latitude = results.getDouble("latitude");
				double longitude = results.getDouble("longitude");
				Zipcode rsZipcode = new Zipcode(Zipcode,city,state,latitude,longitude);
				return rsZipcode;
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
	
	public Zipcode delete(Zipcode zipcode) throws SQLException {
		String deleteZipCode = "DELETE FROM Zipcode WHERE Zipcode=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteZipCode);
			deleteStmt.setInt(1, zipcode.getZipcode());
			deleteStmt.executeUpdate();
			connection.commit();
			
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
				
	public Zipcode updateZipcode(String city, Zipcode zp) throws SQLException {
		String updateZip = "UPDATE Zipcode SET city=? WHERE Zipcode=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateZip);
			updateStmt.setString(1, city);
			updateStmt.setInt(2, zp.getZipcode());
			updateStmt.executeUpdate();
			connection.commit();
			zp.setCity(city);
			return zp;
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
        
        
}
