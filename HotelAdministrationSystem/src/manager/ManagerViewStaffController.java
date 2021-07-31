package manager;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import bean.Staff;
import bean.StaffHolder;
import javafx.collections.ObservableList;
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
import utility.ManagerViewStaffDataUtils;

public class ManagerViewStaffController implements Initializable {

    @FXML
    private TableView<Staff> staffTable;

    @FXML
    private TableColumn<Staff, Integer> staffId;

    @FXML
    private TableColumn<Staff, String> staffUserName;

    @FXML
    private TableColumn<Staff, String> staffEmail;

    @FXML
    private TableColumn<Staff, String> staffPhNo;

    @FXML
    private TableColumn<Staff, String> staffAddress;

    @FXML
    private TableColumn<Staff, String> staffDOB;

    @FXML
    private TableColumn<Staff, String> staffGender;

    @FXML
    private TableColumn<Staff, String> staffRole;

    @FXML
    private TableColumn<Staff, String> staffImageName;

    @FXML
    private JFXComboBox<String> cobSearch;

    @FXML
    private JFXTextField tfSearch;
    
    private final ManagerViewStaffDataUtils staffDataUtils = new ManagerViewStaffDataUtils();
    
    private final MyNotification myNoti = new MyNotification();
    
    private MyAlert alert = new MyAlert();

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
    void processFired(MouseEvent event) throws SQLException {
    	
    	ObservableList<Staff> staffList = staffTable.getSelectionModel().getSelectedItems();
    	
    	Optional<ButtonType> result = alert.getConfirmationAlert("Confirmation Dialog","Are You Sure to Delete Selected Staff?","This action cannot be undone.");

		if (result.get() == ButtonType.OK) {
		
			for (Staff staff : staffList) {
			
				Boolean isDeleteOk = staffDataUtils.deleteFlaskStaff(staff.getStaffId());
			   	
			   	 if (!isDeleteOk) {
					myNoti.getNotification(NotificationType.SUCCESS,"Deleted!","Successfully Deleted!",AnimationType.SLIDE,3000.0);
				
					File imageFile = new File("src/img/"+staff.getStaffImageName());
					
					if (imageFile.exists()) {
						imageFile.delete();
					}
						
						
				}else {
					myNoti.getNotification(NotificationType.ERROR,"Delete Fail","Fail to Delete!",AnimationType.SLIDE,3000.0);
						//System.out.println("Fail To Delete "+diningRoomFood.getFoodMenuName());
				}
			   	 
			}
			
		}
		
		showTable("select * from hoteldb.staff where staffStatus = 'Active';");
 	
    }

    @FXML
    void processRefresh(MouseEvent event) {

    	showTable("select * from hoteldb.staff where staffStatus = 'Active'");
    	
    	cobSearch.setValue("Staff");
    	tfSearch.clear();
    	
    	
    }
    
    public void showTable(String sql) {
    	
    	try {
    		staffTable.setItems(staffDataUtils.getAllStaff(sql));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void processSearch(MouseEvent event) {

    	
    	String columnName = cobSearch.getValue();
    	String dataInput = tfSearch.getText().trim();
    	
    	showTable("select * from hoteldb.staff where "+columnName+" = '"+dataInput+"' and staffStatus = 'Active';");
    	
    }
    
    

    @FXML
    void processView(MouseEvent event) throws IOException {

    	Staff staff = staffTable.getSelectionModel().getSelectedItem();
    	
    	StaffHolder holder = StaffHolder.getStaffInstance();
    	holder.setStaff(staff);
    	
    	Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	primaryStage.setResizable(false);
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("ManagerViewStaffProfileUI.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("ManagerViewStaffProfileUI");
		primaryStage.setScene(scene);
		primaryStage.show();
		
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		staffId.setCellValueFactory(new PropertyValueFactory<>("staffId"));
		staffUserName.setCellValueFactory(new PropertyValueFactory<>("staffUserName"));
		staffEmail.setCellValueFactory(new PropertyValueFactory<>("staffEmail"));
		staffPhNo.setCellValueFactory(new PropertyValueFactory<>("staffPhNo"));
		staffAddress.setCellValueFactory(new PropertyValueFactory<>("staffAddress"));
		staffDOB.setCellValueFactory(new PropertyValueFactory<>("staffDOB"));
		staffGender.setCellValueFactory(new PropertyValueFactory<>("staffGender"));
		staffRole.setCellValueFactory(new PropertyValueFactory<>("staffRole"));
		staffImageName.setCellValueFactory(new PropertyValueFactory<>("staffImageName"));
		
		showTable("select * from hoteldb.staff where staffStatus = 'Active';");
		
		staffTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		try {
			cobSearch.setItems(staffDataUtils.getStaffColumnLabel());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
