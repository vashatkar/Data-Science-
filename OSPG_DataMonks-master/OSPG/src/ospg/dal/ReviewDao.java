package ospg.dal;

import ospg.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReviewDao {
	
	protected ConnectionManager connectionManager;
	private static ReviewDao instance = null;
	
	protected ReviewDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static ReviewDao getInstance() {
		if(instance == null) {
			instance = new ReviewDao();
		}
		return instance;
	}

	public Review create(Review review) throws SQLException {
		String insertReview = "INSERT INTO Review(review,rating,Users_User_id,Estate_Property_id) VALUES (?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertReview, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, review.getReview());
			insertStmt.setFloat(2, review.getRating());
			insertStmt.setInt(3, review.getUser_id().getUser_id());
			insertStmt.setInt(4, review.getProperty_id().getProperty_id());
			
			insertStmt.executeUpdate();
			connection.commit();
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int rvwId = -1;
			if(resultKey.next()) {
				rvwId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			review.setReview_id(rvwId);
			return review;
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

	public void Load() throws SQLException {
		String insertReview = "LOAD DATA INFILE '?' INTO TABLE review\r\n" + 
				"     FIELDS TERMINATED BY ',' ENCLOSED BY '\"'\r\n" + 
				"     LINES TERMINATED BY '\\r\\n'\r\n" + 
				"     IGNORE 1 LINES(@dummy,review,rating,Users_User_id,Estate_Property_id);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertReview);
			insertStmt.setString(1, "C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/Review.csv");
			insertStmt.executeUpdate();
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

	public Review getReviewByReviewId(int review_id) throws SQLException {
		String selectReview = "SELECT Review_id,review,rating,User_id,Property_id FROM Review WHERE Review_id = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReview);
			selectStmt.setInt(1, review_id);
			results = selectStmt.executeQuery();
			UsersDao user = UsersDao.getInstance();
			EstateDao property = EstateDao.getInstance();
			if(results.next()) {
				int resultReviewId = results.getInt("Review_id");
				String review = results.getString("review");
				float rating = results.getFloat("rating");
				int user_id = results.getInt("User_id");
				int property_id = results.getInt("property_id");
				Users usr= user.getUserByUserId(user_id); 
				Estate prop = property.getPropertyByPropertyId(property_id);
				
				Review review1 = new Review (resultReviewId,review,rating,usr,prop);
				return review1;
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
	
	public Review updateReview(String review, Review review1) throws SQLException {
		String updateReview = "UPDATE Review SET review=? WHERE Review_id=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateReview);
			updateStmt.setString(1, review);
			updateStmt.setInt(2, review1.getReview_id());
			updateStmt.executeUpdate();
			connection.commit();
			review1.setReview(review);
			return review1;
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

	public Review delete(Review review) throws SQLException {
		String deleteReview = "DELETE FROM Review WHERE Review_id=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteReview);
			deleteStmt.setInt(1, review.getReview_id());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for ReviewID=" + review.getReview_id());
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

	public List<Review> getReviewByPropertyId(int pid) throws SQLException {
		List<Review> rev = new ArrayList<Review>();
		String selectReview = "SELECT Review_id,review,rating,Users_User_id,Estate_Property_id FROM Review WHERE Estate_Property_id = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReview);
			selectStmt.setInt(1, pid);
			results = selectStmt.executeQuery();
			UsersDao user = UsersDao.getInstance();
			EstateDao property = EstateDao.getInstance();
			while(results.next()) {
				int resultReviewId = results.getInt("Review_id");
				String review = results.getString("review");
				float rating = results.getFloat("rating");
				int user_id = results.getInt("Users_User_id");
				int property_id = results.getInt("Estate_property_id");
				Users usr= user.getUserByUserId(user_id); 
				Estate prop = property.getPropertyByPropertyId(property_id);
				
				Review review1 = new Review (resultReviewId,review,rating,usr,prop);
				rev.add(review1);
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
		return rev;
	}

	
}
