package manager;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDatePicker;

import bean.Income;
import bean.Outcome;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import utility.FinancialUtils;
import utility.IncomeOutcomeUtils;
import utility.MyNotification;

public class ManagerFinancialReportController implements Initializable{

    @FXML
    private JFXDatePicker dpDate;

    @FXML
    private TextField tfDate;

    @FXML
    private Label lblIncome;

    @FXML
    private Label lblOutcome;

    @FXML
    private Label lblProfits;

    @FXML
    private TableView<Income> incomeTable;

    @FXML
    private TableColumn<Income, String> incomeDate;

    @FXML
    private TableColumn<Income, Double> incomeAmount;

    @FXML
    private TableView<Outcome> outcomeTable;

    @FXML
    private TableColumn<Outcome, String> outcomeDate;

    @FXML
    private TableColumn<Outcome, Double> outcomeAmoount;

    private final FinancialUtils financialUtils=new FinancialUtils();
    private final IncomeOutcomeUtils incomeOutcomeUtils=new IncomeOutcomeUtils();
    private final MyNotification noti=new MyNotification();
    String date;
    @FXML
    void processRefresh(ActionEvent event) throws SQLException {
       getProfits(LocalDate.now().toString());
       tfDate.setText(LocalDate.now().toString());
    }

    @FXML
    void processSearch(ActionEvent event) throws SQLException {
         this.date=dpDate.getValue().toString();
         getProfits(this.date);
         tfDate.setText(this.date);
    }
    
    @FXML
    void processIncomeTable(MouseEvent event) throws SQLException {
      if(event.getClickCount()==2) {
    	  Income income=incomeTable.getSelectionModel().getSelectedItem();
    	  this.date=income.getDate().toString();
    	  getProfits(this.date);
    	  tfDate.setText(this.date);
      }
    }

    @FXML
    void processOutcomeTable(MouseEvent event) throws SQLException {
    	 if(event.getClickCount()==2) {
       	  Outcome outcome=outcomeTable.getSelectionModel().getSelectedItem();
       	  this.date=outcome.getDate().toString();
       	  getProfits(this.date);
    	 }
    }
    @FXML
    void processLogout(ActionEvent event) throws IOException {
    	Optional<ButtonType>result=noti.getConfirmationAlert("Comfirmation", "Are you Sure", "Do you really want to Log Out");
    	if(result.get()==ButtonType.OK) {
    		Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../main/LogInUI.fxml"));
    		Scene scene = new Scene(root);
    		Image icon=new Image(getClass().getResourceAsStream("../img/hotel.png"));
    		primaryStage.getIcons().add(icon);
    		primaryStage.setScene(scene);
    		primaryStage.show();
    	}
    	
    }
    
    @FXML
    void processBack(ActionEvent event) throws IOException {
    	Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	primaryStage.setResizable(false);
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("ManagerMainUI.fxml"));
		Scene scene = new Scene(root);
		Image icon=new Image(getClass().getResourceAsStream("../img/hotel.png"));
		primaryStage.getIcons().add(icon);
		primaryStage.setTitle("ManagerMainUI");
		primaryStage.setScene(scene);
		primaryStage.show();
    }
    
    
    //Set label and calculate profits
    public void getProfits(String date) throws SQLException {
    	Double totalIncome;
    	Double totalOutcome;
    	Double profits;
    	Date sqlDate=Date.valueOf(date);
    	if(incomeOutcomeUtils.getIncome("select * from income where date = '"+sqlDate+"'").size()>0) {
    		Income income=incomeOutcomeUtils.getIncome("select * from income where date = '"+sqlDate+"'").get(0);
			totalIncome=income.getTotalAmount();
    	}else {
    		totalIncome=0.0;
    	}
			
		
    	if(incomeOutcomeUtils.getOutcome("select * from outcome where date = '"+sqlDate+"'").size()>0) {
    		Outcome outcome=incomeOutcomeUtils.getOutcome("select * from outcome where date = '"+sqlDate+"'").get(0);
			totalOutcome=outcome.getTotalAmount();
    	}else {
    		totalOutcome=0.0;
    	}
    	
			
		
   
    	lblIncome.setText(totalIncome.toString());
    	lblOutcome.setText(totalOutcome.toString());
    	profits=totalIncome-totalOutcome;
    	lblProfits.setText(profits.toString());
    	if(profits<0) {
    		lblProfits.setTextFill(Paint.valueOf("red"));
    	}else {
    		lblProfits.setTextFill(Paint.valueOf("green"));
    	}
    }


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//Refreshing Income And Outcome
		try {
			financialUtils.saveIncome();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		try {
			financialUtils.saveOutcome();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		//default profits with today date
		this.date=LocalDate.now().toString();
		try {
			getProfits(LocalDate.now().toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tfDate.setText(LocalDate.now().toString());
		
		outcomeDate.setCellValueFactory(new PropertyValueFactory<>("date"));
		outcomeAmoount.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
		incomeDate.setCellValueFactory(new PropertyValueFactory<>("date"));
		incomeAmount.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
		//showing income table
		try {
			incomeTable.setItems(incomeOutcomeUtils.getIncome("select * from income;"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			outcomeTable.setItems(incomeOutcomeUtils.getOutcome("select * from outcome;"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
		
		
	

}
