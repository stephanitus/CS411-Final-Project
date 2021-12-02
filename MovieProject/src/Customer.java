
public class Customer {
	String username;
	long userID;
	String paymentInfo;
	float hoursWatched;
	int genresWatched;
	int loyaltyPoints;
	int pendingShowings;
	
	//Create a new customer profile
	public Customer(String username, long userID, String paymentInfo, float hoursWatched, int genresWatched, int loyaltyPoints, int pendingShowings) {
		this.username = username;
		this.userID = userID;
		this.paymentInfo = paymentInfo;
		this.hoursWatched = hoursWatched;
		this.genresWatched = genresWatched;
		this.loyaltyPoints = loyaltyPoints;
		this.pendingShowings = pendingShowings;
	}

	public void incrementPendingShowings(){
		pendingShowings++;
	}
	
	//Cancel from any active tickets
	public void cancelTicket(int ticketID) {
		//TBI
	
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