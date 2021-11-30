
public class Customer {
	String username;
	String paymentInfo;
	float hoursWatched;
	int genresWatched;
	int loyaltyPoints;
	int pendingShowings;
	
	//Create a new customer profile
	public Customer(String username, String paymentInfo, float hoursWatched, int genresWatched, int loyaltyPoints, int pendingShowings) {
		this.username = username;
		this.paymentInfo = paymentInfo;
		this.hoursWatched = hoursWatched;
		this.genresWatched = genresWatched;
		this.loyaltyPoints = loyaltyPoints;
		this.pendingShowings = pendingShowings;
	}
	
	//Cancel from any active tickets
	public void cancelTicket(int ticketID) {
		//profile.activeTickets.remove(ticketID);
	
	}
	

	
	//update profile with ticket data
	public void updateProfile(MovieTicket ticket) {
		//profile.updateProfile(ticket);
		
	}
	
}
