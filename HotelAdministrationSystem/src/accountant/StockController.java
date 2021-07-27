package accountant;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import bean.Import;
import bean.Stock;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import utility.ImportDataUtils;
import utility.MyNotification;
import utility.StockDataUtils;


public class StockController implements Initializable{
	@FXML
    private JFXRadioButton rbAll;

    @FXML
    private ToggleGroup stockListStatus;

    @FXML
    private JFXRadioButton rbSufficient;

    @FXML
    private JFXRadioButton rbInsufficient;

    @FXML
    private JFXTextField tfSearch;

    @FXML
    private TableView<Stock> stockTable;
    @FXML
    private TableColumn<Stock, String> itemName;

    @FXML
    private TableColumn<Stock, Integer> itemStock;
    
    @FXML
    private TableColumn<Stock, Integer> stockId;
    private final MyNotification noti =new MyNotification();
    private final ImportDataUtils importDataUtils=new ImportDataUtils();
    private final StockDataUtils stockDataUtils=new StockDataUtils();
    
    
    //Refresh button Action
    @FXML
    void processRefresh(MouseEvent event) {
    	showTable("select * from stock");
    	stockListStatus.selectToggle(rbAll);
    }
   // Search Button Action
    @FXML
    void processSearch(MouseEvent event) {
    	//Check validation for text field
if(!tfSearch.getText().trim().isEmpty()) {
	//Check selected radio button
	   if(rbAll.isSelected()) {
		   showTable("select * from stock where itemName = '"+tfSearch.getText().trim()+"';");
	    }
	    else if (rbSufficient.isSelected()) {
	    	showTable("select * from stock where itemName = '"+tfSearch.getText().trim()+"' and itemStock>=10;");
		}else {
			showTable("select * from stock where itemName = '"+tfSearch.getText().trim()+"' and itemStock<10;");
		}
	
}else {
	noti.getNotification(NotificationType.ERROR, "Failed!", "Fields must not be Null!", AnimationType.SLIDE, 2000.0);
}
    }
    
    //Radio toggles Action
    @FXML
    void processSearchByStock(ActionEvent event) {
    if(rbAll.isSelected()) {
    	showTable("select * from stock");
    }
    else if (rbSufficient.isSelected()) {
    	showTable("select * from stock where itemStock>=10;");
	}else {
		showTable("select * from stock where itemStock<10");
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
    	Optional<ButtonType> result=noti.getConfirmationAlert("Comfimation Dialog", "Comfirmation", "Do you really want to Exit?");
		if(result.get()==ButtonType.OK) {
			Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
	    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../main/LogInUI.fxml"));
			Scene scene = new Scene(root);
		
			primaryStage.setScene(scene);
			primaryStage.show();
    }
    }
    
    
    public void showTable(String sql) {
    	try {
			stockTable.setItems(stockDataUtils.getAllStock(sql));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Reduced expired items' stock to 0
		try {
          stockDataUtils.reduceExpiredItem();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Integer isStockSaveOk=stockDataUtils.saveStock();
			if(isStockSaveOk>0) {
				System.out.println("ok");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		stockTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		stockId.setCellValueFactory(new PropertyValueFactory<>("stockId"));
		itemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
		itemStock.setCellValueFactory(new PropertyValueFactory<>("itemStock"));
		showTable("select * from stock");
	}
}
