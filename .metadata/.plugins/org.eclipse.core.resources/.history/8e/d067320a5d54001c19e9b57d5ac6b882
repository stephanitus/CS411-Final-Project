
public class MovieTicket {
	
	MovieShowing showing;
	Customer owner;
	
	public MovieTicket(MovieShowing showing, Customer owner) {
		this.showing = showing;
		this.owner = owner;
	}

	public Customer getOwner(){
		return owner;
	}

	public MovieShowing getShowing(){
		return showing;
	}

	public String toDataString(){
		return 	showing.getTitle() + "\n" + 
				showing.getShowingDate() + "\n" +
				owner.getUserID() + "\n";
	}
}
