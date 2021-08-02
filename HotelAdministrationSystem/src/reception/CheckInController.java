package reception;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import bean.BookingStatusHolder;
import bean.Room;
//import bean.Guest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
//import utility.GuestDataUtils;
import utility.MyAlert;
import utility.RoomUtils;


public class CheckInController implements Initializable{

    @FXML
    private JFXTextField tfGuestName;

    @FXML
    private JFXTextField tfGuestNRC;

    @FXML
    private JFXTextField tfNoOfGuest;

    @FXML
    private JFXTextField tfGuestPhNo;

    @FXML
    private JFXComboBox<String> cobRoomType;
    
    @FXML
    private JFXComboBox<Integer> cobRoomNo;

    @FXML
    private JFXTextField tfGuestRoomNo;

    @FXML
    private JFXTextField tfGuestRoomPrice;

    @FXML
    private JFXTextField tfStayDays;

    @FXML
    private JFXDatePicker dpGuestCheckInDate;

    @FXML
    private JFXDatePicker dpGuestCheckOutDate;
    
    private final RoomUtils roomDataUtils=new RoomUtils();
    
    private final MyAlert alert=new MyAlert();

    @FXML
    void processCancel(ActionEvent event) {
    	clearAllField();
    }

    @FXML
    void processSave(ActionEvent event) throws SQLException, IOException {
    	if(validateFields()&& validateDate()) {
    	    BookingStatusHolder.setButtonStatus("checkin");
    		FXMLLoader loader=new FXMLLoader(getClass().getResource("CheckInFormUI.fxml"));
        	Parent root=(Parent)loader.load();	
    		CheckInFormController formController=loader.getController();
    		formController.showForm(tfGuestName.getText(),tfGuestNRC.getText(),tfNoOfGuest.getText(), tfGuestPhNo.getText(), cobRoomType.getValue(), 
    				cobRoomNo.getValue().toString(),tfGuestRoomPrice.getText(),dpGuestCheckInDate.getValue().toString(),dpGuestCheckOutDate.getValue().toString(),tfStayDays.getText());
    		Stage registerStage=new Stage();
    		registerStage.setTitle("CheckIn Confirmation Form Section");
    		Image icon=new Image(getClass().getResourceAsStream("../img/hotel.png"));
			registerStage.getIcons().add(icon);
    		registerStage.setScene(new Scene(root));
    		registerStage.setResizable(false);
    		registerStage.show();
    	}
    
    
    }
    @FXML
    void processRoomType(ActionEvent event) throws SQLException {
      cobRoomNo.setDisable(false);
      ObservableList<Integer>roomNo=FXCollections.observableArrayList();
      ObservableList<Room>roomList=roomDataUtils.getAllRoom("select * from room where roomType = '"+cobRoomType.getValue()+"' and roomStatus ='Available';");
      for(Room room:roomList) {
    	  roomNo.add(room.getRoomNumber());
      }
      cobRoomNo.setItems(roomNo);
    }
    
    @FXML
    void processRoomNo(ActionEvent event) throws SQLException {
    	if(cobRoomNo.getValue()!=null) {
    		 Room room=roomDataUtils.getAllRoom("select * from room where roomNumber= '"+cobRoomNo.getValue()+"';").get(0);
    	      tfGuestRoomPrice.setText(room.getRoomPrice().toString());
    	}
     
    }
    
    public void clearAllField() {
    	tfGuestName.clear();
    	tfGuestNRC.clear();
    	tfNoOfGuest.clear();
    	tfGuestPhNo.clear();
    	cobRoomType.setValue("Type");
    	cobRoomNo.setValue(null);
    	tfGuestRoomPrice.clear();
    	
    	dpGuestCheckInDate.setValue(LocalDate.now());
    	dpGuestCheckOutDate.setValue(LocalDate.now());
    	
    	tfStayDays.clear();
    }
    
    private boolean validateFields() {
    	if(tfGuestName.getText().isEmpty()||tfGuestNRC.getText().isEmpty()||tfNoOfGuest.getText().isEmpty()||tfGuestPhNo.getText().isEmpty()||cobRoomType.getValue().isEmpty()||cobRoomNo.getValue()==null||tfGuestRoomPrice.getText().isEmpty()||tfStayDays.getText().isEmpty()) {
    		alert.getAlert(AlertType.WARNING,"Validate Date Input", null, "Please Fill the blanks completely");
    		return false;
    	}
    	return true;
	    }
    private boolean validateDate() {
    	if(dpGuestCheckInDate.getEditor().getText().isEmpty()|dpGuestCheckOutDate.getEditor().getText().isEmpty()) {
    		Alert alert=new Alert(AlertType.WARNING);
    		alert.setTitle("Validate Date Input");
    		alert.setHeaderText(null);
    		alert.setContentText("Please Fill the blanks completely");
    		alert.showAndWait();
    		return false;
    	}
    return true;
    	
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
		cobRoomNo.setDisable(true);
		dpGuestCheckInDate.setValue(LocalDate.now());
	}
	
	
}
