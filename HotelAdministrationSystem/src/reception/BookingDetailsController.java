package reception;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import bean.Booking;
import bean.BookingHolder;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import utility.BookingDataUtils;


public class BookingDetailsController implements Initializable{

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
    private Label lblBookingId;

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


    @FXML
    void processCancel(ActionEvent event) {

    }

    @FXML
    void processUpdate(ActionEvent event) throws SQLException {
    	int bookingId=Integer.parseInt(lblBookingId.getText());
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
    	
    	Booking bookingUpdated =new Booking(bookingId, guestName, guestPhNo, roomType, roomNo, roomPrice, noOfGuest, bookedDate, checkInDate, numberOfDays, checkOutDate);
    	
    	Integer rowUpdated = bookingDataUtils.updateBooking(bookingUpdated);
		
		if (rowUpdated > 0) {
			
			System.out.println("Successfully Updated "+guestName+" to DB");
			
			
		}else {
			
			
			System.out.println("Fail to Update "+guestName+" to DB");
		
	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> roomTypeList = FXCollections.observableArrayList(
				   "Single","Double","Triple"		
						);
		cobRoomType.setItems(roomTypeList);
	
		BookingHolder holder = BookingHolder.getBookingInstance();
		
		Booking booking = holder.getBooking();
		
	
		
//		lblTitle.setText(book.getBookTitle()+" Profile");
		lblBookingId.setText(booking.getBookingId().toString());
		
		tfGuestName.setText(booking.getGuestName());
		tfGuestPhNo.setText(booking.getGuestPhNo());
		cobRoomType.setValue(booking.getRoomType().toString());
		tfRoomNo.setText(booking.getRoomNo().toString());
		tfRoomPrice.setText(booking.getRoomPrice().toString());
		tfNoOfGuest.setText(booking.getNoOfGuest().toString());
		
		
		dpBookingDate.setValue(LocalDate.parse(booking.getBookedDate().toString()));
		dpCheckInDate.setValue(LocalDate.parse(booking.getCheckInDate().toString()));
		tfStayDays.setText(booking.getNumberOfDays().toString());
		dpCheckOutDate.setValue(LocalDate.parse(booking.getCheckOutDate().toString()));
	
		
	}

}
