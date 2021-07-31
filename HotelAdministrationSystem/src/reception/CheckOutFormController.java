package reception;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.ResourceBundle;


import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;


import bean.BillSlipHolder;
import bean.CheckOut;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import utility.CheckOutUtils;
import utility.FoodOrderDataUtils;
import utility.InRoomCostDataUtils;
import utility.MyNotification;

public class CheckOutFormController implements Initializable{
	 @FXML
	    private JFXListView<String> lvItem;

	    @FXML
	    private JFXListView<Double> lvCosts;

	    @FXML
	    private Label lblTotalPrice;

	    @FXML
	    private JFXListView<Integer> lvQty;

	    @FXML
	    private JFXTextField tfItemPrice;

	    @FXML
	    private TextField tfRoomPrice;

	    @FXML
	    private TextField tfDays;
	    
	    @FXML
	    private TextField tfTotalRoomPrice;
	    
	    @FXML
	    private TextField tfRoomNo;
	    
	    private final FoodOrderDataUtils foodOrderDataUtils=new FoodOrderDataUtils();
	    private final InRoomCostDataUtils inRoomCostDataUtils=new InRoomCostDataUtils();
        private Double diningRoomPrice=0.0;
        private Double inRoomCost=0.0;
        private Double totalPrice=0.0;
        private final MyNotification noti=new MyNotification();
        private final CheckOutUtils checkOutUtils=new CheckOutUtils();
        
	    @FXML
	    void processCheckOut(ActionEvent event) throws IOException, SQLException {
	    	Optional<ButtonType>result=noti.getConfirmationAlert("Comfirmation Dialog", "Comfirmation!!", "Do You really want to Check Out?");
	    	if(result.get()==ButtonType.OK) {
	    		 BillSlipHolder.setRoomNo(tfRoomNo.getText());
	             BillSlipHolder.setRoomPrice(tfTotalRoomPrice.getText());
	             BillSlipHolder.setDiningRoomPrice(diningRoomPrice.toString());
	             BillSlipHolder.setInRoomCost(inRoomCost.toString());
	             BillSlipHolder.setTotalPrice(lblTotalPrice.getText());
	             
	             //Data for checkOut Table
	            GuestHolder holder=GuestHolder.getGuestInstance();
	 			Guest guest=holder.getGuest();
	 			String checkOutGuestName=guest.getGuestName();
	 			String checkOutGuestNRC=guest.getGuestNRC();
	 			String checkOutGuestPhNo=guest.getGuestPhNo();
	 			Integer roomNo=guest.getGuestRoomNo();
	 			String checkOutDate=LocalDate.now().toString();
	 			
	 			
	 			
	             CheckOut checkOut=new CheckOut(checkOutGuestName, checkOutGuestNRC, checkOutGuestPhNo, roomNo, checkOutDate, this.totalPrice);
	             Boolean isSaveCheckOutOk=checkOutUtils.SaveCheckOutResult(checkOut);
	             if(!isSaveCheckOutOk) {
	            	 //delete All Guests Usage
	            	 ObservableList<FoodOrder>foodOrderList=foodOrderDataUtils.getAllFoodOrder("select * from foodorder where guestRoomNo = '"+guest.getGuestRoomNo()+"';");
	            	 for(FoodOrder food:foodOrderList) {
	            		checkOutUtils.DeleteFoodOrderList(food.getFoodOrderId());
	            	 }
	            	 ObservableList<InRoomCost>inRoomCostList=inRoomCostDataUtils.getAllInRoomCost("select * from inroomcost where guestRoomNo = '"+guest.getGuestRoomNo()+"';");
	            	 for(InRoomCost roomCost:inRoomCostList) {
	            		 checkOutUtils.DeleteInRoomCostList(roomCost.getInRoomItemId());
	            	 }
	            	 //Changing guest status
	            	 checkOutUtils.UpdateGuestStatus(guest.getGuestId());
	            	 checkOutUtils.UpdateRoomStatus(guest.getGuestRoomNo());
	            	 noti.getNotification(NotificationType.SUCCESS, "Success!", "Successfully Checked out!", AnimationType.SLIDE, 2000.0);
	             }
	         	Stage primaryStage=(Stage)((Button)event.getSource()).getScene().getWindow();
	        	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("BillSlipUI.fxml"));
	    		Scene scene = new Scene(root);
	    		Image icon=new Image(getClass().getResourceAsStream("../img/hotel.png"));
				primaryStage.getIcons().add(icon);
	    		primaryStage.setResizable(false);
	    		primaryStage.setScene(scene);
	    		primaryStage.show();
	    		
	    	}
        
	    }

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			
			
			GuestHolder holder=GuestHolder.getGuestInstance();
			Guest guest=holder.getGuest();
			tfRoomNo.setText(guest.getGuestRoomNo().toString());
			Double itemTotalPrice=0.0;
			Double totalPrice=guest.getGuestRoomPrice();
			tfRoomPrice.setText(guest.getGuestRoomPrice().toString());
			LocalDate todayDate=LocalDate.now();
			LocalDate checkInDate=LocalDate.parse(guest.getGuestCheckInDate());
			long daysDifference=ChronoUnit.DAYS.between(checkInDate, todayDate);
			if(daysDifference>=1) {
				tfDays.setText(String.valueOf(daysDifference));
				double totalRoomPrice=guest.getGuestRoomPrice()*daysDifference;
				tfTotalRoomPrice.setText(String.valueOf(totalRoomPrice));
				totalPrice*=daysDifference;
			}else {
				tfDays.setText("1");
				tfTotalRoomPrice.setText(guest.getGuestRoomPrice().toString());
			}
			
			//adding Dining Room Items in List view
			ObservableList<String>foodName=FXCollections.observableArrayList();
			ObservableList<Integer>foodQty=FXCollections.observableArrayList();
			ObservableList<Double> foodPrice=FXCollections.observableArrayList();
			try {
				ObservableList<FoodOrder>foodOrderList=foodOrderDataUtils.getAllFoodOrder("select * from foodorder where guestRoomNo = '"+guest.getGuestRoomNo()+"';");
				for(FoodOrder food:foodOrderList) {
					foodName.add(food.getFoodOrderName());
					foodQty.add(food.getFoodOrderQty());
					foodPrice.add(food.getFoodOrderTotalPrice());
					itemTotalPrice+=food.getFoodOrderTotalPrice();
					totalPrice+=food.getFoodOrderTotalPrice();
					this.diningRoomPrice+=food.getFoodOrderTotalPrice();
				}
				lvItem.setItems(foodName);
				lvQty.setItems(foodQty);
				lvCosts.setItems(foodPrice);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				ObservableList<InRoomCost>inRoomCostList=inRoomCostDataUtils.getAllInRoomCost("select * from inroomcost where guestRoomNo = '"+guest.getGuestRoomNo()+"';");
				for(InRoomCost inRoomCost:inRoomCostList) {
					foodName.add(inRoomCost.getInRoomItemName());
					foodQty.add(inRoomCost.getInRoomItemQuantity());
					foodPrice.add(inRoomCost.getInRoomTotalCost());
					itemTotalPrice+=inRoomCost.getInRoomTotalCost();
					totalPrice+=inRoomCost.getInRoomTotalCost();
					this.inRoomCost+=inRoomCost.getInRoomTotalCost();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      this.totalPrice=totalPrice;
			tfItemPrice.setText(itemTotalPrice.toString());
			lblTotalPrice.setText(totalPrice.toString());
			
			
			
		}
}
