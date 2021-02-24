package ospg.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ospg.dal.EstateDao;
import ospg.dal.Site_VisitDao;
import ospg.dal.UsersDao;
import ospg.model.Estate;
import ospg.model.Site_Visit;
import ospg.model.Users;


/**
 * Servlet implementation class CreateSiteVisit
 */
@WebServlet("/CreateSiteVisit")
public class CreateSiteVisit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
protected Site_VisitDao sitevisitDao;   
    
	
	@Override
	public void init() throws ServletException {
		sitevisitDao = Site_VisitDao.getInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 Map<String, String> messages = new HashMap<String, String>();
	        req.setAttribute("messages", messages);
	        //Just render the JSP.   
	        req.getRequestDispatcher("/CreateSiteVisit.jsp").forward(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		  Map<String, String> messages = new HashMap<String, String>();
	        UsersDao userdao= UsersDao.getInstance();
	        EstateDao estatedao = EstateDao.getInstance();
	        
	        req.setAttribute("messages", messages);
	        //Create Site_Visit
	       
			java.util.Date dates = null;
			try {
				dates = new SimpleDateFormat("MM/dd/yyyy").parse(req.getParameter("datepicker"));
				System.out.println(dates);
			} catch (ParseException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
	        
	        int User_id = Integer.parseInt(req.getParameter("User_id"));
	        String timeslot = req.getParameter("time");
	        
        	Users user =null;
			try {
				user = userdao.getUserByUserId(User_id);
			} catch (java.sql.SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			int Property_id = Integer.parseInt(req.getParameter("Property_id"));
        	Estate property =null;
			try {
				property = estatedao.getPropertyByPropertyId(Property_id);
			} catch (java.sql.SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			   try {
		        	
		        	Site_Visit sitevisit = new Site_Visit(dates,timeslot,user,property);
		        	sitevisit = sitevisitDao.create(sitevisit);
		        	messages.put("success", "Site visit scheduled! Confirmation Id : " + sitevisit.getVisit_id());
		        } catch (SQLException e) {
					e.printStackTrace();
					throw new IOException(e);
		        }
	        
	        
	        req.getRequestDispatcher("/CreateSiteVisit.jsp").forward(req, resp);
	}

}
