package ospg.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;


import ospg.dal.EstateDao;
import ospg.dal.ZipcodeDao;
import ospg.model.Estate;
import ospg.model.Zipcode;



/**
 * Servlet implementation class CreateEstate
 */
@WebServlet("/CreateEstate")
public class CreateEstate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected EstateDao estateDao;   
    
	
	@Override
	public void init() throws ServletException {
		estateDao = EstateDao.getInstance();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/CreateEstate.jsp").forward(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest req, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        ZipcodeDao zipdao= ZipcodeDao.getInstance();
        
        req.setAttribute("messages", messages);
       // Retrieve and validate name.
        
        	// Create the Estate.
        	int bathroom = Integer.parseInt(req.getParameter("bathroom"));
        	int bedroom = Integer.parseInt(req.getParameter("bedroom"));
        	String final_sqft = req.getParameter("final_sqft");
        	double latitude = Double.parseDouble(req.getParameter("latitude"));
        	double longitude = Double.parseDouble(req.getParameter("longitude"));
        	String year_built = req.getParameter("year_built");
        	String city = req.getParameter("city");
        	String state = req.getParameter("state");
        	int price = Integer.parseInt(req.getParameter("price"));
        	int premium = Integer.parseInt(req.getParameter("premium"));
        	
        	int Zipcode_fk = Integer.parseInt(req.getParameter("Zipcode_fk"));
        	Zipcode zip =null;
			try {
				zip = zipdao.getZipCodeInfoByZipCode(Zipcode_fk);
			} catch (java.sql.SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	
      
	        try {
	        	// Exercise: parse the input for StatusLevel.
	        	Estate estate = new Estate(bathroom, bedroom, final_sqft, latitude, longitude, year_built,zip,city,state,price,premium);
	        	estate = estateDao.create(estate);
	        	messages.put("success", "Successfully created Property :" + estate.getProperty_id());
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        
        
        req.getRequestDispatcher("/CreateEstate.jsp").forward(req, resp);
	}

}
