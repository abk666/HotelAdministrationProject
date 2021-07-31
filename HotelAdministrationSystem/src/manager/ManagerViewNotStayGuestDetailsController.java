package manager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import bean.Guest;
import bean.GuestHolder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ManagerViewNotStayGuestDetailsController implements Initializable {

	@FXML
    private Label lblGuestName;

    @FXML
    private Label lblGuestNRC;

    @FXML
    private Label lblGuestPhone;

    @FXML
    private Label lblRoomNo;

    @FXML
    private Label lblRoomType;

    @FXML
    private Label lblCheckInDate;

    @FXML
    private Label lblNoOfDays;

    @FXML
    private Label lblNoOfGuest;

    @FXML
    private Label lblTotalPrice;

    @FXML
    private Label lblCheckOutDate;

    @FXML
    void processBack(MouseEvent event) throws IOException {

    	Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	primaryStage.setResizable(false);
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("ManagerViewGuestNotStayUI.fxml"));
		Scene scene = new Scene(root);
		Image icon=new Image(getClass().getResourceAsStream("../img/hotel.png"));
		primaryStage.getIcons().add(icon);
		primaryStage.setTitle("ManagerViewGuestNotStayUI");
		primaryStage.setScene(scene);
		primaryStage.show();
		
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		GuestHolder holder = GuestHolder.getGuestInstance();
		Guest guest = holder.getGuest();
		
		lblGuestName.setText(guest.getGuestName());
		lblGuestNRC.setText(guest.getGuestNRC());
		lblGuestPhone.setText(guest.getGuestPhNo());
		lblRoomNo.setText(guest.getGuestRoomNo().toString());
		lblRoomType.setText(guest.getGuestRoomType());
		lblCheckInDate.setText(guest.getGuestCheckInDate());
		lblNoOfDays.setText(guest.getNumberOfDays().toString());
		lblNoOfGuest.setText(guest.getNoOfGuests().toString());
		lblTotalPrice.setText(guest.getTotalPrice().toString());
		lblCheckOutDate.setText(guest.getGuestCheckOutDate());
		
		
	}

}
