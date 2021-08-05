package main;

import database.DbConnection;
import javafx.application.Application;
import javafx.stage.Stage;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
	    	
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("LogInUI.fxml"));
			Scene scene = new Scene(root,800,500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Image icon=new Image(getClass().getResourceAsStream("../img/hotel.png"));
			primaryStage.getIcons().add(icon);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Hotel Adminstration Login");
			primaryStage.getScene().getWindow();

			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
	
		launch(args);
	}
}
