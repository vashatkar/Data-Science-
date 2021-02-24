package ospg.model;


public class Zipcode {

	protected int zipcode;
	protected String city;
	protected String state;
	protected double latitude;
	protected double longitude;
	
	public Zipcode(int zipcode, String city, String state, double latitude, double longitude) {
		super();
		this.zipcode = zipcode;
		this.city = city;
		this.state = state;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Zipcode(int zipcode) {
		super();
		this.zipcode = zipcode;
	}

	public Zipcode(String city, String state, double latitude, double longitude) {
		super();
		this.city = city;
		this.state = state;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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
	
}
	
	