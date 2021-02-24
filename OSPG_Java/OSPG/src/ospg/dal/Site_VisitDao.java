package ospg.dal;

import ospg.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;


public class Site_VisitDao {
	protected ConnectionManager connectionManager;
	private static Site_VisitDao instance = null;
	
	protected Site_VisitDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static Site_VisitDao getInstance() {
		if(instance == null) {
			instance = new Site_VisitDao();
		}
		return instance;
	}

	public Site_Visit create(Site_Visit site_visit) throws SQLException {
		String insertSitevisit = "INSERT INTO Site_Visit(date,timeslot,Users_User_id,Estate_Property_id) VALUES (?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertSitevisit, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setDate(1, new java.sql.Date(site_visit.getDate().getTime()));
			insertStmt.setString(2, site_visit.getTimeslot());
			insertStmt.setInt(3, site_visit.getUser_id().getUser_id());
			insertStmt.setInt(4, site_visit.getProperty_id().getProperty_id());
			
			insertStmt.executeUpdate();
			connection.commit();
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int visitId = -1;
			if(resultKey.next()) {
				visitId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			site_visit.setVisit_id(visitId);
			return site_visit;
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
		String insertSitevisit = "LOAD DATA INFILE '?' INTO TABLE site_visit\r\n" + 
				"     FIELDS TERMINATED BY ',' ENCLOSED BY '\"'\r\n" + 
				"     LINES TERMINATED BY '\\r\\n'\r\n" + 
				"     IGNORE 1 LINES(@dummy,date,timeslot,Users_User_id,Estate_Property_id);" ;
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertSitevisit);
			insertStmt.setString(1, "C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/Site_Visit.csv");
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

	public Site_Visit getSiteByVisitId(int visit_id) throws SQLException {
		String selectVisit = "SELECT Visit_id,date,timeslot,User_id,Property_id FROM Site_Visit WHERE Visit_id = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectVisit);
			selectStmt.setInt(1, visit_id);
			results = selectStmt.executeQuery();
			UsersDao user = UsersDao.getInstance();
			EstateDao property = EstateDao.getInstance();
			if(results.next()) {
				int resultVisitId = results.getInt("Visit_id");
				Date date = results.getDate("date");
				String timeslot = results.getString("timeslot");
				int user_id = results.getInt("User_id");
				int property_id = results.getInt("property_id");
				Users usr= user.getUserByUserId(user_id); 
				Estate prop = property.getPropertyByPropertyId(property_id);
				
				Site_Visit visit = new Site_Visit (resultVisitId,date,timeslot,usr,prop);
				return visit;
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
	
	public Site_Visit updateVisit(Date date, Site_Visit visit) throws SQLException {
		String updateVisit = "UPDATE Site_Visit SET date=? WHERE Visit_id=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateVisit);
			updateStmt.setDate(1, date);
			updateStmt.setInt(2, visit.getVisit_id());
			updateStmt.executeUpdate();
			connection.commit();
			visit.setDate(date);
			return visit;
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

	public Site_Visit delete(Site_Visit visit_id) throws SQLException {
		String deletevisit = "DELETE FROM Site_Visit WHERE Visit_id=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deletevisit);
			deleteStmt.setInt(1, visit_id.getVisit_id());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for VisitID=" + visit_id.getVisit_id());
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

	
}
