package accountant;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import bean.ItemUsage;
import bean.Stock;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utiltiy.ItemUsageDataUtils;
import utiltiy.StockDataUtils;


public class ItemUsageController implements Initializable{
	 @FXML
	    private JFXComboBox<String> cobSearch;

	    @FXML
	    private JFXTextField tfSearch;

	    @FXML
	    private JFXTextField tfItemQty;

	    @FXML
	    private JFXDatePicker dpDate;

	    @FXML
	    private JFXComboBox<String> cobName;

	    @FXML
	    private TableView<ItemUsage> itemUsageTable;

	    @FXML
	    private TableColumn<ItemUsage, Integer> itemUsageId;

	    @FXML
	    private TableColumn<ItemUsage, String> itemName;

	    @FXML
	    private TableColumn<ItemUsage, Integer> itemQty;

	    @FXML
	    private TableColumn<ItemUsage, String> itemUsageDate;
	    
	    private final StockDataUtils stockDataUtils=new StockDataUtils();
	    private final ItemUsageDataUtils itemUsageDataUtils=new ItemUsageDataUtils();
	    private boolean isSaveButtonClicked;
	    private Integer itemId;
	    private Integer oldItemUsageQty;
	    private String oldItemName;
	   
	    @FXML
	    void processBack(ActionEvent event) throws IOException {
	    	Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
	    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("AccountantMainUI.fxml"));
			Scene scene = new Scene(root);
            primaryStage.setScene(scene);
			primaryStage.show();
	    }

	    @FXML
	    void processDelete(MouseEvent event) throws SQLException {
	       ItemUsage itemUsage=itemUsageTable.getSelectionModel().getSelectedItem();
	       boolean isDeleteOk=itemUsageDataUtils.deleteItemUsage(itemUsage.getItemUsageId());
	       if(!isDeleteOk) {
	    	   System.out.println("Successfully deleted!");
	    	   showTable("select * from itemusage;");
	       }
	    }

	    @FXML
	    void processLogOut(ActionEvent event) throws IOException {
	    	Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
	    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../main/LogInUI.fxml"));
			Scene scene = new Scene(root);
		
			primaryStage.setScene(scene);
			primaryStage.show();
	    }
	    
	    @FXML
	    void processNew(MouseEvent event) {
	    	 enableAllFields();
	         isSaveButtonClicked=true;
	         dpDate.setValue(LocalDate.now());
	    }

	    @FXML
	    void processRefresh(MouseEvent event) {
	    	showTable("select * from itemusage;");
	    }

	    @FXML
	    void processSave(ActionEvent event) throws SQLException {
	      if(isInteger(tfItemQty.getText().trim())&&cobName.getValue()!=null && !tfItemQty.getText().trim().isEmpty() && dpDate.getValue()!=null) {
	          String itemName=cobName.getValue();
	          Integer itemQty=Integer.parseInt(tfItemQty.getText().trim());
	          String itemUsageDate=dpDate.getValue().toString();
	          Stock stockList=stockDataUtils.getAllStock("select * from stock where itemName ='"+itemName+"';").get(0);
	          Integer stockId=stockList.getStockId();
	          Integer stockQty=stockList.getItemStock();
	         
	       	   if(isSaveButtonClicked) {
	       		 if(stockQty-itemQty>=0) {
	       			 ItemUsage itemUsage=new ItemUsage(itemName, itemQty, itemUsageDate);
	             	   boolean isSaveOk=itemUsageDataUtils.saveItemUsage(itemUsage);
	             	   if(!isSaveOk) {
	             		   boolean isReduckOk=stockDataUtils.reduceStock(stockQty, itemQty, stockId);
	             		   if(!isReduckOk) {
	             			   System.out.println("Successfully Saved");
	             			  showTable("select * from itemusage;");
	             		   }else {
	             			   System.out.println("Error in reducing");
	             		   }
	             		 
	             	   }else{
	             		   System.out.println("Fail to save!");
	             	   }
	       		 }else {
	             	   System.out.println("Insuffient stock");
	                }
	       		 
	           	  
	       		   
	              }else {
	            	  Integer updatedItemQty;
	            	  if(oldItemName.contains(itemName)) {
	            		  updatedItemQty=itemQty-this.oldItemUsageQty;
	            		
	            	  }else {
	            		  updatedItemQty=itemQty;
	            	  }
	             
	               if(stockQty-updatedItemQty>=0) {
	            	   ItemUsage updateItemUsage=new ItemUsage(this.itemId, itemName, itemQty, itemUsageDate);
	               	   Integer updatedRows=itemUsageDataUtils.updateItemUsage(updateItemUsage);
	               	   if(updatedRows>0) {
	               		boolean isReducedOk=stockDataUtils.reduceStock(stockQty, updatedItemQty, stockId);
	               		if(!isReducedOk) {
	               		   System.out.println("Successfully Updated");
	               		   showTable("select * from itemusage");
	               		}else{
	               			System.out.println("Fail to reduce!");
	               		}
	               	   }else {
	               		   System.out.println("Fail to update!");
	               	   }
	               }else {
	            	   System.out.println("Insuffient stock");
	               }
	           	   
	              }
	          }

	     
	    }

	    @FXML
	    void processSearch(MouseEvent event) {
	      if(cobSearch.getValue()!=null && !tfSearch.getText().trim().isEmpty()) {
	    	  String column=cobSearch.getValue();
	    	  String query=tfSearch.getText().trim();
	    	  showTable("select * from itemusage where "+column+" = '"+query+"';");
	      }else {
	    	  System.out.println("Fields must not be null!");
	      }
	    }

	    @FXML
	    void processUpdate(MouseEvent event) {
	    	  enableAllFields();
	          isSaveButtonClicked=false;
	          ItemUsage itemUsage=itemUsageTable.getSelectionModel().getSelectedItem();
	          
	          cobName.setValue(itemUsage.getItemName());
	          tfItemQty.setText(itemUsage.getItemQty().toString());
	          dpDate.setValue(LocalDate.parse(itemUsage.getItemUsageDate()));
	          this.itemId=itemUsage.getItemUsageId();
	          this.oldItemUsageQty=itemUsage.getItemQty();
	          this.oldItemName=itemUsage.getItemName();
	          
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
	    
	    public void disableAllFields() {
	    cobName.setDisable(true);
	    tfItemQty.setDisable(true);
	    dpDate.setDisable(true);
	    }
	    public void enableAllFields() {
	   	cobName.setDisable(false);
	 	tfItemQty.setDisable(false);
	    dpDate.setDisable(false);
	    }
	    public void showTable(String sql) {
	    	try {
				itemUsageTable.setItems(itemUsageDataUtils.getAllItemUsage(sql));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    public boolean isInteger(String num) {
	                try {
		                    Integer.parseInt(num);
		                    return true;
	                    } catch (Exception e) {
		
	                    }return false;
	              }

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			try {
				ObservableList<String> itemNameList=FXCollections.observableArrayList();
				ObservableList<Stock>stockItemList=stockDataUtils.getAllStock("select * from stock;");
				for(Stock stockItem:stockItemList) {
					itemNameList.add(stockItem.getItemName());
					}
				cobName.setItems(itemNameList);
			} catch (SQLException e) {
	            e.printStackTrace();
			}
			
			try {
				cobSearch.setItems(itemUsageDataUtils.getAllItemUsageColumn());
			} catch (SQLException e) {
	            e.printStackTrace();
			}
			itemUsageId.setCellValueFactory(new PropertyValueFactory<>("itemUsageId"));
			itemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
			itemQty.setCellValueFactory(new PropertyValueFactory<>("itemQty"));
			itemUsageDate.setCellValueFactory(new PropertyValueFactory<>("itemUsageDate"));
			showTable("select * from itemusage;");
			disableAllFields();
			
			
		}
}
