import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.text.DateFormat;
import java.io.File;
import java.io.FileNotFoundException;

//Movie Showings, movie tickets, customers`

public class DatabaseManager {

    private ArrayList<MovieShowing> movieShowings;
    private ArrayList<Customer> customers;

    DatabaseManager() {
        this.movieShowings = new ArrayList<>();
        this.customers = new ArrayList<>();
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
            try{
                date = DateFormat.getDateInstance().parse(reader.nextLine().strip());
            }catch(Exception e){
                System.out.println("date error");
                date = new Date();
            }
            int movieLength = Integer.parseInt(reader.nextLine().strip());
            String movieGenre = reader.nextLine().strip();

            //Add showing to memory
            MovieShowing showing = new MovieShowing(title, numSeats, date, movieLength, movieGenre);
            this.movieShowings.add(showing);
        }
        reader.close();
        
        // Load customer info from disk
        reader = new Scanner(new File("./data/customers.txt"));
        while(reader.hasNext()){
            //Read in 6 lines
            String username = reader.nextLine().strip();
            String creditCardNumber = reader.nextLine().strip();
            float hoursWatched = Float.parseFloat(reader.nextLine().strip());
            int genresWatched = Integer.parseInt(reader.nextLine().strip());
            int loyaltyPoints = Integer.parseInt(reader.nextLine().strip());
            int pendingShowings = Integer.parseInt(reader.nextLine().strip());

            //Add customer to memory
            Customer customer = new Customer(username, creditCardNumber, hoursWatched, genresWatched, loyaltyPoints, pendingShowings);
            customers.add(customer);
        }
        reader.close();

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
        return true;
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
    public void addNewCustomer(long ID){
        //TBI
    }
}