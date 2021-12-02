public class MovieShowing {
	
	String title;
	String genre;
	int numSeats;
	int movieLength;
	String date;
	float price;
	
	//Constructor
	public MovieShowing(String title, int numSeats, String date, int movieLength, String movieGenre, float price) {
		this.title = title;
		this.numSeats = numSeats;
		this.genre = movieGenre;
		this.date = date;
		this.movieLength = movieLength;
		this.price = price;
	}

	public void seatSold(){
		this.numSeats--;
	}
	
	/* ***********
	 * Accessors
	 * ***********/

	public String getTitle(){
		return this.title;
	}

	public int getSeatsLeft(){
		return this.numSeats;
	}

	public String getShowingDate(){
		return this.date;
	}

	public String toString(){
		return title + "\n\t" + date + "\n\t" + "Seats Left: " + numSeats;
	}

	public String toDataString(){
		return 	title + "\n" + 
				numSeats + "\n" +
				date + "\n" +
				movieLength + "\n" +
				genre + "\n" + 
				price + "\n";

	}
}
