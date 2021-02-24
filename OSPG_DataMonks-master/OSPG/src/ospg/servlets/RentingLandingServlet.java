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

import ospg.dal.RentalDao;
import ospg.dal.trainsDao;
import ospg.model.Rental;
import ospg.model.Trains;

/**
 * Servlet implementation class RentingLandingServlet
 */
@WebServlet("/RentingLandingServlet")
public class RentingLandingServlet extends HttpServlet {
	
	protected RentalDao rentalDao;   
    
	@Override
	public void init() throws ServletException {
		rentalDao = RentalDao.getInstance();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Rental> rentals = new ArrayList<Rental>();
        
        int bedrooms = Integer.parseInt(req.getParameter("bedroomdropdown"));
        int bathrooms = Integer.parseInt(req.getParameter("bathroomdropdown"));
        String city = req.getParameter("city");
      
        try {
        	rentals = rentalDao.getRentalsbyFilters(bedrooms, bathrooms, city);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        
        req.setAttribute("rentals", rentals);
        
        req.getRequestDispatcher("/RentingLandingPage.jsp").forward(req, resp);
    
		
	}

}
