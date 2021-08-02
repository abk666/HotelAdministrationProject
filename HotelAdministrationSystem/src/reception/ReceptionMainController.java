package reception;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ReceptionMainController implements Initializable {
	 @FXML
	    private Label lblToday;

	    
	   
	
    @FXML
    void processBooking(ActionEvent event) throws IOException {
    	Stage primaryStage = new Stage();
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("BookingUI.fxml"));
		Scene scene = new Scene(root);
		Image icon=new Image(getClass().getResourceAsStream("../img/hotel.png"));
		primaryStage.getIcons().add(icon);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Reception Booking Section");
		primaryStage.setScene(scene);
		primaryStage.show();
    }

    @FXML
    void processCheckIn(ActionEvent event) throws IOException {
    	Stage primaryStage = new Stage();
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("CheckInUI.fxml"));
		Scene scene = new Scene(root);
		Image icon=new Image(getClass().getResourceAsStream("../img/hotel.png"));
		primaryStage.getIcons().add(icon);
		primaryStage.setTitle("Reception CheckIn Section");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
    }

    @FXML
    void processCheckOut(ActionEvent event) throws IOException {
    	Stage primaryStage=(Stage) ((Button)event.getSource()).getScene().getWindow();
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("CheckOutUI.fxml"));
		Scene scene = new Scene(root);
		Image icon=new Image(getClass().getResourceAsStream("../img/hotel.png"));
		primaryStage.getIcons().add(icon);
		primaryStage.setResizable(true);
		primaryStage.setTitle("Reception CheckOut Section");
		primaryStage.setScene(scene);

		primaryStage.show();
    }

    @FXML
    void processGuests(ActionEvent event) throws IOException {
    	Stage primaryStage = new Stage();
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("ReceptionGuestsUI.fxml"));
		Scene scene = new Scene(root);
		Image icon=new Image(getClass().getResourceAsStream("../img/hotel.png"));
		primaryStage.getIcons().add(icon);
		primaryStage.setTitle("Reception Guests Section");
		primaryStage.setScene(scene);
		
		primaryStage.show();
    }

    @FXML
    void processLogout(ActionEvent event) throws IOException {
    	Stage primaryStage = (Stage) ((Button)event.getSource()).getScene().getWindow();
    	primaryStage.hide();
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../main/LoginUI.fxml"));
		Scene scene = new Scene(root);
		Image icon=new Image(getClass().getResourceAsStream("../img/hotel.png"));
		primaryStage.setTitle("Hotel Administration Login");
		primaryStage.getIcons().add(icon);
		primaryStage.setScene(scene);
		
		primaryStage.show();
    }

    @FXML
    void processRooms(ActionEvent event) throws IOException {
    	Stage primaryStage = new Stage();
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("ReceptionRoomsUI.fxml"));
		Scene scene = new Scene(root);
		Image icon=new Image(getClass().getResourceAsStream("../img/hotel.png"));
		primaryStage.getIcons().add(icon);
		primaryStage.setTitle("Reception Rooms Section");
		primaryStage.setScene(scene);
		primaryStage.show();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String date=(LocalDate.now()).toString();
		lblToday.setText(date);
	}

}
