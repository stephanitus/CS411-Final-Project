import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.text.DateFormat;

//Movie Showings, movie tickets, customers`

public class DatabaseManager {

    private ArrayList<MovieShowing> movieShowings;

    DatabaseManager() {
        this.movieShowings = new ArrayList<>();
        readAll();
    }

    public void readAll(){
        Scanner reader = new Scanner("./data/movieshowings.txt");

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
    }

    public List<MovieShowing> getShowings(){
        return movieShowings;
    }
}