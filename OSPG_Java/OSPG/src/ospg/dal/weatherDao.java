package ospg.dal;
import ospg.model.Weather;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;


import ospg.dal.ConnectionManager;

public class weatherDao {
	
	

	protected ConnectionManager connectionManager;

	private static weatherDao instance = null;
	protected weatherDao() {
		connectionManager = new ConnectionManager();
	}
	public static weatherDao getInstance() {
		if(instance == null) {
			instance = new weatherDao();
		}
		return instance;
	}
	public Weather create(Weather w1) throws SQLException {
		String insertweather =
				"INSERT INTO Weather(city, state,\r\n" + 
						"month, avg_temp"  +
						"VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			
	
			insertStmt = connection.prepareStatement(insertweather);
			
			insertStmt.setString(1, w1.getCity());
			insertStmt.setString(2, w1.getState());
			insertStmt.setString(3, w1.getMonth());
			insertStmt.setString(4, w1.getAvg_temp());
		
			return w1;
			
			
		
	
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
	
	public List<Weather> getWeatherByCity(String city) throws SQLException {
		List<Weather> w2 = new ArrayList<Weather>();
		String selectweather =
			"SELECT * " +
			" FROM Weather " +
			"WHERE city =?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectweather);
			selectStmt.setString(1, city);
			
			results = selectStmt.executeQuery();
			
			while(results.next()) {
				
				String city1 = results.getString("city");
				String state = results.getString("state");
				String month = results.getString("month");
				String avgtemp = results.getString("avg_temp");
				
				
				
				Weather we1 = new Weather(city1,state, month, avgtemp);
				w2.add(we1);
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
		return w2;
	}}
