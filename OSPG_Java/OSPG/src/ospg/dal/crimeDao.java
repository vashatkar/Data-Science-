package ospg.dal;
import ospg.model.Estate;
import ospg.model.crime;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;


import ospg.dal.ConnectionManager;

public class crimeDao {
	
	

	protected ConnectionManager connectionManager;

	private static crimeDao instance = null;
	protected crimeDao() {
		connectionManager = new ConnectionManager();
	}
	public static crimeDao getInstance() {
		if(instance == null) {
			instance = new crimeDao();
		}
		return instance;
	}
	public crime create(crime cri) throws SQLException {
		String insertcrime =
				"INSERT INTO crime(crime_type,   city,\r\n" + 
						"state, latitude, longitude, Zipcode"  +
						"VALUES(?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			
	
			insertStmt = connection.prepareStatement(insertcrime,
					Statement.RETURN_GENERATED_KEYS);
			
			insertStmt.setString(1, cri.getCrime_type());
			insertStmt.setString(2, cri.getCity());
			insertStmt.setString(3, cri.getState());
			insertStmt.setDouble(4, cri.getLatitude());
			insertStmt.setDouble(5, cri.getLongitude());
			insertStmt.setInt(6, cri.getZipcode());
			
			
			insertStmt.executeUpdate();
			resultKey = insertStmt.getGeneratedKeys();
			int crimId = -1;
			if(resultKey.next()) {
				crimId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			cri.setCrime_id(crimId);
			return cri;
			
			
		
	
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
	
	 public crime getCrimetById(int crimid) throws SQLException {
		String selectcrim =
				
			"SELECT *" +
			"FROM crime " +
			"WHERE Crime_Id=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectcrim);
			selectStmt.setInt(1, crimid);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int resultcrimeid = results.getInt("crime_id");
				String crimetype = results.getString("crime_type");
				String city = results.getString("city");
				String state = results.getString("state");
				Double lat = results.getDouble("latitude");
				Double longi = results.getDouble("longitude");
				int zip = results.getInt("zipcode");				
				
				crime cri1 = new crime(resultcrimeid, crimetype, city, state,
						lat, longi, zip);
				return cri1;
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
	  public List<crime> getCrimeByType(String critype) throws SQLException {
			List<crime> crimes = new ArrayList<crime>();
			String selectcrimes =
				"SELECT crime_id, crime_type,city,state,latitude,longitude" +
				"FROM crime " +
				"WHERE crime_type=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectcrimes);
				selectStmt.setString(1, critype);
				
				results = selectStmt.executeQuery();
				
				while(results.next()) {
					int resultcrimeid = results.getInt("crime_id");
					String crimetype = results.getString("crime_type");
					String city = results.getString("city");
					String state = results.getString("state");
					Double lat = results.getDouble("latitude");
					Double longi = results.getDouble("longitude");
					int zip = results.getInt("zipcode");
					
					
					crime cri2 = new crime(resultcrimeid, crimetype, city, state,
							lat, longi, zip);
					crimes.add(cri2);
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
			return crimes;
		}
	  
	  public crime delete(crime crim) throws SQLException {
			
			String deletecrime = "DELETE FROM crime WHERE crime_id=?;";
			Connection connection = null;
			PreparedStatement deleteStmt = null;
			try {
				connection = connectionManager.getConnection();
				deleteStmt = connection.prepareStatement(deletecrime);
				deleteStmt.setInt(1, crim.getCrime_id());
				deleteStmt.executeUpdate();

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
	  
	  public crime updateCrimetype(crime cri3, String newcrimetype) throws SQLException {
			String updatecrime = "UPDATE crime SET crime_type=? WHERE crime_id=?;";
			Connection connection = null;
			PreparedStatement updateStmt = null;
			try {
				connection = connectionManager.getConnection();
				updateStmt = connection.prepareStatement(updatecrime);
				updateStmt.setInt(1, cri3.getCrime_id());
				updateStmt.setString(2, newcrimetype);
				
				updateStmt.executeUpdate();
				
				cri3.setCrime_type(newcrimetype);
			
				return cri3;
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
	  public List<crime> getCrimetByZip(int zip) throws SQLException {
		  List<crime> cri = new ArrayList<crime>();
			String selectcrim =
					
				"SELECT *" +
				"FROM crime " +
				"WHERE Zipcode_Zipcode=?;";
			
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectcrim);
				selectStmt.setInt(1, zip);
				results = selectStmt.executeQuery();
				while(results.next()) {
					int resultcrimeid = results.getInt("crime_id");
					String crimetype = results.getString("crime_type");
					String city = results.getString("city");
					String state = results.getString("state");
					Double lat = results.getDouble("latitude");
					Double longi = results.getDouble("longitude");
					int zip1 = results.getInt("Zipcode_Zipcode");				
					
					crime cri1 = new crime(resultcrimeid, crimetype, city, state,
							lat, longi, zip1);
					cri.add(cri1);
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
			return cri;
		}
	
}