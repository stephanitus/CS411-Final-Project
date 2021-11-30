
public class MovieTicket {
	
	MovieShowing showing;
	String movieTitle;
	//need date/time of some sort
	int price;
	int length;
	
	public MovieTicket(MovieShowing showing) {
		this.movieTitle = showing.title;
		//this.date = showing.date; Need time/date of some sort
		this.length = showing.movieLength;
	}
	
	
}
