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
import com.jfoenix.controls.JFXTextField;

import bean.Admin;
import bean.AdminHolder;
import bean.DiningRoomFood;
import bean.DiningRoomFoodHolder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import utility.AdminDataUtils;
import utility.MyNotification;

public class AdminSaveController implements Initializable {

	@FXML
	private ImageView imageAdmin;
	
    @FXML
    public JFXTextField tfFName;

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

    private String adminImageName;
    
    private String oldImageName;
    
    public Boolean isNewButtonClick = false;
    
    
    AdminDataUtils adminDataUtils = new AdminDataUtils();
    
    private final  MyNotification myNoti = new MyNotification();
    
    Integer adminId;
    
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
			imageAdmin.setImage(image);
			
			this.adminImageName = imageFile.getName();
		}

    }

    @FXML
    void processLogOut(MouseEvent event) throws IOException {
    	
    	Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("AdminMainUI.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Admin Main UI");
		primaryStage.setScene(scene);
		primaryStage.show();

    }

    @FXML
    void processNew(ActionEvent event) {
    	isNewButtonClick = true;
    	
    	enableAllField();

    }

    @FXML
    void processSave(ActionEvent event) throws IOException, SQLException {
    	if(!tfFName.getText().trim().isEmpty()&& !tfLName.getText().trim().isEmpty() && !tfUsername.getText().trim().isEmpty() &&!tfEmail.getText().trim().isEmpty()
    			&&!tfPassword.getText().trim().isEmpty()&& !tfPhone.getText().trim().isEmpty()&&!tfAddress.getText().trim().isEmpty() && dpDOB.getValue()!=null && cobStatus.getValue()!=null) {
    		String fName = tfFName.getText().trim();
        	String lName = tfLName.getText().trim();
        	String userName = tfUsername.getText().trim();
        	String email = tfEmail.getText().trim();
        	String password = tfPassword.getText();
        	String phone = tfPhone.getText().trim();
        	String address = tfAddress.getText().trim();
        	String dob = dpDOB.getValue().toString();
        	String status = cobStatus.getValue();
        	
        	String imagename="";
        	
        	if (this.adminImageName!= null) {
        	 
        	Integer indexDot = this.adminImageName.indexOf(".");
        	imagename = this.adminImageName.substring(0, indexDot)+".jpg";
    		
        	}else {
        		imagename = this.oldImageName;
        	}
    		
    		if(isNewButtonClick) {
        	
    		Admin admin = new Admin(fName, lName, userName, email, password, phone, address, dob, status, imagename);
    				
        	Boolean isSaveOK = adminDataUtils.saveAdmin(admin);
        	
        	if (!isSaveOK) {
    			System.out.println("Successfully saved");
    			
    			File imageFile = new File("src/img/admin/"+imagename);
    			
    			BufferedImage bufferedImage = SwingFXUtils.fromFXImage(imageAdmin.getImage(), null);
    			
    			ImageIO.write(bufferedImage, "jpg", imageFile);
    			
    			myNoti.getNotification(NotificationType.SUCCESS, "Save Success!", "Successfully Save "+userName+" to DB", AnimationType.SLIDE, 3000.0);
    			
    			clearAllField();
    			
    		}else
    		{
    			myNoti.getNotification(NotificationType.ERROR, "Save Fail!", "Fail to Save "+userName+" to DB", AnimationType.SLIDE, 3000.0);

    			}

    		}
    	}else {
    		myNoti.getNotification(NotificationType.WARNING, "Warning", "Input fields must not be null", AnimationType.SLIDE, 3000.0);
    	}
    	
    	
    	
    	

    }

    @FXML
    void processViewLlist(ActionEvent event) throws IOException {
    	
    	Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	primaryStage.setResizable(false);
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("AdminListUI.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Admin List Section");
		primaryStage.setScene(scene);
		primaryStage.show();

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		ObservableList<String> statusList = FXCollections.observableArrayList(
				"Active","Inactive"	
					
					);
			
			cobStatus.setItems(statusList);
			
			disableAllField();
		
	}

	 public void enableAllField() {
	    	
	    	tfFName.setDisable(false);
	    	tfLName.setDisable(false);
	    	tfUsername.setDisable(false);
	    	tfEmail.setDisable(false);
	    	tfPassword.setDisable(false);
	    	tfPhone.setDisable(false);
	    	tfAddress.setDisable(false);
	    	dpDOB.setDisable(false);
	    	cobStatus.setDisable(false);
	    	imageAdmin.setDisable(false);
	    	
	    	
	    }
	  
	  	public void clearAllField() {
	  		
	  		tfFName.clear();
	  		tfLName.clear();
	  		tfUsername.clear();
	  		tfEmail.clear();
	  		tfPassword.clear();
	  		tfPhone.clear();
	  		tfAddress.clear();
	  		dpDOB.setValue(LocalDate.now());
	  		cobStatus.setValue("Status");
	  		imageAdmin.setImage(new Image(getClass().getResourceAsStream("../img/administrative.png")));
		  
	  }

	    public void disableAllField() {
	    	
	    	tfFName.setDisable(true);
	    	tfLName.setDisable(true);
	    	tfUsername.setDisable(true);
	    	tfEmail.setDisable(true);
	    	tfPassword.setDisable(true);
	    	tfPhone.setDisable(true);
	    	tfAddress.setDisable(true);
	    	dpDOB.setDisable(true);
	    	cobStatus.setDisable(true);
	    	imageAdmin.setDisable(true);
	    	
	    	
	    }
	
}
