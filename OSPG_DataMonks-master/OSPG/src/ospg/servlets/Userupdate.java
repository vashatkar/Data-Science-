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


@WebServlet("/Userupdate")
public class Userupdate extends HttpServlet {
	
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

        // Retrieve user and validate.
        Integer userid = Integer.parseInt(req.getParameter("userid"));
        if (userid == null) {
            messages.put("success", "Please enter a valid UserId.");
        } else {
        	try {
        		Users User = usersDao.getUserByUserId(userid);
        		if(User == null) {
        			messages.put("success", "UserId does not exist.");
        		}
        		req.setAttribute("User", User);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/Userupdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        Integer userid = Integer.parseInt(req.getParameter("userid"));
        if (userid == null ) {
            messages.put("success", "Please enter a valid Userid.");
        } else {
        	try {
        		Users User = usersDao.getUserByUserId(userid);
        		if(User == null) {
        			messages.put("success", "Userid does not exist. No update to perform.");
        		} else {
        			String newpassword = req.getParameter("password");
        			if (newpassword == null || newpassword.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid password.");
        	        } else {
        	        	User = usersDao.updateUser(newpassword, User);
        	        	messages.put("success", "Successfully updated " + newpassword);
        	        }
        		}
        		req.setAttribute("User", User);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/Userupdate.jsp").forward(req, resp);
    }
}
