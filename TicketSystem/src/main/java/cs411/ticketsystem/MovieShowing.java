package cs411.ticketsystem;

public class MovieShowing {
	
	String title;
	int genre;
	int numSeats;
	int movieLength;
	String date;
	float price;
	
	//Constructor
	public MovieShowing(String title, int numSeats, String date, int movieLength, int movieGenre, float price) {
		this.title = title;
		this.numSeats = numSeats;
		this.genre = movieGenre;
		this.date = date;
		this.movieLength = movieLength;
		this.price = price;
	}

	/**
	 * Decrease the number of available seats in the movie showing in order to indicate a certain number of sold seats
	 * @param seats
	 */
	public void seatSold(int seats){
		this.numSeats-=seats;
	}
	
	/**
	 * Increase the number of available seats in the movie showing in order to indicate a cancelled seat
	 */
	public void seatCancelled() {
		this.numSeats++;
	}
	
	/**
	 * Set the price of a movieShowing to a newPrice. Called by administrators.
	 * @param newPrice
	 */
	public void setPrice(float newPrice) {
		this.price = newPrice;
	}
	
	/**
	 * Add a number of seats to the showing. Called by administrators.
	 * @param numSeats
	 */
	public void addSeat(int numSeats) {
		this.numSeats += numSeats;
	}
	
	/* ***********
	 * Accessors
	 * ***********/

	public String getTitle(){
		return this.title;
	}
	
	public int getMovieLength() {
		return this.movieLength;
	}
	
	public float getPrice() {
		return this.price;
	}

	public int getSeatsLeft(){
		return this.numSeats;
	}

	public String getShowingDate(){
		return this.date;
	}
	
	/**
	 * Return the genre as an integer (1: action, 2: comedy, 3: drama, 4: horror, 5: scifi)
	 * @return
	 */
	public int getGenre() {
		return this.genre;
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