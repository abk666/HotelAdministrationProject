package admin;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import bean.RefrigeratorFood;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import utility.MyNotification;
import utility.RefrigeratorFoodDataUtils;

public class RefrigeratorFoodController implements Initializable{

    @FXML
    private ImageView itemImage;

    @FXML
    private JFXTextField tfItemName;

    @FXML
    private JFXTextField tfItemPrice;

    @FXML
    private JFXComboBox<String> cobItemCategory;

    @FXML
    private JFXTextField tfItemQty;
    
    private String itemImageName;
    
    private String oldImageName;

    private final MyNotification myNoti = new MyNotification();
    
    private final RefrigeratorFoodDataUtils refrigeratorFoodDataUtils = new RefrigeratorFoodDataUtils();
    
    
    @FXML
    void processImage(MouseEvent event) {
    	
    	FileChooser imageChooser =new FileChooser();
    	imageChooser.setInitialDirectory(new File("C:\\"));
    	imageChooser.getExtensionFilters().add(new ExtensionFilter("Image Files","*.png","*.jpg","*.jpeg","*.ico"));
    	
    	File imageFile = imageChooser.showOpenDialog(null);
    	
    	if (imageFile != null) {
			
    		Image image = new Image(imageFile.toURI().toString());
    		
    		itemImage.setImage(image);
    		
    		this.itemImageName = imageFile.getName();
    		
    		System.out.println(this.itemImageName);
		}

    }
    
    public void clearAllField() {
    	
    	tfItemName.clear();
    	tfItemPrice.clear();
    	cobItemCategory.setValue("Category");
    	tfItemQty.clear();
    	itemImage.setImage(new Image(getClass().getResourceAsStream("../img/upload.png")));//Default Image
    	
    }
    
    @FXML
    void processCancel(ActionEvent event) {

    	clearAllField();
    	
    }
    
    @FXML
    void processBack(MouseEvent event) throws IOException {

    	Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	primaryStage.setResizable(false);
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("AdminMainUI.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("AdminMainUI");
		primaryStage.setScene(scene);
		primaryStage.show();
		
    }

    @FXML
    void processSave(ActionEvent event) throws SQLException, IOException {
    	
    	if(tfItemName.getText() == null || tfItemName.getText().length() == 0 || 
				tfItemPrice.getText() == null || tfItemPrice.getText().length() == 0 ||
				cobItemCategory.getValue() == null || cobItemCategory.getValue().length() == 0 ||
				tfItemQty.getText() == null || tfItemQty.getText().length() == 0) {
    		
    		
    		myNoti.getNotification(NotificationType.WARNING,"Required User Input","Please Fill All Fields!",AnimationType.FADE,3000.0);
    		
    		//System.out.println("Empty");
    		
    	}else {
    		
    		String itemName = tfItemName.getText().trim();
        	String itemCategory = cobItemCategory.getValue();
        	Double itemPrice = Double.parseDouble(tfItemPrice.getText());
        	Integer itemQty = Integer.parseInt(tfItemQty.getText());
        	
        	
        	String imageName = "";
        	if (this.itemImageName != null || !this.itemImageName.isEmpty()) {
        		
    	    	int indexDot = this.itemImageName.indexOf(".");
    			imageName = this.itemImageName.substring(0,indexDot)+".jpg";
    			
        	}else {
        		imageName = this.oldImageName;
        	}
        	
    		
    		RefrigeratorFood refrigeratorFood = new RefrigeratorFood(itemName, itemCategory, itemPrice, itemQty, imageName);
        	
        	Boolean isSaveOk = refrigeratorFoodDataUtils.saveRefrigeratorFood(refrigeratorFood);
        	
        	if (!isSaveOk) {
        		
    			myNoti.getNotification(NotificationType.SUCCESS,"Saved Success","Successfully Saved "+itemName+" to DB",AnimationType.FADE,3000.0);

    			File imageFile = new File("src/img/RefrigeratorFood/"+imageName);
    			
    			BufferedImage bufferedImage = SwingFXUtils.fromFXImage(itemImage.getImage(),null);//ImageView
    			
    			ImageIO.write(bufferedImage,"jpg",imageFile);
    			
    			clearAllField();
    			
    		}else {
    			
    			myNoti.getNotification(NotificationType.ERROR,"Saved Fail","Fail to save "+itemName+" to DB",AnimationType.FADE,3000.0);
    			
    		}
        	
    	}
    	
	}


    @FXML
    void processView(MouseEvent event) throws IOException {

    	Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	primaryStage.setResizable(false);
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("RefrigeratorFoodDetailsUI.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("RefrigeratorFoodDetailsUI");
		primaryStage.setScene(scene);
		primaryStage.show();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		ObservableList<String> itemMenuCategoryList = FXCollections.observableArrayList(
				"Cold Drinks","Beer","Wine","Purified Water"
				);		
		
		cobItemCategory.setItems(itemMenuCategoryList);

		
	}

    

}
