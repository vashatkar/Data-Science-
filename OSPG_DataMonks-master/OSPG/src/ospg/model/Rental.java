package ospg.model;

public class Rental {
	  protected int Rental_id;
	  protected int bathrooms;
	  protected int bedrooms;
	  protected int beds;
	  protected int	host_response_rate;
	  protected double latitude;
	  protected double longitude;
	  protected int number_of_reviews;
	  protected int review_scores_rating;
	  protected int hours24_check_in;
	  protected int air_conditioning;
	  protected int bath_towel;
	  protected int bed_linens;
	  protected int body_soap;
	  protected int wireless_intercom;
	  protected int Cable_TV;
	  protected int Cleaning_before_checkout;
	  protected int Coffee_maker;
	  protected int Cooking_basics;
	  protected int Dishes;
	  protected int Etherne_connection;
	  protected int Free_parking_on_premises;
	  protected int Free_parking_on_street;
	  protected int Garden_or_backyard;
	  protected int Hot_water;
	  protected int Indoor_fireplace;
	  protected int Luggage_dropoff_allowed;
	  protected int Paid_parking_off_premises;
	  protected int Patio_or_balcony;
	  protected int Pets_allowed;
	  protected int Self_Check_In;
	  protected int Wireless_Internet;
	  protected int Bathtub;
	  protected int Breakfast;
	  protected int Dryer;
	  protected int Elevator;
	  protected int Gym;
	  protected int Heating;
	  protected int Internet;
	  protected int Iron;
	  protected int Kitchen;
	  protected int Microwave;
	  protected int Pool;
	  protected int Refrigerator;
	  protected int Shampoo;
	  protected int Stove;
	  protected int TV;
	  protected int Washer;
	  protected String Property_type;
	  protected String Room_type;
	  protected String Bed_type;
	  protected String Cancellation_policy;
	  protected String City;
	  protected Zipcode Zipcode_fk;
	  protected int price;
	public Rental(int rental_id, int bathrooms, int bedrooms, int beds, int host_response_rate, double latitude,
			double longitude, int number_of_reviews, int review_scores_rating, int hours24_check_in,
			int air_conditioning, int bath_towel, int bed_linens, int body_soap, int wireless_intercom, int cable_TV,
			int cleaning_before_checkout, int coffee_maker, int cooking_basics, int dishes, int etherne_connection,
			int free_parking_on_premises, int free_parking_on_street, int garden_or_backyard, int hot_water,
			int indoor_fireplace, int luggage_dropoff_allowed, int paid_parking_off_premises, int patio_or_balcony,
			int pets_allowed, int self_Check_In, int wireless_Internet, int bathtub, int breakfast, int dryer,
			int elevator, int gym, int heating, int internet, int iron, int kitchen, int microwave, int pool,
			int refrigerator, int shampoo, int stove, int tV, int washer, String property_type, String room_type,
			String bed_type, String cancellation_policy, String city, Zipcode zipcode_fk, int price) {
		super();
		Rental_id = rental_id;
		this.bathrooms = bathrooms;
		this.bedrooms = bedrooms;
		this.beds = beds;
		this.host_response_rate = host_response_rate;
		this.latitude = latitude;
		this.longitude = longitude;
		this.number_of_reviews = number_of_reviews;
		this.review_scores_rating = review_scores_rating;
		this.hours24_check_in = hours24_check_in;
		this.air_conditioning = air_conditioning;
		this.bath_towel = bath_towel;
		this.bed_linens = bed_linens;
		this.body_soap = body_soap;
		this.wireless_intercom = wireless_intercom;
		this.Cable_TV = cable_TV;
		this.Cleaning_before_checkout = cleaning_before_checkout;
		this.Coffee_maker = coffee_maker;
		this.Cooking_basics = cooking_basics;
		this.Dishes = dishes;
		this.Etherne_connection = etherne_connection;
		this.Free_parking_on_premises = free_parking_on_premises;
		this.Free_parking_on_street = free_parking_on_street;
		this.Garden_or_backyard = garden_or_backyard;
		this.Hot_water = hot_water;
		this.Indoor_fireplace = indoor_fireplace;
		this.Luggage_dropoff_allowed = luggage_dropoff_allowed;
		this.Paid_parking_off_premises = paid_parking_off_premises;
		this.Patio_or_balcony = patio_or_balcony;
		this.Pets_allowed = pets_allowed;
		this.Self_Check_In = self_Check_In;
		this.Wireless_Internet = wireless_Internet;
		this.Bathtub = bathtub;
		this.Breakfast = breakfast;
		this.Dryer = dryer;
		this.Elevator = elevator;
		this.Gym = gym;
		this.Heating = heating;
		this.Internet = internet;
		this.Iron = iron;
		this.Kitchen = kitchen;
		this.Microwave = microwave;
		this.Pool = pool;
		this.Refrigerator = refrigerator;
		this.Shampoo = shampoo;
		this.Stove = stove;
		this.TV = tV;
		this.Washer = washer;
		this.Property_type = property_type;
		this.Room_type = room_type;
		this.Bed_type = bed_type;
		this.Cancellation_policy = cancellation_policy;
		this.City = city;
		this.Zipcode_fk = zipcode_fk;
		this.price =price;
	}
	public Rental(int rental_id) {
		super();
		Rental_id = rental_id;
	}
	public Rental(int bathrooms, int bedrooms, int beds, int host_response_rate, double latitude, double longitude,
			int number_of_reviews, int review_scores_rating, int hours24_check_in, int air_conditioning, int bath_towel,
			int bed_linens, int body_soap, int wireless_intercom, int cable_TV, int cleaning_before_checkout,
			int coffee_maker, int cooking_basics, int dishes, int etherne_connection, int free_parking_on_premises,
			int free_parking_on_street, int garden_or_backyard, int hot_water, int indoor_fireplace,
			int luggage_dropoff_allowed, int paid_parking_off_premises, int patio_or_balcony, int pets_allowed,
			int self_Check_In, int wireless_Internet, int bathtub, int breakfast, int dryer, int elevator, int gym,
			int heating, int internet, int iron, int kitchen, int microwave, int pool, int refrigerator, int shampoo,
			int stove, int tV, int washer, String property_type, String room_type, String bed_type,
			String cancellation_policy, String city, Zipcode zipcode_fk, int price) {
		super();
		this.bathrooms = bathrooms;
		this.bedrooms = bedrooms;
		this.beds = beds;
		this.host_response_rate = host_response_rate;
		this.latitude = latitude;
		this.longitude = longitude;
		this.number_of_reviews = number_of_reviews;
		this.review_scores_rating = review_scores_rating;
		this.hours24_check_in = hours24_check_in;
		this.air_conditioning = air_conditioning;
		this.bath_towel = bath_towel;
		this.bed_linens = bed_linens;
		this.body_soap = body_soap;
		this.wireless_intercom = wireless_intercom;
		this.Cable_TV = cable_TV;
		this.Cleaning_before_checkout = cleaning_before_checkout;
		this.Coffee_maker = coffee_maker;
		this.Cooking_basics = cooking_basics;
		this.Dishes = dishes;
		this.Etherne_connection = etherne_connection;
		this.Free_parking_on_premises = free_parking_on_premises;
		this.Free_parking_on_street = free_parking_on_street;
		this.Garden_or_backyard = garden_or_backyard;
		this.Hot_water = hot_water;
		this.Indoor_fireplace = indoor_fireplace;
		this.Luggage_dropoff_allowed = luggage_dropoff_allowed;
		this.Paid_parking_off_premises = paid_parking_off_premises;
		this.Patio_or_balcony = patio_or_balcony;
		this.Pets_allowed = pets_allowed;
		this.Self_Check_In = self_Check_In;
		this.Wireless_Internet = wireless_Internet;
		this.Bathtub = bathtub;
		this.Breakfast = breakfast;
		this.Dryer = dryer;
		this.Elevator = elevator;
		this.Gym = gym;
		this.Heating = heating;
		this.Internet = internet;
		this.Iron = iron;
		this.Kitchen = kitchen;
		this.Microwave = microwave;
		this.Pool = pool;
		this.Refrigerator = refrigerator;
		this.Shampoo = shampoo;
		this.Stove = stove;
		this.TV = tV;
		this.Washer = washer;
		this.Property_type = property_type;
		this.Room_type = room_type;
		this.Bed_type = bed_type;
		this.Cancellation_policy = cancellation_policy;
		this.City = city;
		this.Zipcode_fk = zipcode_fk;
		this.price =price;
	}
	
	
	public Rental(int rental_id, int bathrooms, int bedrooms, String property_type, String room_type, String bed_type,
			String city, Zipcode zipcode_fk, int price) {
		super();
		this.Rental_id = rental_id;
		this.bathrooms = bathrooms;
		this.bedrooms = bedrooms;
		this.Property_type = property_type;
		this.Room_type = room_type;
		this.Bed_type = bed_type;
		this.City = city;
		this.Zipcode_fk = zipcode_fk;
		this.price = price;
	}
	
	
	public int getRental_id() {
		return Rental_id;
	}
	public void setRental_id(int rental_id) {
		Rental_id = rental_id;
	}
	public int getBathrooms() {
		return bathrooms;
	}
	public void setBathrooms(int bathrooms) {
		this.bathrooms = bathrooms;
	}
	public int getBedrooms() {
		return bedrooms;
	}
	public void setBedrooms(int bedrooms) {
		this.bedrooms = bedrooms;
	}
	public int getBeds() {
		return beds;
	}
	public void setBeds(int beds) {
		this.beds = beds;
	}
	public int getHost_response_rate() {
		return host_response_rate;
	}
	public void setHost_response_rate(int host_response_rate) {
		this.host_response_rate = host_response_rate;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public int getNumber_of_reviews() {
		return number_of_reviews;
	}
	public void setNumber_of_reviews(int number_of_reviews) {
		this.number_of_reviews = number_of_reviews;
	}
	public int getReview_scores_rating() {
		return review_scores_rating;
	}
	public void setReview_scores_rating(int review_scores_rating) {
		this.review_scores_rating = review_scores_rating;
	}
	public int getHours24_check_in() {
		return hours24_check_in;
	}
	public void setHours24_check_in(int hours24_check_in) {
		this.hours24_check_in = hours24_check_in;
	}
	public int getAir_conditioning() {
		return air_conditioning;
	}
	public void setAir_conditioning(int air_conditioning) {
		this.air_conditioning = air_conditioning;
	}
	public int getBath_towel() {
		return bath_towel;
	}
	public void setBath_towel(int bath_towel) {
		this.bath_towel = bath_towel;
	}
	public int getBed_linens() {
		return bed_linens;
	}
	public void setBed_linens(int bed_linens) {
		this.bed_linens = bed_linens;
	}
	public int getBody_soap() {
		return body_soap;
	}
	public void setBody_soap(int body_soap) {
		this.body_soap = body_soap;
	}
	public int getWireless_intercom() {
		return wireless_intercom;
	}
	public void setWireless_intercom(int wireless_intercom) {
		this.wireless_intercom = wireless_intercom;
	}
	public int getCable_TV() {
		return Cable_TV;
	}
	public void setCable_TV(int cable_TV) {
		Cable_TV = cable_TV;
	}
	public int getCleaning_before_checkout() {
		return Cleaning_before_checkout;
	}
	public void setCleaning_before_checkout(int cleaning_before_checkout) {
		Cleaning_before_checkout = cleaning_before_checkout;
	}
	public int getCoffee_maker() {
		return Coffee_maker;
	}
	public void setCoffee_maker(int coffee_maker) {
		Coffee_maker = coffee_maker;
	}
	public int getCooking_basics() {
		return Cooking_basics;
	}
	public void setCooking_basics(int cooking_basics) {
		Cooking_basics = cooking_basics;
	}
	public int getDishes() {
		return Dishes;
	}
	public void setDishes(int dishes) {
		Dishes = dishes;
	}
	public int getEtherne_connection() {
		return Etherne_connection;
	}
	public void setEtherne_connection(int etherne_connection) {
		Etherne_connection = etherne_connection;
	}
	public int getFree_parking_on_premises() {
		return Free_parking_on_premises;
	}
	public void setFree_parking_on_premises(int free_parking_on_premises) {
		Free_parking_on_premises = free_parking_on_premises;
	}
	public int getFree_parking_on_street() {
		return Free_parking_on_street;
	}
	public void setFree_parking_on_street(int free_parking_on_street) {
		Free_parking_on_street = free_parking_on_street;
	}
	public int getGarden_or_backyard() {
		return Garden_or_backyard;
	}
	public void setGarden_or_backyard(int garden_or_backyard) {
		Garden_or_backyard = garden_or_backyard;
	}
	public int getHot_water() {
		return Hot_water;
	}
	public void setHot_water(int hot_water) {
		Hot_water = hot_water;
	}
	public int getIndoor_fireplace() {
		return Indoor_fireplace;
	}
	public void setIndoor_fireplace(int indoor_fireplace) {
		Indoor_fireplace = indoor_fireplace;
	}
	public int getLuggage_dropoff_allowed() {
		return Luggage_dropoff_allowed;
	}
	public void setLuggage_dropoff_allowed(int luggage_dropoff_allowed) {
		Luggage_dropoff_allowed = luggage_dropoff_allowed;
	}
	public int getPaid_parking_off_premises() {
		return Paid_parking_off_premises;
	}
	public void setPaid_parking_off_premises(int paid_parking_off_premises) {
		Paid_parking_off_premises = paid_parking_off_premises;
	}
	public int getPatio_or_balcony() {
		return Patio_or_balcony;
	}
	public void setPatio_or_balcony(int patio_or_balcony) {
		Patio_or_balcony = patio_or_balcony;
	}
	public int getPets_allowed() {
		return Pets_allowed;
	}
	public void setPets_allowed(int pets_allowed) {
		Pets_allowed = pets_allowed;
	}
	public int getSelf_Check_In() {
		return Self_Check_In;
	}
	public void setSelf_Check_In(int self_Check_In) {
		Self_Check_In = self_Check_In;
	}
	public int getWireless_Internet() {
		return Wireless_Internet;
	}
	public void setWireless_Internet(int wireless_Internet) {
		Wireless_Internet = wireless_Internet;
	}
	public int getBathtub() {
		return Bathtub;
	}
	public void setBathtub(int bathtub) {
		Bathtub = bathtub;
	}
	public int getBreakfast() {
		return Breakfast;
	}
	public void setBreakfast(int breakfast) {
		Breakfast = breakfast;
	}
	public int getDryer() {
		return Dryer;
	}
	public void setDryer(int dryer) {
		Dryer = dryer;
	}
	public int getElevator() {
		return Elevator;
	}
	public void setElevator(int elevator) {
		Elevator = elevator;
	}
	public int getGym() {
		return Gym;
	}
	public void setGym(int gym) {
		Gym = gym;
	}
	public int getHeating() {
		return Heating;
	}
	public void setHeating(int heating) {
		Heating = heating;
	}
	public int getInternet() {
		return Internet;
	}
	public void setInternet(int internet) {
		Internet = internet;
	}
	public int getIron() {
		return Iron;
	}
	public void setIron(int iron) {
		Iron = iron;
	}
	public int getKitchen() {
		return Kitchen;
	}
	public void setKitchen(int kitchen) {
		Kitchen = kitchen;
	}
	public int getMicrowave() {
		return Microwave;
	}
	public void setMicrowave(int microwave) {
		Microwave = microwave;
	}
	public int getPool() {
		return Pool;
	}
	public void setPool(int pool) {
		Pool = pool;
	}
	public int getRefrigerator() {
		return Refrigerator;
	}
	public void setRefrigerator(int refrigerator) {
		Refrigerator = refrigerator;
	}
	public int getShampoo() {
		return Shampoo;
	}
	public void setShampoo(int shampoo) {
		Shampoo = shampoo;
	}
	public int getStove() {
		return Stove;
	}
	public void setStove(int stove) {
		Stove = stove;
	}
	public int getTV() {
		return TV;
	}
	public void setTV(int tV) {
		TV = tV;
	}
	public int getWasher() {
		return Washer;
	}
	public void setWasher(int washer) {
		Washer = washer;
	}
	public String getProperty_type() {
		return Property_type;
	}
	public void setProperty_type(String property_type) {
		Property_type = property_type;
	}
	public String getRoom_type() {
		return Room_type;
	}
	public void setRoom_type(String room_type) {
		Room_type = room_type;
	}
	public String getBed_type() {
		return Bed_type;
	}
	public void setBed_type(String bed_type) {
		Bed_type = bed_type;
	}
	public String getCancellation_policy() {
		return Cancellation_policy;
	}
	public void setCancellation_policy(String cancellation_policy) {
		Cancellation_policy = cancellation_policy;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public Zipcode getZipcode_fk() {
		return Zipcode_fk;
	}
	public void setZipcode_fk(Zipcode zipcode_fk) {
		Zipcode_fk = zipcode_fk;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	  



}
