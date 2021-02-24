package ospg.servlets;

import ospg.dal.UsersDao;
import ospg.model.Users;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Usercreate")
public class Usercreate extends HttpServlet {
	
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
        //Just render the JSP.   
        req.getRequestDispatcher("/Usercreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String password = req.getParameter("password");
        if (password == null || password.trim().isEmpty()) {
            messages.put("success", "Invalid Password");
        } else {
        	// Create the BlogUser.
        	String firstname = req.getParameter("firstname");
        	String lastname = req.getParameter("lastname");
        	String email = req.getParameter("email");
        	String phone = req.getParameter("phone");
        	String type = req.getParameter("type");
        	
        
	        try {
	        	// Exercise: parse the input for StatusLevel.
	        	Users User = new Users(password,firstname, lastname, email,phone,type);
	        	User = usersDao.create(User);
	        	messages.put("success", "Successfully created " + firstname);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/Usercreate.jsp").forward(req, resp);
    }
}
