package reception;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import bean.Guest;
import bean.GuestHolder;
import bean.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import utility.GuestDataUtils;
import utility.MyNotification;
import utility.RoomUtils;

public class GuestDetailsController implements Initializable {

	  @FXML
	    private Label lblGuestId;

    @FXML
    private JFXTextField tfGuestName;

    @FXML
    private JFXTextField tfGuestNRC;

    @FXML
    private JFXTextField tfNoOfGuest;

    @FXML
    private JFXTextField tfGuestPhNo;

    @FXML
    private JFXTextField tfGuestRoomNo;

    @FXML
    private JFXComboBox<String> cobRoomType;

    @FXML
    private JFXTextField tfGuestRoomPrice;

    @FXML
    private JFXTextField tfGuestFoodOrderPrice;

    @FXML
    private JFXTextField tfGuestInRoomCost;

    @FXML
    private JFXTextField tfStayDays;

    @FXML
    private JFXDatePicker dpGuestCheckInDate;

    @FXML
    private JFXDatePicker dpGuestCheckOutDate;
    
    private final GuestDataUtils guestDataUtils=new GuestDataUtils();
    private final RoomUtils roomDataUtils=new RoomUtils();
    private final MyNotification noti=new MyNotification();
    @FXML
    void processCancel(ActionEvent event) {
    	
    }

    @FXML
    void processUpdate(ActionEvent event) throws SQLException {
    	int guestId= Integer.parseInt(lblGuestId.getText());
    	String guestName = tfGuestName.getText().trim();
    	String guestNRC = tfGuestNRC.getText().trim();
    	int noOfGuests= Integer.parseInt(tfNoOfGuest.getText());
    	String guestPhNo = tfGuestPhNo.getText().trim();
    	String guestRoomType = cobRoomType.getValue();
    	int guestRoomNo = Integer.parseInt(tfGuestRoomNo.getText());
    	double guestRoomPrice = Double.parseDouble(tfGuestRoomPrice.getText());
    	
    	double  foodOrderPrice=0.0;
    	
    	double guestInRoomCost=0.0;

    	String guestCheckInDate= dpGuestCheckInDate.getValue().toString();

    	String guestCheckOutDate = dpGuestCheckOutDate.getValue().toString();
    	
    	int numberOfDays = Integer.parseInt(tfStayDays.getText());
    	String guestStatus="CheckIn";
    	Guest guestUpdated = new Guest(guestId, guestName, guestNRC, noOfGuests, guestPhNo, guestRoomType, guestRoomNo, guestRoomPrice, foodOrderPrice, guestInRoomCost, guestCheckInDate, guestCheckOutDate, numberOfDays,guestStatus);
		
		Integer rowUpdated = guestDataUtils.updateGuest(guestUpdated);
		
		if (rowUpdated > 0) {
			
			noti.getNotification(NotificationType.SUCCESS, "Success", "Successfully Updated "+guestName+" to DB", AnimationType.SLIDE, 2000.0);
			
			
		}else {
			
			
			noti.getNotification(NotificationType.ERROR, "Failed", "Fail to update "+guestName+" in DB", AnimationType.SLIDE, 2000.0);
		
	}
    }

    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		ObservableList<String> roomTypeList = FXCollections.observableArrayList();

		try {
			ObservableList<Room>roomList=roomDataUtils.getAllRoom("select * from room group by roomType;");
			for(Room room:roomList) {
				roomTypeList.add(room.getRoomType());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cobRoomType.setItems(roomTypeList);
	
		GuestHolder holder = GuestHolder.getGuestInstance();
		
		Guest guest = holder.getGuest();
		
	
		
//		lblTitle.setText(book.getBookTitle()+" Profile");
		lblGuestId.setText(guest.getGuestId().toString());
		
		tfGuestName.setText(guest.getGuestName());
		tfGuestNRC.setText(guest.getGuestNRC());
		tfNoOfGuest.setText(guest.getNoOfGuests().toString());
		tfGuestPhNo.setText(guest.getGuestPhNo());
		tfGuestRoomNo.setText(guest.getGuestRoomNo().toString());
		cobRoomType.setValue(guest.getGuestRoomType().toString());
		tfGuestRoomPrice.setText(guest.getGuestRoomPrice().toString());
		tfGuestFoodOrderPrice.setText(guest.getFoodOrderPrice().toString());
		tfGuestInRoomCost.setText(guest.getGuestInRoomCost().toString());
		dpGuestCheckInDate.setValue(LocalDate.parse(guest.getGuestCheckInDate().toString()));
		dpGuestCheckOutDate.setValue(LocalDate.parse(guest.getGuestCheckOutDate().toString()));
		tfStayDays.setText(guest.getNumberOfDays().toString());
		
		
	}

}
