import java.util.Date;

public class MovieShowing {
	
	String title;
	String genre;
	int numSeats;
	int movieLength;
	Date date;
	//time; Need some time/date variable.
	MovieTicket[] tickets = new MovieTicket[numSeats]; //Perhaps have multiple ticket types per movie
	
	//Constructor
	public MovieShowing(String title, int numSeats, Date date, int movieLength, String movieGenre) {
		this.title = title;
		this.numSeats = numSeats;
		this.genre = movieGenre;
		this.date = date;
		this.movieLength = movieLength;
	}
	
	//Buy a showing, give ticket to customer
	public void buyShowing() {
		numSeats--;
	}
}
