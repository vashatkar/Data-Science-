package ospg.servlets;

import ospg.dal.trainsDao;
import ospg.model.Trains;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Stationcreate")
public class Stationcreate extends HttpServlet {
	
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
        //Just render the JSP.   
        req.getRequestDispatcher("/Stationcreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String stationname = req.getParameter("stationname");
        if (stationname == null || stationname.trim().isEmpty()) {
            messages.put("success", "Invalid StationName");
        } else {
        	// Create the BlogUser.
        	Double latitude = Double.parseDouble(req.getParameter("latitude"));
        	Double longitude = Double.parseDouble(req.getParameter("longitude"));
        	String city = req.getParameter("city");
        	Integer zip = Integer.parseInt(req.getParameter("zipcode"));
  
        
	        try {
	        	// Exercise: parse the input for StatusLevel.
	        	Trains train = new Trains(stationname, latitude, longitude, city, zip);
	             train = trainDao.create(train);
	        	messages.put("success", "Successfully created " + stationname);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/Stationcreate.jsp").forward(req, resp);
    }
}
