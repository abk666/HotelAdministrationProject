package dining_room;

import java.io.IOException;



import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import bean.DiningRoomFood;
import bean.FoodOrder;
import bean.FoodOrderHolder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import utility.DiningRoomFoodDataUtils;
import utility.FoodOrderDataUtils;
import utility.MyNotification;

public class FoodOrderListEditController implements Initializable{

	@FXML
	private TableView<DiningRoomFood> foodMenuTable;
	 
	@FXML
	private TableColumn<DiningRoomFood,String> foodMenuName;

	@FXML
	private TableColumn<DiningRoomFood,Double> foodMenuPrice;
	      
    @FXML
    private JFXComboBox<String> cobFoodMenuCategory;

    @FXML
    private JFXTextField tfFoodOrderQty;

    @FXML
    private JFXTextField tfGuestRoomNo;

    @FXML
    private JFXDatePicker dpFoodOrderDate;

    @FXML
    private JFXTextField tfFoodOrderName;

    @FXML
    private JFXTextField tfFoodOrderPrice;
    
    private Integer id;

    private final DiningRoomFoodDataUtils diningRoomFoodDataUtils = new DiningRoomFoodDataUtils();
    
    private final FoodOrderDataUtils foodOrderDataUtils = new FoodOrderDataUtils();
    
    private final MyNotification myNoti = new MyNotification();
    
    @FXML
    void processCancel(ActionEvent event) {

    	tfFoodOrderName.clear();
    	tfFoodOrderPrice.clear();
    	tfFoodOrderQty.clear();
    	dpFoodOrderDate.setValue(LocalDate.now());
    	tfGuestRoomNo.clear();
    }

    @FXML
    void processUpdate(ActionEvent event) throws SQLException, IOException {
    	
    	if(tfFoodOrderName.getText() == null || tfFoodOrderName.getText().length() == 0 || 
    			tfFoodOrderPrice.getText() == null || tfFoodOrderPrice.getText().length() == 0 ||
    			tfFoodOrderQty.getText() == null || tfFoodOrderQty.getText().length() == 0 ||
    			tfGuestRoomNo.getText() == null || tfGuestRoomNo.getText().length() == 0 ||
    			dpFoodOrderDate.getValue() == null) {
    		
    		
    		myNoti.getNotification(NotificationType.WARNING,"Required User Input","Please Fill All Fields!",AnimationType.FADE,3000.0);
    		
    		//System.out.println("Empty");
    		
    	}else {
    		
    		String foodOrderName = tfFoodOrderName.getText().trim();
        	
        	Integer qty = Integer.parseInt(tfFoodOrderQty.getText());
        	Double price = Double.parseDouble(tfFoodOrderPrice.getText());
        	
        	Double totalPrice = price * qty;
        	
        	String foodOrderDate = dpFoodOrderDate.getValue().toString();
        	Integer guestRoomNo = Integer.parseInt(tfGuestRoomNo.getText());
       
    		FoodOrder foodOrder =  new FoodOrder(this.id,foodOrderName, price, foodOrderDate, qty, guestRoomNo,totalPrice);
    		Integer isUpdateOk = foodOrderDataUtils.updateFoodOrder(foodOrder);
    	    	
    		if(isUpdateOk > 0) {

    			myNoti.getNotification(NotificationType.SUCCESS,"Updated Success","Successfully Update Ordered from "+ guestRoomNo+" to DB",AnimationType.FADE,3000.0);
    			
    			FoodOrderDataUtils foodOrderDataUtils = new FoodOrderDataUtils();
        		FoodOrder foodOrderUpdate = foodOrderDataUtils.getAllFoodOrder("select * from hoteldb.foodorder where foodorderId = '"+id+"';").get(0);
        		FoodOrderHolder foodOrderHolder=FoodOrderHolder.getFoodorderInstance();
        		foodOrderHolder.setFoodOrder(foodOrderUpdate);
        		
        		Stage primaryStage=(Stage) ((Node)event.getSource()).getScene().getWindow();
            	primaryStage.close();
            	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("FoodOrderListUI.fxml"));
        		Scene scene = new Scene(root);
        		primaryStage.setTitle("FoodOrderListUI");
        		primaryStage.setScene(scene);
        		primaryStage.show();
    			
    	    }
    	    else {
    	    	
    	    	myNoti.getNotification(NotificationType.ERROR,"Updated Fail","Fail Update Ordered From  "+guestRoomNo+" to DB",AnimationType.FADE,3000.0);
    	    }
    	}
    	
	 }
  
    
    @FXML
    void processOrderView(MouseEvent event) throws IOException {

    	 Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	 primaryStage.hide();
    	 AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("FoodOrderListUI.fxml"));
    	 Scene scene = new Scene(root);
    	 primaryStage.setTitle("Food Order List");
    	 primaryStage.setScene(scene);
    	 primaryStage.show();
    }
    
    @FXML
    void processOrder(MouseEvent event) {
    	
    	if(event.getClickCount() ==2) {
    	
    		DiningRoomFood diningRoomFood = foodMenuTable.getSelectionModel().getSelectedItem(); 
    		tfFoodOrderName.setText(diningRoomFood.getFoodMenuName());
    		tfFoodOrderPrice.setText(diningRoomFood.getFoodMenuPrice().toString());
    		
    	}

    }
    public void showTable(String sql) {
    	
    	
    	try {
			foodMenuTable.setItems(diningRoomFoodDataUtils.getAllDiningRoomFood(sql));
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
    }
    
    @FXML
    void processBack(MouseEvent event) throws IOException {
    	
    	Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	primaryStage.setResizable(false);
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("FoodMenuListUI.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Dining Room Main Section");
		primaryStage.setScene(scene);
		primaryStage.show();
    	

    }
    
    @FXML
    void processChoose(ActionEvent event) {

    	String cobCategory = cobFoodMenuCategory.getValue();
    	showTable("select * from hoteldb.foodmenu where foodMenuCategory = '"+cobCategory+"';");
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		ObservableList<String> foodMenuCategoryList = FXCollections.observableArrayList();
				try {
					ObservableList<DiningRoomFood>foodList=diningRoomFoodDataUtils.getAllDiningRoomFood("select * from foodmenu group by foodMenuCategory;");
				    for (DiningRoomFood food:foodList) {
				    	foodMenuCategoryList.add(food.getFoodMenuCategory());
				    }
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		cobFoodMenuCategory.setItems(foodMenuCategoryList);
		
		dpFoodOrderDate.setValue(LocalDate.now());
		foodMenuName.setCellValueFactory(new PropertyValueFactory<>("foodMenuName"));
		foodMenuPrice.setCellValueFactory(new PropertyValueFactory<>("foodMenuPrice"));
		
		FoodOrderHolder holder = FoodOrderHolder.getFoodorderInstance();
		FoodOrder food = holder.getFoodOrder();
		
		tfFoodOrderName.setText(food.getFoodOrderName());
		tfFoodOrderPrice.setText(food.getFoodOrderPrice().toString());
		tfFoodOrderQty.setText(food.getFoodOrderQty().toString());
		tfGuestRoomNo.setText(food.getGuestRoomNo().toString());
		dpFoodOrderDate.setValue(LocalDate.parse(food.getFoodOrderDate()));
		this.id = food.getFoodOrderId();
		
	}

}
