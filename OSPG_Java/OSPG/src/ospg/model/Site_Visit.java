package ospg.model;

import java.util.Date;


public class Site_Visit {
	protected int Visit_id;
	protected Date date;
	protected String timeslot;
	protected Users User_id;
	protected Estate Property_id;

public Site_Visit(int visit_id, Date date, String timeslot, Users user_id, Estate property_id) {
		super();
		Visit_id = visit_id;
		this.date = date;
		this.timeslot = timeslot;
		User_id = user_id;
		Property_id = property_id;
	}

public Site_Visit(int visit_id) {
	super();
	Visit_id = visit_id;
}

public Site_Visit(Date date, String timeslot, Users user_id, Estate property_id) {
	super();
	this.date = date;
	this.timeslot = timeslot;
	User_id = user_id;
	Property_id = property_id;
}
public Site_Visit(Date date,  Users user_id, Estate property_id) {
	super();
	this.date = date;
	User_id = user_id;
	Property_id = property_id;
}

public int getVisit_id() {
	return Visit_id;
}

public void setVisit_id(int visit_id) {
	Visit_id = visit_id;
}

public Date getDate() {
	return date;
}

public void setDate(Date date) {
	this.date = date;
}

public String getTimeslot() {
	return timeslot;
}

public void setTimeslot(String timeslot) {
	this.timeslot = timeslot;
}

public Users getUser_id() {
	return User_id;
}

public void setUser_id(Users user_id) {
	User_id = user_id;
}

public Estate getProperty_id() {
	return Property_id;
}

public void setProperty_id(Estate property_id) {
	Property_id = property_id;
}


	



}
