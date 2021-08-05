package reception;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import bean.Booking;
import bean.BookingHolder;
import bean.BookingStatusHolder;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import utility.BookingDataUtils;
import utility.MyNotification;

public class BookingTableController implements Initializable {
	
	@FXML
	private JFXComboBox<String> cobColumn;
	
	@FXML
	private JFXTextField tfSearch;

    @FXML
    private TableView<Booking> bookingTable;

    @FXML
    private TableColumn<Booking,Integer> bookingId;

    @FXML
    private TableColumn<Booking,String> guestName;

    @FXML
    private TableColumn<Booking,String> guestPhNo;

    @FXML
    private TableColumn<Booking,String> roomType;

    @FXML
    private TableColumn<Booking,Integer> roomNo;

    @FXML
    private TableColumn<Booking,Double> roomPrice;

    @FXML
    private TableColumn<Booking,Integer> noOfGuest;

    @FXML
    private TableColumn<Booking,String> bookedDate;

    @FXML
    private TableColumn<Booking,String> checkInDate;

    @FXML
    private TableColumn<Booking,Integer> numberOfDays;

    @FXML
    private TableColumn<Booking,String> checkOutDate;

    private final BookingDataUtils bookingDataUtils=new BookingDataUtils();
    private final MyNotification noti=new MyNotification();
    
    
    @FXML
    void processCheckIn(ActionEvent event) throws IOException {
    	if(bookingTable.getSelectionModel().getSelectedIndex()<0) {
    	noti.getNotification(NotificationType.WARNING, "Warning", "You must first select an item!", AnimationType.SLIDE, 2000.0);	
    	}else {
    		Booking booking= bookingTable.getSelectionModel().getSelectedItem();
       	 
        	BookingHolder holder = BookingHolder.getBookingInstance();
        	 
        	holder.setBooking(booking);
        	 BookingStatusHolder.setBookingId(booking.getBookingId());
        	 BookingStatusHolder.setButtonStatus("booking");
        	Stage primaryStage = new Stage();
         	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("CheckInBookingUI.fxml"));
     		Scene scene = new Scene(root);
     		Image icon=new Image(getClass().getResourceAsStream("../img/hotel.png"));
    		primaryStage.getIcons().add(icon);
     		primaryStage.setTitle("Check In Section");
     		primaryStage.setScene(scene);
     		primaryStage.setResizable(false);
     		primaryStage.show();
    	}
    
    }
    @FXML
    void processDelete(ActionEvent event) throws SQLException {
    	if(bookingTable.getSelectionModel().getSelectedIndex()<0) {
        	noti.getNotification(NotificationType.WARNING, "Warning", "You must first select an item!", AnimationType.SLIDE, 2000.0);	
        	}else {
        		Booking booking = bookingTable.getSelectionModel().getSelectedItem();
             	 
             	 Boolean isDeleteOk =bookingDataUtils.deleteBooking(booking.getBookingId());
             	
             	 if (!isDeleteOk) {
             		
          			System.out.println("Deleted "+booking.getGuestName());
          			showTable("select * from booking");
          			

          			
          		}else {
          			
          			System.out.println("Fail To Delete "+booking.getGuestName());
          		}
        	}
    	
    }

    @FXML
    void processRefresh(ActionEvent event) {
    	showTable("select * from booking;");
    }

    @FXML
    void processSearch(ActionEvent event) {
    	String column = cobColumn.getValue();
    	String query = tfSearch.getText().trim();
    	
    	showTable("select * from booking where "+column+" = '"+query+"';");
    }
    @FXML
    void processView(ActionEvent event) throws IOException {
    	BookingStatusHolder.setButtonStatus("booking");
     	if(bookingTable.getSelectionModel().getSelectedIndex()<0) {
        	noti.getNotification(NotificationType.WARNING, "Warning", "You must first select an item!", AnimationType.SLIDE, 2000.0);	
        	}else {
        		Booking booking= bookingTable.getSelectionModel().getSelectedItem();
            	 
            	BookingHolder holder = BookingHolder.getBookingInstance();
            	 
            	holder.setBooking(booking);
            	 
            	Stage primaryStage = new Stage();
             	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("BookingDetailsUI.fxml"));
         		Scene scene = new Scene(root);
         		Image icon=new Image(getClass().getResourceAsStream("../img/hotel.png"));
        		primaryStage.getIcons().add(icon);
         		primaryStage.setTitle("Booking Details");
         		primaryStage.setScene(scene);
         		primaryStage.setResizable(false);
         		primaryStage.show();
        	}
    
    }
 public void showTable(String sql) {
    	
    	try {
			bookingTable.setItems(bookingDataUtils.getAllBooking(sql));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    		
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		try {
			cobColumn.setItems(bookingDataUtils.getAllColumnLabel());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ObservableList<Booking>bookingList=bookingDataUtils.getAllBooking("select * from booking;");
			for(Booking booking:bookingList) {
				long dayDifference=ChronoUnit.DAYS.between(LocalDate.now(),LocalDate.parse(booking.getCheckInDate()));
//				System.out.println(dayDifference);
				if(dayDifference<0) {
				 Optional<ButtonType>result=noti.getConfirmationAlert("Comfirmation", "Guest "+booking.getGuestName()+"'s checkin date is expired", "Do you want to delete?");
				 if(result.get()==ButtonType.OK) {
					 boolean isDeleteOk=bookingDataUtils.deleteBooking(booking.getBookingId());
					 if(!isDeleteOk) {
						 bookingDataUtils.updateDeletedBooking(booking.getRoomNo());
					 }
				 }
				}
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
		
		bookingId.setCellValueFactory(new PropertyValueFactory<>("bookingId"));
		guestName.setCellValueFactory(new PropertyValueFactory<>("guestName"));
		guestPhNo.setCellValueFactory(new PropertyValueFactory<>("guestPhNo"));
		roomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
		roomNo.setCellValueFactory(new PropertyValueFactory<>("roomNo"));
		roomPrice.setCellValueFactory(new PropertyValueFactory<>("roomPrice"));
		noOfGuest.setCellValueFactory(new PropertyValueFactory<>("noOfGuest"));
		bookedDate.setCellValueFactory(new PropertyValueFactory<>("bookedDate"));
		checkInDate.setCellValueFactory(new PropertyValueFactory<>("checkInDate"));
		numberOfDays.setCellValueFactory(new PropertyValueFactory<>("numberOfDays"));
		checkOutDate.setCellValueFactory(new PropertyValueFactory<>("checkOutDate"));
		
		showTable("select * from booking;");
	}


}
