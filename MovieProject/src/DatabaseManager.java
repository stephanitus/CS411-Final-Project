import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
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
    
    private Customer activeUser;

    public DatabaseManager() {
        this.movieShowings = new ArrayList<>();
        this.movieTickets = new ArrayList<>();
        this.customers = new ArrayList<>();
        try{
            readAll();
        }catch(FileNotFoundException e){
            System.out.println(e);
        }
    }


    /**
     * Searches directory for .txt database and loads all files into object (self)
     * Params: None
     * @throws FileNotFoundException
     */
    private void readAll() throws FileNotFoundException{
        // Load movie showings from disk
        Scanner reader = new Scanner(new File("./src/data/movieshowings.txt"));
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
            String movieGenre = reader.nextLine().strip();
            float price = Float.parseFloat(reader.nextLine().strip());

            //Add showing to memory
            MovieShowing showing = new MovieShowing(title, numSeats, datestring, movieLength, movieGenre, price);
            this.movieShowings.add(showing);
        }
        reader.close();
        
        // Load customer info from disk
        reader = new Scanner(new File("./src/data/customers.txt"));
        while(reader.hasNext()){
            //Read in 6 lines
            String username = reader.nextLine().strip();
            long userID = Long.parseLong(reader.nextLine().strip());
            String creditCardNumber = reader.nextLine().strip();
            float hoursWatched = Float.parseFloat(reader.nextLine().strip());
            int genresWatched = Integer.parseInt(reader.nextLine().strip());
            int loyaltyPoints = Integer.parseInt(reader.nextLine().strip());
            int pendingShowings = Integer.parseInt(reader.nextLine().strip());

            //Add customer to memory
            Customer customer = new Customer(username, userID, creditCardNumber, hoursWatched, genresWatched, loyaltyPoints, pendingShowings);
            customers.add(customer);
        }
        reader.close();

        // Load movie ticket table from disk
        reader = new Scanner(new File("./src/data/movietickets.txt"));
        while(reader.hasNext()){
            // Read in 3 lines
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
            MovieTicket ticket = new MovieTicket(showing, owner);
            movieTickets.add(ticket);
        }
        reader.close();
    }

    public void writeChanges(){
        // Write changes to disk
        BufferedWriter writer;
        try{
            // Customers
            writer = new BufferedWriter(new FileWriter("./src/data/customers.txt"));
            writer.write("");
            for(Customer c : customers){
                writer.append(c.toDataString());
            }
            writer.close();
            
            //Movie Showings
            writer = new BufferedWriter(new FileWriter("./src/data/movieshowings.txt"));
            writer.write("");
            for(MovieShowing s : movieShowings){
                writer.append(s.toDataString());
            }
            writer.close();

            writer = new BufferedWriter(new FileWriter("./src/data/movietickets.txt"));
            writer.write("");
            for(MovieTicket t : movieTickets){
                writer.append(t.toDataString());
            }
            writer.close();
        }catch(IOException e){
            System.out.println("Write failure");
        }
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
    
    public Customer getActiveUser() {
    	return activeUser;
    }

    /***************
        Mutators
    ***************/

    /**
     * Adds movie showing to database
     * @param showing
     */
    public void addShowing(MovieShowing showing){
        this.movieShowings.add(showing);
    }

    /**
     * Adds new customer to database
     * @param ID
     */
    public void addNewCustomer(Customer c){
        customers.add(c);
    }

    public void addMovieTicket(MovieTicket ticket){
        movieTickets.add(ticket);
    }
    
    public void setActiveUser(Customer user) {
    	this.activeUser = user;
    }
}