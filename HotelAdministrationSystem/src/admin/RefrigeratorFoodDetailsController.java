package admin;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import bean.RefrigeratorFood;
import bean.RefrigeratorFoodHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import utility.MyNotification;
import utility.RefrigeratorFoodDataUtils;

public class RefrigeratorFoodDetailsController implements Initializable{

    @FXML
    private TableView<RefrigeratorFood> refrigeratorTable;

    @FXML
    private TableColumn<RefrigeratorFood, Integer> itemId;

    @FXML
    private TableColumn<RefrigeratorFood, String> itemName;

    @FXML
    private TableColumn<RefrigeratorFood, String> itemCategory;

    @FXML
    private TableColumn<RefrigeratorFood, Double> itemPrice;

    @FXML
    private TableColumn<RefrigeratorFood, Integer> itemQty;

    @FXML
    private TableColumn<RefrigeratorFood, String> itemImage;

    @FXML
    private JFXComboBox<String> cobSearch;

    @FXML
    private JFXTextField tfSearch;
    
   
    private final RefrigeratorFoodDataUtils refrigeratorFoodDataUtils = new RefrigeratorFoodDataUtils();
    
    private final MyNotification myNoti = new MyNotification();

    @FXML
    void processBack(MouseEvent event) throws IOException {

    	Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	primaryStage.setResizable(false);
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("RefrigeratorFoodUI.fxml"));
		Scene scene = new Scene(root);
		Image icon=new Image(getClass().getResourceAsStream("../img/hotel.png"));
		primaryStage.getIcons().add(icon);
		primaryStage.setTitle("RefrigeratorFoodUI");
		primaryStage.setScene(scene);
		primaryStage.show();
    }

    @FXML
    void processDelete(ActionEvent event) throws SQLException {

    	RefrigeratorFood refrigeratorFood = refrigeratorTable.getSelectionModel().getSelectedItem();
    	
    	Boolean isDeleteOk =refrigeratorFoodDataUtils.deleteRefrigeratorFood(refrigeratorFood.getItemId());
	   	
	   	 if (!isDeleteOk) {
				myNoti.getNotification(NotificationType.SUCCESS,"Deleted!","Deleted "+refrigeratorFood.getItemName(),AnimationType.SLIDE,3000.0);
				showTable("select * from hoteldb.refrigeratoritem;");
				
				File imageFile = new File("src/img/RefrigeratorFood/"+refrigeratorFood.getItemImage());
				
				if (imageFile.exists()) {
					imageFile.delete();
				}
				
				
			}else {
				myNoti.getNotification(NotificationType.ERROR,"Delete Fail","Fail to delete "+refrigeratorFood.getItemName(),AnimationType.SLIDE,3000.0);
				//System.out.println("Fail To Delete "+diningRoomFood.getFoodMenuName());
			}
    }

    @FXML
    void processEdit(ActionEvent event) throws IOException {
    	
    	RefrigeratorFood refrigeratorFood = refrigeratorTable.getSelectionModel().getSelectedItem();
		
		RefrigeratorFoodHolder holder = RefrigeratorFoodHolder.getRefrigeratorfoodInstance();
   	 
		holder.setRefrigeratorFood(refrigeratorFood);
		
		Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	primaryStage.setResizable(false);
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("RefrigeratorFoodEditUI.fxml"));
		Scene scene = new Scene(root);
		Image icon=new Image(getClass().getResourceAsStream("../img/hotel.png"));
		primaryStage.getIcons().add(icon);
		primaryStage.setTitle("RefrigeratorFoodEditUI");
		primaryStage.setScene(scene);
		primaryStage.show();

    }

    @FXML
    void processNew(ActionEvent event) throws IOException {

    	Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	primaryStage.setResizable(false);
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("RefrigeratorFoodUI.fxml"));
		Scene scene = new Scene(root);
		Image icon=new Image(getClass().getResourceAsStream("../img/hotel.png"));
		primaryStage.getIcons().add(icon);
		primaryStage.setTitle("RefrigeratorFoodUI");
		primaryStage.setScene(scene);
		primaryStage.show();
    }

    @FXML
    void processRefresh(ActionEvent event) {

    	showTable("select * from hoteldb.refrigeratoritem;");
    	
    	cobSearch.setValue("Food");
    	tfSearch.clear();
    }

    @FXML
    void processSearch(ActionEvent event) {

    	String columnName = cobSearch.getValue();
    	String dataInput = tfSearch.getText().trim();
    	
    	showTable("select * from hoteldb.refrigeratoritem where "+columnName+" = '"+dataInput+"';");
    }
    
    @FXML
    void processView(ActionEvent event) {

    	
    }
 
    public void showTable(String sql) {
    	
    	try {
    		refrigeratorTable.setItems(refrigeratorFoodDataUtils.getAllRefrigeratorFood(sql));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		itemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
		itemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
		itemCategory.setCellValueFactory(new PropertyValueFactory<>("itemCategory"));
		itemPrice.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));
		itemQty.setCellValueFactory(new PropertyValueFactory<>("itemQty"));
		itemImage.setCellValueFactory(new PropertyValueFactory<>("itemImage"));
		
		showTable("select * from hoteldb.refrigeratoritem;");
		
		try {
			cobSearch.setItems(refrigeratorFoodDataUtils.getAllColumnLabel());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
