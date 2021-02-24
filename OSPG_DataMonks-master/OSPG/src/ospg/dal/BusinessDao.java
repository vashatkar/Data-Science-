package ospg.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ospg.model.Business;
import ospg.model.Estate;
import ospg.model.Zipcode;

public class BusinessDao {

	protected ConnectionManager connectionManager;

	private static BusinessDao instance = null;
	protected BusinessDao() {
		connectionManager = new ConnectionManager();
	}
	public static BusinessDao getInstance() {
		if(instance == null) {
			instance = new BusinessDao();
		}
		return instance;
	}


	public Business create(Business busy) throws SQLException {
		String insertBusiness =
				"INSERT INTO Business(State,category,Business_name,rating,city,latitude,longitude,address,Zipcode_Zipcode) " +
				"VALUES(?,?,?,?,?,?,?,?,?);";
			Connection connection = null;
			PreparedStatement insertStmt = null;
			ResultSet resultKey = null;
			try {
				connection = connectionManager.getConnection();
				insertStmt = connection.prepareStatement(insertBusiness,
					Statement.RETURN_GENERATED_KEYS);
				insertStmt.setString(1, busy.getState());
				insertStmt.setString(2, busy.getCategory());
				insertStmt.setString(3, busy.getBusiness_name());
				insertStmt.setString(4, busy.getRating());
				insertStmt.setString(5, busy.getCity());
				insertStmt.setDouble(6, busy.getLatitude());
				insertStmt.setDouble(7, busy.getLongitude());
				insertStmt.setString(8, busy.getAddress());
				insertStmt.setInt(9, busy.getZipcode_fk().getZipcode());
				insertStmt.executeUpdate();
				
				// Retrieve the auto-generated key and set it, so it can be used by the caller.
				resultKey = insertStmt.getGeneratedKeys();
				int busiId = -1;
				if(resultKey.next()) {
					busiId = resultKey.getInt(1);
				} else {
					throw new SQLException("Unable to retrieve auto-generated key.");
				}
				busy.setBusiness_id(busiId);
				return busy;
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

	
	
	
	public void load() throws SQLException {
        String loadBusiness = "ALTER TABLE Business AUTO_INCREMENT = 1; LOAD DATA INFILE ? INTO TABLE Business\r\n" + 
        		"     FIELDS TERMINATED BY ',' ENCLOSED BY '\"'\r\n" + 
        		"     LINES TERMINATED BY '\\r\\n'\r\n" + 
        		"     IGNORE 1 LINES;";
        Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(loadBusiness);
			insertStmt.setString(1, "C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/business.csv");
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
	
	
	public Business getBusinessById(int business_id) throws SQLException {
		String selectRes = "SELECT * FROM Business WHERE Business_id = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRes);
			selectStmt.setInt(1, business_id);
			results = selectStmt.executeQuery();
			
			ZipcodeDao zipDao = ZipcodeDao.getInstance();
			if(results.next()) {
				int Business_id = results.getInt("Business_id");
				String State = results.getString("State");
				String category = results.getString("category");
				String Business_name = results.getString("Business_name");
				String rating = results.getString("rating");
				String city = results.getString("city");
				double latitude = results.getDouble("latitude");
				double longitude = results.getDouble("longitude");
				String address = results.getString("address");
				int zipCode = results.getInt("Zipcode_Zipcode");
				
				Zipcode zpCode = zipDao.getZipCodeInfoByZipCode(zipCode);
				
				Business busy = new Business(Business_id,State,category,Business_name,rating,city,latitude,longitude,address,zpCode);
				return busy;
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

	
	
	public Business delete(Business bsness) throws SQLException {
		String deleteBusiness = "DELETE FROM Business WHERE Business_id=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteBusiness);
			deleteStmt.setInt(1, bsness.getBusiness_id());
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
	
	public Business updateBusiness(String rating, Business bsns) throws SQLException {
		String updateBusi = "UPDATE Business SET rating=? WHERE Business_id=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateBusi);
			updateStmt.setString(1, rating);
			updateStmt.setInt(2, bsns.getBusiness_id());
			updateStmt.executeUpdate();
			connection.commit();
			bsns.setRating(rating);
			return bsns;
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
	
	public List<Business> getBusinessByZip(int zip) throws SQLException {
		List<Business> business2 = new ArrayList<Business>();
		String selectRes = "SELECT * FROM Business WHERE Zipcode_Zipcode = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRes);
			selectStmt.setInt(1, zip);
			results = selectStmt.executeQuery();
			
			ZipcodeDao zipDao = ZipcodeDao.getInstance();
			while(results.next()) {
				int Business_id = results.getInt("Business_id");
				String State = results.getString("State");
				String category = results.getString("category");
				String Business_name = results.getString("Business_name");
				String rating = results.getString("rating");
				String city = results.getString("city");
				double latitude = results.getDouble("latitude");
				double longitude = results.getDouble("longitude");
				String address = results.getString("address");
				int zipCode = results.getInt("Zipcode_Zipcode");
				
				Zipcode zpCode = zipDao.getZipCodeInfoByZipCode(zipCode);
				
				Business busy = new Business(Business_id,State,category,Business_name,rating,city,latitude,longitude,address,zpCode);
				business2.add(busy);
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
		return business2;
	}
	
}	

