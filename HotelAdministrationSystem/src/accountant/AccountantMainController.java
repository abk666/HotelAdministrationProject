package accountant;

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



public class AccountantMainController {
	private final MyNotification noti=new MyNotification();
	 @FXML
	    void processImport(MouseEvent event) throws IOException {
	    	Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
	    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("ImportUI.fxml"));
			Scene scene = new Scene(root);
			
			primaryStage.setScene(scene);
			primaryStage.show();
	    }

	    @FXML
	    void processItemUsage(MouseEvent event) throws IOException {
	    	Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
	    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("ItemUsageUI.fxml"));
			Scene scene = new Scene(root);
			
			primaryStage.setScene(scene);
			primaryStage.show();
	    }

	    @FXML
	    void processLogOut(MouseEvent event) throws IOException {
	    	Optional<ButtonType> result=noti.getConfirmationAlert("Comfimation Dialog", "Comfirmation", "Do you really want to Exit?");
			if(result.get()==ButtonType.OK) {
				Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../main/LogInUI.fxml"));
				Scene scene = new Scene(root);
			
				primaryStage.setScene(scene);
				primaryStage.show();
			}
	    	
	    }

	    @FXML
	    void processStock(MouseEvent event) throws IOException {
	    	Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
	    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("StockUI.fxml"));
			Scene scene = new Scene(root);

			primaryStage.setScene(scene);
			primaryStage.show();
	    }
}
