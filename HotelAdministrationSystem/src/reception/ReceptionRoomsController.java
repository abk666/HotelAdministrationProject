package reception;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import bean.Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import utility.RoomUtils;

public class ReceptionRoomsController implements Initializable{

    @FXML
    private TableView<Room> roomTable;

    @FXML
    private TableColumn<Room,Integer> roomId;

    @FXML
    private TableColumn<Room,String> roomType;

    @FXML
    private TableColumn<Room,Integer> roomNo;

    @FXML
    private TableColumn<Room,Double> roomPrice;



    @FXML
    private TableColumn<Room,String> roomStatus;

    @FXML
    private JFXComboBox<String> cobColumn;

    @FXML
    private JFXTextField tfSearch;
    
    @FXML
    private Label lblToday;
    
    private final RoomUtils roomDataUtils=new RoomUtils();

    @FXML
    void processSearch(ActionEvent event) {
    	String column = cobColumn.getValue();
    	String query = tfSearch.getText().trim();
    	
    	showTable("select * from room where "+column+" = '"+query+"';");
    }
    
    public void showTable(String sql) {
    	
    	try {
			roomTable.setItems(roomDataUtils.getAllRoom(sql));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    		
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			cobColumn.setItems(roomDataUtils.getAllColumnLabel());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		roomId.setCellValueFactory(new PropertyValueFactory<>("roomId"));
		roomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
		roomNo.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
		roomPrice.setCellValueFactory(new PropertyValueFactory<>("roomPrice"));
		
		roomStatus.setCellValueFactory(new PropertyValueFactory<>("roomStatus"));
		
		showTable("select * from room;");
		
		String date=(LocalDate.now()).toString();
		lblToday.setText(date);
	}

}
