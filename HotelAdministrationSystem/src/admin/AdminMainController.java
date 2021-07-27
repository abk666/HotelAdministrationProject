package admin;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AdminMainController {

    @FXML
    void processAdmin(MouseEvent event) throws IOException {
    	
    	Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	primaryStage.setResizable(false);
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("AdminSaveUI.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Admin Save Section");
		primaryStage.setScene(scene);
		primaryStage.show();

    }

    @FXML
    void processDining(MouseEvent event) throws IOException {
    	
    	Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	primaryStage.setResizable(false);
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("DiningRoomFoodUI.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Admin Dining Section");
		primaryStage.setScene(scene);
		primaryStage.show();

    }

    @FXML
    void processLogOut(MouseEvent event) throws IOException {
    	
    	Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../main/LogInUI.fxml"));
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		primaryStage.show();

    }

    @FXML
    void processRefrigerator(MouseEvent event) throws IOException {
    	
    	Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	primaryStage.setResizable(false);
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("RefrigeratorFoodUI.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Admin Refrigerator Section");
		primaryStage.setScene(scene);
		primaryStage.show();

    }

    @FXML
    void processRoom(MouseEvent event) throws IOException {

    	Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	primaryStage.setResizable(false);
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("AdminRoomUI.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Admin Room Section");
		primaryStage.setScene(scene);
		primaryStage.show();
    }

    @FXML
    void processStaff(MouseEvent event) throws IOException {
    	
    	Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	primaryStage.setResizable(false);
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("AdminStaffUI.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Admin Staff Section");
		primaryStage.setScene(scene);
		primaryStage.show();

    }

}
