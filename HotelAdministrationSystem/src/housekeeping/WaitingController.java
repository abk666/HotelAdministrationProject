package housekeeping;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class WaitingController {
	
    @FXML
    void processNewRefrigerator(ActionEvent event) throws IOException {

      	Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	primaryStage.setResizable(true);
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("refrigeratorUI.fxml"));
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("Waiting Room Section");
		primaryStage.setScene(scene);
		primaryStage.show();
    }

    @FXML
    void processClick(ActionEvent event) throws IOException {

    	Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	primaryStage.setResizable(true);
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("WaitingRoomUI.fxml"));
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("Waiting Room Section");
		primaryStage.setScene(scene);
		primaryStage.show();
    }

    @FXML
    void processRefrigerator(ActionEvent event) throws IOException {

    	Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	primaryStage.setResizable(true);
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("refrigeratorUI.fxml"));
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("Refrigerator Section");
		primaryStage.setScene(scene);
		primaryStage.show();
    	
    }
    
    @FXML
    void processAdminRoom(ActionEvent event) throws IOException {

      	Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	primaryStage.setResizable(true);
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../admin/AdminRoomUI.fxml"));
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("Refrigerator Section");
		primaryStage.setScene(scene);
		primaryStage.show();
    	
    }
}
