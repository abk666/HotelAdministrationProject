package main;

import database.DbConnection;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
	    	
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("LogInUI.fxml"));
			Scene scene = new Scene(root,800,500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Hotel Adminstration Login");
			primaryStage.getScene().getWindow();
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		DbConnection dbConnection=new DbConnection();
		if (dbConnection.getConnection()!=null){
			System.out.println("Success!");
		}else
		{
			System.out.println("Fail to connect!");
		}
		launch(args);
	}
}
