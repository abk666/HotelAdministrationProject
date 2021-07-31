package admin;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import bean.RefrigeratorFood;
import bean.RefrigeratorFoodHolder;
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
import utility.MyAlert;
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
    
    private MyAlert alert = new MyAlert();

    @FXML
    void processBack(MouseEvent event) throws IOException {

    	Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	primaryStage.setResizable(false);
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("RefrigeratorFoodUI.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("RefrigeratorFoodUI");
		primaryStage.setScene(scene);
		primaryStage.show();
    }

    @FXML
    void processDelete(ActionEvent event) throws SQLException {

    	ObservableList<RefrigeratorFood> refrigeratorFoodList = refrigeratorTable.getSelectionModel().getSelectedItems();
    	
    	Optional<ButtonType> result = alert.getConfirmationAlert("Confirmation Dialog","Are You Sure to Delete Selected Item?","This action cannot be undone.");

		if (result.get() == ButtonType.OK) {
			
			Boolean flag = false;
		
			for (RefrigeratorFood refrigeratorFood : refrigeratorFoodList) {
			
				Boolean isDeleteOk =refrigeratorFoodDataUtils.deleteRefrigeratorFood(refrigeratorFood.getItemId());
			   	
			   	 if (!isDeleteOk) {
			   		 
			   		 	flag = true;
			   		 	
						File imageFile = new File("src/img/RefrigeratorFood/"+refrigeratorFood.getItemImage());
						
						if (imageFile.exists()) {
							imageFile.delete();
						}
				
						
				}
			   	 
			}
			
			if(flag == true) {
				
				myNoti.getNotification(NotificationType.SUCCESS,"Deleted!","Successfully Deleted!",AnimationType.SLIDE,3000.0);
				
			}else {
				
				myNoti.getNotification(NotificationType.ERROR,"Delete Fail","Fail to Delete!",AnimationType.SLIDE,3000.0);
				
			}
			
		}
		
		showTable("select * from hoteldb.refrigeratoritem;");
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
		primaryStage.setTitle("RefrigeratorFoodUI");
		primaryStage.setScene(scene);
		primaryStage.show();
    }

    @FXML
    void processRefresh(MouseEvent event) {

    	showTable("select * from hoteldb.refrigeratoritem;");
    	
    	cobSearch.setValue("Food");
    	tfSearch.clear();
    }

    @FXML
    void processSearch(MouseEvent event) {

    	String columnName = cobSearch.getValue();
    	String dataInput = tfSearch.getText().trim();
    	
    	showTable("select * from hoteldb.refrigeratoritem where "+columnName+" = '"+dataInput+"';");
    }
    
    @FXML
    void processView(MouseEvent event) throws IOException {

    	RefrigeratorFood refrigeratorFood = refrigeratorTable.getSelectionModel().getSelectedItem();
    	
    	RefrigeratorFoodHolder holder = RefrigeratorFoodHolder.getRefrigeratorfoodInstance();
    	holder.setRefrigeratorFood(refrigeratorFood);
    	
    	Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	primaryStage.setResizable(false);
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("RefrigeratorFoodProfileUI.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("RefrigeratorFoodProfileUI");
		primaryStage.setScene(scene);
		primaryStage.show();
    	
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
		
		refrigeratorTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		try {
			cobSearch.setItems(refrigeratorFoodDataUtils.getAllColumnLabel());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
