package reception;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;


import bean.Guest;
import bean.GuestHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utility.GuestDataUtils;

public class ReceptionGuestsController implements Initializable {

    @FXML
    private JFXComboBox<String> cobColumn;

    @FXML
    private JFXTextField tfSearch;

    @FXML
    private TableView<Guest> guestTable;

    @FXML
    private TableColumn<Guest,Integer> guestId;

    @FXML
    private TableColumn<Guest,Double> guestName;

    @FXML
    private TableColumn<Guest,String> guestNRC;

    @FXML
    private TableColumn<Guest,Integer> noOfGuests;

    @FXML
    private TableColumn<Guest,String> guestPhNo;

    @FXML
    private TableColumn<Guest,String> guestRoomType;

    @FXML
    private TableColumn<Guest,Integer> guestRoomNo;

    @FXML
    private TableColumn<Guest,Double> guestRoomPrice;

    @FXML
    private TableColumn<Guest,Double> guestFoodOrderPrice;

    @FXML
    private TableColumn<Guest,Double> guestInRoomCost;

    @FXML
    private TableColumn<Guest,String> guestCheckInDate;

    @FXML
    private TableColumn<Guest,String> guestCheckOutDate;

    @FXML
    private TableColumn<Guest,Integer> numberOfDays;
    
    private final GuestDataUtils guestDataUtils=new GuestDataUtils();

    @FXML
    void processDelete(ActionEvent event) throws SQLException {
    	Guest guest= guestTable.getSelectionModel().getSelectedItem();
     	 
     	 Boolean isDeleteOk =guestDataUtils.deleteGuest(guest.getGuestId());
     	
     	 if (!isDeleteOk) {
     		
  			System.out.println("Deleted "+guest.getGuestName());
  			showTable("select * from guest;");
  			

  			
  		}else {
  			
  			System.out.println("Fail To Delete "+guest.getGuestName());
  		}
    }

    @FXML
    void processRefresh(ActionEvent event) {
    	showTable("select * from guest;");
    }

    @FXML
    void processSearch(ActionEvent event) {
    	String column = cobColumn.getValue();
    	String query = tfSearch.getText().trim();
    	
    	showTable("select * from guest where "+column+" = '"+query+"';");
    }

    @FXML
    void processView(ActionEvent event) throws IOException {
    	Guest guest= guestTable.getSelectionModel().getSelectedItem();
      	 
    	GuestHolder holder = GuestHolder.getGuestInstance();
    	 
    	holder.setGuest(guest);
    	 
    	Stage primaryStage = new Stage();
     	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("GuestDetailsUI.fxml"));
 		Scene scene = new Scene(root);
 		
 		primaryStage.setTitle("Guest Details");
 		primaryStage.setScene(scene);
 		primaryStage.show();
    }
    
public void showTable(String sql) {
    	
    	try {
			guestTable.setItems(guestDataUtils.getAllGuest(sql));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    		
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			cobColumn.setItems(guestDataUtils.getAllColumnLabel());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		guestId.setCellValueFactory(new PropertyValueFactory<>("guestId"));
		guestName.setCellValueFactory(new PropertyValueFactory<>("guestName"));
		guestNRC.setCellValueFactory(new PropertyValueFactory<>("guestNRC"));
		noOfGuests.setCellValueFactory(new PropertyValueFactory<>("noOfGuests"));
		guestPhNo.setCellValueFactory(new PropertyValueFactory<>("guestPhNo"));
		guestRoomType.setCellValueFactory(new PropertyValueFactory<>("guestRoomType"));
		guestRoomNo.setCellValueFactory(new PropertyValueFactory<>("guestRoomNo"));
		guestRoomPrice.setCellValueFactory(new PropertyValueFactory<>("guestRoomPrice"));
		guestFoodOrderPrice.setCellValueFactory(new PropertyValueFactory<>("foodOrderPrice"));
		guestInRoomCost.setCellValueFactory(new PropertyValueFactory<>("guestInRoomCost"));
		guestCheckInDate.setCellValueFactory(new PropertyValueFactory<>("guestCheckInDate"));
		guestCheckOutDate.setCellValueFactory(new PropertyValueFactory<>("guestCheckOutDate"));
		numberOfDays.setCellValueFactory(new PropertyValueFactory<>("numberOfDays"));
		
		showTable("select * from guest;");
	}

}
