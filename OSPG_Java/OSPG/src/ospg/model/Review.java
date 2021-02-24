package ospg.model;

public class Review {
	 protected int Review_id;
	 protected String review;
	 protected float rating;
	 protected Users User_id; 
	 protected Estate Property_id;

public Review(int review_id, String review, float rating, Users user_id, Estate property_id) {
		super();
		Review_id = review_id;
		this.review = review;
		this.rating = rating;
		User_id = user_id;
		Property_id = property_id;
	}

public Review(int review_id) {
	super();
	Review_id = review_id;
}

public Review(String review, float rating, Users user_id, Estate property_id) {
	super();
	this.review = review;
	this.rating = rating;
	User_id = user_id;
	Property_id = property_id;
}

public int getReview_id() {
	return Review_id;
}

public void setReview_id(int review_id) {
	Review_id = review_id;
}

public String getReview() {
	return review;
}

public void setReview(String review) {
	this.review = review;
}

public float getRating() {
	return rating;
}

public void setRating(float rating) {
	this.rating = rating;
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
