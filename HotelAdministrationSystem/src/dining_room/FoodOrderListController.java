package dining_room;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import bean.FoodOrder;
import bean.FoodOrderHolder;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import utility.FoodOrderDataUtils;
import utility.MyAlert;
import utility.MyNotification;

public class FoodOrderListController implements Initializable{

    @FXML
    private TableView<FoodOrder> foodOrderTable;

    @FXML
    private TableColumn<FoodOrder,Integer> foodOrderId;

    @FXML
    private TableColumn<FoodOrder,String> foodOrderName;

    @FXML
    private TableColumn<FoodOrder,Double> foodOrderPrice;

    @FXML
    private TableColumn<FoodOrder,Integer> foodOrderQty;

    @FXML
    private TableColumn<FoodOrder,Double> foodOrderTotalPrice;

    @FXML
    private TableColumn<FoodOrder,String> guestRoomNo;

    @FXML
    private TableColumn<FoodOrder,String> foodOrderDate;

    @FXML
    private JFXComboBox<String> cobSearch;

    @FXML
    private JFXTextField tfSearch;
    
    private final FoodOrderDataUtils foodOrderDataUtils = new FoodOrderDataUtils();
    
    private final MyNotification myNoti = new MyNotification();
    
    private MyAlert alert = new MyAlert();

    @FXML
    void processBack(MouseEvent event) throws IOException {
    	
    	Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	primaryStage.setResizable(false);
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("FoodOrderUI.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("FoodOrderUI");
		primaryStage.setScene(scene);
		primaryStage.show();
    	
    }

    @FXML
    void processDelete(ActionEvent event) throws SQLException {

    	ObservableList<FoodOrder> foodOrderList = foodOrderTable.getSelectionModel().getSelectedItems();
    	
    	
    	Optional<ButtonType> result = alert.getConfirmationAlert("Confirmation Dialog","Are You Sure to Delete Selected Food Order?","This action cannot be undone.");

		if (result.get() == ButtonType.OK) {
		
			for (FoodOrder foodOrder : foodOrderList) {
			
				Boolean isDeleteOk =foodOrderDataUtils.deleteFoodOrder(foodOrder.getFoodOrderId());
			   	
			   	 if (!isDeleteOk) {
			   		 
			   		myNoti.getNotification(NotificationType.SUCCESS,"Deleted!","Successfully Deleted!",AnimationType.SLIDE,3000.0);
					
						
				}else {
					
					myNoti.getNotification(NotificationType.ERROR,"Delete Fail","Fail to Delete!",AnimationType.SLIDE,3000.0);
					
				}	   	 
			   	
			}
    	
		}
		
		showTable("select * from hoteldb.foodorder;");
    }

    @FXML
    void processEdit(ActionEvent event) throws IOException {

    	FoodOrder foodOrder = foodOrderTable.getSelectionModel().getSelectedItem();
    	
    	FoodOrderHolder holder = FoodOrderHolder.getFoodorderInstance();
		holder.setFoodOrder(foodOrder);
    	
		Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	primaryStage.setResizable(false);
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("FoodOrderListEditUI.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("FoodOrderUI");
		primaryStage.setScene(scene);
		primaryStage.show();
		
    	
    }

    @FXML
    void processNew(ActionEvent event) throws IOException {

    	Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	primaryStage.setResizable(false);
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("FoodOrderUI.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("FoodOrderUI");
		primaryStage.setScene(scene);
		primaryStage.show();
    }

    @FXML
    void processRefresh(ActionEvent event) {

    	cobSearch.setValue("Column");
    	tfSearch.clear();
    	showTable("select * from hoteldb.foodorder;");
    }

    @FXML
    void processSearch(ActionEvent event) {

    	String column = cobSearch.getValue();
    	String query = tfSearch.getText().trim();
    	
    	showTable("select * from hoteldb.foodorder where "+column+" = '"+query+"';");
    }
    
    public void showTable(String sql) {
    	
    	try {
			foodOrderTable.setItems(foodOrderDataUtils.getAllFoodOrder(sql));
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		foodOrderId.setCellValueFactory(new PropertyValueFactory<>("foodOrderId"));
		guestRoomNo.setCellValueFactory(new PropertyValueFactory<>("guestRoomNo"));
		foodOrderName.setCellValueFactory(new PropertyValueFactory<>("foodOrderName"));
		foodOrderPrice.setCellValueFactory(new PropertyValueFactory<>("foodOrderPrice"));
		foodOrderQty.setCellValueFactory(new PropertyValueFactory<>("foodOrderQty"));
		foodOrderTotalPrice.setCellValueFactory(new PropertyValueFactory<>("foodOrderTotalPrice"));
		foodOrderDate.setCellValueFactory(new PropertyValueFactory<>("foodOrderDate"));
		
		showTable("select * from hoteldb.foodorder;");
		
		foodOrderTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		try {
			cobSearch.setItems(foodOrderDataUtils.getAllColumnLabel());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
