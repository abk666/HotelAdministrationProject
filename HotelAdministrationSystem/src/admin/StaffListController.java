package admin;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import bean.Admin;
import bean.AdminHolder;
import bean.Staff;
import bean.StaffHolder;
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
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import utility.MyNotification;
import utility.StaffDataUtils;

public class StaffListController implements Initializable {

    @FXML
    private JFXComboBox<String> cobSearch;

    @FXML
    private JFXTextField tfSearch;

    @FXML
    private TableView<Staff> staffTable;

    @FXML
    private TableColumn<Staff, Integer> id;

    @FXML
    private TableColumn<Staff, String> firstName;

    @FXML
    private TableColumn<Staff, String> lastName;

    @FXML
    private TableColumn<Staff, String> username;

    @FXML
    private TableColumn<Staff, String> email;

    @FXML
    private TableColumn<Staff, String> password;

    @FXML
    private TableColumn<Staff, String> role;

    @FXML
    private TableColumn<Staff, String> gender;

    @FXML
    private TableColumn<Staff, String> phone;

    @FXML
    private TableColumn<Staff, String> address;

    @FXML
    private TableColumn<Staff, String> dob;

    @FXML
    private TableColumn<Staff, String> status;

    @FXML
    private TableColumn<Staff, String> imageName;
    
    
    
    private final MyNotification myNoti = new MyNotification();

    private StaffDataUtils staffDataUtils = new StaffDataUtils();
    
    
    @FXML
    void processBack(MouseEvent event) throws IOException {
    	
    	Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../admin/StaffSaveUI.fxml"));
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		primaryStage.show();

    }

    @FXML
    void processDelete(ActionEvent event) throws SQLException {
    	if(staffTable.getSelectionModel().getSelectedIndex()>=0) {
    		Staff staff = staffTable.getSelectionModel().getSelectedItem();
         	 
      	  Boolean isDeleteOk = staffDataUtils.deleteStaff(staff.getStaffId());
      	  
      	  if(!isDeleteOk) {
      		  myNoti.getNotification(NotificationType.SUCCESS, "Deleted!", "Deleted "+staff.getStaffUserName()+" to DB", AnimationType.SLIDE, 3000.0);
      		  		
      		  showTable("select * from staff;");
      		  
      		  File imageFile = new File("src/img/staff/"+staff.getStaffImageName());
      		  if(imageFile.exists()) {
      			  
      			  imageFile.delete();
      		  }
      	  }
      	  else {
      		  myNoti.getNotification(NotificationType.ERROR, " Fail to Deleted!", " Fail to Deleted "+staff.getStaffUserName()+" to DB", AnimationType.SLIDE, 3000.0);
      		  System.out.println("Fail to Delete" + staff.getStaffUserName());
      	  }

    	}
    	else {
    		 myNoti.getNotification(NotificationType.ERROR, " Fail to Deleted!", "You first need to select an item!", AnimationType.SLIDE, 3000.0);
    	}
    

    }

    @FXML
    void processEdit(ActionEvent event) throws IOException {
    	if(staffTable.getSelectionModel().getSelectedIndex()>=0) {
    		Staff staff = staffTable.getSelectionModel().getSelectedItem();
        	
        	StaffHolder holder = StaffHolder.getStaffInstance();
        	holder.setStaff(staff);
        	
        	Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../admin/StaffUpdateUI.fxml"));
    		Scene scene = new Scene(root);
    		primaryStage.setTitle("Staff Update Section");
    		primaryStage.setScene(scene);
    		primaryStage.show();
    	}else{
    		myNoti.getNotification(NotificationType.ERROR, " Fail to Deleted!", "You first need to select an item!", AnimationType.SLIDE, 3000.0);
    	}
    

    }
    
    @FXML
    void processInactive(MouseEvent event) {
    	showTable("select * from staff where staffStatus= 'Inactive' ;");
    }

    @FXML
    void processRefresh(ActionEvent event) {
    	
    	showTable("select * from staff;");
    	cobSearch.setValue("Column");
    	tfSearch.clear();

    }

    @FXML
    void processSearch(ActionEvent event) {
    	
    	String column = cobSearch.getValue();
    	String query = tfSearch.getText().trim();
    	
    	showTable("select * from staff where "+column+" = '"+query+"';");

    }

    @FXML
    void processView(ActionEvent event) throws IOException {
        if(staffTable.getSelectionModel().getSelectedIndex()>=0) {
        	Staff staff = staffTable.getSelectionModel().getSelectedItem();
        	
        	StaffHolder holder = StaffHolder.getStaffInstance();
        	holder.setStaff(staff);
        	
        	Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../admin/StaffViewUI.fxml"));
    		Scene scene = new Scene(root);
    		primaryStage.setTitle("Staff Profile Section");
    		primaryStage.setScene(scene);
    		primaryStage.show();
        }else {
     		myNoti.getNotification(NotificationType.ERROR, "Failed", "You must first select an item", AnimationType.SLIDE, 2000.0);
     	}
    	
    	
    }
    
    public void showTable(String sql) {
    	try {
			staffTable.setItems(staffDataUtils.getAllStaff(sql));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		try {
			cobSearch.setItems(staffDataUtils.getAllColumnLabel());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		id.setCellValueFactory(new PropertyValueFactory<>("staffId"));
		firstName.setCellValueFactory(new PropertyValueFactory<>("staffFName"));
		lastName.setCellValueFactory(new PropertyValueFactory<>("staffLName"));
		username.setCellValueFactory(new PropertyValueFactory<>("staffUserName"));
		email.setCellValueFactory(new PropertyValueFactory<>("staffEmail"));
		password.setCellValueFactory(new PropertyValueFactory<>("staffPassword"));
		role.setCellValueFactory(new PropertyValueFactory<>("staffRole"));
		gender.setCellValueFactory(new PropertyValueFactory<>("staffGender"));
		phone.setCellValueFactory(new PropertyValueFactory<>("staffPhNo"));
		address.setCellValueFactory(new PropertyValueFactory<>("staffAddress"));
		status.setCellValueFactory(new PropertyValueFactory<>("staffStatus"));
		dob.setCellValueFactory(new PropertyValueFactory<>("staffDOB"));
		imageName.setCellValueFactory(new PropertyValueFactory<>("staffImageName"));
		
		showTable("select * from staff;");
		
	}

}
