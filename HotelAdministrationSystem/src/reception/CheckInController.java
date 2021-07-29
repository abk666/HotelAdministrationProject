package reception;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;


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
import javafx.stage.Stage;
//import utility.GuestDataUtils;
import utility.MyAlert;

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
    private JFXTextField tfGuestRoomNo;

    @FXML
    private JFXTextField tfGuestRoomPrice;

    @FXML
    private JFXTextField tfStayDays;

    @FXML
    private JFXDatePicker dpGuestCheckInDate;

    @FXML
    private JFXDatePicker dpGuestCheckOutDate;
    
 
    
    private final MyAlert alert=new MyAlert();

    @FXML
    void processCancel(ActionEvent event) {
    	clearAllField();
    }

    @FXML
    void processSave(ActionEvent event) throws SQLException, IOException {
    	if(validateFields()&& validateDate()) {
    		
    		FXMLLoader loader=new FXMLLoader(getClass().getResource("CheckInFormUI.fxml"));
        	Parent root=(Parent)loader.load();	
    		CheckInFormController formController=loader.getController();
    		formController.showForm(tfGuestName.getText(),tfGuestNRC.getText(),tfNoOfGuest.getText(), tfGuestPhNo.getText(), cobRoomType.getValue(), 
    				tfGuestRoomNo.getText(),tfGuestRoomPrice.getText(),dpGuestCheckInDate.getValue().toString(),dpGuestCheckOutDate.getValue().toString(),tfStayDays.getText());
    		Stage registerStage=new Stage();
    		registerStage.setTitle("CheckIn Confirmation Form Section");
    		registerStage.setScene(new Scene(root));
    		registerStage.setResizable(false);
    		registerStage.show();
    	}
    
    
    }
    
    public void clearAllField() {
    	tfGuestName.clear();
    	tfGuestNRC.clear();
    	tfNoOfGuest.clear();
    	tfGuestPhNo.clear();
    	cobRoomType.setValue("Type");
    	tfGuestRoomNo.clear();
    	tfGuestRoomPrice.clear();
    	
    	dpGuestCheckInDate.setValue(LocalDate.now());
    	dpGuestCheckOutDate.setValue(LocalDate.now());
    	
    	tfStayDays.clear();
    }
    
    private boolean validateFields() {
    	if(tfGuestName.getText().isEmpty()|tfGuestNRC.getText().isEmpty()|tfNoOfGuest.getText().isEmpty()|tfGuestPhNo.getText().isEmpty()|cobRoomType.getValue().isEmpty()|tfGuestRoomNo.getText().isEmpty()|tfGuestRoomPrice.getText().isEmpty()|tfStayDays.getText().isEmpty()) {
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
		ObservableList<String> roomTypeList = FXCollections.observableArrayList(
				   "Single","Double","Triple","Family"		
						);
		cobRoomType.setItems(roomTypeList);
	}
	
}
