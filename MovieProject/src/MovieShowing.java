
public class MovieShowing {
	String title;
	int numSeats;
	//Need some time/date variable.
	MovieTicket[] tickets = new MovieTicket[numSeats]; //Perhaps have multiple ticket types per movie
	
	public MovieShowing(String title, int numSeats) {
		this.title = title;
		this.numSeats = numSeats;
		for (int i = 0; i < numSeats; i++) {
			tickets[i] = new MovieTicket(this);
		}
	}
	
	public void buyShowing() {
		
	}
	
	
	
}
