package housekeeping;

import java.io.IOException;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utility.MyNotification;

public class HouseKeepingController {
	private final MyNotification noti=new MyNotification();
    @FXML
    void processLogout(MouseEvent event) throws IOException {
    	Optional<ButtonType> result=noti.getConfirmationAlert("Comfimation Dialog", "Comfirmation", "Do you really want to Exit?");
		if(result.get()==ButtonType.OK) {
			Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
	    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../main/LogInUI.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setTitle("Hotel Administration Login");
			primaryStage.setScene(scene);
			primaryStage.show();
		}
    }

    @FXML
    void processRefrigerator(MouseEvent event) throws IOException {
    	Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	primaryStage.setResizable(true);
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("RefrigeratorUI.fxml"));
    	Scene scene = new Scene(root);

    	primaryStage.setTitle("Refrigerator Section");
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }

    @FXML
    void processWaitingRoom(MouseEvent event) throws IOException {
    	Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	primaryStage.setResizable(true);
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("WaitingRoomUI.fxml"));
    	Scene scene = new Scene(root);

    	primaryStage.setTitle("Waiting Room Section");
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }

}
