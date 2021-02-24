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


import ospg.dal.EstateDao;
import ospg.model.Estate;


/**
 * Servlet implementation class FindEstate
 */
@WebServlet("/EstateLandingPage")
public class EstateLandingPage extends HttpServlet {
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

        
        List<Estate> estate = new ArrayList<Estate>();
        Integer bath = Integer.parseInt(req.getParameter("bedroomdropdown"));
        Integer bed = Integer.parseInt(req.getParameter("bathroomdropdown"));
        String city = req.getParameter("city");
        
        
        
    
        	
        	
        	try {
            	estate = estateDao.getProperty(city,bed,bath);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying result for city : " + city);
        	
        	messages.put("previousEstateId", city);
        
        req.setAttribute("estate", estate);
        
        req.getRequestDispatcher("/EstateLandingPage.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Estate> estate = new ArrayList<Estate>();
        
        Integer bath = Integer.parseInt(req.getParameter("bedroomdropdown"));
        Integer bed = Integer.parseInt(req.getParameter("bathroomdropdown"));
        String city = req.getParameter("city");
        System.out.println(req.getParameter("hidden"));
        int userId = req.getParameter("hidden").equalsIgnoreCase("")? 0:Integer.parseInt(req.getParameter("hidden"));
        
        	
        	try {
            	estate = estateDao.getProperty(city,bed,bath);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying result for " + city);
        	
        	messages.put("previousEstateId", city);
        
        req.setAttribute("estate", estate);
        req.setAttribute("user", userId);
        
        req.getRequestDispatcher("/EstateLandingPage.jsp").forward(req, resp);
    }
}
