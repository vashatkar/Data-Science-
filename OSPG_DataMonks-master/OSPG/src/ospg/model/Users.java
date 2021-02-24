package ospg.model;

public class Users {
	protected int user_id;
	protected String password;
	protected String first_name;
	protected String last_name;
	protected String email;
	protected String phone;
	protected String type;
	
public Users(int user_id,String password,String first_name,String last_name,String email, String phone, String type) {
	super();
	this.user_id= user_id;
	this.password = password;
	this.first_name = first_name;
	this.last_name = last_name;
	this.email = email;
	this.phone = phone;
	this.type = type;
}

public Users(int user_id) {
	super();
	this.user_id = user_id;
}

public Users(String password,String first_name,String last_name,String email, String phone, String type) {
	super();
	this.password = password;
	this.first_name = first_name;
	this.last_name = last_name;
	this.email = email;
	this.phone = phone;
	this.type = type;
}

public int getUser_id() {
	return user_id;
}

public void setUser_id(int user_id) {
	this.user_id = user_id;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getFirst_name() {
	return first_name;
}

public void setFirst_name(String first_name) {
	this.first_name = first_name;
}

public String getLast_name() {
	return last_name;
}

public void setLast_name(String last_name) {
	this.last_name = last_name;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPhone() {
	return phone;
}

public void setPhone(String phone) {
	this.phone = phone;
}

public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}




}
