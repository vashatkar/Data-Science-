package ospg.model;

public class crime  {
	
	protected Integer crime_id;
	protected String crime_type;
	protected String city;
	protected String state;
	protected Double latitude;
	protected Double longitude;
	protected Integer zipcode;
	
	public crime(Integer crime_id, String crime_type, String city, String state, Double latitude, Double longitude,
			Integer zipcode) {
		this.crime_id = crime_id;
		this.crime_type = crime_type;
		this.city = city;
		this.state = state;
		this.latitude = latitude;
		this.longitude = longitude;
		this.zipcode = zipcode;
	}

	public crime(Integer crime_id) {
		this.crime_id = crime_id;
	}

	public crime(String crime_type, String city, String state, Double latitude, Double longitude, Integer zipcode) {
		this.crime_type = crime_type;
		this.city = city;
		this.state = state;
		this.latitude = latitude;
		this.longitude = longitude;
		this.zipcode = zipcode;
	}

	public Integer getCrime_id() {
		return crime_id;
	}

	public void setCrime_id(Integer crime_id) {
		this.crime_id = crime_id;
	}

	public String getCrime_type() {
		return crime_type;
	}

	public void setCrime_type(String crime_type) {
		this.crime_type = crime_type;
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

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Integer getZipcode() {
		return zipcode;
	}

	public void setZipcode(Integer zipcode) {
		this.zipcode = zipcode;
	}
	
	
	
}