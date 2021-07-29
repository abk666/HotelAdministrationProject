package admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import bean.Admin;
import bean.AdminHolder;
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

public class StaffViewController implements Initializable {

    @FXML
    private Label lblTitle;

    @FXML
    private ImageView staffImage;

    @FXML
    private Label lblFName;

    @FXML
    private Label lblLName;

    @FXML
    private Label lblUsername;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblPassword;

    @FXML
    private Label lblPhone;

    @FXML
    private Label lblAddress;

    @FXML
    private Label lblStatus;

    @FXML
    private Label lblDOB;

    @FXML
    private Label lblRole;

    @FXML
    private Label lblGender;

    @FXML
    private Label lblId;

    @FXML
    private Label lblImageName;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		StaffHolder holder = StaffHolder.getStaffInstance();
		Staff staff = holder.getStaff();
		
		
		staffImage.setImage(new Image(getClass().getResourceAsStream("../img/staff/"+staff.getImageName())));
		
		lblTitle.setText(staff.getUsername()+ "  Profile");
		lblId.setText("ID- "+staff.getId().toString());
		lblImageName.setText(staff.getImageName());
		
		lblFName.setText(staff.getFirstName());
		lblLName.setText(staff.getLastName());
		lblUsername.setText(staff.getUsername());
		lblEmail.setText(staff.getEmail());
		lblPassword.setText(staff.getPassword());
		lblRole.setText(staff.getRole());
		lblGender.setText(staff.getGender());
		lblPhone.setText(staff.getPhone());
		
		lblAddress.setText(staff.getAddress());
		lblDOB.setText(staff.getDob());
		lblStatus.setText(staff.getStatus());
		
	}
	
	  @FXML
	    void processBack(MouseEvent event) throws IOException {
		  

	    	Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
	    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../admin/StaffListUI.fxml"));
			Scene scene = new Scene(root);
			
			primaryStage.setScene(scene);
			primaryStage.show();

	    }

}
