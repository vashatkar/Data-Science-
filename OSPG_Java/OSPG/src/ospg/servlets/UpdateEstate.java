package ospg.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ospg.dal.EstateDao;
import ospg.model.Estate;

import java.sql.SQLException;





/**
 * Servlet implementation class CreateEstate
 */
@WebServlet("/UpdateEstate")
public class UpdateEstate extends HttpServlet {
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
        req.getRequestDispatcher("/UpdateEstate.jsp").forward(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest req, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        
                
        req.setAttribute("messages", messages);

        String estateId = req.getParameter("estateId");
        System.out.println(estateId);
        if (estateId == null || estateId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid Estate Id.");
        } else {
        	try {
        		int estId = Integer.parseInt(estateId);
        		Estate estate = estateDao.getPropertyByPropertyId(estId);
        		if(estate == null) {
        			messages.put("success", "Estate does not exist. No update to perform.");
        		} else {
        			String newBathroomCount = req.getParameter("bathroom");
        			if (newBathroomCount == null || newBathroomCount.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid bathroom count.");
        	        } else {
        	        	estate = estateDao.updateEstate(Integer.parseInt(newBathroomCount), estate);
        	        	messages.put("success", "Successfully updated Estate with Id : " + estId);
        	        }
        		}
        		req.setAttribute("estate", estate);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        
        req.getRequestDispatcher("/UpdateEstate.jsp").forward(req, resp);
	}

}
