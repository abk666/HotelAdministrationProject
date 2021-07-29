package reception;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;

import bean.FoodOrder;
import bean.Guest;
import bean.GuestHolder;
import bean.InRoomCost;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utility.FoodOrderDataUtils;
import utility.InRoomCostDataUtils;

public class GuestInfoController implements Initializable{

    @FXML
    private Label lblRoomNo;

    @FXML
    private JFXDatePicker dpCheckIn;

    @FXML
    private JFXDatePicker dpCheckOut;

    @FXML
    private Label lblGuestId;

    @FXML
    private Label lblGuestName;

    @FXML
    private Label lblGuestNRC;

    @FXML
    private Label lblGuestPhNo;

    @FXML
    private Label lblNumberOfGuest;

    @FXML
    private Label lblRoomType;

    @FXML
    private Label lblRoomPrice;

    @FXML
    private Label lblDiningRoom;

    @FXML
    private Label lblInRoomCost;

    @FXML
    private Label lblTotalDays;

    @FXML
    private TextField tfTotalPrice;
    

    @FXML
    private JFXButton backButton;
    private final FoodOrderDataUtils foodOrderDataUtils=new FoodOrderDataUtils();
    private final InRoomCostDataUtils inRoomCostDataUtils=new InRoomCostDataUtils();
    
    @FXML
    void processBack(ActionEvent event) throws IOException {
    	Stage stage= (Stage)backButton.getScene().getWindow();
    	   stage.close();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		GuestHolder holder=GuestHolder.getGuestInstance();
		Guest guest=holder.getGuest();
		Double totalDiningRoomCost=0.0;
		Double totalInRoomCost=0.0;
		
		
		try {
			ObservableList<FoodOrder>foodOrderList = foodOrderDataUtils.getAllFoodOrder("select * from foodorder where guestRoomNo = '"+guest.getGuestRoomNo()+"';");
			for(FoodOrder food:foodOrderList) {
				totalDiningRoomCost+=food.getFoodOrderTotalPrice();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		try {
			ObservableList<InRoomCost>inRoomCostList=inRoomCostDataUtils.getAllInRoomCost("select * from inroomcost where guestRoomNo = '"+guest.getGuestRoomNo()+"';");
		    for(InRoomCost inRoomCost:inRoomCostList) {
		    	totalInRoomCost+=inRoomCost.getInRoomTotalCost();
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    
			dpCheckIn.setValue(LocalDate.parse(guest.getGuestCheckInDate()));
			dpCheckOut.setValue(LocalDate.parse(guest.getGuestCheckOutDate()));
			long dayDifference=ChronoUnit.DAYS.between(LocalDate.parse(guest.getGuestCheckInDate()),LocalDate.now());
			Double totalPrice=(guest.getGuestRoomPrice()*dayDifference)+totalDiningRoomCost+totalInRoomCost;
			
			tfTotalPrice.setText(totalPrice.toString());
			lblGuestId.setText(guest.getGuestId().toString());
			lblGuestName.setText(guest.getGuestName());
			lblGuestNRC.setText(guest.getGuestNRC());
			lblGuestPhNo.setText(guest.getGuestPhNo());
			lblNumberOfGuest.setText(guest.getNoOfGuests().toString());
			lblRoomNo.setText(guest.getGuestRoomNo().toString()+"'s Infos");
			lblRoomPrice.setText(guest.getGuestRoomPrice().toString());
			lblRoomType.setText(guest.getGuestRoomType());
			lblDiningRoom.setText(totalDiningRoomCost.toString());
			lblInRoomCost.setText(totalInRoomCost.toString());
			lblTotalDays.setText(String.valueOf(dayDifference));


	}

}
