import java.util.*;
public class CustomerProfile {
	int totalMovieWatchHours;
	int loyaltyPoints;
	ArrayList<MovieTicket> pendingShowings = new ArrayList<MovieTicket>();
	
	public CustomerProfile() {
		this.totalMovieWatchHours = 0;
		this.loyaltyPoints = 0;
		
	}
}
