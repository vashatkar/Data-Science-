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


@WebServlet("/Stationupdate")
public class Stationupdate extends HttpServlet {
	
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

        // Retrieve user and validate.
        Integer stationid = Integer.parseInt(req.getParameter("stationid"));
        if (stationid == null) {
            messages.put("success", "Please enter a valid StationId.");
        } else {
        	try {
        		Trains train = trainDao.getStationtById(stationid);
        		if(train == null) {
        			messages.put("success", "Stationid does not exist.");
        		}
        		req.setAttribute("train", train);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/Stationupdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        Integer stationid = Integer.parseInt(req.getParameter("stationid"));
        if (stationid == null ) {
            messages.put("success", "Please enter a valid UserName.");
        } else {
        	try {
        		Trains train = trainDao.getStationtById(stationid);
        		if(stationid == null) {
        			messages.put("success", "Stationid does not exist. No update to perform.");
        		} else {
        			String newstationname = req.getParameter("stationname");
        			if (newstationname == null || newstationname.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid stationname.");
        	        } else {
        	        	train = trainDao.updateStationName(train,newstationname);
        	        	messages.put("success", "Successfully updated " + newstationname);
        	        }
        		}
        		req.setAttribute("train", train);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/Stationupdate.jsp").forward(req, resp);
    }
}
