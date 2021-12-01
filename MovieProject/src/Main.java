import java.util.*;

public class Main {
	public static void main(String[] args) {
		DatabaseManager db = new DatabaseManager();	
		Scanner userInput = new Scanner(System.in);
		System.out.println("Welcome to the movie ticket booking system!");
		System.out.println("Please select the type of user you are:\n1. Customer\n2. Admin");
		int input = userInput.nextInt();
		if(input == 1){
			runUserMenu(db);
		}else if(input == 2){
			runAdminMenu(db);
		}else{
			System.out.println("Invalid input");
			//TBD
		}
		userInput.close();
	}

	public static void runUserMenu(DatabaseManager db){
		Scanner userInput = new Scanner(System.in);
		//Login dialogue
		System.out.println("Are you a new or returning customer?\n1. New\n2. Returning");
		int input = userInput.nextInt();
		if(input == 1){
			//New customer
			//Give user ID and store in database
			Random generator = new Random();
			long ID = 10000000 + generator.nextLong(90000000);
			db.addNewCustomer(ID);
			System.out.println("New user profile created!\n Your new user ID (Don't lose this): " + ID);
		}else if(input == 2){
			//Returning customer
			System.out.println("Please input your user ID: ");
			long ID = userInput.nextLong();
			if(db.userExists(ID)){
				//Login complete
				System.out.println("Login Complete! Welcome");
			}else{
				//User doesn't exist
				System.out.println("User ID does not exist");
			}
		}else{
			System.out.println("Invalid input");
			//TBD
		}

		//User option dialogue
		//Print movie showings(extends purchase movie ticket), show purchased tickets
		System.out.println("What would you like to do?\n1. Browse Movie Showings\n2. Show Purchased Tickets\n3. Show User Info");
		input = userInput.nextInt();
		switch(input){
		case 1:
			//Print all movie showings
			break;
		case 2:
			//Load customer's purchased tickets
			break;
		case 3:
			//Load general user info
			break;
		default:
			System.out.println("Invalid input");
		}
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
	}
}
