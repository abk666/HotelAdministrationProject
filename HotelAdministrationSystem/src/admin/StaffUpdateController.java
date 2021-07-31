package admin;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import bean.Admin;
import bean.AdminHolder;
import bean.Staff;
import bean.StaffHolder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import utility.MyNotification;
import utility.StaffDataUtils;

public class StaffUpdateController implements Initializable {

    @FXML
    private ImageView imageStaff;

    @FXML
    private JFXTextField tfFName;

    @FXML
    private JFXTextField tfLName;

    @FXML
    private JFXTextField tfUsername;

    @FXML
    private JFXTextField tfEmail;

    @FXML
    private JFXTextField tfPassword;

    @FXML
    private JFXTextField tfPhone;

    @FXML
    private JFXTextField tfAddress;

    @FXML
    private JFXDatePicker dpDOB;

    @FXML
    private JFXComboBox<String> cobStatus;

    @FXML
    private JFXComboBox<String> cobRole;

    @FXML
    private JFXRadioButton rbMale;

    @FXML
    private ToggleGroup Gender;

    @FXML
    private JFXRadioButton rbFemale;
    
    private String staffImageName;
    
    private String oldImageName;
    
    public Boolean isNewButtonClick = false;
    
    private final  MyNotification myNoti = new MyNotification();
    
    StaffDataUtils staffDataUtils = new StaffDataUtils();
    
    private Integer staffId;
    
    @FXML
    void processClear(ActionEvent event) {
    	
    	clearAllField();

    }

    @FXML
    void processImage(MouseEvent event) {
    	
    	FileChooser imageChooser = new FileChooser();
    	imageChooser.setInitialDirectory(new File("C:\\"));
    	imageChooser.getExtensionFilters().add(new ExtensionFilter("Image File", "*.jpg","*.png","*.jpeg","*.ico"));
    	
    	File imageFile = imageChooser.showOpenDialog(null);
    	
    	if (imageFile != null) {
    		
    		Image image = new Image(imageFile.toURI().toString());
			imageStaff.setImage(image);
			
			this.staffImageName = imageFile.getName();
		}

    }

    @FXML
    void processLogOut(MouseEvent event) throws IOException {
    	
    	Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../admin/AdminMainUI.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Admin Main Section");
		primaryStage.setScene(scene);
		primaryStage.show();

    }

    @FXML
    void processNew(ActionEvent event) {
    	isNewButtonClick = true;
    	enableAllField();

    }
    
    

    @FXML
    void processSave(ActionEvent event) throws SQLException, IOException {
    	
    	String fName = tfFName.getText().trim();
    	String lName = tfLName.getText().trim();
    	String userName = tfUsername.getText().trim();
    	String email = tfEmail.getText().trim();
    	String password = tfPassword.getText();
    	String role = cobRole.getValue();
    	
    	String gender;
    	
    	
    	if(rbMale.isSelected()) {
    		gender= rbMale.getText();
    	}else {
    		gender = rbFemale.getText();
    	}
    	
    	String phone = tfPhone.getText().trim();
    	String address = tfAddress.getText().trim();
    	String dob = dpDOB.getValue().toString();
    	String status = cobStatus.getValue();
    	
    	
    	String imagename = "";
    	
    	if (this.staffImageName!= null ) {
    	 
    	Integer indexDot = this.staffImageName.indexOf(".");
    	imagename = this.staffImageName.substring(0, indexDot)+".jpg";
		
    	}else {
    		imagename = this.oldImageName;
    	}
		
		if(isNewButtonClick) {
    	
		Staff staff = new Staff(fName, lName, userName, email, password, role, gender, phone, address, dob, status, imagename);
			
    	Boolean isSaveOK = staffDataUtils.saveStaff(staff);
    	
    	if (!isSaveOK) {
			System.out.println("Successfully saved");
			
			File imageFile = new File("src/img/staff/"+imagename);
			
			BufferedImage bufferedImage = SwingFXUtils.fromFXImage(imageStaff.getImage(), null);
			
			ImageIO.write(bufferedImage, "jpg", imageFile);
			
			myNoti.getNotification(NotificationType.SUCCESS, "Save Success!", "Successfully Save "+userName+" to DB", AnimationType.SLIDE, 3000.0);
			
			clearAllField();
			
    	}
    	
			else
		{
			myNoti.getNotification(NotificationType.ERROR, "Save Fail!", "Fail to Save "+userName+" to DB", AnimationType.SLIDE, 3000.0);

			}
			
		}else {
			
			
			
			Staff staffUpdated = new Staff(this.staffId, fName, lName, userName, email, password, role, gender, phone, address, dob, status, imagename);
					
			Integer rowUpdated = staffDataUtils.updatestaff(staffUpdated);
			
			if (rowUpdated > 0 ) {
				
				
				
				myNoti.getNotification(NotificationType.SUCCESS, "Updated Success!", "Successfully Update "+userName+" to DB", AnimationType.SLIDE, 3000.0);
				File deletedFile = new File("src/img/staff/"+this.oldImageName);
					deletedFile.delete();
					
					File imageFile = new File("src/img/staff/"+imagename);
					
					BufferedImage bufferedImage = SwingFXUtils.fromFXImage(imageStaff.getImage(), null);
					
					ImageIO.write(bufferedImage, "jpg", imageFile);
					
					
					clearAllField();	
					disableAllField();
					
			}
			
		}

    }

    @FXML
    void processViewLlist(ActionEvent event) throws IOException {
    	
    	Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	primaryStage.setResizable(false);
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("StaffListUI.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Staff List Section");
		primaryStage.setScene(scene);
		primaryStage.show();

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		ObservableList<String> statusList = FXCollections.observableArrayList(
				"Active","Inactive"		
					
					);
			
			cobStatus.setItems(statusList);
			
			
			ObservableList<String> roleList = FXCollections.observableArrayList(
					"Manager","Reception","HouseKeeping","DiningRoom","Accountant"	
						
						);
				
				cobRole.setItems(roleList);
				
				StaffHolder holder =StaffHolder.getStaffInstance();
				
				Staff staff = holder.getStaff();
				
				imageStaff.setImage(new Image(getClass().getResourceAsStream("../img/staff/"+staff.getStaffImageName())));
				
				
				staffId = staff.getStaffId();
				tfFName.setText(staff.getStaffFName());
				tfLName.setText(staff.getStaffLName());
				tfUsername.setText(staff.getStaffUserName());
				tfEmail.setText(staff.getStaffEmail());
				tfPassword.setText(staff.getStaffPassword());
				cobRole.setValue(staff.getStaffRole());
				
		    	if(staff.getStaffGender()=="Male") {
		    		Gender.selectToggle(rbMale);
		    	}else {
		    		Gender.selectToggle(rbFemale);
		    	}
		    	
				tfPhone.setText(staff.getStaffPhNo());
				tfAddress.setText(staff.getStaffAddress());
				dpDOB.setValue(LocalDate.parse(staff.getStaffDOB()));
				cobStatus.setValue(staff.getStaffStatus());
				oldImageName=staff.getStaffImageName();
				
				
			
//			disableAllField();
		
	}
	
	public void enableAllField() {
    	
    	tfFName.setDisable(false);
    	tfLName.setDisable(false);
    	tfUsername.setDisable(false);
    	tfEmail.setDisable(false);
    	tfPassword.setDisable(false);
    	cobRole.setDisable(false);
    	Gender.selectToggle(rbMale);
    	tfPhone.setDisable(false);
    	tfAddress.setDisable(false);
    	dpDOB.setDisable(false);
    	cobStatus.setDisable(false);
    	imageStaff.setDisable(false);
    	
    	
    }
  
  	public void clearAllField() {
  		
  		tfFName.clear();
  		tfLName.clear();
  		tfUsername.clear();
  		tfEmail.clear();
  		tfPassword.clear();
  		cobRole.setValue("Role");
  		Gender.selectToggle(rbMale);
  		tfPhone.clear();
  		tfAddress.clear();
  		dpDOB.setValue(LocalDate.now());
  		cobStatus.setValue("Status");
  		imageStaff.setImage(new Image(getClass().getResourceAsStream("../img/staffEdit.png")));
	  
  }

    public void disableAllField() {
    	
    	tfFName.setDisable(true);
    	tfLName.setDisable(true);
    	tfUsername.setDisable(true);
    	tfEmail.setDisable(true);
    	tfPassword.setDisable(true);
    	cobRole.setDisable(true);
    	Gender.selectToggle(rbMale);
    	tfPhone.setDisable(true);
    	tfAddress.setDisable(true);
    	dpDOB.setDisable(true);
    	cobStatus.setDisable(true);
    	imageStaff.setDisable(true);
    	
    	
    }

}
