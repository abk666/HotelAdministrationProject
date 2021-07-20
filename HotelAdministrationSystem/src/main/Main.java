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
			
	    	primaryStage.setResizable(false);
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("LogInUI.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
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
