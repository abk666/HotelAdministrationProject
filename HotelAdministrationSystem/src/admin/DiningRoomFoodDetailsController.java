package admin;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import bean.DiningRoomFood;
import bean.DiningRoomFoodHolder;
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
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import utility.DiningRoomFoodDataUtils;
import utility.MyAlert;
import utility.MyNotification;

public class DiningRoomFoodDetailsController implements Initializable {

    @FXML
    private TableView<DiningRoomFood> foodMenuTable;

    @FXML
    private TableColumn<DiningRoomFood, Integer> foodMenuId;

    @FXML
    private TableColumn<DiningRoomFood, String> foodMenuName;

    @FXML
    private TableColumn<DiningRoomFood, String> foodMenuCategory;

    @FXML
    private TableColumn<DiningRoomFood, Double> foodMenuPrice;

    @FXML
    private TableColumn<DiningRoomFood, String> foodMenuImage;

    @FXML
    private JFXComboBox<String> cobSearch;

    @FXML
    private JFXTextField tfSearch;
    
    private final DiningRoomFoodDataUtils diningRoomFoodDataUtils = new DiningRoomFoodDataUtils();
    
    private final MyNotification myNoti = new MyNotification();
    
    private MyAlert alert = new MyAlert();
    

    @FXML
    void processBack(MouseEvent event) throws IOException {

    	Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	primaryStage.setResizable(false);
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("DiningRoomFoodUI.fxml"));
		Scene scene = new Scene(root);
		Image icon=new Image(getClass().getResourceAsStream("../img/hotel.png"));
		primaryStage.getIcons().add(icon);
		primaryStage.setTitle("DiningRoomFoodUI");
		primaryStage.setScene(scene);
		primaryStage.show();
		
    }

    @FXML
    void processView(MouseEvent event) throws IOException {

    	DiningRoomFood diningRoomFood = foodMenuTable.getSelectionModel().getSelectedItem();
    	
    	DiningRoomFoodHolder holder = DiningRoomFoodHolder.getDiningRoomFoodInstance();
    	holder.setDiningRoomFood(diningRoomFood);
    	
    	Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	primaryStage.setResizable(false);
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("DiningRoomFoodProfileUI.fxml"));
		Scene scene = new Scene(root);
		Image icon=new Image(getClass().getResourceAsStream("../img/hotel.png"));
		primaryStage.getIcons().add(icon);
		primaryStage.setTitle("Food Menu Detail");
		primaryStage.setScene(scene);
		primaryStage.show();
    }
    @FXML
    void processDelete(ActionEvent event) throws SQLException {
    	
    	ObservableList<DiningRoomFood> diningRoomFoodList = foodMenuTable.getSelectionModel().getSelectedItems();
    	
    	Optional<ButtonType> result = alert.getConfirmationAlert("Confirmation Dialog","Are You Sure to Delete Selected Food Menu?","This action cannot be undone.");

		if (result.get() == ButtonType.OK) {
		
			for (DiningRoomFood diningRoomFood : diningRoomFoodList) {
			
				Boolean isDeleteOk =diningRoomFoodDataUtils.deleteDiningRoomFood(diningRoomFood.getFoodMenuId());
			   	
			   	 if (!isDeleteOk) {
					myNoti.getNotification(NotificationType.SUCCESS,"Deleted!","Successfully Deleted!",AnimationType.SLIDE,3000.0);
				
					File imageFile = new File("src/img/DiningRoomFood/"+diningRoomFood.getFoodMenuImage());
					
					if (imageFile.exists()) {
						imageFile.delete();
					}
						
						
				}else {
					myNoti.getNotification(NotificationType.ERROR,"Delete Fail","Fail to Delete!",AnimationType.SLIDE,3000.0);
						//System.out.println("Fail To Delete "+diningRoomFood.getFoodMenuName());
				}
			   	 
			}
			
		}
		
		showTable("select * from hoteldb.foodmenu;");
   
    }

    @FXML
    void processEdit(ActionEvent event) throws IOException {
		
		DiningRoomFood diningRoomFood = foodMenuTable.getSelectionModel().getSelectedItem();
		
		DiningRoomFoodHolder holder = DiningRoomFoodHolder.getDiningRoomFoodInstance();
   	 
		holder.setDiningRoomFood(diningRoomFood);
		
		Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	primaryStage.setResizable(false);
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("DiningRoomFoodEditUI.fxml"));
		Scene scene = new Scene(root);
		Image icon=new Image(getClass().getResourceAsStream("../img/hotel.png"));
		primaryStage.getIcons().add(icon);
		primaryStage.setTitle("DiningRoomFoodUI");
		primaryStage.setScene(scene);
		primaryStage.show();
    
    }

    @FXML
    void processNew(ActionEvent event) throws IOException {

    	Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	primaryStage.setResizable(false);
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("DiningRoomFoodUI.fxml"));
		Scene scene = new Scene(root);
		Image icon=new Image(getClass().getResourceAsStream("../img/hotel.png"));
		primaryStage.getIcons().add(icon);
		primaryStage.setTitle("DiningRoomFoodUI");
		primaryStage.setScene(scene);
		primaryStage.show();
		
    }

    @FXML
    void processRefresh(MouseEvent event) {

    	showTable("select * from hoteldb.foodmenu");
    	
    	cobSearch.setValue("Food");
    	tfSearch.clear();
    	
    }

    @FXML
    void processSearch(MouseEvent event) {
    	
    	String columnName = cobSearch.getValue();
    	String dataInput = tfSearch.getText().trim();
    	
    	showTable("select * from hoteldb.foodmenu where "+columnName+" = '"+dataInput+"';");

    }
    
     
    public void showTable(String sql) {
    	
    	try {
			foodMenuTable.setItems(diningRoomFoodDataUtils.getAllDiningRoomFood(sql));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//connection between bean & table column
		foodMenuId.setCellValueFactory(new PropertyValueFactory<>("foodMenuId"));
		foodMenuName.setCellValueFactory(new PropertyValueFactory<>("foodMenuName"));
		foodMenuCategory.setCellValueFactory(new PropertyValueFactory<>("foodMenuCategory"));
		foodMenuPrice.setCellValueFactory(new PropertyValueFactory<>("foodMenuPrice"));
		foodMenuImage.setCellValueFactory(new PropertyValueFactory<>("foodMenuImage"));
		
		showTable("select * from hoteldb.foodmenu;");
		
		foodMenuTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		try {
			cobSearch.setItems(diningRoomFoodDataUtils.getAllColumnLabel());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
