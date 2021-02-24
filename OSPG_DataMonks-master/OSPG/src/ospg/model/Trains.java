package ospg.model;

public class Trains {
	
	protected Integer station_id;
	protected String staion_name;
	protected Double latitude;
	protected Double longitude;
	protected String city;
	protected Integer zipcode;
	
	public Trains(Integer station_id, String staion_name, Double latitude, Double longitude, String city,
			Integer zipcode) {
		this.station_id = station_id;
		this.staion_name = staion_name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.city = city;
		this.zipcode = zipcode;
	}

	public Trains(Integer station_id) {
		this.station_id = station_id;
	}

	public Trains(String staion_name, Double latitude, Double longitude, String city, Integer zipcode) {
		this.staion_name = staion_name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.city = city;
		this.zipcode = zipcode;
	}

	public Integer getStation_id() {
		return station_id;
	}

	public void setStation_id(Integer station_id) {
		this.station_id = station_id;
	}

	public String getStaion_name() {
		return staion_name;
	}

	public void setStaion_name(String staion_name) {
		this.staion_name = staion_name;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getZipcode() {
		return zipcode;
	}

	public void setZipcode(Integer zipcode) {
		this.zipcode = zipcode;
	}
	
}
