package ospg.model;

public class University {

	protected int university_id;
	protected String name;
	protected String address;
	protected String city;
	protected String state;
	protected String phone;
	protected double latitude;
	protected double longitude;
	protected String website;
	protected Zipcode zipcode_fk;
	
	public University(int university_id, String name, String address, String city, String state, String phone,
			double latitude, double longitude, String website, Zipcode zipcode_fk) {
		super();
		this.university_id = university_id;
		this.name = name;
		this.address = address;
		this.city = city;
		this.state = state;
		this.phone = phone;
		this.latitude = latitude;
		this.longitude = longitude;
		this.website = website;
		this.zipcode_fk = zipcode_fk;
	}

	public University(int university_id) {
		super();
		this.university_id = university_id;
	}

	public University(String name, String address, String city, String state, String phone, double latitude,
			double longitude, String website, Zipcode zipcode_fk) {
		super();
		this.name = name;
		this.address = address;
		this.city = city;
		this.state = state;
		this.phone = phone;
		this.latitude = latitude;
		this.longitude = longitude;
		this.website = website;
		this.zipcode_fk = zipcode_fk;
	}

	public int getUniversity_id() {
		return university_id;
	}

	public void setUniversity_id(int university_id) {
		this.university_id = university_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Zipcode getZipcode_fk() {
		return zipcode_fk;
	}

	public void setZipcode_fk(Zipcode zipcode_fk) {
		this.zipcode_fk = zipcode_fk;
	}
	
	
	

}
