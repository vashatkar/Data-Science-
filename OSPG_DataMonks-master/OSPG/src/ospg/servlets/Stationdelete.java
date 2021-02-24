package ospg.servlets;

import ospg.dal.trainsDao;
import ospg.model.Trains;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Stationdelete")
public class Stationdelete extends HttpServlet {
	
	protected trainsDao trainDao;
	
	@Override
	public void init() throws ServletException {
		trainDao = trainsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete Station");        
        req.getRequestDispatcher("/Stationdelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        Integer stationid = Integer.parseInt(req.getParameter("stationid"));
        if (stationid == null) {
            messages.put("title", "Invalid Stationid");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the BlogUser.
	        Trains train = new Trains(stationid);
	        try {
	        	train = trainDao.delete(train);
	        	// Update the message.
		        if (train == null) {
		            messages.put("title", "Successfully deleted " + stationid);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + stationid);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/Stationdelete.jsp").forward(req, resp);
    }
}
