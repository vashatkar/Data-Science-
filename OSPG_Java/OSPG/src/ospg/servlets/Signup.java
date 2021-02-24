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

import ospg.dal.UsersDao;
import ospg.model.Users;

/**
 * Servlet implementation class Signup
 */
@WebServlet("/Signup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
protected UsersDao usersDao;
	
	@Override
	public void init() throws ServletException {
		usersDao = UsersDao.getInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
	        	messages.put("success", "You are welcome " + firstname+". Please sign-in to avail our services");
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/SignUp.jsp").forward(req, resp);

	}

}
