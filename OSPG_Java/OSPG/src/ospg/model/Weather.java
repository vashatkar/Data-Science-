package ospg.model;

public class Weather {
	protected String city;
	protected String state;
	protected String month;
	protected String avg_temp;
	
	public Weather(String city, String state, String month, String avg_temp) {
		this.city = city;
		this.state = state;
		this.month = month;
		this.avg_temp = avg_temp;
	}

	
	
	
	public Weather(String city) {
		super();
		this.city = city;
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

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getAvg_temp() {
		return avg_temp;
	}

	public void setAvg_temp(String avg_temp) {
		this.avg_temp = avg_temp;
	}
}
