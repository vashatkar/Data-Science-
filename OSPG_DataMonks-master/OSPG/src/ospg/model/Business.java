package ospg.model;

public class Business {

	protected int business_id;
	protected String state;
	protected String category;
	protected String business_name;
	protected String rating;
	protected String city;
	protected double latitude;
	protected double longitude;
	protected String address;
	protected Zipcode zipcode_fk;
	
	public Business(int business_id, String state, String category, String business_name, String rating, String city,
			double latitude, double longitude, String address, Zipcode zipcode_fk) {
		super();
		this.business_id = business_id;
		this.state = state;
		this.category = category;
		this.business_name = business_name;
		this.rating = rating;
		this.city = city;
		this.latitude = latitude;
		this.longitude = longitude;
		this.address = address;
		this.zipcode_fk = zipcode_fk;
	}

	public Business(int business_id) {
		super();
		this.business_id = business_id;
	}

	public Business(String state, String category, String business_name, String rating, String city, double latitude,
			double longitude, String address, Zipcode zipcode_fk) {
		super();
		this.state = state;
		this.category = category;
		this.business_name = business_name;
		this.rating = rating;
		this.city = city;
		this.latitude = latitude;
		this.longitude = longitude;
		this.address = address;
		this.zipcode_fk = zipcode_fk;
	}

	public int getBusiness_id() {
		return business_id;
	}

	public void setBusiness_id(int business_id) {
		this.business_id = business_id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBusiness_name() {
		return business_name;
	}

	public void setBusiness_name(String business_name) {
		this.business_name = business_name;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Zipcode getZipcode_fk() {
		return zipcode_fk;
	}

	public void setZipcode_fk(Zipcode zipcode_fk) {
		this.zipcode_fk = zipcode_fk;
	}
	
	
	

}
