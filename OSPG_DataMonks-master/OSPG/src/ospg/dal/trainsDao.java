package ospg.dal;
import ospg.model.Trains;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;


import ospg.dal.ConnectionManager;

public class trainsDao {
	protected ConnectionManager connectionManager;

	private static trainsDao instance = null;
	protected trainsDao() {
		connectionManager = new ConnectionManager();
	}
	public static trainsDao getInstance() {
		if(instance == null) {
			instance = new trainsDao();
		}
		return instance;
	}
	
	public Trains create(Trains train) throws SQLException {
		String inserttrain =
				"INSERT INTO Trains(station_name,\r\n" + 
						"latitude, longitude,city, Zipcode_Zipcode)"  +
						" VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			
	
			insertStmt = connection.prepareStatement(inserttrain,
					Statement.RETURN_GENERATED_KEYS);
			
			insertStmt.setString(1, train.getStaion_name());
			insertStmt.setDouble(2, train.getLatitude());
			insertStmt.setDouble(3, train.getLongitude());
			insertStmt.setString(4, train.getCity());
			insertStmt.setInt(5, train.getZipcode());
			
			
			insertStmt.executeUpdate();
			resultKey = insertStmt.getGeneratedKeys();
			int trainId = -1;
			if(resultKey.next()) {
				trainId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			train.setStation_id(trainId);;
			return train;
			
			
		
	
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
	public Trains getStationtById(int stid) throws SQLException {
		String selectstation =
				
			"SELECT *" +
			"FROM Trains " +
			"WHERE station_id=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectstation);
			selectStmt.setInt(1, stid);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int stationid = results.getInt("station_id");
				String stationname = results.getString("station_name");
				Double lat = results.getDouble("latitude");
				Double longi = results.getDouble("longitude");
				String city = results.getString("city");			
				int zip = results.getInt("Zipcode_Zipcode");				
				
				Trains t1 = new Trains(stationid, stationname,lat, longi,city, zip);
				return t1;
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
	
	public List<Trains> getStationByCity(String city) throws SQLException {
		List<Trains> train1 = new ArrayList<Trains>();
		String selecttrain =
			"SELECT station_id, station_name, latitude, longitude, city, Zipcode_Zipcode" +
			" FROM Trains " +
			"WHERE city=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selecttrain);
			selectStmt.setString(1, city);
			
			results = selectStmt.executeQuery();
			
			while(results.next()) {
				int stationid = results.getInt("station_id");
				String stationname = results.getString("station_name");
				Double lat = results.getDouble("latitude");
				Double longi = results.getDouble("longitude");
				String city1 = results.getString("city");			
				int zip = results.getInt("Zipcode_Zipcode");
				
				
				Trains t2 = new Trains(stationid, stationname,lat, longi,city1, zip);
				train1.add(t2);
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
		return train1;
	}
  
	public Trains delete(Trains train) throws SQLException {
		
		String deletetrain = "DELETE FROM Trains WHERE station_id=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deletetrain);
			deleteStmt.setInt(1, train.getStation_id());
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
	
	public Trains updateStationName(Trains train, String newstationname) throws SQLException {
		String updatetrain = "UPDATE Trains SET station_name=? WHERE station_id=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updatetrain);			
			updateStmt.setString(1, newstationname);
			updateStmt.setInt(2, train.getStation_id());
			updateStmt.executeUpdate();
			
			train.setStaion_name(newstationname);;
		
			return train;
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
	public List<Trains> getStationByZip(int zip) throws SQLException {
		List<Trains> train1 = new ArrayList<Trains>();
		String selecttrain =
			"SELECT station_id, station_name,latitude,longitude,city,Zipcode_Zipcode" +
			"FROM Trains " +
			"WHERE Zipcode_Zipcode=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selecttrain);
			selectStmt.setInt(1, zip);
			
			results = selectStmt.executeQuery();
			
			while(results.next()) {
				int stationid = results.getInt("station_id");
				String stationname = results.getString("station_name");
				Double lat = results.getDouble("latitude");
				Double longi = results.getDouble("longitude");
				String city1 = results.getString("city");			
				int zip1 = results.getInt("Zipcode_Zipcode");
				
				
				Trains t2 = new Trains(stationid, stationname,lat, longi,city1, zip1);
				train1.add(t2);
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
		return train1;
	}
}
