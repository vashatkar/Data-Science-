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
import ospg.dal.RentalDao;
import ospg.model.Estate;
import ospg.model.Rental;



/**
 * Servlet implementation class UpdateRental
 */
@WebServlet("/UpdateRental")
public class UpdateRental extends HttpServlet {
	private static final long serialVersionUID = 1L;
protected RentalDao rentalDao;   
    
	
	@Override
	public void init() throws ServletException {
		rentalDao = RentalDao.getInstance();
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
        req.getRequestDispatcher("/UpdateRental.jsp").forward(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        
                
        req.setAttribute("messages", messages);

        String rentalId = req.getParameter("rental_Id");
        if (rentalId == null || rentalId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid Rental Id.");
        } else {
        	try {
        		int rentId = Integer.parseInt(rentalId);
        		Rental rental = rentalDao.getRentalById(rentId);
        		if(rental == null) {
        			messages.put("success", "Rental does not exist. No update to perform.");
        		} else {
        			String newcancellationpolicy = req.getParameter("cancellation_policy");
        			if (newcancellationpolicy == null || newcancellationpolicy.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid cancellation policy.");
        	        } else {
        	        	rental = rentalDao.updateRental(newcancellationpolicy, rental);
        	        	messages.put("success", "Successfully updated Rental Estate having Id : " + rentId);
        	        }
        		}
        		req.setAttribute("rental", rental);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        
        req.getRequestDispatcher("/UpdateRental.jsp").forward(req, resp);
	}

}
