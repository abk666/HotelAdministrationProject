package reception;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import bean.Guest;
import bean.GuestHolder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import utility.CheckOutUtils;
import utility.MyNotification;


public class CheckOutController implements Initializable{
	 @FXML
	    private JFXRadioButton searchByRoomNo;

	    @FXML
	    private ToggleGroup searchMethod;

	    @FXML
	    private JFXRadioButton searchByName;

	    @FXML
	    private JFXComboBox<Integer> cobRoomNo;

	    @FXML
	    private JFXTextField tfName;

	    @FXML
	    private TableView<Guest> guestTable;

	    @FXML
	    private TableColumn<Guest, Integer> guestId;

	    @FXML
	    private TableColumn<Guest, String> guestName;

	    @FXML
	    private TableColumn<Guest, String> guestNRC;

	    @FXML
	    private TableColumn<Guest, Integer> noOfGuests;

	    @FXML
	    private TableColumn<Guest, String> guestPhNo;

	    @FXML
	    private TableColumn<Guest, Integer> guestRoomNo;

	    @FXML
	    private TableColumn<Guest, String> guestRoomType;

	    @FXML
	    private TableColumn<Guest, Double> guestRoomPrice;

	

	    @FXML
	    private TableColumn<Guest, String> guestCheckInDate;

	    @FXML
	    private TableColumn<Guest, String> guestCheckOutDate;
	    
	    @FXML
	    private TableColumn<Guest, String> guestStatus;

	    @FXML
	    private TableColumn<Guest, Integer> numberOfDays;
	    private CheckOutUtils checkOutDataUtils=new CheckOutUtils();
	    private MyNotification noti=new MyNotification();

	    @FXML
	    void processCheckOut(ActionEvent event) throws IOException {
	    	Guest guest=guestTable.getSelectionModel().getSelectedItem();
	    	GuestHolder holder=GuestHolder.getGuestInstance();
	    	holder.setGuest(guest);
	    	
	    	Stage primaryStage=new Stage();
	    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("CheckOutFormUI.fxml"));
			Scene scene = new Scene(root);

			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
	    }

	    @FXML
	    void processSearch(ActionEvent event) throws IOException {
          if(searchByRoomNo.isSelected()) {
        	  if(cobRoomNo.getValue()!=null) {
        		  showTable("select * from guest where guestRoomNo = '"+cobRoomNo.getValue()+"'");
        	  }else {
        		  noti.getNotification(NotificationType.ERROR, "Fail!", "Fail to Search,Fields must not be null.", AnimationType.SLIDE, 2000.0);
        	  }
        	  
        	  
          }
          else {
        	  if(!tfName.getText().isEmpty()) {
        		  showTable("select * from guest where guestName = '"+tfName.getText().trim()+"'");
        	  }else {
        		  noti.getNotification(NotificationType.ERROR, "Fail!", "Fail to Search,Fields must not be null.", AnimationType.SLIDE, 2000.0);
        	  }
        	 
          }
	    }
	    @FXML
	    void processRadioButton(ActionEvent event) {
           if(searchByName.isSelected()) {
        	   tfName.setDisable(false);
        	   cobRoomNo.setDisable(true);
           }else {
        	   cobRoomNo.setDisable(false);
        	   tfName.setDisable(true);
        	   }
           cobRoomNo.setValue(null);
           tfName.clear();;
	    }
	    

	    @FXML
	    void processRefresh(ActionEvent event) {
	    	searchMethod.selectToggle(searchByRoomNo);
	    	tfName.clear();
	    	cobRoomNo.setValue(null);
	    	showTable("select * from guest where guestStatus='CheckIn'");
	    }
	    

	    @FXML
	    void processView(ActionEvent event) throws IOException {
	    	Guest guest=guestTable.getSelectionModel().getSelectedItem();
	    	GuestHolder holder=GuestHolder.getGuestInstance();
	    	holder.setGuest(guest);
	    	Stage primaryStage=new Stage();
	    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("GuestInfoUI.fxml"));
			Scene scene = new Scene(root);

			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
	    }
	   public void showTable(String sql) {
		   try {
			guestTable.setItems(checkOutDataUtils.getAllGuests(sql));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }
	      
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {

            
			ObservableList<Integer>guestRoomList=FXCollections.observableArrayList();
			try {
				ObservableList<Guest>guestList=checkOutDataUtils.getAllGuests("select * from guest where guestStatus = 'CheckIn'");
				for(Guest guest:guestList) {
					guestRoomList.add(guest.getGuestRoomNo());
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cobRoomNo.setItems(guestRoomList);
			tfName.setDisable(true);
	        guestId.setCellValueFactory(new PropertyValueFactory<>("guestId"));
	        guestName.setCellValueFactory(new PropertyValueFactory<>("guestName"));
	        guestNRC.setCellValueFactory(new PropertyValueFactory<>("guestNRC"));
	        noOfGuests.setCellValueFactory(new PropertyValueFactory<>("noOfGuests"));
	        guestPhNo.setCellValueFactory(new PropertyValueFactory<>("guestPhNo"));
	        guestRoomNo.setCellValueFactory(new PropertyValueFactory<>("guestRoomNo"));
	        guestRoomType.setCellValueFactory(new PropertyValueFactory<>("guestRoomType"));
	        guestRoomPrice.setCellValueFactory(new PropertyValueFactory<>("guestRoomPrice"));
	        guestCheckInDate.setCellValueFactory(new PropertyValueFactory<>("guestCheckInDate"));
	        guestCheckOutDate.setCellValueFactory(new PropertyValueFactory<>("guestCheckOutDate"));
	        numberOfDays.setCellValueFactory(new PropertyValueFactory<>("numberOfDays"));
	        guestStatus.setCellValueFactory(new PropertyValueFactory<>("guestStatus"));
			showTable("select * from guest where guestStatus='CheckIn'");
		}
}
