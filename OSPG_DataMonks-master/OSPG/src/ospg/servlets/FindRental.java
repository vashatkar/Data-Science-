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

import ospg.dal.RentalDao;
import ospg.model.Estate;
import ospg.model.Rental;

/**
 * Servlet implementation class FindRental
 */
@WebServlet("/FindRental")
public class FindRental extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
protected RentalDao rentalDao;
	
	@Override
	public void init() throws ServletException {
		rentalDao = RentalDao.getInstance();
    }

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        
        Rental rental = null;
        String rental_Id = req.getParameter("rental_Id");
        
        if (rental_Id == null || rental_Id.trim().isEmpty()) {
            messages.put("success", "Please enter a valid Id.");
        } else {
        	
        	
        	try {
            	rental = rentalDao.getRentalById(Integer.parseInt(rental_Id));
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying result for Rental Id : " + rental_Id);
        	
        	messages.put("previousRentalId", rental_Id);
        }
        req.setAttribute("rental", rental);
        
        req.getRequestDispatcher("/FindRental.jsp").forward(req, resp);

	}


	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        Rental rental = null;
        
        String rentalId = req.getParameter("rental_Id");
        if (rentalId == null || rentalId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid Id.");
        } else {
        	
        	try {
            	rental = rentalDao.getRentalById(Integer.parseInt(rentalId));
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying result for " + rentalId);
        	
        	messages.put("previousEstateId",rentalId);
        }
        req.setAttribute("rental", rental);
        
        req.getRequestDispatcher("/FindRental.jsp").forward(req, resp);
    }

	}


