package main;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import bean.Staff;
import bean.StaffHolder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import utility.CheckUserCredentials;
import utility.MyNotification;
import utility.StaffDataUtils;



public class LogInController implements Initializable {

    @FXML
    private JFXTextField tfEmail;

    @FXML
    private JFXPasswordField pfPassword;

    @FXML
    private JFXComboBox<String> cobStaff;

    @FXML
    private Label lblEmail;
    
    private final StaffDataUtils staffDataUtils=new StaffDataUtils();
    private final CheckUserCredentials checkUser = new CheckUserCredentials();
    private final MyNotification noti=new MyNotification();
    @FXML
    void processLogin(ActionEvent event) throws SQLException, IOException {
    	if( !tfEmail.getText().trim().isEmpty() &&!pfPassword.getText().isEmpty() && cobStaff.getValue()!=null) {
    		if(tfEmail.getText().trim().contains("@")) {
    			String email = tfEmail.getText().trim();
            	String password = pfPassword.getText();
            	String role = cobStaff.getValue();
            	String loginType = "Email";
if(checkUser.isUserValid(email,password,role,loginType)) {
            		
            		switch (role) {
        			case "Admin":
        				Stage adminStage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        				adminStage.hide();
        				
        				AnchorPane adminRoot = (AnchorPane)FXMLLoader.load(getClass().getResource("../admin/AdminMainUI.fxml"));
        				Scene adminScene = new Scene(adminRoot);
        				adminStage.setTitle("Admin Main Section");
        				adminStage.setScene(adminScene);
        				adminStage.show();
        				
        				break;
        				
        				
        			case "Manager":
        				Stage managerStage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        				managerStage.hide();
        				
        				AnchorPane managerRoot = (AnchorPane)FXMLLoader.load(getClass().getResource("../manager/ManagerMainUI.fxml"));
        				Scene managerScene = new Scene(managerRoot);
        				managerStage.setTitle("Manager Main Section");
        				managerStage.setScene(managerScene);
        				managerStage.show();
        			
        				break;
        				
        			case "Reception":
        				Stage receptionStage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        				receptionStage.hide();
//        				receptionStage.setResizable(false);
        				AnchorPane receptionRoot = (AnchorPane)FXMLLoader.load(getClass().getResource("../reception/ReceptionMainUI.fxml"));
        				Scene receptionScene = new Scene(receptionRoot);
        				receptionStage.setTitle("Reception Main Section");
        				receptionStage.setScene(receptionScene);
        				receptionStage.show();
        			
        				break;
        				
        			case "HouseKeeping":
        				Stage housekeepingStage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        				housekeepingStage.hide();
        				
        				AnchorPane housekeepingRoot = (AnchorPane)FXMLLoader.load(getClass().getResource("../housekeeping/WaitingUI.fxml"));
        				Scene housekeepingScene = new Scene(housekeepingRoot);
        				housekeepingStage.setTitle("HouseKeeping Main Section");
        				housekeepingStage.setScene(housekeepingScene);
        				housekeepingStage.show();
        			
        				break;	
        				
        			case "DiningRoom":
        				Stage diningroomStage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        				diningroomStage.hide();
        				
        				AnchorPane diningroomRoot = (AnchorPane)FXMLLoader.load(getClass().getResource("../diningroom/DiningRoomMainUI.fxml"));
        				Scene diningroomScene = new Scene(diningroomRoot);
        				diningroomStage.setTitle("DiningRoom Main Section");
        				diningroomStage.setScene(diningroomScene);
        				diningroomStage.show();
        			
        				break;		
        				
        			case "Accountant":
        				Staff staff=staffDataUtils.getAllStaff("select * from staff where staffEmail='"+email+"' and staffPassword ='"+password+"';").get(0);
        				StaffHolder holder=StaffHolder.getStaffInstance();
        				holder.setStaff(staff);
        				Stage accountantStage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        				accountantStage.hide();
        				
        				AnchorPane accountantRoot = (AnchorPane)FXMLLoader.load(getClass().getResource("../accountant/AccountantMainUI.fxml"));
        				Scene accountantScene = new Scene(accountantRoot);
        				accountantStage.setTitle("Accountant Main Section");
        				accountantStage.setScene(accountantScene);
        				accountantStage.show();
        			
        				break;
            		}
                   }else {
                   	 noti.getNotification(NotificationType.ERROR, "LogIn failed!", "Incorrect username or password", AnimationType.SLIDE, 2000.0);
                    }
            	}else {
        	    	System.out.println("Email");
        	    	lblEmail.setVisible(true);
        	    	lblEmail.setText("Email must contain @ sign and must no be null.");
        	    	lblEmail.setTextFill(Paint.valueOf("Red"));

        	    	}
    	}else {
        	lblEmail.setVisible(true);
    		lblEmail.setText("Input Fields must not be null");
    		lblEmail.setTextFill(Paint.valueOf("Red"));
    	}

    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		ObservableList<String> roleList = FXCollections.observableArrayList(
				"Admin","Manager","Reception","HouseKeeping","DiningRoom","Accountant"
				
				);
		
		cobStaff.setItems(roleList);
		
	tfEmail.setText("a@gmail.com");
	pfPassword.setText("1234");
	cobStaff.setValue("Admin");
		
	}

}
