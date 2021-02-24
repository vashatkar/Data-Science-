package ospg.model;

public class Car_rentals {
	
	protected Integer rental_id;
	protected Double latitude;
	protected Double longitude;
	protected Integer total_cars;	
	protected Integer zipcode;
	
	public Car_rentals(Integer rental_id, Double latitude, Double longitude, Integer total_cars, Integer zipcode) {
		this.rental_id = rental_id;
		this.latitude = latitude;
		this.longitude = longitude;
		this.total_cars = total_cars;
		this.zipcode = zipcode;
	}

	public Car_rentals(Integer rental_id) {
		this.rental_id = rental_id;
	}

	public Car_rentals(Double latitude, Double longitude, Integer total_cars, Integer zipcode) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.total_cars = total_cars;
		this.zipcode = zipcode;
	}

	public Integer getRental_id() {
		return rental_id;
	}

	public void setRental_id(Integer rental_id) {
		this.rental_id = rental_id;
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

	public Integer getTotal_cars() {
		return total_cars;
	}

	public void setTotal_cars(Integer total_cars) {
		this.total_cars = total_cars;
	}

	public Integer getZipcode() {
		return zipcode;
	}

	public void setZipcode(Integer zipcode) {
		this.zipcode = zipcode;
	}
	
}
