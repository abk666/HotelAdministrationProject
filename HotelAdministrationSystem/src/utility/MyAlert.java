package utility;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class MyAlert {
	
	public Optional<ButtonType> getConfirmationAlert(String title,String header,String content){
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		
		alert.setTitle(title);
		
		alert.setHeaderText(header);
		
		alert.setContentText(content);
		
		Optional<ButtonType> result = alert.showAndWait();
		return result;
	}
	
	public void getAlert(AlertType alertType,String title,String header,String content){
		
		Alert alert = new Alert(alertType);
		
		alert.setTitle(title);
		
		alert.setHeaderText(header);
		
		alert.setContentText(content);
		
		alert.showAndWait();
		
		
	}

}
