package housekeeping;

import java.io.IOException;
import java.net.URL;

import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import bean.Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utility.MyNotification;
import utility.RoomUtils;;

public class WaitingRoomController implements Initializable {

    @FXML
    private TableView<Room> WaitingRoomTable;

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
    
    private RoomUtils roomUtils=new RoomUtils();

    private final MyNotification noti=new MyNotification();
    
    @FXML
    void processBack(MouseEvent event) throws IOException {
    	Optional<ButtonType> result=noti.getConfirmationAlert("Comfimation Dialog", "Comfirmation", "Do you really want to Exit?");
		if(result.get()==ButtonType.OK) {
			Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
	    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("WaitingUI.fxml"));
			Scene scene = new Scene(root);
			Image icon=new Image(getClass().getResourceAsStream("../img/hotel.png"));
			primaryStage.getIcons().add(icon);
            primaryStage.setTitle("Accountant Main Section");
			primaryStage.setScene(scene);
			primaryStage.show();
		}
    }
    
    @FXML
    void processCleaning(ActionEvent event) throws SQLException {
    	Room waitingRoom = WaitingRoomTable.getSelectionModel().getSelectedItem();
    	
    	String roomStatus="Waiting";
    	Boolean isCleaningOk = roomUtils.UpdateStatus(waitingRoom.getRoomId(),roomStatus);
    	
    	if (!isCleaningOk) {
			System.out.println("Cleaning"+waitingRoom.getRoomNumber());
			showTable("select * from hoteldb.room where roomStatus = 'CheckOut' or roomStatus = 'Waiting';");
		}else {
			System.out.println("Undone"+waitingRoom.getRoomNumber());
		}

    }

    @FXML
    void processDone(ActionEvent event) throws SQLException {
    	Room waitingRoom = WaitingRoomTable.getSelectionModel().getSelectedItem();
    	String roomStatus="Available";
    	Boolean isCleaningOk = roomUtils.UpdateStatus(waitingRoom.getRoomId(),roomStatus);
    	
    	if (!isCleaningOk) {
			System.out.println("Done"+waitingRoom.getRoomNumber());
			showTable("select * from hoteldb.room where roomStatus = 'CheckOut' or roomStatus = 'Waiting';");
		}else {
			System.out.println("Undone"+waitingRoom.getRoomNumber());
		}
    }

    public void showTable(String sql) {
    	
    	try {
			WaitingRoomTable.setItems(roomUtils.getAllRoom(sql));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		roomId.setCellValueFactory(new PropertyValueFactory<>("roomId"));
		roomNumber.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
		roomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
		roomPrice.setCellValueFactory(new PropertyValueFactory<>("roomPrice"));
		roomStatus.setCellValueFactory(new PropertyValueFactory<>("roomStatus"));
		
		showTable("select * from hoteldb.room where roomStatus = 'CheckOut' or roomStatus = 'Waiting';");
	}

}
