package ospg.servlets;

import ospg.dal.RentalDao;
import ospg.model.Rental;


import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class DeleteRental
 */
@WebServlet("/DeleteRental")
public class DeleteRental extends HttpServlet {
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
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete Rental");        
        req.getRequestDispatcher("/DeleteRental.jsp").forward(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String rentalid = req.getParameter("rental_Id");
        if (rentalid == null) 
        {
            messages.put("title", "Invalid Rental Id");
            messages.put("disableSubmit", "true");
        } 
        else {
        	// Delete the BlogUser.
	        Rental rental = new Rental(Integer.parseInt(rentalid));
	        try {
	        	rental = rentalDao.delete(rental);
	        	// Update the message.
		        if (rental == null) {
		            messages.put("title", "Successfully deleted Rental Estate having Id : " + rentalid);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete Rental Estate having Id : " + rentalid);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/DeleteRental.jsp").forward(req, resp);
    }

	

}
