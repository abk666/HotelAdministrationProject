package accountant;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import bean.Import;
import bean.ImportHolder;
import bean.ImportStatusHolder;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import utiltiy.ImportDataUtils;
import utiltiy.MyNotification;



public class ImportController implements Initializable{
	 @FXML
	    private JFXTextField tfSearch;

	    @FXML
	    private JFXComboBox<String> cobSearch;

	    @FXML
	    private TableView<Import> importTable;

	    @FXML
	    private TableColumn<Import, Integer> importId;

	    @FXML
	    private TableColumn<Import, String> importItemName;

	    @FXML
	    private TableColumn<Import, String> importItemCategory;

	    @FXML
	    private TableColumn<Import, Double> importItemPrice;

	    @FXML
	    private TableColumn<Import, Integer> importItemQty;

	    @FXML
	    private TableColumn<Import, String> importItemUnit;

	    @FXML
	    private TableColumn<Import, String> importDate;

	    @FXML
	    private TableColumn<Import, String> importItemExpiredDate;

	    @FXML
	    private TableColumn<Import, String> accountantUserName;
	    
	    @FXML
	    private TableColumn<Import, Double> totalPrice;
	    
	    @FXML
	    private TableColumn<Import, String> itemStatus;
	    
	    @FXML
	    private RadioButton rbExpired;

	    @FXML
	    private ToggleGroup status;

	    @FXML
	    private RadioButton rbGood;
	    
	    private final ImportDataUtils importDataUtils=new ImportDataUtils();
	   private final MyNotification noti=new MyNotification();
;
	   
    //Add button Action
	    @FXML
	    void processAdd(MouseEvent event) throws IOException {
	    	//Set the button status as 'Add' Import status holder for importForm Action
	       ImportStatusHolder.setButtonStatus("add");
	    	Stage primaryStage=new Stage();
	    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("ImportFormUI.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Import Form");
			primaryStage.setScene(scene);
			primaryStage.show();
			
	    }
// Delete Button Action
	    @FXML
	    void processDelete(MouseEvent event) throws SQLException {
	    	

	Optional<ButtonType>result=noti.getConfirmationAlert("Comfirmation Dialog", "Are you Sure You want to Delete?", "This cant be undone.");
	
	if(result.get()==ButtonType.OK) {
		Import importItem=importTable.getSelectionModel().getSelectedItem();
		
			Boolean isDeleteOk=importDataUtils.deleteImportItems(importItem);
			if(!isDeleteOk) {
				System.out.println("successfully deleted!");

			
			}
			showTable("select * from import where itemStatus='Good';");
	}
		

	}

	    
// Edit Button action
	    @FXML
	    void processEdit(MouseEvent event) throws IOException {
	      
	    
	      Import importItem=importTable.getSelectionModel().getSelectedItem();
	      ImportStatusHolder.setButtonStatus("edit");
          ImportHolder holder=ImportHolder.getImportInstance();
	      holder.setImportItem(importItem);
	    
	     
	  	Stage primaryStage=new Stage();
		AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("ImportFormUI.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Update form");
		primaryStage.setScene(scene);
		primaryStage.show();
	    }

	    @FXML
	    void processRefresh(MouseEvent event) {
	    	showTable("select * from import where itemStatus='Good';");	
	    	status.selectToggle(rbGood);
	    }

	    @FXML
	    void processSearch(MouseEvent event) {
	    	//Validation for combo box and text field
	    	if(cobSearch.getValue()!=null && !tfSearch.getText().trim().isEmpty()) {
	    	    String column=cobSearch.getValue();
	    	    String query=tfSearch.getText().trim();
	    	    showTable("select * from import where "+column+"= '"+query+"' and itemStatus='Good';");
	    	}
	    	else {
	    		noti.getNotification(NotificationType.ERROR, "Fail!", "Fail to Search,Fields must not be null.", AnimationType.SLIDE, 2000.0);
	    	}
	  

	    }

	    @FXML
	    void processBack(ActionEvent event) throws IOException {
	    	Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
	    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("AccountantMainUI.fxml"));
			Scene scene = new Scene(root);
            primaryStage.setTitle("Accountant Main Section");
			primaryStage.setScene(scene);
			primaryStage.show();
	    }
	    @FXML
	    void processLogOut(ActionEvent event) throws IOException {
	    	Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
	    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../main/LogInUI.fxml"));
			Scene scene = new Scene(root);
		    primaryStage.setTitle("Hotel Administration LogIn");
			primaryStage.setScene(scene);
			primaryStage.show();
	    }
 //Radio button action
	    @FXML
	    void processRadio(ActionEvent event) {

	if(rbGood.isSelected()) {
		showTable("select * from import where itemStatus='Good';");
	}else {
		showTable("select * from import where itemStatus='Expired';");
	}
	    }
	    
	    @FXML
	    void processStock(ActionEvent event) throws IOException {
	      	Stage primaryStage=new Stage();
	    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("StockUI.fxml"));
	    	Scene scene = new Scene(root);
	    	primaryStage.setTitle("Stock Lists");
	    	primaryStage.setScene(scene);
	    	primaryStage.show();
	    }
	 
	    public void showTable(String sql) {
	    	try {
				importTable.setItems(importDataUtils.getAllImportItems(sql));
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
	    }
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			//Adding Column to search combo Box
			try {
				cobSearch.setItems(importDataUtils.getAllImportColumn());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Check the existing items if they are expired or not
			try {
				ObservableList<Import> importList=importDataUtils.getAllImportItems("select * from import;");
				for(Import importItem:importList) {
					LocalDate todayDate=LocalDate.now();
					String expiredDate=importItem.getImportItemExpiredDate();
					Integer importId=importItem.getImportId();
					Boolean isExpired=importDataUtils.isExpired(todayDate, expiredDate, importId);
					if( isExpired!=null &&!isExpired) {
						System.out.println("Success");
					}
					}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		

			importId.setCellValueFactory(new PropertyValueFactory<>("importId"));
			importItemName.setCellValueFactory(new PropertyValueFactory<>("importItemName"));
			importItemCategory.setCellValueFactory(new PropertyValueFactory<>("importItemCategory"));
			importItemPrice.setCellValueFactory(new PropertyValueFactory<>("importItemPrice"));
			importItemQty.setCellValueFactory(new PropertyValueFactory<>("importItemQty"));
			importItemUnit.setCellValueFactory(new PropertyValueFactory<>("importItemUnit"));
			importDate.setCellValueFactory(new PropertyValueFactory<>("importDate"));
			importItemExpiredDate.setCellValueFactory(new PropertyValueFactory<>("importItemExpiredDate"));
			accountantUserName.setCellValueFactory(new PropertyValueFactory<>("accountantUserName"));
			totalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
			itemStatus.setCellValueFactory(new PropertyValueFactory<>("itemStatus"));
			
	        showTable("select * from import where itemStatus='Good';");		
		}
}
