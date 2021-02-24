package ospg.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ospg.model.Business;
import ospg.model.Disasters;


public class DisastersDao {

	protected ConnectionManager connectionManager;

	private static DisastersDao instance = null;
	protected DisastersDao() {
		connectionManager = new ConnectionManager();
	}
	public static DisastersDao getInstance() {
		if(instance == null) {
			instance = new DisastersDao();
		}
		return instance;
	}
	
	public Disasters create(Disasters dstr) throws SQLException {
		String insertDisaster =
				"INSERT INTO Disasters(state,disaster_title) " +
				"VALUES(?,?);";
			Connection connection = null;
			PreparedStatement insertStmt = null;
			ResultSet resultKey = null;
			try {
				connection = connectionManager.getConnection();
				insertStmt = connection.prepareStatement(insertDisaster,
					Statement.RETURN_GENERATED_KEYS);
				insertStmt.setString(1, dstr.getState());
				insertStmt.setString(2, dstr.getDisaster_title());
				insertStmt.executeUpdate();
				
				// Retrieve the auto-generated key and set it, so it can be used by the caller.
				resultKey = insertStmt.getGeneratedKeys();
				int dstrId = -1;
				if(resultKey.next()) {
					dstrId = resultKey.getInt(1);
				} else {
					throw new SQLException("Unable to retrieve auto-generated key.");
				}
				dstr.setDisaster_id(dstrId);
				return dstr;
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
        String loadBusiness = "ALTER TABLE Disasters AUTO_INCREMENT = 1; LOAD DATA INFILE ? INTO TABLE Disasters\r\n" + 
        		"     FIELDS TERMINATED BY ',' ENCLOSED BY '\"'\r\n" + 
        		"     LINES TERMINATED BY '\\r\\n'\r\n" + 
        		"     IGNORE 1 LINES;";
        Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(loadBusiness);
			insertStmt.setString(1, "C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/disasters_data.csv");
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
	
	public Disasters getDisasterById(int disaster_id) throws SQLException {
		String selectRes = "SELECT * FROM Disasters WHERE Disaster_id = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRes);
			selectStmt.setInt(1, disaster_id);
			results = selectStmt.executeQuery();
			
			
			if(results.next()) {
				int Disaster_id = results.getInt("Disaster_id");
				String state = results.getString("state");
				String disaster_title = results.getString("disaster_title");
								
				Disasters dstr = new Disasters(Disaster_id,state,disaster_title);
				return dstr;
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
	
	
	public Disasters updateDisaster(String title, Disasters dis) throws SQLException {
		String updateDisaster = "UPDATE Disasters SET disaster_title=? WHERE Disaster_id=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateDisaster);
			updateStmt.setString(1, title);
			updateStmt.setInt(2, dis.getDisaster_id());
			updateStmt.executeUpdate();
			connection.commit();
			dis.setDisaster_title(title);
			return dis;
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
	
	public Disasters delete(Disasters distr) throws SQLException {
		String deleteDisastrs = "DELETE FROM Disasters WHERE Disaster_id=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteDisastrs);
			deleteStmt.setInt(1, distr.getDisaster_id());
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
	
	public List<Disasters> getDisasterByCity(String state1) throws SQLException {
		List<Disasters> d1 = new ArrayList<Disasters>();
		String selectRes = "SELECT * FROM Disasters WHERE state = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRes);
			selectStmt.setString(1, state1);
			results = selectStmt.executeQuery();
			
			
			while(results.next()) {
				int Disaster_id = results.getInt("Disaster_id");
				String state = results.getString("state");
				String disaster_title = results.getString("disaster_title");
								
				Disasters dstr = new Disasters(Disaster_id,state,disaster_title);
				d1.add(dstr);
				
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
		return d1;
	}
	
	
	
	
	
	
	
}
