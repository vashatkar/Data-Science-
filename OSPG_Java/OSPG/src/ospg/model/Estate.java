package ospg.model;

public class Estate {
	 protected int Property_id;
	 protected int bathroom;
	 protected int bedroom;
	 protected String final_sqft;
	 protected double latitude; 
	 protected double longitude;
	 protected String year_built;
	 protected Zipcode Zipcode_fk;	
	 protected String city;
	 protected String state;
	 protected int Price;
	 protected int InsurancePremium;
	 
	public Estate(int property_id, int bathroom, int bedroom, String final_sqft, double latitude, double longitude,
			String year_built, Zipcode zipcode_fk, String city, String state, int price, int insurancePremium) {
		super();
		this.Property_id = property_id;
		this.bathroom = bathroom;
		this.bedroom = bedroom;
		this.final_sqft = final_sqft;
		this.latitude = latitude;
		this.longitude = longitude;
		this.year_built = year_built;
		this.Zipcode_fk = zipcode_fk;
		this.city = city;
		this.state = state;
		this.Price = price;
		this.InsurancePremium = insurancePremium;
	}

	public Estate(int bathroom, int bedroom, String final_sqft, double latitude, double longitude, String year_built,
			Zipcode zipcode_fk, String city, String state, int price, int insurancePremium) {
		super();
		this.bathroom = bathroom;
		this.bedroom = bedroom;
		this.final_sqft = final_sqft;
		this.latitude = latitude;
		this.longitude = longitude;
		this.year_built = year_built;
		this.Zipcode_fk = zipcode_fk;
		this.city = city;
		this.state = state;
		this.Price = price;
		this.InsurancePremium = insurancePremium;
	}

	public Estate(int property_id) {
		super();
		Property_id = property_id;
	}
	

	public int getProperty_id() {
		return Property_id;
	}

	public void setProperty_id(int property_id) {
		Property_id = property_id;
	}

	public int getBathroom() {
		return bathroom;
	}

	public void setBathroom(int bathroom) {
		this.bathroom = bathroom;
	}

	public int getBedroom() {
		return bedroom;
	}

	public void setBedroom(int bedroom) {
		this.bedroom = bedroom;
	}

	public String getFinal_sqft() {
		return final_sqft;
	}

	public void setFinal_sqft(String final_sqft) {
		this.final_sqft = final_sqft;
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

	public String getYear_built() {
		return year_built;
	}

	public void setYear_built(String year_built) {
		this.year_built = year_built;
	}

	public Zipcode getZipcode_fk() {
		return Zipcode_fk;
	}

	public void setZipcode_fk(Zipcode zipcode_fk) {
		Zipcode_fk = zipcode_fk;
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

	public int getPrice() {
		return Price;
	}

	public void setPrice(int price) {
		Price = price;
	}

	public int getInsurancePremium() {
		return InsurancePremium;
	}

	public void setInsurancePremium(int insurancePremium) {
		InsurancePremium = insurancePremium;
	}
	 
	
	


}
