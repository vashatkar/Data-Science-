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


import ospg.dal.RentalDao;
import ospg.dal.ZipcodeDao;
import ospg.model.Rental;
import ospg.model.Zipcode;

/**
 * Servlet implementation class CreateRental
 */
@WebServlet("/CreateRental")
public class CreateRental extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected RentalDao rentalDao;   
	@Override
	public void init() throws ServletException {
		rentalDao = RentalDao.getInstance();
   
    }

	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 Map<String, String> messages = new HashMap<String, String>();
	        req.setAttribute("messages", messages);
	        //Just render the JSP.   
	        req.getRequestDispatcher("/CreateRental.jsp").forward(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
        ZipcodeDao zipdao= ZipcodeDao.getInstance();
        req.setAttribute("messages", messages);
        
        int bathrooms = Integer.parseInt(req.getParameter("bathrooms"));
        int bedrooms = Integer.parseInt(req.getParameter("bedrooms"));
        int beds = Integer.parseInt(req.getParameter("beds"));
        int host_response_rate = Integer.parseInt(req.getParameter("host_response_rate"));
        double latitude = Double.parseDouble(req.getParameter("latitude"));
		double longitude = Double.parseDouble(req.getParameter("longitude"));
		int number_of_reviews = Integer.parseInt(req.getParameter("number_of_reviews"));
		int review_scores_rating = Integer.parseInt(req.getParameter("review_scores_rating"));
		int hours24_check_in = Integer.parseInt(req.getParameter("hours24_check_in"));
		int air_conditioning = Integer.parseInt(req.getParameter("air_conditioning"));
		int bath_towel = Integer.parseInt(req.getParameter("bath_towel"));
		int bed_linens = Integer.parseInt(req.getParameter("bed_linens"));
		int body_soap = Integer.parseInt(req.getParameter("body_soap"));
		int wireless_intercom = Integer.parseInt(req.getParameter("wireless_intercom"));
		int cable_TV = Integer.parseInt(req.getParameter("cable_TV"));
		int cleaning_before_checkout = Integer.parseInt(req.getParameter("cleaning_before_checkout"));
		int coffee_maker = Integer.parseInt(req.getParameter("coffee_maker"));
		int cooking_basics = Integer.parseInt(req.getParameter("cooking_basics"));
		int dishes = Integer.parseInt(req.getParameter("dishes"));
		int etherne_connection = Integer.parseInt(req.getParameter("etherne_connection"));
		int free_parking_on_premises = Integer.parseInt(req.getParameter("free_parking_on_premises"));
		int free_parking_on_street = Integer.parseInt(req.getParameter("free_parking_on_street"));
		int garden_or_backyard = Integer.parseInt(req.getParameter("garden_or_backyard"));
		int hot_water = Integer.parseInt(req.getParameter("hot_water"));
		int indoor_fireplace = Integer.parseInt(req.getParameter("indoor_fireplace"));
		int luggage_dropoff_allowed = Integer.parseInt(req.getParameter("luggage_dropoff_allowed"));
		int paid_parking_off_premises = Integer.parseInt(req.getParameter("paid_parking_off_premises"));
		int patio_or_balcony = Integer.parseInt(req.getParameter("patio_or_balcony"));
		int pets_allowed = Integer.parseInt(req.getParameter("pets_allowed"));
		int self_Check_In = Integer.parseInt(req.getParameter("self_Check_In"));
		int wireless_Internet = Integer.parseInt(req.getParameter("wireless_Internet"));
		int bathtub = Integer.parseInt(req.getParameter("bathtub"));
		int breakfast = Integer.parseInt(req.getParameter("breakfast"));
		int dryer = Integer.parseInt(req.getParameter("dryer"));
		int elevator = Integer.parseInt(req.getParameter("elevator"));
		int gym = Integer.parseInt(req.getParameter("gym"));
		int heating = Integer.parseInt(req.getParameter("heating"));
		int internet = Integer.parseInt(req.getParameter("internet"));
		int iron = Integer.parseInt(req.getParameter("iron"));
		int kitchen = Integer.parseInt(req.getParameter("kitchen"));
		int microwave= Integer.parseInt(req.getParameter("microwave"));
		int pool = Integer.parseInt(req.getParameter("pool"));
		int refrigerator = Integer.parseInt(req.getParameter("refrigerator"));
		int shampoo = Integer.parseInt(req.getParameter("shampoo"));
		int stove = Integer.parseInt(req.getParameter("stove"));
		int tV = Integer.parseInt(req.getParameter("tV"));
		int washer = Integer.parseInt(req.getParameter("washer"));
		String property_type = req.getParameter("property_type");
		String room_type = req.getParameter("room_type");
		String bed_type = req.getParameter("bed_type");
		String cancellation_policy = req.getParameter("cancellation_policy");
		String city = req.getParameter("city");
		int Zipcode_fk = Integer.parseInt(req.getParameter("Zipcode_fk"));
		int price = Integer.parseInt(req.getParameter("price"));
		
    	Zipcode zip =null;
		try {
			zip = zipdao.getZipCodeInfoByZipCode(Zipcode_fk);
		} catch (java.sql.SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
        	// Exercise: parse the input for StatusLevel.
        	Rental rental = new Rental(bathrooms,bedrooms,beds,host_response_rate,latitude,longitude,number_of_reviews,review_scores_rating, hours24_check_in,air_conditioning,bath_towel,bed_linens,body_soap, wireless_intercom,cable_TV,cleaning_before_checkout,coffee_maker,cooking_basics,dishes,etherne_connection, free_parking_on_premises,free_parking_on_street,garden_or_backyard,hot_water,indoor_fireplace,luggage_dropoff_allowed,paid_parking_off_premises,patio_or_balcony,pets_allowed,self_Check_In,wireless_Internet,bathtub,breakfast,dryer,elevator,gym ,heating,internet,iron,kitchen,microwave,pool,refrigerator,shampoo,stove ,tV ,washer,property_type,room_type,bed_type,cancellation_policy,city,zip,price);
        	rental = rentalDao.create(rental);
        	messages.put("success", "Successfully created Property :" + rental.getRental_id());
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
    
    
    req.getRequestDispatcher("/CreateRental.jsp").forward(req, resp);
       
	}

}
