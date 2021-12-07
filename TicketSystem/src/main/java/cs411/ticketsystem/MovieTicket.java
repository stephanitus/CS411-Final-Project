package cs411.ticketsystem;

public class MovieTicket {
	
	long ticketID;
	MovieShowing showing;
	Customer owner;
	
	public MovieTicket(long ticketID, MovieShowing showing, Customer owner) {
		this.ticketID = ticketID;
		this.showing = showing;
		this.owner = owner;
	}
	
	public long getTicketID() {
		return ticketID;
	}

	public Customer getOwner(){
		return owner;
	}

	public MovieShowing getShowing(){
		return showing;
	}

	public String toDataString(){
		return 	ticketID + "\n" +
				showing.getTitle() + "\n" + 
				showing.getShowingDate() + "\n" +
				owner.getUserID() + "\n";
	}

	public String toString(){
		return  showing.getTitle() + "\n" +
				showing.getShowingDate();
	}
}
