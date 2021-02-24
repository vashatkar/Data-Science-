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
import ospg.dal.trainsDao;
import ospg.model.Trains;
import ospg.dal.weatherDao;
import ospg.model.Weather;
import ospg.dal.UniversityDao;
import ospg.model.University;
import ospg.dal.crimeDao;
import ospg.model.crime;
import ospg.dal.BusinessDao;
import ospg.model.Business;
import ospg.dal.Car_rentalsDao;
import ospg.model.Car_rentals;
import ospg.dal.DisastersDao;
import ospg.model.Disasters;


/**
 * Servlet implementation class DeleteEstate
 */
@WebServlet("/ViewZipcode")
public class ViewZipcode extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected EstateDao estateDao;
	protected crimeDao CrimeDao;
	protected trainsDao TrainsDao;
	protected UniversityDao universityDao;
	protected Car_rentalsDao car_rentalsDao;
	protected BusinessDao businessDao;
	protected weatherDao WeatherDao;
	protected DisastersDao disastersDao;
	protected trainsDao trainsdao;
	
	
	
	@Override
	public void init() throws ServletException {
		estateDao = EstateDao.getInstance();
		CrimeDao = crimeDao.getInstance();
		TrainsDao = trainsDao.getInstance();
		universityDao = UniversityDao.getInstance();
		car_rentalsDao = Car_rentalsDao.getInstance();
		businessDao = BusinessDao.getInstance();
		WeatherDao = weatherDao.getInstance();
		disastersDao = DisastersDao.getInstance();
		trainsdao = trainsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
     
        // Provide a title and render the JSP.
        messages.put("title", "View Zipcode"); 
        
        String estateId = req.getParameter("zip");
        String city = req.getParameter("city");
        String state = req.getParameter("state");
        Integer esid = Integer.parseInt(estateId);
        
               
        List<crime> crim = new ArrayList<crime>();
        List<University> univ = new ArrayList<University>();
        Car_rentals car = new Car_rentals(Integer.parseInt(estateId));
        List<Business> bus = new ArrayList<Business>();
        List<Weather> weather = new ArrayList<Weather>() ;
        List<Disasters> disaster = new ArrayList<Disasters>() ;
        List<Trains> trains = new ArrayList<Trains>() ;
        
        try {
        	crim = CrimeDao.getCrimetByZip(esid);
        	univ = universityDao.getUniversityByZip(esid);
        	car = car_rentalsDao.getCarByZip(esid);
        	bus = businessDao.getBusinessByZip(esid);
        	weather = WeatherDao.getWeatherByCity(city);
        	disaster = disastersDao.getDisasterByCity(state);
        	trains = trainsdao.getStationByCity(city);
        	
        	// Update the message.
	        
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        
        req.setAttribute("crime", crim.subList(0, 5));
        req.setAttribute("univ", univ);
        req.setAttribute("car", car);
        req.setAttribute("bus", bus);
        req.setAttribute("weather", weather);
        req.setAttribute("disaster", disaster.subList(0, 5));
        req.setAttribute("trains", trains.subList(0, 5));
      
        req.getRequestDispatcher("/ViewZipcode.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        

        // Retrieve and validate Id.
        String estateId = req.getParameter("zip");
        String city = req.getParameter("city");
        String state = req.getParameter("state");
        
        Integer esid = Integer.parseInt(estateId);
      
        	// Delete the Estate.
        
	       
            List<crime> crim = new ArrayList<crime>();
	        List<University> univ = new ArrayList<University>();
	        Car_rentals car = new Car_rentals(Integer.parseInt(estateId));
	        List<Business> bus = new ArrayList<Business>();
	        List<Weather> weather = new ArrayList<Weather>() ;
	        List<Disasters> disaster = new ArrayList<Disasters>() ;
	        List<Trains> trains = new ArrayList<Trains>() ;
	        
	        
	        try {
	        	
	        	crim = CrimeDao.getCrimetByZip(esid);
	        	univ = universityDao.getUniversityByZip(esid);
	        	car = car_rentalsDao.getCarByZip(esid);
	        	bus = businessDao.getBusinessByZip(esid);
	        	weather = WeatherDao.getWeatherByCity(city);
	        	disaster = disastersDao.getDisasterByCity(state);
	        	trains = trainsdao.getStationByCity(city);
	        	
	        	// Update the message.
		        
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        
	        req.setAttribute("crime", crim.subList(0, 5));
	        req.setAttribute("univ", univ.subList(0, 5));
	        req.setAttribute("car", car);
	        req.setAttribute("bus", bus.subList(0, 5));
	        req.setAttribute("weather", weather);
	        req.setAttribute("disaster", disaster.subList(0, 5));
	        req.setAttribute("trains", trains.subList(0, 5));
	        
        req.getRequestDispatcher("/ViewZipcode.jsp").forward(req, resp);
    }
}

