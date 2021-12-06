import java.io.FileNotFoundException;
import java.util.*;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		DatabaseManager db = new DatabaseManager();	
		Scanner userInput = new Scanner(System.in);

		int userType = privilegeMenu(userInput);
		Customer c = loginMenu(userInput, db);
		if(userType == 1){
			runUserMenu(db, c);
		}else if(userType == 2){
			runAdminMenu(db);
		}
		userInput.close();
	}

	public static void runUserMenu(DatabaseManager db, Customer c) throws FileNotFoundException {
		Scanner userInput = new Scanner(System.in);

		//User option dialogue
		//Print movie showings(extends purchase movie ticket), show purchased tickets
		boolean done = false;
		while(!done){
			System.out.println("----------------------------------------------");
			System.out.println("What would you like to do?\n1. Browse Movie Showings\n2. Show Purchased Tickets\n3. Show User Info\n4. Exit");
			int input = userInput.nextInt();
			switch(input){
			case 1:
				//Print all movie showings
				List<MovieShowing> showingsList = db.getShowings();
				int i = 1;
				for (MovieShowing m : showingsList){
					System.out.println("" + i + ". " + m.toString());
					i++;
				}
				//Allow user to buy ticket
				System.out.println("If you would like to reserve a ticket, please enter the corresponding movie number (otherwise enter 0): ");
				input = userInput.nextInt();
				if(input != 0){
					//Update customer pending ticket count and add movieticket to database
					
					if(0 < input && input <= db.getShowings().size()){
						MovieShowing m = db.getMovieShowing(input);
						System.out.println("Select number of tickets to purchase (otherwise enter 0): ");
						input = userInput.nextInt();
						
						if (input > 0) {
							if (input > m.numSeats) {
								System.out.println("Not enough seats remaining");
							} else {
								m.seatSold(input);
								for (int j = 0; j < input; j++) {
									Random generator = new Random();
									long ticketID = 10000000 + generator.nextLong();
									MovieTicket soldTicket = new MovieTicket(ticketID, m, c);
									db.addMovieTicket(soldTicket);
									c.updateStatistics(soldTicket);
									db.writeChanges();
								}
							}
						}
						
						
					}else{
						System.out.println("Invalid selection");
					}
				}
				break;
			case 2:
				//Load customer's purchased tickets
				List<MovieTicket> tickets = db.getMovieTickets();
				List<MovieTicket> customerTickets = new ArrayList<MovieTicket>();
				if(!tickets.isEmpty()){
					for(MovieTicket t : tickets){
						if(t.getOwner().getUserID() == c.getUserID()){
							//Print ticket info
							System.out.println(t.getShowing().toString());
							customerTickets.add(t);
						}
					}
					
					System.out.println("If you would like to delete a ticket, enter corresponding ticket number (otherwise enter 0): ");
					input = userInput.nextInt();
					if(input != 0){
						//Update customer pending ticket count and remove movieticket to database
						if(0 < input && input <= customerTickets.size()){
							MovieTicket tickettoDelete = customerTickets.get(input-1);
							tickettoDelete.showing.seatCancelled();
							c.cancelTicket(tickettoDelete);
							db.removeMovieTicket(tickettoDelete);
							
							db.writeChanges();
							//db.removeFromTickets(tickettoDelete.getTicketID());
							

						}else{
							System.out.println("Invalid selection");
						}
					}
				}else{
					System.out.println("You have no purchased tickets.");
				}
				
				
				break;
			case 3:
				//Load general user info
				System.out.println(c.toString());
				break;
			case 4:
				done = true;
				break;
			default:
				System.out.println("Invalid input");
			}
		}
	}

	public static int privilegeMenu(Scanner userInput){
		System.out.println("Welcome to the movie ticket booking system!");
		int input = 0;
		while(input != 1 && input != 2){
			System.out.println("Please select the type of user you are:\n1. Customer\n2. Admin");
			input = userInput.nextInt();
			if(input != 1 && input != 2){
				System.out.println("Invalid input!");
			}
		}
		return input;
	}

	public static Customer loginMenu(Scanner userInput, DatabaseManager db){
		//Login dialogue
		int input = 0;
		while(input != 1 && input != 2){
			System.out.println("Are you a new or returning customer?\n1. New\n2. Returning");
			input = userInput.nextInt();
			if(input != 1 && input != 2){
				System.out.println("Invalid option selected, try again.");
			}
		}
		if(input == 1){
			//New customer
			System.out.println("Enter your username: ");
			String username = userInput.next().strip();

			System.out.println("Enter your credit card info: ");
			String creditCard = userInput.next().strip();

			//Give user ID and store in database
			Random generator = new Random();
			
			long customerID = 10000000 + generator.nextLong() % 9000000L;
			
			int[] genreList = new int[5];
			Customer c = new Customer(username, customerID, creditCard, 0, genreList, 0, 0);
			db.addNewCustomer(c);
			db.writeChanges();
			System.out.println("New user profile created!\n Your new user ID (Don't lose this): " + customerID);
			return c;
		}else if(input == 2){
			//Returning customer
			long ID = 0;
			while(true){
				System.out.println("Please input your user ID: ");
				ID = userInput.nextLong();
				if(Boolean.TRUE.equals(db.userExists(ID))){
					//Login complete
					Customer c = db.getCustomer(ID);
					//db.addNewCustomer(c);
					//db.writeChanges();
					System.out.println("Login Complete! Welcome");
					return c;
				}else{
					//User doesn't exist
					System.out.println("User ID does not exist, please try again.");
				}
			}
		}
		return null;
	}

	public static void runAdminMenu(DatabaseManager db){
		System.out.println("Input root password: ");
		Scanner userInput = new Scanner(System.in);
		//Hardcoded admin password
		String truePass = "123456";
		String input = userInput.nextLine().strip();
		if(truePass.equals(input)){
			//Grant entrance
			System.out.println("Welcome root user!");
		}else{
			System.out.println("Invalid password");
		}

		//Implement admin menu
	}
}
