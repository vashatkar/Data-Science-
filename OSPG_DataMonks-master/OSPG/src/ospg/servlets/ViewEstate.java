package ospg.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import ospg.dal.EstateDao;
import ospg.model.Estate;
import ospg.dal.ReviewDao;
import ospg.model.Review;

/**
 * Servlet implementation class DeleteEstate
 */
@WebServlet("/ViewEstate")
public class ViewEstate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected EstateDao estateDao;   
	protected ReviewDao reviewDao;
	
	@Override
	public void init() throws ServletException {
		estateDao = EstateDao.getInstance();
		reviewDao = ReviewDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
     
        // Provide a title and render the JSP.
        messages.put("title", "View Estate");     
        String estateId = req.getParameter("estateId");
        Integer esid = Integer.parseInt(estateId);
        Estate estate = new Estate(Integer.parseInt(estateId));
        List<Review> review = new ArrayList<Review>();
        
        try {
        	estate = estateDao.getPropertyByPropertyId(esid);
        	review = reviewDao.getReviewByPropertyId(esid);
        	// Update the message.
	        
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        req.setAttribute("estate", estate);
        req.setAttribute("review", review);
       
      
        req.getRequestDispatcher("/ViewEstate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        

        // Retrieve and validate Id.
        String estateId = req.getParameter("estateId");
        Integer esid = Integer.parseInt(estateId);
      
        	// Delete the Estate.
        
	        Estate estate = new Estate(Integer.parseInt(estateId));
	        List<Review> review = new ArrayList<Review>();
	        
	        
	        System.out.println(estate.getProperty_id());
	        try {
	        	estate = estateDao.getPropertyByPropertyId(esid);
	        	review = reviewDao.getReviewByPropertyId(esid);
		        
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        
	        req.setAttribute("estate", estate);
	        req.setAttribute("review", review);
	        
        req.getRequestDispatcher("/ViewEstate.jsp").forward(req, resp);
    }
}

