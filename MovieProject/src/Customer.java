
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
	
	public void watchMovie() {
		//Update customerProfile information based on movie info
	}
	public void cancelTicket() {
		
	}
	
}
