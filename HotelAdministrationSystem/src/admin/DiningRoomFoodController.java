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

import bean.DiningRoomFood;
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
import utility.DiningRoomFoodDataUtils;
import utility.MyNotification;

public class DiningRoomFoodController implements Initializable{

    @FXML
    private ImageView foodMenuImage;

    @FXML
    private JFXTextField tfFoodName;

    @FXML
    private JFXTextField tfPrice;

    @FXML
    private JFXComboBox<String> cobFoodCategory;
    
    private String foodMenuImageName;
    
    private String oldImageName;
    
    private final MyNotification myNoti = new MyNotification();
    
    private final DiningRoomFoodDataUtils diningRoomFoodDataUtils = new DiningRoomFoodDataUtils();
    
    @FXML
    void processImage(MouseEvent event) {
    	
    	FileChooser imageChooser =new FileChooser();
    	imageChooser.setInitialDirectory(new File("C:\\"));
    	imageChooser.getExtensionFilters().add(new ExtensionFilter("Image Files","*.png","*.jpg","*.jpeg","*.ico"));
    	
    	File imageFile = imageChooser.showOpenDialog(null);
    	
    	if (imageFile != null) {
			
    		Image image = new Image(imageFile.toURI().toString());
    		
    		foodMenuImage.setImage(image);
    		
    		this.foodMenuImageName = imageFile.getName();
    		
    		System.out.println(this.foodMenuImageName);
		}

    }
    

    public void clearAllField() {
    	
    	tfFoodName.clear();
    	tfPrice.clear();
    	cobFoodCategory.setValue("Category");
    	foodMenuImage.setImage(new Image(getClass().getResourceAsStream("../img/upload.png")));//Default Image
    	
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
		primaryStage.setTitle("DiningRoomFoodUI");
		primaryStage.setScene(scene);
		primaryStage.show();
		
    }

    @FXML
    void processSave(ActionEvent event) throws SQLException, IOException {
    	
    	String foodMenuName = tfFoodName.getText().trim();
    	String foodMenuCategory = cobFoodCategory.getValue();
    	Double foodMenuPrice = Double.parseDouble(tfPrice.getText());
    	
    	String imageName = "";
    	if (this.foodMenuImageName != null || !this.foodMenuImageName.isEmpty()) {
    		
	    	int indexDot = this.foodMenuImageName.indexOf(".");
			imageName = this.foodMenuImageName.substring(0,indexDot)+".jpg";
			
    	}else {
    		imageName = this.oldImageName;
    	}
    	
		
		DiningRoomFood diningRoomFood = new DiningRoomFood(foodMenuName, foodMenuCategory, foodMenuPrice, imageName);
    	
    	Boolean isSaveOk = diningRoomFoodDataUtils.saveDiningRoomFood(diningRoomFood);
    	
    	if (!isSaveOk) {
    		
			myNoti.getNotification(NotificationType.SUCCESS,"Saved Success","Successfully Saved "+foodMenuName+" to DB",AnimationType.FADE,3000.0);

			File imageFile = new File("src/img/DiningRoomFood/"+imageName);
			
			BufferedImage bufferedImage = SwingFXUtils.fromFXImage(foodMenuImage.getImage(),null);//ImageView
			
			ImageIO.write(bufferedImage,"jpg",imageFile);
			
			clearAllField();
			
		}else {
			
			myNoti.getNotification(NotificationType.ERROR,"Saved Fail","Fail to save "+foodMenuName+" to DB",AnimationType.FADE,3000.0);
			
		}
	}

    @FXML
    void processView(MouseEvent event) throws IOException {

    	Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	primaryStage.setResizable(false);
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("DiningRoomFoodDetailsUI.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("DiningRoomFoodDetailsUI");
		primaryStage.setScene(scene);
		primaryStage.show();
		
    }
    

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		ObservableList<String> foodMenuCategoryList = FXCollections.observableArrayList(
				"Rice","Curry","Burger","Pizza","Soup","Fried","Salad","Cake","Ice Cream","Fruit","Juice","Cold Drinks","Hot Drinks","Beer","Wine"
				);		
		
		cobFoodCategory.setItems(foodMenuCategoryList);
		
	}
    	
}

