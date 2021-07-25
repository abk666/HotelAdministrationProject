package utiltiy;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class MyNotification {
	public void getNotification(NotificationType notiType,String title,String message,AnimationType aniType,Double milliSecond) {
		TrayNotification trayNoti=new TrayNotification();
		trayNoti.setNotificationType(notiType);
		trayNoti.setTitle(title);
		trayNoti.setMessage(message);
		trayNoti.setAnimationType(aniType);
		trayNoti.showAndDismiss(Duration.millis(milliSecond));
	}
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
