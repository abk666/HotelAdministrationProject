package manager;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import bean.CheckOut;
import bean.Guest;
import bean.GuestHolder;
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
import utility.ManagerViewGuestCheckOutDataUtils;
import utility.ManagerViewGuestDataUtils;

public class ManagerViewGuestNotStayController implements Initializable {

    @FXML
    private TableView<CheckOut> guestCheckOutTable;

    @FXML
    private TableColumn<CheckOut, Integer> checkOutId;

    @FXML
    private TableColumn<CheckOut, String> checkOutGuestName;

    @FXML
    private TableColumn<CheckOut, String> checkOutGuestNRC;

    @FXML
    private TableColumn<CheckOut, String> checkOutGuestPhNo;

    @FXML
    private TableColumn<CheckOut, Integer> roomNo;

    @FXML
    private TableColumn<CheckOut, String> checkOutDate;

    @FXML
    private TableColumn<CheckOut, Double> totalPrice;

    @FXML
    private JFXComboBox<String> cobSearch;

    @FXML
    private JFXTextField tfSearch;
    
    private final ManagerViewGuestCheckOutDataUtils checkOutGuestDataUtils = new ManagerViewGuestCheckOutDataUtils();
    
    private final ManagerViewGuestDataUtils guestDataUtils = new ManagerViewGuestDataUtils();

    @FXML
    void processBack(MouseEvent event) throws IOException {

    	Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	primaryStage.setResizable(false);
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("ManagerMainUI.fxml"));
		Scene scene = new Scene(root);
		Image icon=new Image(getClass().getResourceAsStream("../img/hotel.png"));
		primaryStage.getIcons().add(icon);
		primaryStage.setTitle("ManagerMainUI");
		primaryStage.setScene(scene);
		primaryStage.show();
    	
    }
    
    @FXML
    void processGuestDetails(MouseEvent event) throws IOException, SQLException {
    	
    	CheckOut guestCheckOut = guestCheckOutTable.getSelectionModel().getSelectedItem();
    	
    	String nrc =  guestCheckOut.getCheckOutGuestNRC();
    	String checkOutDate = guestCheckOut.getCheckOutDate();
    	Double totalPrice = guestCheckOut.getTotalPrice();
    	
    	Guest guest = guestDataUtils.getManagerViewGuest("select * from hoteldb.guest where guestNRC = '"+nrc+"';");
    	String checkInDate = guest.getGuestCheckInDate();
    	
    	long dayDifference=ChronoUnit.DAYS.between(LocalDate.parse(checkInDate),LocalDate.parse(checkOutDate));
    	
    	guest.setGuestCheckOutDate(checkOutDate);	
    	guest.setNumberOfDays((int)dayDifference);
    	guest.setTotalPrice(totalPrice);
	
    	GuestHolder holder = GuestHolder.getGuestInstance();
    	holder.setGuest(guest);
    	
    	Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	primaryStage.setResizable(false);
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("ManagerViewNotStayGuestDetailsUI.fxml"));
		Scene scene = new Scene(root);
		Image icon=new Image(getClass().getResourceAsStream("../img/hotel.png"));
		primaryStage.getIcons().add(icon);
		primaryStage.setTitle("ManagerViewNotStayGuestDetailsUI");
		primaryStage.setScene(scene);
		primaryStage.show();
		
    }

    @FXML
    void processRefresh(MouseEvent event) {

    	showTable("select * from hoteldb.checkout;");
    	
    	cobSearch.setValue("Guest");
    	tfSearch.clear();
    	
    }
    
    public void showTable(String sql) {
    	
    	try {
    		guestCheckOutTable.setItems(checkOutGuestDataUtils.getAllGuestCheckOut(sql));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


    @FXML
    void processSearch(MouseEvent event) {

    	String columnName = cobSearch.getValue();
    	String dataInput = tfSearch.getText().trim();
    	
    	showTable("select * from hoteldb.checkout where "+columnName+" = '"+dataInput+"';");
    	
    }

    @FXML
    void processStay(MouseEvent event) throws IOException {

    	Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	primaryStage.setResizable(false);
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("ManagerViewGuestStayUI.fxml"));
		Scene scene = new Scene(root);
		Image icon=new Image(getClass().getResourceAsStream("../img/hotel.png"));
		primaryStage.getIcons().add(icon);
		primaryStage.setTitle("ManagerViewGuestStayUI");
		primaryStage.setScene(scene);
		primaryStage.show();
    	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		checkOutId.setCellValueFactory(new PropertyValueFactory<>("checkOutId"));
		checkOutGuestName.setCellValueFactory(new PropertyValueFactory<>("checkOutGuestName"));
		checkOutGuestNRC.setCellValueFactory(new PropertyValueFactory<>("checkOutGuestNRC"));
		checkOutGuestPhNo.setCellValueFactory(new PropertyValueFactory<>("checkOutGuestPhNo"));
		roomNo.setCellValueFactory(new PropertyValueFactory<>("roomNo"));
		checkOutDate.setCellValueFactory(new PropertyValueFactory<>("checkOutDate"));
		totalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
		
		showTable("select * from hoteldb.checkout;");
		
		try {
			cobSearch.setItems(checkOutGuestDataUtils.getGuestCheckoutColumnLabel());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
