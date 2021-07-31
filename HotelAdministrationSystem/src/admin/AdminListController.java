package admin;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import bean.Admin;
import bean.AdminHolder;
import javafx.event.ActionEvent;
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
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import utility.AdminDataUtils;
import utility.MyNotification;

public class AdminListController implements Initializable {

    @FXML
    private JFXComboBox<String> cobSearch;

    @FXML
    private JFXTextField tfSearch;

    @FXML
    private TableView<Admin> adminTable;

    @FXML
    private TableColumn<Admin, Integer> id;

    @FXML
    private TableColumn<Admin, String> firstName;

    @FXML
    private TableColumn<Admin, String> lastName;

    @FXML
    private TableColumn<Admin, String> username;

    @FXML
    private TableColumn<Admin, String> email;

    @FXML
    private TableColumn<Admin, String> password;

    @FXML
    private TableColumn<Admin, String> phone;

    @FXML
    private TableColumn<Admin, String> address;

    @FXML
    private TableColumn<Admin, String> dob;

    @FXML
    private TableColumn<Admin, String> status;

    @FXML
    private TableColumn<Admin, String> imageName;
    
    private Boolean isNewButtonClick = false;
    
    private final MyNotification myNoti = new MyNotification();
    
    AdminDataUtils adminDataUtils = new AdminDataUtils();
    
    
    @FXML
    void processBack(MouseEvent event) throws IOException {
    	
    	Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../admin/AdminSaveUI.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Admin Save Section");
		primaryStage.setScene(scene);
		primaryStage.show();
    }


    @FXML
    void processDelete(ActionEvent event) throws SQLException {
    	
    	Admin admin = adminTable.getSelectionModel().getSelectedItem();
   	 
  	  Boolean isDeleteOk = adminDataUtils.deleteAdmin(admin.getId());
  	  
  	  if(!isDeleteOk) {
  		  myNoti.getNotification(NotificationType.SUCCESS, "Deleted!", "Deleted "+admin.getUsername()+" to DB", AnimationType.SLIDE, 3000.0);
  		  		
  		  showTable("select * from admin;");
  		  
  		  File imageFile = new File("src/img/admin/"+admin.getImageName());
  		  if(imageFile.exists()) {
  			  
  			  imageFile.delete();
  		  }
  	  }
  	  else {
  		  myNoti.getNotification(NotificationType.ERROR, " Fail to Deleted!", " Fail to Deleted "+admin.getUsername()+" to DB", AnimationType.SLIDE, 3000.0);
  		  System.out.println("Fail to Delete" + admin.getUsername());
  	  }

    }

    @FXML
    void processEdit(ActionEvent event) throws IOException {
    	

    	
    	Admin admin = adminTable.getSelectionModel().getSelectedItem();
    	
    	AdminHolder holder = AdminHolder.getAdminInstance();
    	holder.setAdmin(admin);
    	
    	Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../admin/AdminUpdateUI.fxml"));
    	primaryStage.setTitle("Admin Update Section");
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		

    }
    
    @FXML
    void processRefresh(ActionEvent event) {
    	showTable("select * from admin;");
    	cobSearch.setValue("Column");
    	tfSearch.clear();

    }

    @FXML
    void processSearch(ActionEvent event) {
    	
    	String column = cobSearch.getValue();
    	String query = tfSearch.getText().trim();
    	
    	showTable("select * from admin where "+column+" = '"+query+"';");

    }
    
    @FXML
    void processView(ActionEvent event) throws IOException {
    	
    	Admin admin = adminTable.getSelectionModel().getSelectedItem();
    	
    	AdminHolder holder = AdminHolder.getAdminInstance();
    	holder.setAdmin(admin);
    	
    	Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../admin/AdminViewUI.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Admin Profile Section");
		primaryStage.setScene(scene);
		primaryStage.show();

    }

    
    public void showTable(String sql) {
    	try {
			adminTable.setItems(adminDataUtils.getAllAdmin(sql));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		try {
			cobSearch.setItems(adminDataUtils.getAllColumnLabel());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		username.setCellValueFactory(new PropertyValueFactory<>("username"));
		email.setCellValueFactory(new PropertyValueFactory<>("email"));
		password.setCellValueFactory(new PropertyValueFactory<>("password"));
		phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		address.setCellValueFactory(new PropertyValueFactory<>("address"));
		status.setCellValueFactory(new PropertyValueFactory<>("status"));
		dob.setCellValueFactory(new PropertyValueFactory<>("dob"));
		imageName.setCellValueFactory(new PropertyValueFactory<>("imageName"));
		
		showTable("select * from admin;");
		
	}

}
