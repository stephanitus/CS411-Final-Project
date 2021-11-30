
public class MovieShowing {
	
	String title;
	String genre;
	int numSeats;
	int movieLength;
	int price;
	//time; Need some time/date variable.
	MovieTicket[] tickets = new MovieTicket[numSeats]; //Perhaps have multiple ticket types per movie
	
	//Constructor
	public MovieShowing(String title, int numSeats, int cost) {
		this.title = title;
		this.numSeats = numSeats;
		this.price = cost;
		for (int i = 0; i < numSeats; i++) {
			tickets[i] = new MovieTicket(this);
		}
		
		
	}
	
	//Buy a showing, give ticket to customer
	public void buyShowing() {
		numSeats--;
	}
	
	
	
}
