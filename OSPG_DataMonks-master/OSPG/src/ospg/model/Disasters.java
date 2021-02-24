package ospg.model;

public class Disasters {

	protected int disaster_id;
	protected String state;
	protected String disaster_title;
	
	public Disasters(int disaster_id, String state, String disaster_title) {
		super();
		this.disaster_id = disaster_id;
		this.state = state;
		this.disaster_title = disaster_title;
	}

	public Disasters(int disaster_id) {
		super();
		this.disaster_id = disaster_id;
	}

	public Disasters(String state, String disaster_title) {
		super();
		this.state = state;
		this.disaster_title = disaster_title;
	}

	public int getDisaster_id() {
		return disaster_id;
	}

	public void setDisaster_id(int disaster_id) {
		this.disaster_id = disaster_id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDisaster_title() {
		return disaster_title;
	}

	public void setDisaster_title(String disaster_title) {
		this.disaster_title = disaster_title;
	}
	
	

}
