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


@WebServlet("/SignIn")
public class SignIn extends HttpServlet {
	
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
        messages.put("title", "Signin User");        
        req.getRequestDispatcher("/SignIn.jsp").forward(req, resp);
        
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String usrname = req.getParameter("Username");
        String password = req.getParameter("password");
        if (!usrname.isEmpty() || !password.isEmpty()) {
        Integer userid = Integer.parseInt(req.getParameter("Username"));
        String password1 = req.getParameter("password");
        String admin_check = req.getParameter("admin"); 
        Users User = null;
        try {
        User = usersDao.getUserByUserId(userid);
        
        if (User == null) {
            messages.put("title", "Enter valid user ");
            messages.put("disableSubmit", "true");
            req.setAttribute("messages", messages);
            req.getRequestDispatcher("/SignIn.jsp").forward(req, resp);
        } else {
        	String pwd = User.getPassword();
        	if(!pwd.equalsIgnoreCase(password1)) {
        		messages.put("title", "Enter valid password ");
                messages.put("disableSubmit", "true");
                req.setAttribute("messages", messages);
                req.getRequestDispatcher("/SignIn.jsp").forward(req, resp);
        	}else {
        		
        	String type = User.getType();
        	
        	if(admin_check == null & type.equalsIgnoreCase("admin")) {
        		req.setAttribute("User", User);
            req.getRequestDispatcher("/EstateLandingPage.jsp").forward(req, resp);}
        	   	
        	
        	
        	else if(admin_check == null & type.equalsIgnoreCase("user")) {
        		req.setAttribute("User", User);
        		System.out.println(User.getUser_id());
                req.getRequestDispatcher("/EstateLandingPage.jsp").forward(req, resp);
        	}
        	
        	else if (admin_check.equalsIgnoreCase("Admin") & !type.equalsIgnoreCase("admin")) {
        		messages.put("title", "Entered User is not an Admin ");
                messages.put("disableSubmit", "true");
                req.setAttribute("messages", messages);
                req.getRequestDispatcher("/SignIn.jsp").forward(req, resp);
                
        	}
        	else if(admin_check.equalsIgnoreCase("Admin") & type.equalsIgnoreCase("admin")){
            	req.setAttribute("User", User);
                req.getRequestDispatcher("/Home.jsp").forward(req, resp);}}
        	
        }
    } catch (SQLException e) {
		e.printStackTrace();
		throw new IOException(e);
    } } 
        else {messages.put("title", "Username Or Password cannot be blank ");
    messages.put("disableSubmit", "true");
    req.setAttribute("messages", messages);
    req.getRequestDispatcher("/SignIn.jsp").forward(req, resp);}	        
        
        
    }
}
