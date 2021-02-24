package ospg.dal;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ospg.model.Rental;
import ospg.model.Zipcode;

public class RentalDao {

	protected ConnectionManager connectionManager;

	private static RentalDao instance = null;
	protected RentalDao() {
		connectionManager = new ConnectionManager();
	}
	public static RentalDao getInstance() {
		if(instance == null) {
			instance = new RentalDao();
		}
		return instance;
	}
	
	public Rental create(Rental rental) throws SQLException {
		String insertrental = "INSERT INTO Rental(bathrooms,bedrooms,beds,host_response_rate,latitude,longitude,number_of_reviews,review_scores_rating, 24_hour_check_in,air_conditioning,bath_towel,bed_linens,body_soap, wireless_intercom,Cable_TV,Cleaning_before_checkout,Coffee_maker,Cooking_basics,Dishes,Etherne_connection, Free_parking_on_premises,Free_parking_on_street,Garden_or_backyard,Hot_water,Indoor_fireplace,Luggage_dropoff_allowed,Paid_parking_off_premises,Patio_or_balcony,Pets_allowed,Self_Check_In,Wireless_Internet,Bathtub,Breakfast,Dryer,Elevator,Gym ,Heating,Internet,Iron,Kitchen,Microwave,Pool,Refrigerator,Shampoo,Stove ,TV ,Washer,Property_type,Room_type,Bed_type,Cancellation_policy,City,Zipcode_zipcode,price) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertrental, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1,rental.getBathrooms());
			insertStmt.setInt(2, rental.getBedrooms());
			insertStmt.setInt(3, rental.getBeds());
			insertStmt.setInt(4,rental.getHost_response_rate());
			insertStmt.setDouble(5, rental.getLatitude());
			insertStmt.setDouble(6,rental.getLongitude());
			insertStmt.setInt(7, rental.getNumber_of_reviews());
			insertStmt.setInt(8,rental.getReview_scores_rating());
			insertStmt.setInt(9,rental.getHours24_check_in());
			insertStmt.setInt(10,rental.getAir_conditioning());
			insertStmt.setInt(11,rental.getBath_towel());
			insertStmt.setInt(12,rental.getBed_linens());
			insertStmt.setInt(13,rental.getBody_soap()); 
			insertStmt.setInt(14,rental.getWireless_intercom());
			insertStmt.setInt(15,rental.getCable_TV());
			insertStmt.setInt(16,rental.getCleaning_before_checkout());
			insertStmt.setInt(17,rental.getCoffee_maker());
			insertStmt.setInt(18,rental.getCooking_basics());
			insertStmt.setInt(19,rental.getDishes());
			insertStmt.setInt(20,rental.getEtherne_connection());
			insertStmt.setInt(21,rental.getFree_parking_on_premises());
			insertStmt.setInt(22,rental.getFree_parking_on_street());
			insertStmt.setInt(23,rental.getGarden_or_backyard());
			insertStmt.setInt(24,rental.getHot_water());
			insertStmt.setInt(25,rental.getIndoor_fireplace());
			insertStmt.setInt(26,rental.getLuggage_dropoff_allowed());
			insertStmt.setInt(27,rental.getPaid_parking_off_premises());
			insertStmt.setInt(28,rental.getPatio_or_balcony());
			insertStmt.setInt(29,rental.getPets_allowed());
			insertStmt.setInt(30,rental.getSelf_Check_In());
			insertStmt.setInt(31,rental.getWireless_Internet());
			insertStmt.setInt(32,rental.getBathtub());
			insertStmt.setInt(33,rental.getBreakfast());
			insertStmt.setInt(34,rental.getDryer());
			insertStmt.setInt(35,rental.getElevator());
			insertStmt.setInt(36,rental.getGym());
			insertStmt.setInt(37,rental.getHeating());
			insertStmt.setInt(38,rental.getInternet());
			insertStmt.setInt(39,rental.getIron());
			insertStmt.setInt(40,rental.getKitchen());
			insertStmt.setInt(41,rental.getMicrowave());
			insertStmt.setInt(42,rental.getPool());
			insertStmt.setInt(43,rental.getRefrigerator());
			insertStmt.setInt(44,rental.getShampoo());
			insertStmt.setInt(45,rental.getStove());
			insertStmt.setInt(46,rental.getTV ());
			insertStmt.setInt(47,rental.getWasher());
			insertStmt.setString(48,rental.getProperty_type());
			insertStmt.setString(49,rental.getRoom_type());
			insertStmt.setString(50,rental.getBed_type());
			insertStmt.setString(51,rental.getCancellation_policy());
			insertStmt.setString(52,rental.getCity());
			insertStmt.setInt(53,rental.getZipcode_fk().getZipcode());
			insertStmt.setInt(54,rental.getPrice());
			
			insertStmt.executeUpdate();
			connection.commit();
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int rntlId = -1;
			if(resultKey.next()) {
				rntlId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			rental.setRental_id(rntlId);
			System.out.println(rental.getRental_id());
			return rental;
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


	
	
	
	
	public Rental getRentalById(int Rental_id) throws SQLException {
		String selectRes = "SELECT * FROM Rental WHERE Rental_id = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRes);
			selectStmt.setInt(1, Rental_id);
			results = selectStmt.executeQuery();
			ZipcodeDao zipdao= ZipcodeDao.getInstance();
			if(results.next()) {
				int resultRentalId = results.getInt("Rental_id");
				int bathrooms = results.getInt("bathrooms") ;
				int bedrooms = results.getInt("bedrooms");
				int beds = results.getInt("beds");
				int host_response_rate = results.getInt("host_response_rate");
				double latitude = results.getDouble("latitude");
				double longitude = results.getDouble("longitude");
				int number_of_reviews=results.getInt("number_of_reviews");
				int review_scores_rating = results.getInt("review_scores_rating");
				int hours24_check_in = results.getInt("24_hour_check_in");
				int air_conditioning = results.getInt("air_conditioning");
				int bath_towel= results.getInt("bath_towel");
				int bed_linens= results.getInt("bed_linens");
				int body_soap = results.getInt("body_soap");
				int wireless_intercom= results.getInt("wireless_intercom");
				int Cable_TV = results.getInt("Cable_TV");
				int Cleaning_before_checkout= results.getInt("Cleaning_before_checkout");
				int Coffee_maker= results.getInt("Coffee_maker");
				int Cooking_basics = results.getInt("Cooking_basics");
				int Dishes= results.getInt("Dishes");
				int Etherne_connection = results.getInt("Etherne_connection");
				int Free_parking_on_premises = results.getInt("Free_parking_on_premises");
				int Free_parking_on_street = results.getInt("Free_parking_on_street");
				int Garden_or_backyard = results.getInt("Garden_or_backyard");
				int Hot_water = results.getInt("Hot_water");
				int Indoor_fireplace= results.getInt("Indoor_fireplace");
				int Luggage_dropoff_allowed= results.getInt("Luggage_dropoff_allowed");
				int Paid_parking_off_premises= results.getInt("Paid_parking_off_premises");
				int Patio_or_balcony= results.getInt("Patio_or_balcony");
				int Pets_allowed= results.getInt("Pets_allowed");
				int Self_Check_In = results.getInt("Self_Check_In"); 
				int Wireless_Internet = results.getInt("Wireless_Internet");
				int Bathtub = results.getInt("Bathtub");
				int Breakfast = results.getInt("Breakfast");
				int Dryer = results.getInt("Dryer");
				int Elevator = results.getInt("Elevator");
				int Gym = results.getInt("Gym"); 
				int Heating= results.getInt("Heating");
				int Internet= results.getInt("Internet");
				int Iron = results.getInt("Iron");
				int Kitchen = results.getInt("Kitchen");
				int Microwave = results.getInt("Microwave");
				int Pool= results.getInt("Pool");
				int Refrigerator = results.getInt("Refrigerator");
				int Shampoo = results.getInt("Shampoo");
				int Stove = results.getInt("Stove");
				int TV = results.getInt("TV");
				int Washer = results.getInt("Washer");
				String Property_type = results.getString("Property_type");
				String Room_type = results.getString("Room_type");
				String Bed_type = results.getString("Bed_type");
				String Cancellation_policy = results.getString("Cancellation_policy");
				String City = results.getString("City");
				int Zipcode_fk = results.getInt("Zipcode_Zipcode");
				Zipcode zip = zipdao.getZipCodeInfoByZipCode(Zipcode_fk);
				int price = results.getInt("price");
				Rental rntl = new Rental(resultRentalId,bathrooms,bedrooms,beds,host_response_rate,latitude,longitude,number_of_reviews,review_scores_rating, hours24_check_in,air_conditioning,bath_towel,bed_linens,body_soap, wireless_intercom,Cable_TV,Cleaning_before_checkout,Coffee_maker,Cooking_basics,Dishes,Etherne_connection, Free_parking_on_premises,Free_parking_on_street,Garden_or_backyard,Hot_water,Indoor_fireplace,Luggage_dropoff_allowed,Paid_parking_off_premises,Patio_or_balcony,Pets_allowed,Self_Check_In,Wireless_Internet,Bathtub,Breakfast,Dryer,Elevator,Gym ,Heating,Internet,Iron,Kitchen,Microwave,Pool,Refrigerator,Shampoo,Stove ,TV ,Washer,Property_type,Room_type,Bed_type,Cancellation_policy,City,zip,price);
				return rntl;
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
	
	
	public Rental updateRental(String newPolicy, Rental rntl) throws SQLException {
		String updateRental = "UPDATE Rental SET Cancellation_policy=? WHERE Rental_id=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateRental);
			updateStmt.setString(1, newPolicy);
			updateStmt.setInt(2, rntl.getRental_id());
			updateStmt.executeUpdate();
			connection.commit();
			rntl.setCancellation_policy(newPolicy);
			return rntl;
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
	
	public Rental delete(Rental rnt) throws SQLException {
		String deleteRentals = "DELETE FROM Rental WHERE Rental_id=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteRentals);
			deleteStmt.setInt(1, rnt.getRental_id());
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
	
	public List<Rental> getRentalsbyFilters(int bedrooms, int bathrooms, String city) throws SQLException {
	{
		List<Rental> rentals = new ArrayList<Rental>();
		ZipcodeDao zip = ZipcodeDao.getInstance();
		String getRentals ="SELECT Rental_id,bathrooms,bedrooms,Property_type,Room_type,Bed_type,City,Zipcode_Zipcode,price FROM Rental WHERE bedrooms=? AND bathrooms=? AND City=? LIMIT 10;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(getRentals);
				selectStmt.setInt(1, bedrooms);
				selectStmt.setInt(2, bathrooms);
				selectStmt.setString(3, city);
				
				results = selectStmt.executeQuery();
				
				while(results.next()) {
					int Rental_id = results.getInt("Rental_id");
					int returnedbathrooms = results.getInt("bathrooms");
					int returnedbedrooms = results.getInt("bedrooms");
					String Property_type = results.getString("Property_type");
					String Room_type = results.getString("Room_type");
					String Bed_type = results.getString("Bed_type");
					String City = results.getString("City");
					int Zipcode_Zipcode = results.getInt("Zipcode_Zipcode");
					int price = results.getInt("price");
					
					Zipcode zp = zip.getZipCodeInfoByZipCode(Zipcode_Zipcode);
					
					Rental rntl = new Rental(Rental_id, returnedbathrooms,returnedbedrooms, Property_type,Room_type, Bed_type,City,zp,price);
					rentals.add(rntl);
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
			return rentals;
		
	}
	
}	
	
	
	
	
	
	
	
}
