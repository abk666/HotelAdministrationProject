package admin;

import java.net.URL;
import java.util.ResourceBundle;

import bean.Admin;
import bean.AdminHolder;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AdminViewController implements Initializable {

    @FXML
    private Label lblTitle;

    @FXML
    private ImageView adminImage;

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
    private Label lblId;

    @FXML
    private Label lblImageName;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		AdminHolder holder = AdminHolder.getAdminInstance();
		Admin admin = holder.getAdmin();
		
		
		adminImage.setImage(new Image(getClass().getResourceAsStream("../img/admin/"+admin.getImageName())));
		
		lblTitle.setText(admin.getUsername()+ "  Profile");
		lblId.setText(admin.getId().toString());
		lblImageName.setText(admin.getImageName());
		
		lblFName.setText(admin.getFirstName());
		lblLName.setText(admin.getLastName());
		lblUsername.setText(admin.getUsername());
		lblEmail.setText(admin.getEmail());
		lblPassword.setText(admin.getPassword());
		lblPhone.setText(admin.getPhone());
		
		lblAddress.setText(admin.getAddress());
		lblDOB.setText(admin.getDob());
		lblStatus.setText(admin.getStatus());
		
	}

}
