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
import ospg.model.Estate;

/**
 * Servlet implementation class DeleteEstate
 */
@WebServlet("/DeleteEstate")
public class DeleteEstate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected EstateDao estateDao;   
	
	@Override
	public void init() throws ServletException {
		estateDao = EstateDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete Estate");        
        req.getRequestDispatcher("/DeleteEstate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        

        // Retrieve and validate Id.
        String estateId = req.getParameter("estateId");
        if (estateId == null || estateId.trim().isEmpty()) {
            messages.put("title", "Invalid Estate Id");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the Estate.
	        Estate estate = new Estate(Integer.parseInt(estateId));
	        System.out.println(estate.getProperty_id());
	        try {
	        	estate = estateDao.delete(estate);
	        	// Update the message.
		        if (estate == null) {
		            messages.put("title", "Successfully deleted estate with Id : " +estateId);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete estate with Id : " + estateId);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/DeleteEstate.jsp").forward(req, resp);
    }
}

