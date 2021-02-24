package ospg.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ospg.dal.EstateDao;
import ospg.dal.ReviewDao;
import ospg.dal.UsersDao;
import ospg.dal.ZipcodeDao;
import ospg.model.Estate;
import ospg.model.Review;
import ospg.model.Users;
import ospg.model.Zipcode;

/**
 * Servlet implementation class CreateReview
 */
@WebServlet("/CreateReview")
public class CreateReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
protected ReviewDao reviewDao;   
    
	
	@Override
	public void init() throws ServletException {
		reviewDao = ReviewDao.getInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 Map<String, String> messages = new HashMap<String, String>();
	        req.setAttribute("messages", messages);
	        //Just render the JSP.   
	        req.getRequestDispatcher("/CreateReview.jsp").forward(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 Map<String, String> messages = new HashMap<String, String>();
		 UsersDao userdao= UsersDao.getInstance();
	        EstateDao estatedao = EstateDao.getInstance();
	        
	        req.setAttribute("messages", messages);
	       // Retrieve and validate name.
	        
	        	String review = req.getParameter("Review");
	        	float rating = Float.parseFloat(req.getParameter("Rating"));
	        	int User_id = Integer.parseInt(req.getParameter("User_id"));
	        	Users user =null;
				try {
					user = userdao.getUserByUserId(User_id);
				} catch (java.sql.SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				int Property_id = Integer.parseInt(req.getParameter("Property_id"));
	        	Estate property = null;
				try {
					property = estatedao.getPropertyByPropertyId(Property_id);
				} catch (java.sql.SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

	        	
	      
		        try {
		        	// Exercise: parse the input for StatusLevel.
		        	Review reviews = new Review(review,rating,user,property);
		        	reviews = reviewDao.create(reviews);
		        	messages.put("success", "Review "+reviews.getReview_id()+" successfully posted! ");
		        } catch (SQLException e) {
					e.printStackTrace();
					throw new IOException(e);
		        }
	        
	        
	        req.getRequestDispatcher("/CreateReview.jsp").forward(req, resp);

	}

}
