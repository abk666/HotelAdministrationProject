package manager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import bean.Staff;
import bean.StaffHolder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ManagerViewStaffProfileController implements Initializable{

	 @FXML
    private ImageView staffImage;

    @FXML
    private Label lblStaffName;

    @FXML
    private Label lblStaffEmail;

    @FXML
    private Label lblStaffPhone;

    @FXML
    private Label lblStaffAddress;

    @FXML
    private Label lblStaffDOB;

    @FXML
    private Label lblStaffGender;

    @FXML
    private Label lblStaffRole;
    
    @FXML
    void processBack(MouseEvent event) throws IOException {
    	
    	Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	primaryStage.setResizable(false);
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("ManagerViewStaffUI.fxml"));
		Scene scene = new Scene(root);
		Image icon=new Image(getClass().getResourceAsStream("../img/hotel.png"));
		primaryStage.getIcons().add(icon);
		primaryStage.setTitle("ManagerViewStaffUI");
		primaryStage.setScene(scene);
		primaryStage.show();
    	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		StaffHolder holder = StaffHolder.getStaffInstance();
		Staff staff = holder.getStaff();
		
		
		staffImage.setImage(new Image(getClass().getResourceAsStream("../img/"+staff.getStaffImageName())));
		
		lblStaffName.setText(staff.getStaffUserName());
		lblStaffEmail.setText(staff.getStaffEmail());
		lblStaffPhone.setText(staff.getStaffPhNo());
		lblStaffAddress.setText(staff.getStaffAddress());
		lblStaffDOB.setText(staff.getStaffDOB());
		lblStaffGender.setText(staff.getStaffGender());
		lblStaffRole.setText(staff.getStaffRole());
		
	}

}
