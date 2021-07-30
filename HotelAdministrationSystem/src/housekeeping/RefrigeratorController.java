package housekeeping;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import bean.InRoomCost;
import bean.RefrigeratorFood;
import bean.Room;
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
import utility.InRoomCostDataUtils;
import utility.RefrigeratorFoodDataUtils;
import utility.RefrigeratorUtils;
import utility.RoomUtils;

public class RefrigeratorController implements Initializable{

    @FXML
    private JFXTextField tfroomNumber;

    @FXML
    private JFXTextField tfitemName;

    @FXML
    private JFXComboBox<String> cobitemCategory;

    @FXML
    private JFXTextField tfitemPrice;

    @FXML
    private JFXTextField tfitemQty;
    

    
    @FXML
    private TableView<RefrigeratorFood> refrigeratorTable;

    @FXML
    private TableColumn<RefrigeratorFood, Integer> itemId;

    @FXML
    private TableColumn<RefrigeratorFood, String> itemRoomNo;

    @FXML
    private TableColumn<RefrigeratorFood, String> itemName;

    @FXML
    private TableColumn<RefrigeratorFood, String> itemCategory;

    @FXML
    private TableColumn<RefrigeratorFood, Double> itemPrice;

    @FXML
    private TableColumn<RefrigeratorFood, String> itemQty;

  

    @FXML
    private JFXComboBox<String> cobSearch;

    @FXML
    private JFXTextField tfSearch;
    @FXML
    private JFXComboBox<Integer> cobRoomNumber;
    
    private final RefrigeratorUtils refrigeratorUtils = new RefrigeratorUtils();
    private final RefrigeratorFoodDataUtils refriFoodUtils=new RefrigeratorFoodDataUtils();
    private final InRoomCostDataUtils inRoomCostUtils=new InRoomCostDataUtils();
    private final RoomUtils roomUtils=new RoomUtils();
    


    @FXML
    void processBack(MouseEvent event) throws IOException {
    	Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	primaryStage.setResizable(true);
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("WaitingUI.fxml"));
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("HouseKeeping Section");
		primaryStage.setScene(scene);
		primaryStage.show();
    }

    @FXML
    void processCancel(ActionEvent event) {

    	clearAllField();
    }

   

   

    @FXML
    void processNew(MouseEvent event) {
    if(event.getClickCount()==2) {
    	RefrigeratorFood food=refrigeratorTable.getSelectionModel().getSelectedItem();
    	tfitemName.setText(food.getItemName());
    	tfitemPrice.setText(food.getItemPrice().toString());
    	cobitemCategory.setValue(food.getItemCategory());
    	  }
    }

    @FXML
    void processRefresh(ActionEvent event) {
    	showTable("select * from refrigeratoritem");
    }

    @FXML
    void processSave(ActionEvent event) throws SQLException {
    	String inRoomItemName=tfitemName.getText().trim();
    	String inRoomItemCategory=cobitemCategory.getValue();
    	Double inRoomItemPrice=Double.parseDouble(tfitemPrice.getText().trim());
    	Integer inRoomItemQuantity=Integer.parseInt(tfitemQty.getText().trim());
    	Integer guestRoomNo=cobRoomNumber.getValue();
    	Double inRoomTotalCost=inRoomItemPrice*inRoomItemQuantity;
    	InRoomCost inRoomCost=new InRoomCost(inRoomItemName, inRoomItemCategory, inRoomItemPrice, inRoomItemQuantity, guestRoomNo, inRoomTotalCost);
    	boolean isSaveOk=inRoomCostUtils.saveInRoomCost(inRoomCost);
    	if(!isSaveOk) {
    		System.out.println("Successfully saved");
    	}
    	
    	
    }

    @FXML
    void processSearch(ActionEvent event) {

    	String column = cobSearch.getValue();
    	String query = tfSearch.getText().trim();
    	
    	showTable("select * from refrigeratoritem where "+column+" = '"+query+"';");
    	
    }
    
    public void enableAllField() {
    	
    	tfroomNumber.setDisable(false);
    	tfitemName.setDisable(false);
    	cobitemCategory.setDisable(false);
    	tfitemPrice.setDisable(false);
    	tfitemQty.setDisable(false);
    }
    
    public void disableAllField() {
    	
    	tfroomNumber.setDisable(true);
    	tfitemName.setDisable(true);
    	cobitemCategory.setDisable(true);
    	tfitemPrice.setDisable(true);
    	tfitemQty.setDisable(true);
    }
    
    public void clearAllField() {
    	tfroomNumber.clear();
    	tfitemName.clear();
    	tfitemPrice.clear();
    	tfitemQty.clear();
    	cobitemCategory.setValue("");

    }
    
    public void showTable(String sql) {
    	
    	try {
			refrigeratorTable.setItems(refriFoodUtils.getAllRefrigeratorFood(sql));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String>foodCategory=FXCollections.observableArrayList();
		ObservableList<Integer>roomNo=FXCollections.observableArrayList();
		ObservableList<RefrigeratorFood> foodList;
		try {
			foodList = refriFoodUtils.getAllRefrigeratorFood("select * from refrigeratoritem group by itemCategory;");
			for(RefrigeratorFood food:foodList) {
				foodCategory.add(food.getItemCategory());
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			ObservableList<Room>roomList=roomUtils.getAllRoom("select * from room where roomStatus = 'CheckIn'");
			for(Room room:roomList) {
				roomNo.add(room.getRoomNumber());
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cobRoomNumber.setItems(roomNo);
		cobitemCategory.setItems(foodCategory);
		
		try {
			cobSearch.setItems(refriFoodUtils.getAllColumnLabel());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
		itemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
		itemRoomNo.setCellValueFactory(new PropertyValueFactory<>("itemRoomNo"));
		itemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
		itemCategory.setCellValueFactory(new PropertyValueFactory<>("itemCategory"));
		itemPrice.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));
		itemQty.setCellValueFactory(new PropertyValueFactory<>("itemQty"));

		
		showTable("select * from refrigeratoritem;");
	
	}

}
