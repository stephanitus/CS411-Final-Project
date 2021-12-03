
public class Customer {
	String username;
	long userID;
	String paymentInfo;
	float hoursWatched;
	int genresWatched;
	float loyaltyPoints;
	int pendingShowings;
	
	//Create a new customer profile
	public Customer(String username, long userID, String paymentInfo, float hoursWatched, int genresWatched, float loyaltyPoints, int pendingShowings) {
		this.username = username;
		this.userID = userID;
		this.paymentInfo = paymentInfo;
		this.hoursWatched = hoursWatched;
		this.genresWatched = genresWatched;
		this.loyaltyPoints = loyaltyPoints;
		this.pendingShowings = pendingShowings;
	}

	/**
	 * Update the customer statistics after a ticket was purchased.
	 * @param ticket
	 */
	public void updateStatistics(MovieTicket ticket) {
		pendingShowings++;
		hoursWatched+=ticket.getShowing().getMovieLength();
		loyaltyPoints+= ticket.getShowing().getPrice();
	}
	
	/**
	 * Update the customer statistics after a ticket was cancelled
	 * @param ticket
	 */
	public void cancelTicket(MovieTicket ticket) {
		pendingShowings--;
		hoursWatched -= ticket.getShowing().getMovieLength();
		loyaltyPoints -= ticket.getShowing().getPrice();
	
	}

	public long getUserID(){
		return userID;
	}
	
	public String toString(){
		return 	"Username: " + username + "\n" + 
				"User ID: " + userID + "\n" + 
				"Hours Watched: " + hoursWatched + "\n" + 
				"Genres Watched: " + genresWatched + "\n" +
				"Loyalty Points: " + loyaltyPoints + "\n";
	}

	public String toDataString(){
		return 	username + "\n" + 
				userID + "\n" + 
				paymentInfo + "\n" +
				hoursWatched + "\n" +
				genresWatched + "\n" +
				loyaltyPoints + "\n" +
				pendingShowings + "\n";
	}
}
