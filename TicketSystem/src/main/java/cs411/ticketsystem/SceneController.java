package cs411.ticketsystem;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SceneController implements Initializable {
	
	ObservableList<String> list = FXCollections.observableArrayList();
	
	@FXML
	private TextField NewUsername;
	@FXML
	private TextField CreditInfo;
	@FXML
	private TextField ReturningUserID;
	@FXML
	private ListView<String> ShowingsListView;
	@FXML
	private ListView<String> PurchasedTicketsListView;
	@FXML
	private Label ProfileInfoLabel;
	@FXML
	private TextField LoginFailed;
	@FXML
	private TextField BuyTicketAmount;
	
	public static DatabaseManager db = new DatabaseManager();
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		if(ShowingsListView != null) {
			loadShowingData();
		}
		
		if(PurchasedTicketsListView != null) {
			loadTicketData();
		}
		
		if(ProfileInfoLabel != null) {
			ProfileInfoLabel.setText(db.getActiveUser().toString());
		}
	}
	
	private void loadShowingData() {
		list.clear();
		List<MovieShowing> showings = db.getShowings();
		for(MovieShowing s : showings) {
			list.add(s.toString());
		}
		ShowingsListView.setItems(list);
	}
	
	private void loadTicketData() {
		list.clear();
		List<MovieTicket> tickets = db.getMovieTickets();
		Customer activeUser = db.getActiveUser();
		for(MovieTicket s : tickets) {
			if(s.getOwner().getUserID() == activeUser.getUserID()) {
				list.add(s.toString());
			}
		}
		PurchasedTicketsListView.setItems(list);
	}
	
	public void switchToLoginScene(ActionEvent event) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToNewUserScene(ActionEvent event) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("NewUserScene.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToReturningUserScene(ActionEvent event) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("ReturningUserScene.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToUserMenuScene(ActionEvent event) throws IOException{
		//If new user
		if(NewUsername != null) {
			// Collect new user info
			String username = NewUsername.getText();
			String creditCard = CreditInfo.getText();
			Random generator = new Random();
			long customerID = 10000000L + generator.nextLong() % 90000000L;
			int[] genresWatched = {0, 0, 0, 0, 0};
			Customer customer = new Customer(username, customerID, creditCard, 0, genresWatched, 0, 0);
			db.addNewCustomer(customer);
			db.setActiveUser(customer);
			db.writeChanges();
		}else{
			// Checking if the ID is empty or if it is not a number
			if (ReturningUserID.getText().isEmpty()){
				LoginFailed.setText("User ID can't be empty");
				LoginFailed.setVisible(Boolean.TRUE);
				return;
			}else if (!ReturningUserID.getText().matches("^[0-9]*$")){
				LoginFailed.setText("User ID consists of only numbers");
				LoginFailed.setVisible(Boolean.TRUE);
				return;
			}

			// Returning User validation
			long ID = Long.parseLong(ReturningUserID.getText());
			if(Boolean.TRUE.equals(db.userExists(ID))){
				//Login complete
				db.setActiveUser(db.getCustomer(ID));
			}else{
				//User doesn't exist
				//Send popup
				LoginFailed.setText("User ID Incorrect");
				LoginFailed.setVisible(Boolean.TRUE);
				return;
			}
		}
		
		Parent root = FXMLLoader.load(getClass().getResource("UserMenuScene.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void returnToUserMenuScene(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("UserMenuScene.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void switchToAdminLoginScene(ActionEvent event) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("AdminLoginScene.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	//TBI
	public void switchToAdminPortalScene(ActionEvent event) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("AdminPortalScene.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToDisplayMovieShowingsScene(ActionEvent event) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("DisplayMovieShowingsScene.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToPurchasedTicketsScene(ActionEvent event) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("PurchasedTicketsScene.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToProfileInfoScene(ActionEvent event) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("ProfileInfoScene.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void switchToAddMovieShowingScene(ActionEvent event) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("AddMovieShowingScene.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void switchToUserSelectScene(ActionEvent event) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("UserSelectScene.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void logoutToUserSelectScene(ActionEvent event) throws IOException{
		//Logout Active User first
		db.logoutActiveUser();
		//Change Scene
		Parent root = FXMLLoader.load(getClass().getResource("UserSelectScene.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void recordTicketPurchase(ActionEvent event) throws IOException{
		//Checks if ticket amount is empty, not an int, a zero value, or if no movie is selected
		if (ShowingsListView.getSelectionModel().getSelectedItem() == null) {
			BuyTicketAmount.setPromptText("Please select a movie");
			BuyTicketAmount.setStyle("-fx-prompt-text-fill: red;");
			BuyTicketAmount.setText("");
			return;
		} else if (BuyTicketAmount.getText().isEmpty()) {
			BuyTicketAmount.setPromptText("Please enter number of tickets");
			BuyTicketAmount.setStyle("-fx-prompt-text-fill: red;");
			return;
		} else if (!BuyTicketAmount.getText().matches("^[0-9]*$") || BuyTicketAmount.getText().equals("0")) {
			BuyTicketAmount.setPromptText("Please enter a valid number");
			BuyTicketAmount.setStyle("-fx-prompt-text-fill: red;");
			BuyTicketAmount.setText("");
			return;
		}
		// End of Initial Error Checking
		int ticketsPurchased = Integer.parseInt(BuyTicketAmount.getText());
		String item = ShowingsListView.getSelectionModel().getSelectedItem();
		//Attempt to match through toString calls
		MovieShowing showing = null;
		for (MovieShowing s : db.getShowings()){
			if(s.toString().equals(item)){
				showing = s;
			}
		}
		// Checks if the number of tickets exceeds seats left for that showing/movie
		if (Integer.parseInt(BuyTicketAmount.getText()) > showing.getSeatsLeft()) {
			BuyTicketAmount.setPromptText("Not enough seats left");
			BuyTicketAmount.setStyle("-fx-prompt-text-fill: red;");
			BuyTicketAmount.setText("");
			return;
		}
		// Generates Tickets
		List<MovieTicket> newTickets = showing.generateTickets(ticketsPurchased, db.getActiveUser());
		//Assign to current user
		db.addMovieTickets(newTickets);
		db.writeChanges();

		returnToUserMenuScene(event);
	}
}
