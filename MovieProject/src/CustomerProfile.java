import java.util.*;
public class CustomerProfile {
	int totalMovieWatchHours;
	int loyaltyPoints;
	ArrayList<MovieTicket> activeTickets = new ArrayList<MovieTicket>();
	ArrayList<MovieTicket> ticketHistory = new ArrayList<MovieTicket>();
	
	
	public CustomerProfile() {
		this.totalMovieWatchHours = 0;
		this.loyaltyPoints = 0;
		
	}
	
	//Update profile with ticket data
	public void updateProfile(MovieTicket ticket) {
		totalMovieWatchHours+=ticket.length;
		loyaltyPoints+=ticket.price;
		ticketHistory.add(ticket);
	}
}
