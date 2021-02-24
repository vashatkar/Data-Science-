package ospg.servlets;

import ospg.dal.UsersDao;
import ospg.model.Users;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Userdelete")
public class Userdelete extends HttpServlet {
	
	protected UsersDao usersDao;
	
	@Override
	public void init() throws ServletException {
		usersDao = UsersDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete BlogUser");        
        req.getRequestDispatcher("/Userdelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        Integer userid = Integer.parseInt(req.getParameter("userid"));
        if (userid == null) {
            messages.put("title", "Invalid UserId");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the BlogUser.
	        Users User = new Users(userid);
	        try {
	        	User = usersDao.delete(User);
	        	// Update the message.
		        if (User == null) {
		            messages.put("title", "Successfully deleted " + userid);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + userid);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/Userdelete.jsp").forward(req, resp);
    }
}
