package ospg.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ospg.model.Estate;
import ospg.model.University;
import ospg.model.Zipcode;

public class UniversityDao {

	protected ConnectionManager connectionManager;

	private static UniversityDao instance = null;
	protected UniversityDao() {
		connectionManager = new ConnectionManager();
	}
	public static UniversityDao getInstance() {
		if(instance == null) {
			instance = new UniversityDao();
		}
		return instance;
	}

	
	public University create(University univ) throws SQLException {
		String insertUniv =
				"INSERT INTO University(name,address,city,state,phone,latitude,longitude,website,Zipcode_Zipcode) " +
				"VALUES(?,?,?,?,?,?,?,?,?);";
			Connection connection = null;
			PreparedStatement insertStmt = null;
			ResultSet resultKey = null;
			try {
				connection = connectionManager.getConnection();
				insertStmt = connection.prepareStatement(insertUniv,
					Statement.RETURN_GENERATED_KEYS);
				insertStmt.setString(1, univ.getName());
				insertStmt.setString(2, univ.getAddress());
				insertStmt.setString(3, univ.getCity());
				insertStmt.setString(4, univ.getState());
				insertStmt.setString(5, univ.getPhone());
				insertStmt.setDouble(6, univ.getLatitude());
				insertStmt.setDouble(7, univ.getLongitude());
				insertStmt.setString(8, univ.getWebsite());
				insertStmt.setInt(9, univ.getZipcode_fk().getZipcode());
				insertStmt.executeUpdate();
				
				// Retrieve the auto-generated key and set it, so it can be used by the caller.
				resultKey = insertStmt.getGeneratedKeys();
				int univId = -1;
				if(resultKey.next()) {
					univId = resultKey.getInt(1);
				} else {
					throw new SQLException("Unable to retrieve auto-generated key.");
				}
				univ.setUniversity_id(univId);
				return univ;
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
        String loadUniversity = "ALTER TABLE University AUTO_INCREMENT = 1; LOAD DATA INFILE ? INTO TABLE University\r\n" + 
        		"     FIELDS TERMINATED BY ',' ENCLOSED BY '\"'\r\n" + 
        		"     LINES TERMINATED BY '\\r\\n'\r\n" + 
        		"     IGNORE 1 LINES;";
        Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(loadUniversity);
			insertStmt.setString(1, "C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/University.csv");
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

	public University getUniversityById(int univId) throws SQLException {
		String selectUniv = "SELECT * FROM University WHERE University_id = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUniv);
			selectStmt.setInt(1, univId);
			results = selectStmt.executeQuery();
			
			ZipcodeDao zipDao = ZipcodeDao.getInstance();
			
			if(results.next()) {
				int University_id = results.getInt("University_id");
				String name = results.getString("name");
				String address = results.getString("address");
				String city = results.getString("city");
				String state = results.getString("state");
				String phone = results.getString("phone");
				double latitude = results.getDouble("latitude");
				double longitude = results.getDouble("longitude");
				String website = results.getString("website");
				int Zipcode_Zipcode = results.getInt("Zipcode_Zipcode");
				
				Zipcode zip = zipDao.getZipCodeInfoByZipCode(Zipcode_Zipcode);
								
				University univ = new University(University_id,name,address,city,state,phone,latitude,longitude,website,zip);
				return univ;
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



	public University updateUniversity(String website, University univ) throws SQLException {
		String updateUniversity = "UPDATE University SET website=? WHERE University_id=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateUniversity);
			updateStmt.setString(1, website);
			updateStmt.setInt(2, univ.getUniversity_id());
			updateStmt.executeUpdate();
			connection.commit();
			univ.setWebsite(website);
			return univ;
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



	public University delete(University univrsty) throws SQLException {
		String deleteDisastrs = "DELETE FROM University WHERE University_id=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteDisastrs);
			deleteStmt.setInt(1, univrsty.getUniversity_id());
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

	public List<University> getUniversityByZip(int zip) throws SQLException {
		List<University> university = new ArrayList<University>();
		String selectUniv = "SELECT * FROM University WHERE Zipcode_Zipcode = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUniv);
			selectStmt.setInt(1, zip);
			results = selectStmt.executeQuery();
			
			ZipcodeDao zipDao = ZipcodeDao.getInstance();
			
			while(results.next()) {
				int University_id = results.getInt("University_id");
				String name = results.getString("name");
				String address = results.getString("address");
				String city = results.getString("city");
				String state = results.getString("state");
				String phone = results.getString("phone");
				double latitude = results.getDouble("latitude");
				double longitude = results.getDouble("longitude");
				String website = results.getString("website");
				int Zipcode_Zipcode = results.getInt("Zipcode_Zipcode");
				
				Zipcode zip1 = zipDao.getZipCodeInfoByZipCode(Zipcode_Zipcode);
								
				University univ = new University(University_id,name,address,city,state,phone,latitude,longitude,website,zip1);
				university.add(univ);
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
		return university;
	}












}
