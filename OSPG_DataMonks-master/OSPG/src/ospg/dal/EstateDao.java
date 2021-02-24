package ospg.dal;

import ospg.model.Estate;
import ospg.model.Trains;
import ospg.model.Zipcode;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EstateDao {
	
	protected ConnectionManager connectionManager;
	private static EstateDao instance = null;
	
	protected EstateDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static EstateDao getInstance() {
		if(instance == null) {
			instance = new EstateDao();
		}
		return instance;
	}
	
	
	public Estate create(Estate estate) throws SQLException {
		String insertEstate = "INSERT INTO Estate(bathroom,bedroom,final_sqft,latitude,longitude,year_built,Zipcode_Zipcode,city,state,Price,InsurancePremium) VALUES (?,?,?,?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertEstate, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, estate.getBathroom());
			insertStmt.setInt(2, estate.getBedroom());
			insertStmt.setString(3, estate.getFinal_sqft());
			insertStmt.setDouble(4, estate.getLatitude());
			insertStmt.setDouble(5, estate.getLongitude());
			insertStmt.setString(6, estate.getYear_built());
			insertStmt.setInt(7, estate.getZipcode_fk().getZipcode());
			insertStmt.setString(8, estate.getCity());
			insertStmt.setString(9, estate.getState());
			insertStmt.setInt(10, estate.getPrice());
			insertStmt.setInt(11, estate.getInsurancePremium());
			
			insertStmt.executeUpdate();
			connection.commit();
			resultKey = insertStmt.getGeneratedKeys();
			int estateId = -1;
			if(resultKey.next()) {
				estateId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			estate.setProperty_id(estateId);
			return estate;
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

	

	public Estate getPropertyByPropertyId(int Property_id) throws SQLException {
		String selectProperty = "SELECT Property_id,bathroom,bedroom,final_sqft,latitude,longitude,year_built,Zipcode_Zipcode,city,state,Price,InsurancePremium FROM Estate WHERE Property_id = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectProperty);
			selectStmt.setInt(1,Property_id);
			results = selectStmt.executeQuery();
			ZipcodeDao zipdao= ZipcodeDao.getInstance();
			if(results.next()) {
				int resultPropertyId = results.getInt("Property_id");
				int bathroom = results.getInt("bathroom");
				int bedroom = results.getInt("bedroom");
				String final_sqft = results.getString("final_sqft");
				double latitude = results.getDouble("latitude");
				double longitude = results.getDouble("longitude");
				String year_built = results.getString("year_built");
				int Zipcode_fk = results.getInt("Zipcode_Zipcode");
				Zipcode zip = zipdao.getZipCodeInfoByZipCode(Zipcode_fk);
				String city = results.getString("city");
				String state = results.getString("state");
				int price = results.getInt("Price");
				int premium = results.getInt("InsurancePremium");
				
				Estate property = new Estate (resultPropertyId,bathroom,bedroom,final_sqft,latitude,longitude,year_built,zip,city,state,price,premium);
				return property;
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

	public Estate updateEstate(int bathroom, Estate prop) throws SQLException {
		String updateEstate = "UPDATE Estate SET bathroom=? WHERE Property_id=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateEstate);
			updateStmt.setInt(1, bathroom);
			updateStmt.setInt(2, prop.getProperty_id());
			updateStmt.executeUpdate();
			connection.commit();
			prop.setBathroom(bathroom);
			return prop;
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

	public Estate delete(Estate property) throws SQLException {
		String deleteUser = "DELETE FROM Estate WHERE Property_id=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteUser);
			deleteStmt.setInt(1, property.getProperty_id());
			int affectedRows = deleteStmt.executeUpdate();
			connection.commit();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for Property_id=" + property.getProperty_id());
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

	public List<Estate> getPropertyBycity(String city1) throws SQLException {
		List<Estate> estatecity = new ArrayList<Estate>();
		String selectPropertycity = "SELECT Property_id,bathroom,bedroom,final_sqft,latitude,longitude,year_built,Zipcode_Zipcode,city,state,Price,InsurancePremium FROM Estate WHERE city = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPropertycity);
			selectStmt.setString(1,city1);
			results = selectStmt.executeQuery();
			ZipcodeDao zipdao= ZipcodeDao.getInstance();
			while(results.next()) {
				int resultPropertyId = results.getInt("Property_id");
				int bathroom = results.getInt("bathroom");
				int bedroom = results.getInt("bedroom");
				String final_sqft = results.getString("final_sqft");
				double latitude = results.getDouble("latitude");
				double longitude = results.getDouble("longitude");
				String year_built = results.getString("year_built");
				int Zipcode_fk = results.getInt("Zipcode_Zipcode");
				Zipcode zip = zipdao.getZipCodeInfoByZipCode(Zipcode_fk);
				String city = results.getString("city");
				String state = results.getString("state");
				int price = results.getInt("Price");
				int premium = results.getInt("InsurancePremium");
				
				Estate property = new Estate (resultPropertyId,bathroom,bedroom,final_sqft,latitude,longitude,year_built,zip,city,state,price,premium);
				estatecity.add(property);
				
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
		return estatecity;
	}
	
	public List<Estate> getPropertyByState(String state1) throws SQLException {
		List<Estate> estatestate = new ArrayList<Estate>();
		String selectPropertystate = "SELECT Property_id,bathroom,bedroom,final_sqft,latitude,longitude,year_built,Zipcode_Zipcode,city,state,Price,InsurancePremium FROM Estate WHERE state = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPropertystate);
			selectStmt.setString(1,state1);
			results = selectStmt.executeQuery();
			ZipcodeDao zipdao= ZipcodeDao.getInstance();
			while(results.next()) {
				int resultPropertyId = results.getInt("Property_id");
				int bathroom = results.getInt("bathroom");
				int bedroom = results.getInt("bedroom");
				String final_sqft = results.getString("final_sqft");
				double latitude = results.getDouble("latitude");
				double longitude = results.getDouble("longitude");
				String year_built = results.getString("year_built");
				int Zipcode_fk = results.getInt("Zipcode_Zipcode");
				Zipcode zip = zipdao.getZipCodeInfoByZipCode(Zipcode_fk);
				String city = results.getString("city");
				String state = results.getString("state");
				int price = results.getInt("Price");
				int premium = results.getInt("InsurancePremium"); 
				
				Estate property1 = new Estate (resultPropertyId,bathroom,bedroom,final_sqft,latitude,longitude,year_built,zip,city,state,price,premium);
				estatestate.add(property1);
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
		return estatestate;
	}
	
	public List<Estate> getProperty(String city1,Integer bed, Integer bath) throws SQLException {
		List<Estate> estatecity = new ArrayList<Estate>();
		String selectPropertycity = "SELECT Property_id,bathroom,bedroom,final_sqft,latitude,longitude,year_built,Zipcode_Zipcode,city,state,Price,InsurancePremium FROM Estate WHERE city = ? and bedroom = ? and bathroom = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPropertycity);
			selectStmt.setString(1,city1);
			selectStmt.setInt(2,bed);
			selectStmt.setInt(3,bath);
			results = selectStmt.executeQuery();
			ZipcodeDao zipdao= ZipcodeDao.getInstance();
			while(results.next()) {
				int resultPropertyId = results.getInt("Property_id");
				int bathroom = results.getInt("bathroom");
				int bedroom = results.getInt("bedroom");
				String final_sqft = results.getString("final_sqft");
				double latitude = results.getDouble("latitude");
				double longitude = results.getDouble("longitude");
				String year_built = results.getString("year_built");
				int Zipcode_fk = results.getInt("Zipcode_Zipcode");
				Zipcode zip = zipdao.getZipCodeInfoByZipCode(Zipcode_fk);
				String city = results.getString("city");
				String state = results.getString("state");
				int price = results.getInt("Price");
				int premium = results.getInt("InsurancePremium");
				
				Estate property = new Estate (resultPropertyId,bathroom,bedroom,final_sqft,latitude,longitude,year_built,zip,city,state,price,premium);
				estatecity.add(property);
				
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
		return estatecity;
	}
	
	
}
