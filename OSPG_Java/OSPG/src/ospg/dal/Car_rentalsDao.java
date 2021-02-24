package ospg.dal;
import ospg.model.Car_rentals;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import ospg.dal.ConnectionManager;
public class Car_rentalsDao {
	protected ConnectionManager connectionManager;

	private static Car_rentalsDao instance = null;
	protected Car_rentalsDao() {
		connectionManager = new ConnectionManager();
	}
	public static Car_rentalsDao getInstance() {
		if(instance == null) {
			instance = new Car_rentalsDao();
		}
		return instance;
	}
	public Car_rentals create(Car_rentals car) throws SQLException {
		String insertcar =
				"INSERT INTO Car_rentals(latitude,longitude,total_cars\r\n" + 
						"Zipcode"  +
						"VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			
	
			insertStmt = connection.prepareStatement(insertcar,
					Statement.RETURN_GENERATED_KEYS);
			
			insertStmt.setDouble(1, car.getLatitude());
			insertStmt.setDouble(2, car.getLongitude());
			insertStmt.setInt(3, car.getTotal_cars());
			insertStmt.setInt(4, car.getZipcode());
			
			
			
			insertStmt.executeUpdate();
			resultKey = insertStmt.getGeneratedKeys();
			int carid = -1;
			if(resultKey.next()) {
				carid = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			car.setRental_id(carid);;
			return car;	
			
		
	
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
	
	public Car_rentals getCarById(int carid) throws SQLException {
		String selectcar =
				
			"SELECT *" +
			"FROM Car_rentals " +
			"WHERE rental_id=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectcar);
			selectStmt.setInt(1, carid);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int carid1 = results.getInt("rental_id");
				Double lat = results.getDouble("latitude");
				Double longi = results.getDouble("longitude");
				int tot = results.getInt("total_cars");
				int zip = results.getInt("zipcode");				
				
				Car_rentals cars = new Car_rentals(carid1, lat, longi, tot, zip);
				return cars;
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
	
	public Car_rentals updateNumCars(Car_rentals car, Integer numcar) throws SQLException {
		String updatecar = "UPDATE Car_rentals SET total_cars=? WHERE rental_id=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updatecar);
			updateStmt.setInt(1, car.getRental_id());
			updateStmt.setInt(2, numcar);
			
			updateStmt.executeUpdate();
			
			car.setTotal_cars(numcar);
		
			return car;
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

	public Car_rentals delete(Car_rentals car) throws SQLException {
		
		String deletecar = "DELETE FROM Car_rentals WHERE rental_id=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deletecar);
			deleteStmt.setInt(1, car.getRental_id());
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
	public Car_rentals getCarByZip(int zip) throws SQLException {
		String selectcar =
				
			"SELECT *" +
			"FROM Car_rentals " +
			"WHERE Zipcode_Zipcode=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectcar);
			selectStmt.setInt(1, zip);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int carid1 = results.getInt("rental_id");
				Double lat = results.getDouble("latitude");
				Double longi = results.getDouble("longitude");
				int tot = results.getInt("total_cars");
				int zip1 = results.getInt("Zipcode_Zipcode");				
				
				Car_rentals cars = new Car_rentals(carid1, lat, longi, tot, zip1);
				return cars;
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
	
}
