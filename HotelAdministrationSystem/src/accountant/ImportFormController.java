package accountant;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import bean.Import;
import bean.ImportHolder;
import bean.ImportStatusHolder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import utility.ImportDataUtils;
import utility.MyNotification;
import utility.StockDataUtils;
;


public class ImportFormController implements Initializable{
	@FXML
    private JFXTextField tfName;

    @FXML
    private JFXTextField tfPrice;

    @FXML
    private JFXTextField tfQuantity;

    @FXML
    private ComboBox<String> cobCategory;

    @FXML
    private ComboBox<String> cobUnit;
    
    @FXML
    private JFXDatePicker dpExpiredDate;

    @FXML
    private JFXDatePicker dpImportDate;
    
    @FXML
    private JFXButton closeButton;
    
    @FXML
    private JFXButton btnSave;
    
    private final ImportDataUtils importDataUtils=new ImportDataUtils();
    private final MyNotification noti=new MyNotification();
    private final StockDataUtils stockDataUtils=new StockDataUtils();
 
   
    private Integer importId;
    
    @FXML
    void processBack(ActionEvent event)  {
   Stage stage= (Stage)closeButton.getScene().getWindow();
   stage.close();
    }

    @FXML
    void processClear(ActionEvent event) {
		tfName.clear();
		tfPrice.clear();
		tfQuantity.clear();
        cobCategory.setValue(null);
        cobUnit.setValue(null);
        dpExpiredDate.setValue(null);
        dpImportDate.setValue(null);
    }

    @FXML
    void processSave(ActionEvent event) throws SQLException {
    	//Check validation for every Inputs
    	if(isDouble(tfPrice.getText().trim())&& isInteger(tfQuantity.getText().trim())&&!tfName.getText().trim().isEmpty() && cobCategory.getValue()!=null && !tfPrice.getText().trim().isEmpty() && !tfQuantity.getText().trim().isEmpty() && cobUnit.getValue()!=null
    	  && dpExpiredDate.getValue()!=null && dpImportDate.getValue()!=null ) {
    		String importItemName=tfName.getText().trim();
    		String importItemCategory=cobCategory.getValue();
    		Double importItemPrice=Double.parseDouble(tfPrice.getText());
    		Integer importItemQty=Integer.parseInt(tfQuantity.getText());
    		String importItemUnit=cobUnit.getValue();
    		String importDate=dpImportDate.getValue().toString();
    		String importItemExpiredDate=dpExpiredDate.getValue().toString();
    		String accountantUserName="abk";
    		Double totalPrice=importItemPrice*importItemQty;
    		String itemStatus="Good";
    		
    		// Check which button is clicked 
    		if(ImportStatusHolder.getButtonStatus()=="add") {
    			Optional<ButtonType> result=noti.getConfirmationAlert("Comfimation Dialog", "Comfirmation", "Do you really want to save?");
    			if(result.get()==ButtonType.OK) {
    				Import importItem=new Import(importItemName, importItemCategory, importItemPrice, importItemQty, importItemUnit, importDate, importItemExpiredDate, accountantUserName, totalPrice,itemStatus);
        			//Save importItem
    				Boolean isSaveOk=importDataUtils.saveImportItems(importItem);
        			           if(!isSaveOk) {
        			        	   //Save imported item in stocks
        			        	       stockDataUtils.saveStock();
        			        	  
        			        		   noti.getNotification(NotificationType.SUCCESS, "Success!", "Successfully Saved", AnimationType.SLIDE, 2000.0);
            			        	   Stage stage=(Stage) btnSave.getScene().getWindow();
            			        	   stage.close();
        			        	  
        			        	  
        			           }
        			           else {
        			        	   noti.getNotification(NotificationType.ERROR, "Fail!", "Fail to Save", AnimationType.SLIDE, 2000.0);
        			           
    			}
    			}
        	}
    		else {
    			Optional<ButtonType> result=noti.getConfirmationAlert("Comfimation Dialog", "Comfirmation", "Do you really want to Update?");
    			if(result.get()==ButtonType.OK) {
    				
    				Import importItem=new Import(this.importId, importItemName, importItemCategory, importItemPrice, importItemQty, importItemUnit, importDate, importItemExpiredDate, accountantUserName, totalPrice,itemStatus);
        			Integer isUpdateOk=importDataUtils.updateImportItems(importItem);
        			if(isUpdateOk>0) {
        				//Save in stock
        				stockDataUtils.saveStock();
        				
        					noti.getNotification(NotificationType.SUCCESS, "Success!", "Successfully Updated", AnimationType.SLIDE, 2000.0);
           				 Stage stage=(Stage) btnSave.getScene().getWindow();
       		        	   stage.close();
        				}
        				
        			}else {
        				noti.getNotification(NotificationType.ERROR, "Fail!", "Fail to Update", AnimationType.SLIDE, 2000.0);
        			}
    			}
    			
    		}else {
        		noti.getNotification(NotificationType.ERROR, "Error", "Something went wrong ,Please check your input", AnimationType.SLIDE, 2000.0);
        	}

    		
    	}




   
    
    public boolean isDouble(String num) {
   try {
	   Double.parseDouble(num);
	   return true;
} catch (Exception e) {
	
}return false;
    }
    public boolean isInteger(String num) {
   try {
	   Integer.parseInt(num);
	   return true;
       } catch (Exception e) {
	
       }return false;
  }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> categoryList=FXCollections.observableArrayList("Crops","Meat","Fish","Seafood","Vegetables","Fruits","Beans","Dairy Produce","Drinks","Beverage","Others");
		ObservableList<String> unitList=FXCollections.observableArrayList("kg","litre","packs","ton");
		
		cobCategory.setItems(categoryList);
		cobUnit.setItems(unitList);
		
		
		if(ImportStatusHolder.getButtonStatus()=="edit") {
			ImportHolder holder=ImportHolder.getImportInstance();
			Import importItem=holder.getImportItem();
			tfName.setText(importItem.getImportItemName());
			cobCategory.setValue(importItem.getImportItemCategory());
			tfPrice.setText(importItem.getImportItemPrice().toString());
			tfQuantity.setText(importItem.getImportItemQty().toString());
			cobUnit.setValue(importItem.getImportItemUnit());
			dpImportDate.setValue(LocalDate.parse(importItem.getImportDate()));
			dpExpiredDate.setValue(LocalDate.parse(importItem.getImportItemExpiredDate()));
			this.importId=importItem.getImportId();
		}else {
			dpImportDate.setValue(LocalDate.now());
		}
		
	}
}
