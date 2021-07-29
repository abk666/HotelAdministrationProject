package reception;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import java.util.ResourceBundle;

import bean.Guest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import utility.GuestDataUtils;
import utility.MyNotification;

public class CheckInFormController implements Initializable{

    @FXML
    private Label lblGuestName;

    @FXML
    private Label lblGuestNRC;

    @FXML
    private Label lblNoOfGuest;

    @FXML
    private Label lblGuestPhNo;

    @FXML
    private Label lblRoomType;

    @FXML
    private Label lblGuestRoomNo;

    @FXML
    private Label lblGuestRoomPrice;

    @FXML
    private Label lblGuestCheckInDate;

    @FXML
    private Label lblGuestCheckOutDate;

    @FXML
    private Label lblStayDays;
    
    private final GuestDataUtils guestDataUtils=new GuestDataUtils();
    
    private final MyNotification noti=new MyNotification();

    @FXML
    void processCancel(ActionEvent event) throws IOException {
    	Stage primaryStage = new Stage();
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("CheckInUI.fxml"));
		Scene scene = new Scene(root);
		primaryStage.hide();
		primaryStage.setTitle("CheckIn UI Section");
		primaryStage.setScene(scene);
		primaryStage.show();
    }

    @FXML
    void processConfirm(ActionEvent event) throws SQLException {

    	String guestName = lblGuestName.getText().trim();
    	String guestNRC = lblGuestNRC.getText().trim();
    	int noOfGuests= Integer.parseInt(lblNoOfGuest.getText());
    	String guestPhNo = lblGuestPhNo.getText().trim();
    	String guestRoomType =lblRoomType.getText();
    	int guestRoomNo =Integer.parseInt(lblGuestRoomNo.getText());

    	double guestRoomPrice = Double.parseDouble(lblGuestRoomPrice.getText());
    	
    	double  foodOrderPrice=0.0;
    	
    	double guestInRoomCost=0.0;
    	
    	String guestCheckInDate=lblGuestCheckInDate.getText();



    	String guestCheckOutDate =lblGuestCheckOutDate.getText();
    	
    	int numberOfDays = Integer.parseInt(lblStayDays.getText());
    	
    	String guestStatus="CheckIn";
    	
    	Guest guest=new Guest(guestName, guestNRC, noOfGuests, guestPhNo, guestRoomType, guestRoomNo, guestRoomPrice, foodOrderPrice, guestInRoomCost, guestCheckInDate, guestCheckOutDate, numberOfDays,guestStatus);
    	
    	Boolean isSaveOk = guestDataUtils.saveGuest(guest);
    	
    	if (!isSaveOk) {
    		
        	guestDataUtils.UpdateRoomStatus(guest.getGuestRoomNo());
        	
        	 noti.getNotification(NotificationType.SUCCESS, "Success!", "Successfully Checked In!", AnimationType.SLIDE, 2000.0);
			System.out.println("Successfully Saved "+guestName+" to DB");
			
			
		
    	}else {
    		noti.getNotification(NotificationType.ERROR, "Fail!", "Fail Check In!", AnimationType.SLIDE, 2000.0);
			System.out.println("Fail to Save "+guestName+" to DB");
			
		}
    }
   
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

    public void showForm(String guestname,String guestNRC,String noOfGuest,String guestPhNo,
    		String roomType,String guestRoomNo,String guestRoomPrice,String guestCheckIn,String guestCheckOut,String numberofDays) {
    lblGuestName.setText(guestname);
    lblGuestNRC.setText(guestNRC);
    lblNoOfGuest.setText(noOfGuest);
    lblGuestPhNo.setText(guestPhNo);
    lblRoomType.setText(roomType);
    lblGuestRoomNo.setText(guestRoomNo);
    lblGuestRoomPrice.setText(guestRoomPrice);
    lblGuestCheckInDate.setText(guestCheckIn);
    lblGuestCheckOutDate.setText(guestCheckOut);
    lblStayDays.setText(numberofDays);
}
}
