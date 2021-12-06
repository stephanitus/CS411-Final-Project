import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.lang.StringBuffer;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

//Movie Showings, movie tickets, customers`

public class DatabaseManager {

    private ArrayList<MovieShowing> movieShowings;
    private ArrayList<MovieTicket> movieTickets;
    private ArrayList<Customer> customers;
    private ArrayList<Administrator> administrators;

    public DatabaseManager() {
        this.movieShowings = new ArrayList<>();
        this.movieTickets = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.administrators = new ArrayList<>();
        try{
            readAll();
        }catch(FileNotFoundException e){
            System.out.println("Error loading file");
        }
    }


    /**
     * Searches directory for .txt database and loads all files into object (self)
     * Params: None
     * @throws FileNotFoundException
     */
    private void readAll() throws FileNotFoundException{
        // Load movie showings from disk
        Scanner reader = new Scanner(new File("./data/movieshowings.txt"));
        while(reader.hasNext()){
            //Read in 5 lines
            String title = reader.nextLine().strip();
            int numSeats = Integer.parseInt(reader.nextLine().strip());
            Date date;
            String datestring;
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mmaa");
            try{
                date = formatter.parse(reader.nextLine().strip());
                datestring = formatter.format(date);
            }catch(Exception e){
                System.out.println("date error");
                date = new Date();
                datestring = formatter.format(date);
            }
            int movieLength = Integer.parseInt(reader.nextLine().strip());
            int movieGenre = Integer.parseInt(reader.nextLine().strip());
            float price = Float.parseFloat(reader.nextLine().strip());

            //Add showing to memory
            MovieShowing showing = new MovieShowing(title, numSeats, datestring, movieLength, movieGenre, price);
            this.movieShowings.add(showing);
        }
        reader.close();
        
        // Load customer info from disk
        reader = new Scanner(new File("./data/customers.txt"));
        while(reader.hasNext()){
            //Read in 11 lines
            String username = reader.nextLine().strip();
            long userID = Long.parseLong(reader.nextLine().strip());
            String creditCardNumber = reader.nextLine().strip();
            float hoursWatched = Float.parseFloat(reader.nextLine().strip());
            
            //Number of movies watched per genre
            int actionWatched = Integer.parseInt(reader.nextLine().strip());
            int comedyWatched = Integer.parseInt(reader.nextLine().strip());
            int dramaWatched = Integer.parseInt(reader.nextLine().strip());
            int horrorWatched = Integer.parseInt(reader.nextLine().strip());
            int scifiWatched = Integer.parseInt(reader.nextLine().strip());
            
            int[] genresWatched = {actionWatched, comedyWatched, dramaWatched, horrorWatched, scifiWatched};
            
            float loyaltyPoints = Float.parseFloat(reader.nextLine().strip());
            int pendingShowings = Integer.parseInt(reader.nextLine().strip());

            //Add customer to memory
            Customer customer = new Customer(username, userID, creditCardNumber, hoursWatched, genresWatched, loyaltyPoints, pendingShowings);
            customers.add(customer);
        }
        reader.close();

        // Load movie ticket table from disk
        reader = new Scanner(new File("./data/movietickets.txt"));
        while(reader.hasNext()){
            // Read in 4 lines
        	long ticketID = Long.parseLong(reader.nextLine().strip()); 
            String title = reader.nextLine().strip();
            String date = reader.nextLine().strip();
            // Locate movie showing
            MovieShowing showing = null;
            for(MovieShowing s : movieShowings){
                if(s.getTitle().equals(title) && s.getShowingDate().equals(date)){
                    showing = s;
                }
            }
            long userID = Long.parseLong(reader.nextLine().strip());
            // Locate ticket owner
            Customer owner = getCustomer(userID);
            // Create ticket
            MovieTicket ticket = new MovieTicket(ticketID, showing, owner);
            movieTickets.add(ticket);
        }
        
        //Load administrator table from disk (maybe removed)
        reader = new Scanner(new File("./data/administrators.txt"));
        while(reader.hasNext()){
        	//Read in 2 lines
            String username = reader.nextLine().strip();
            long adminID = Long.parseLong(reader.nextLine().strip());
            
            //Add administrator to memory
            Administrator administrator = new Administrator(username, adminID);
            administrators.add(administrator);
        }
        reader.close();
    }

    public void writeChanges(){
        // Write changes to disk
        BufferedWriter writer;
        try{
            // Customers
            writer = new BufferedWriter(new FileWriter("./data/customers.txt"));
            writer.write("");
            for(Customer c : customers){
                writer.append(c.toDataString());
            }
            writer.close();
            
            //Movie Showings
            writer = new BufferedWriter(new FileWriter("./data/movieshowings.txt"));
            writer.write("");
            for(MovieShowing s : movieShowings){
                writer.append(s.toDataString());
            }
            writer.close();

            writer = new BufferedWriter(new FileWriter("./data/movietickets.txt"));
            writer.write("");
            for(MovieTicket t : movieTickets){
                writer.append(t.toDataString());
            }
            writer.close();
        }catch(IOException e){
            System.out.println("Write failure");
        }
    }
    
    //TO REMOVE
    /**
     * Takes in one ticketID and removes the corresponding ticket from movietickets.txt
     * @param ticketID
     * @throws FileNotFoundException
     */
    public void removeFromTickets(long ticketID) throws FileNotFoundException {
    	Scanner reader = new Scanner(new File("./data/movietickets.txt"));
    	try {
    		BufferedWriter writer = new BufferedWriter(new FileWriter("./data/movietickets.txt"));
    	
        while(reader.hasNext()){
            // Read in ticket ID
        	long readTicketID = Long.parseLong(reader.nextLine().strip());
        	//Skip next 3 lines if ID matches ID to delete
        	if (readTicketID == ticketID) {
        		for (int i = 0; i < 3; i++) {
        			reader.nextLine();
        		}
        		if (reader.hasNext()) {
        			readTicketID = Long.parseLong(reader.nextLine().strip());
        		}
        	}
        	if (reader.hasNext()) {
        	writer.append(String.valueOf(readTicketID));
            String title = reader.nextLine().strip();
            writer.append("\n"+title);
            String date = reader.nextLine().strip();
            writer.append("\n"+date);
            long userID = Long.parseLong(reader.nextLine().strip());
            writer.append("\n"+String.valueOf(userID));
        	}
        }
        writer.close();
    	} catch (IOException e) {
    		System.out.println("Write failure");
    	}
    	//delete and rename movieTickets.txt file
    	File toDelete = new File("./data/movietickets.txt");
    	toDelete.delete();
    	new File("./data/movieticketsupdated.txt").renameTo(new File("./data/movietickets.txt"));
        
	        
        }
    

    /***************
        Accessors
    ****************/

    /**
     * Accessor for list of movie showings
     * @return List of all movie showings
     */
    public List<MovieShowing> getShowings(){
        return movieShowings;
    }

    /**
     * 
     * @param ID
     * @return Boolean
     */
    public Boolean userExists(long ID){
        for (Customer c : customers){
            if (c.getUserID() == ID)
                return true;
        }
        return false;
    }

    public Customer getCustomer(long ID){
        for (Customer c : customers){
            if (c.getUserID() == ID)
                return c;
        }
        return null;
    }

    public MovieShowing getMovieShowing(int i){
        return movieShowings.get(i-1);
    }

    public List<MovieTicket> getMovieTickets(){
        return movieTickets;
    }

    /***************
        Mutators
    ***************/

    /**
     * Adds movie showing to database called by administrator
     * @param MovieShowing
     */
    public void addShowing(MovieShowing showing){
        this.movieShowings.add(showing);
    }

    /**
     * Adds new customer to database
     * @param Customer
     */
    public void addNewCustomer(Customer c){
        customers.add(c);
    }
    /**
     * Adds ticket to database, called by administrator
     * @param MovieTicket ticket
     */
    public void addMovieTicket(MovieTicket ticket){
        movieTickets.add(ticket);
    }
    /**
     * Removes ticket from database
     * @param MovieTicket
     */
    public void removeMovieTicket(MovieTicket ticket) {
    	movieTickets.remove(ticket);
    }
    /**
     * Removes a showing from database
     * @param MovieShowing
     */
    public void removeShowing(MovieShowing showing) {
    	movieShowings.remove(showing);
    }
}