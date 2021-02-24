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
 * Servlet implementation class FindEstate
 */
@WebServlet("/FindEstate")
public class FindEstate extends HttpServlet {
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

        
        Estate estate = null;
        String estateId = req.getParameter("estateId");
        
        if (estateId == null || estateId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid Id.");
        } else {
        	
        	
        	try {
            	estate = estateDao.getPropertyByPropertyId(Integer.parseInt(estateId));
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying result for Estate Id : " + estateId);
        	
        	messages.put("previousEstateId", estateId);
        }
        req.setAttribute("estate", estate);
        
        req.getRequestDispatcher("/FindEstate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        Estate estate = null;
        
        String estateId = req.getParameter("estateId");
        if (estateId == null || estateId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid Id.");
        } else {
        	
        	try {
            	estate = estateDao.getPropertyByPropertyId(Integer.parseInt(estateId));
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying result for " + estateId);
        	
        	messages.put("previousEstateId", estateId);
        }
        req.setAttribute("estate", estate);
        
        req.getRequestDispatcher("/FindEstate.jsp").forward(req, resp);
    }
}
