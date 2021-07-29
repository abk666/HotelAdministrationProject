package reception;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import bean.Booking;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import utility.BookingDataUtils;
import utility.MyNotification;

public class BookingController implements Initializable {

    @FXML
    private JFXTextField tfGuestName;

    @FXML
    private JFXTextField tfGuestPhNo;

    @FXML
    private JFXComboBox<String> cobRoomType;

    @FXML
    private JFXTextField tfRoomNo;

    @FXML
    private JFXTextField tfRoomPrice;

    @FXML
    private JFXTextField tfNoOfGuest;

    @FXML
    private JFXTextField tfStayDays;

    @FXML
    private JFXDatePicker dpBookingDate;

    @FXML
    private JFXDatePicker dpCheckInDate;

    @FXML
    private JFXDatePicker dpCheckOutDate;
    
    private final BookingDataUtils bookingDataUtils=new BookingDataUtils();
    
    private final MyNotification noti=new MyNotification();

    @FXML
    void processCancel(ActionEvent event) {
    	clearAllField();
    }

    @FXML
    void processSave(ActionEvent event) throws SQLException {
    	String guestName = tfGuestName.getText().trim();
    	String guestPhNo = tfGuestPhNo.getText().trim();
    	String roomType = cobRoomType.getValue();
    	int roomNo = Integer.parseInt(tfRoomNo.getText());
    	double roomPrice = Double.parseDouble(tfRoomPrice.getText());
    	int noOfGuest = Integer.parseInt(tfNoOfGuest.getText());
    	String bookedDate = dpBookingDate.getValue().toString();
    	String checkInDate= dpCheckInDate.getValue().toString();
    	int numberOfDays = Integer.parseInt(tfStayDays.getText());
    	
    	String checkOutDate = dpCheckOutDate.getValue().toString();
    	
    	Booking booking =new Booking(guestName, guestPhNo, roomType, roomNo, roomPrice, noOfGuest, bookedDate, checkInDate, numberOfDays, checkOutDate);
    	
    	Boolean isSaveOk = bookingDataUtils.saveBooking(booking);
		
    	if (!isSaveOk) {
    		 noti.getNotification(NotificationType.SUCCESS, "Success!", "Successfully Booked!", AnimationType.POPUP, 2000.0);
			System.out.println("Successfully Saved "+guestName+" to DB");
			
			
			clearAllField();
    	}else {
    		noti.getNotification(NotificationType.ERROR, "Failed!", "Fail to Book!", AnimationType.POPUP, 2000.0);
			System.out.println("Fail to Save "+guestName+" to DB");
			
		}
    }

    @FXML
    void processViewBooking(ActionEvent event) throws IOException {
    	Stage primaryStage = new Stage();
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("BookingTableUI.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("View Booking Table Section");
		primaryStage.setScene(scene);
		primaryStage.show();
    }
    public void clearAllField() {
    	tfGuestName.clear();
    	tfGuestPhNo.clear();
    	cobRoomType.setValue("Type");
    	tfRoomNo.clear();
    	tfRoomPrice.clear();
    	tfNoOfGuest.clear();
    	tfStayDays.clear();
    	dpBookingDate.setValue(LocalDate.now());
    	dpCheckInDate.setValue(LocalDate.now());
    	dpCheckOutDate.setValue(LocalDate.now());
    	
    	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> roomTypeList = FXCollections.observableArrayList(
				   "Single","Double","Triple"		
						);
		cobRoomType.setItems(roomTypeList);
		
	}

}
