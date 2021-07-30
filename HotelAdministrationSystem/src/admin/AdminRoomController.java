package admin;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import bean.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import utility.RoomUtils;

public class AdminRoomController implements Initializable{

    @FXML
    private JFXComboBox<String> cobSearch;

    @FXML
    private JFXTextField tfSeach;

    @FXML
    private JFXTextField tfroomNumber;

    @FXML
    private JFXComboBox<String> cobroomType;

    @FXML
    private JFXTextField tfroomPrice;

    @FXML
    private JFXComboBox<String> cobroomStatus;

    @FXML
    private TableView<Room> adminRoomTable;

    @FXML
    private TableColumn<Room, Integer> roomId;

    @FXML
    private TableColumn<Room, Integer> roomNumber;

    @FXML
    private TableColumn<Room, String> roomType;

    @FXML
    private TableColumn<Room, Double> roomPrice;

    @FXML
    private TableColumn<Room, String> roomStatus;
    
    private final RoomUtils roomUtils = new RoomUtils();
    
    private Boolean isNewButtonClick = false ;
    
    private Integer id;

    @FXML
    void processBack(MouseEvent event) {

    }
    
    @FXML
    void processClear(ActionEvent event) {

    	clearAllField();
    	
    }

    @FXML
    void processDelete(ActionEvent event) throws SQLException {

    	Room room = adminRoomTable.getSelectionModel().getSelectedItem();
    	
    	Boolean isDeleteOk = roomUtils.deleteRoom(room.getRoomId());
    	
    	if (!isDeleteOk) {
    		System.out.println("Deleted"+room.getRoomNumber());
    		showTable("select * from room;");
    	}else {
    		System.out.println("Fail to Delete"+room.getRoomNumber());
    	}
    }
    

    @FXML
    void processNew(ActionEvent event) {

    	isNewButtonClick = true ;
    	enableAllField();
    }

    @FXML
    void processEdit(ActionEvent event) {

    	isNewButtonClick = false;
    	
    	enableAllField();
    	
    	Room room = adminRoomTable.getSelectionModel().getSelectedItem();
    	
    	tfroomNumber.setText(room.getRoomNumber().toString());
    	cobroomType.setValue(room.getRoomType());
    	tfroomPrice.setText(room.getRoomPrice().toString());
    	cobroomStatus.setValue(room.getRoomStatus());
    	
    	this.id = room.getRoomId();
    }

    @FXML
    void processRefresh(ActionEvent event) {

    	showTable("select * from room;");
    	cobSearch.setValue("");
    	tfSeach.setText("");
    }
    
    public void disableAllField() {
    	tfroomNumber.setDisable(true);
    	tfroomPrice.setDisable(true);
    	cobroomType.setDisable(true);
    	cobroomStatus.setDisable(true);
    }
    
    public void enableAllField() {
    	tfroomNumber.setDisable(false);
    	tfroomPrice.setDisable(false);
    	cobroomType.setDisable(false);
    	cobroomStatus.setDisable(false);
    }
    
    public void clearAllField() {
    	tfroomNumber.clear();
    	tfroomPrice.clear();
    	cobroomType.setValue("");
    	cobroomStatus.setValue("");
    }

    @FXML
    void processSave(ActionEvent event) throws SQLException {

      	String roomType = cobroomType.getValue();
    	Integer roomNumber = Integer.parseInt(tfroomNumber.getText().trim());
    	Double roomPrice = Double.parseDouble(tfroomPrice.getText().trim());
    	String roomStatus = cobroomStatus.getValue();
    	
    	
    	if (isNewButtonClick) {
        	Room room = new Room(roomType, roomNumber, roomPrice, roomStatus);
        	
        	Boolean isSaveOk =roomUtils.saveRoom(room);
        	
        	if (!isSaveOk) {
    			System.out.println("Successfully Saved "+roomNumber+" to DB");
    			showTable("select * from room;");
    			
    			clearAllField();
    		}else {
    			System.out.println("Fail to Save "+roomNumber+" to DB");
    		}
		}else {
			Room roomUpdated = new Room(this.id, roomType, roomNumber, roomPrice, roomStatus);
			
			Integer rowUpdated = roomUtils.updateRoom(roomUpdated);
			
			if (rowUpdated > 0) {
				showTable("select * from room;");
				clearAllField();
			}else {
				System.out.println("Fail to Updated "+roomNumber+" to DB");
			}
			
		}


    }

    @FXML
    void processSearch(ActionEvent event) {

    	String column = cobSearch.getValue();
    	String query = tfSeach.getText().trim();
    	
    	showTable("select * from room where "+column+" = '"+query+"';");
    	
    }
    
    public void showTable(String sql) {
        
    	try {
    		adminRoomTable.setItems(roomUtils.getAllRoom(sql));
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		ObservableList<String> roomTypeList = FXCollections.observableArrayList(
				"SingleRoom","DoubleRoom","TripleRoom","FamilyRoom"
				);
		cobroomType.setItems(roomTypeList);
		
		ObservableList<String> roomStatusList = FXCollections.observableArrayList(
				"Available","Waiting","Booking","Unavailable"
				);
		
		cobroomStatus.setItems(roomStatusList);
		
		
		roomId.setCellValueFactory(new PropertyValueFactory<>("roomId"));
		roomNumber.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
		roomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
		roomPrice.setCellValueFactory(new PropertyValueFactory<>("roomPrice"));
		roomStatus.setCellValueFactory(new PropertyValueFactory<>("roomStatus"));

		showTable("select * from room;");
		
		disableAllField();

		try {
			cobSearch.setItems(roomUtils.getAllColumnLabel());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
