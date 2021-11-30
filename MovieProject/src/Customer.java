
public class Customer {
	String username;
	String paymentInfo;
	CustomerProfile profile;
	
	//Create a new customer profile
	public Customer(String username, String paymentInfo) {
		this.username = username;
		this.paymentInfo = paymentInfo;
		this.profile = new CustomerProfile();
	}
	
	//Cancel from any active tickets
	public void cancelTicket(int ticketID) {
		profile.activeTickets.remove(ticketID);
	
	}
	

	
	//update profile with ticket data
	public void updateProfile(MovieTicket ticket) {
		profile.updateProfile(ticket);
		
	}
	
}
