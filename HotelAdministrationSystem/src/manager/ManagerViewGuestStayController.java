package manager;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import bean.Guest;
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
import utility.ManagerViewGuestDataUtils;

public class ManagerViewGuestStayController implements Initializable {

    @FXML
    private TableView<Guest> guestTable;

    @FXML
    private TableColumn<Guest, Integer> guestId;

    @FXML
    private TableColumn<Guest, String> guestName;

    @FXML
    private TableColumn<Guest, String> guestNRC;

    @FXML
    private TableColumn<Guest, String> guestPhNo;

    @FXML
    private TableColumn<Guest, Integer> noOfGuests;

    @FXML
    private TableColumn<Guest, Integer> guestRoomNo;

    @FXML
    private TableColumn<Guest, String> guestRoomType;

    @FXML
    private TableColumn<Guest, String> guestCheckInDate;

    @FXML
    private JFXComboBox<String> cobSearch;

    @FXML
    private JFXTextField tfSearch;
    
    private final ManagerViewGuestDataUtils guestDataUtils = new ManagerViewGuestDataUtils();

    @FXML
    void processBack(MouseEvent event) throws IOException {

    	Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	primaryStage.setResizable(false);
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("ManagerMainUI.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("ManagerMainUI");
		primaryStage.setScene(scene);
		primaryStage.show();
		
    }
    

    @FXML
    void processNotStay(MouseEvent event) throws IOException {
    	
    	Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	primaryStage.setResizable(false);
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("ManagerViewGuestNotStayUI.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("ManagerViewGuestNotStayUI");
		primaryStage.setScene(scene);
		primaryStage.show();
    	
    }

    @FXML
    void processRefresh(MouseEvent event) {

    	showTable("select * from hoteldb.guest where guestStatus = 'CheckIn';");
    	
    	cobSearch.setValue("Guest");
    	tfSearch.clear();
    }
    
    public void showTable(String sql) {
    	
    	try {
    		guestTable.setItems(guestDataUtils.getAllGuest(sql));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
 

    @FXML
    void processSearch(MouseEvent event) {

    	String columnName = cobSearch.getValue();
    	String dataInput = tfSearch.getText().trim();
    	
    	showTable("select * from hoteldb.guest where "+columnName+" = '"+dataInput+"' and guestStatus = 'CheckIn';");
    	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		guestId.setCellValueFactory(new PropertyValueFactory<>("guestId"));
		guestName.setCellValueFactory(new PropertyValueFactory<>("guestName"));
		guestNRC.setCellValueFactory(new PropertyValueFactory<>("guestNRC"));
		guestPhNo.setCellValueFactory(new PropertyValueFactory<>("guestPhNo"));
		noOfGuests.setCellValueFactory(new PropertyValueFactory<>("noOfGuests"));
		guestRoomNo.setCellValueFactory(new PropertyValueFactory<>("guestRoomNo"));
		guestRoomType.setCellValueFactory(new PropertyValueFactory<>("guestRoomType"));
		guestCheckInDate.setCellValueFactory(new PropertyValueFactory<>("guestCheckInDate"));
		
		showTable("select * from hoteldb.guest where guestStatus = 'CheckIn';");
		
		//guestTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		try {
			cobSearch.setItems(guestDataUtils.getGuestColumnLabel());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
