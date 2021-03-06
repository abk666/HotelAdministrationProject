package manager;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ManagerMainController {

		@FXML
	    void processStaff(MouseEvent event) throws IOException {
	    	
	    	Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	primaryStage.setResizable(false);
	    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("ManagerViewStaffUI.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setTitle("ManagerViewStaffUI");
			primaryStage.setScene(scene);
			primaryStage.show();
	
	    }	

	    @FXML
	    void processStaffPieChart(MouseEvent event) throws IOException {
	    	
	    	Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	primaryStage.setResizable(false);
	    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("ManagerViewStaffPieChartUI.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setTitle("ManagerViewStaffPieChartUI");
			primaryStage.setScene(scene);
			primaryStage.show();

	    }
	    
	    @FXML
	    void processGuest(MouseEvent event) throws IOException {
	    	
	    	Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	primaryStage.setResizable(false);
	    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("ManagerViewGuestStayUI.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setTitle("ManagerViewGuestStayUI");
			primaryStage.setScene(scene);
			primaryStage.show();
	
	    }
		

	    
	    @FXML
	    void processIncome(MouseEvent event) throws IOException {
	    	
	    	Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	primaryStage.setResizable(false);
	    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("ManagerViewIncomeUI.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setTitle("ManagerViewIncomeUI");
			primaryStage.setScene(scene);
			primaryStage.show();

	    }

	    @FXML
	    void processOutcome(MouseEvent event) throws IOException {

	    	Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	primaryStage.setResizable(false);
	    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("ManagerViewOutcomeUI.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setTitle("ManagerViewOutcomeUI");
			primaryStage.setScene(scene);
			primaryStage.show();
	    }
	    
	    @FXML
	    void processProfits(MouseEvent event) throws IOException {
	    	Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	primaryStage.setResizable(false);
	    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("ManagerFinancialReportUI.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setTitle("ManagerViewOutcomeUI");
			primaryStage.setScene(scene);
			primaryStage.show();
	    }

	    
	    
	    @FXML
	    void processLogOut(MouseEvent event) throws IOException {
	    	
	    	Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
	    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../main/LogInUI.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setTitle("Hotel Administration Login");
			primaryStage.setScene(scene);
			primaryStage.show();

	    }

	    
}
