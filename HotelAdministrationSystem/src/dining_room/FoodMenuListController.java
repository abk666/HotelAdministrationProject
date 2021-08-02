package dining_room;

import java.io.IOException;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import bean.DiningRoomFood;
import bean.DiningRoomFoodHolder;
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
import utility.MyNotification;

public class FoodMenuListController implements Initializable{

    @FXML
    private TableView<DiningRoomFood> foodMenuTable;

    @FXML
    private TableColumn<DiningRoomFood,String> foodMenuName;

    @FXML
    private TableColumn<DiningRoomFood,String> foodMenuCategory;

    @FXML
    private TableColumn<DiningRoomFood,Double> foodMenuPrice;

    @FXML
    private JFXComboBox<String> cobFoodMenuSearch;

    @FXML
    private JFXTextField tfSearch;

    @FXML
    private JFXComboBox<String> cobFoodMenuCategory;
    
    private final DiningRoomFoodDataUtils diningRoomFoodDataUtils = new DiningRoomFoodDataUtils();
    
    private final MyNotification myNoti = new MyNotification();

    @FXML
    void processBack(MouseEvent event) throws IOException {

    	Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	primaryStage.setResizable(false);
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../main/LogInUI.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Hotel Administration Login");

		primaryStage.setScene(scene);
		primaryStage.show();
    }

    @FXML
    void processRefresh(ActionEvent event) {

    	showTable("select * from hoteldb.foodmenu;");
    	
    	cobFoodMenuCategory.setValue("Category");
    	cobFoodMenuSearch.setValue("Food");
    	tfSearch.clear();
    }

    @FXML
    void processSearch(ActionEvent event) {

    	String column = cobFoodMenuSearch.getValue();
    	String query = tfSearch.getText().trim();
    	
    	String cobCategory = cobFoodMenuCategory.getValue();
    	
    	//showTable("select * from hoteldb.foodmenu where "+column+" = '"+query+"';");
    	showTable("select * from hoteldb.foodmenu where foodMenuCategory = '"+cobCategory+"' and "+column+" = '"+query+"';");
    	
    }

    @FXML
    void processChoose(ActionEvent event) {
    	
    	String cobCategory = cobFoodMenuCategory.getValue();
    	showTable("select * from hoteldb.foodmenu where foodMenuCategory = '"+cobCategory+"';");

    }
    
    @FXML
    void processOrderView(MouseEvent event) throws IOException {

    	Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	primaryStage.setResizable(false);
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../dining_room/FoodOrderUI.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("FoodOrderUI");
		primaryStage.setScene(scene);
		primaryStage.show();
		
    }
    
    @FXML
    void processView(ActionEvent event) throws IOException {
  
    	DiningRoomFood diningRoomFood = foodMenuTable.getSelectionModel().getSelectedItem();
    	

    	if(diningRoomFood == null) {
    		
    		myNoti.getNotification(NotificationType.WARNING,"Required Data!","Please Select The Data You Want Firstly.",AnimationType.SLIDE,3000.0);
    		
    	}else {
    		
    		DiningRoomFoodHolder holder = DiningRoomFoodHolder.getDiningRoomFoodInstance();
        	holder.setDiningRoomFood(diningRoomFood);
        	
        	Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        	primaryStage.setResizable(false);
        	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("FoodMenuProfileUI.fxml"));
    		Scene scene = new Scene(root);
    		primaryStage.setTitle("Food Menu Detail");
    		primaryStage.setScene(scene);
    		primaryStage.show();
    		
    	}
    	
    }

    
    public void showTable(String sql) {
    	
    	try {
			foodMenuTable.setItems(diningRoomFoodDataUtils.getAllDiningRoomFood(sql));
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		ObservableList<String> foodMenuCategoryList = FXCollections.observableArrayList();
//				"Rice","Curry","Burger","Pizza","Soup","Fried","Salad","Cake","Ice Cream","Fruit","Juice","Cold Drinks","Hot Drinks","Beer","Wine","Purified Water","Hotpot","Hotdog"
//				);		
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
	
		foodMenuName.setCellValueFactory(new PropertyValueFactory<>("foodMenuName"));
		foodMenuCategory.setCellValueFactory(new PropertyValueFactory<>("foodMenuCategory"));
		foodMenuPrice.setCellValueFactory(new PropertyValueFactory<>("foodMenuPrice"));
		
		showTable("select * from hoteldb.foodmenu;");

		

		try {
			cobFoodMenuSearch.setItems(diningRoomFoodDataUtils.getFoodMenuColumnLabel());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
