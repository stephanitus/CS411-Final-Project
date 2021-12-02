import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;

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
	
	private DatabaseManager db;
	private Customer customer;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		this.db = new DatabaseManager();
		if(ShowingsListView != null) {
			loadShowingData();
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
			this.customer = new Customer(username, customerID, creditCard, 0, 0, 0, 0);
			db.addNewCustomer(this.customer);
			db.writeChanges();
		}else{
			// Returning User validation
			long ID = Long.parseLong(ReturningUserID.getText());
			if(Boolean.TRUE.equals(db.userExists(ID))){
				//Login complete
				this.customer = db.getCustomer(ID);
			}else{
				//User doesn't exist
				//Send popup
				return;
			}
		}
		
		Parent root = FXMLLoader.load(getClass().getResource("UserMenuScene.fxml"));
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
}
