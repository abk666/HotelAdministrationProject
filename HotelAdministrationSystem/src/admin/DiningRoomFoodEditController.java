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
import bean.DiningRoomFoodHolder;
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

public class DiningRoomFoodEditController implements Initializable{

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
    
    private Integer id;
    
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
		primaryStage.setTitle("AdminMainUI");
		primaryStage.setScene(scene);
		primaryStage.show();
		
    }

    @FXML
    void processUpdate(ActionEvent event) throws SQLException, IOException {
    	
    	if(tfFoodName.getText() == null || tfFoodName.getText().length() == 0 || 
    			tfPrice.getText() == null || tfPrice.getText().length() == 0 ||
    			cobFoodCategory.getValue() == null || cobFoodCategory.getValue().length() == 0) {
    		
    		
    		myNoti.getNotification(NotificationType.WARNING,"Required User Input","Please Fill All Fields!",AnimationType.FADE,3000.0);
    		
    		//System.out.println("Empty");
    		
    	}else {
    		
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
        	
    		
    		DiningRoomFood diningRoomFood = new DiningRoomFood(this.id,foodMenuName, foodMenuCategory, foodMenuPrice, imageName);
        	
    		Integer isUpdateOk = diningRoomFoodDataUtils.updateDiningRoomFood(diningRoomFood);
        	
    		if(isUpdateOk > 0) {

    			myNoti.getNotification(NotificationType.SUCCESS,"Updated Success","Successfully Update  "+ foodMenuName+" to DB",AnimationType.FADE,3000.0);

    			File imageFile = new File("src/img/DiningRoomFood/"+imageName);
    			
    			BufferedImage bufferedImage = SwingFXUtils.fromFXImage(foodMenuImage.getImage(),null);//ImageView
    			
    			ImageIO.write(bufferedImage,"jpg",imageFile);
    			
    			
    			
    			
    			DiningRoomFoodDataUtils diningRoomFoodDataUtils = new DiningRoomFoodDataUtils();
    			DiningRoomFood diningRoomFoodUpdate = diningRoomFoodDataUtils.getAllDiningRoomFood("select * from hoteldb.foodmenu where foodmenuId = '"+id+"';").get(0);
    			
        		DiningRoomFoodHolder diningRoomFoodHolder=DiningRoomFoodHolder.getDiningRoomFoodInstance();
        		diningRoomFoodHolder.setDiningRoomFood(diningRoomFoodUpdate);
        		
        		Stage primaryStage=(Stage) ((Node)event.getSource()).getScene().getWindow();
            	primaryStage.close();
            	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("DiningRoomFoodDetailsUI.fxml"));
        		Scene scene = new Scene(root);
        		primaryStage.setScene(scene);
        		primaryStage.show();
    			
    			clearAllField();
    			
    		}else {
    			
    			myNoti.getNotification(NotificationType.ERROR,"Updated Fail","Fail to update "+foodMenuName+" to DB",AnimationType.FADE,3000.0);
    			
    		}
    		
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
				"Rice","Curry","Burger","Pizza","Soup","Fried","Salad","Cake","Ice Cream","Fruit","Juice","Cold Drinks","Hot Drinks","Beer","Wine","Purified Water","Hotpot","Hotdog"
				);		
		
		cobFoodCategory.setItems(foodMenuCategoryList);
		
		
		DiningRoomFoodHolder holder = DiningRoomFoodHolder.getDiningRoomFoodInstance();
		DiningRoomFood food = holder.getDiningRoomFood();
		
		foodMenuImage.setImage(new Image(getClass().getResourceAsStream("../img/DiningRoomFood/"+food.getFoodMenuImage())));
		tfFoodName.setText(food.getFoodMenuName());
		cobFoodCategory.setValue(food.getFoodMenuCategory());
		tfPrice.setText(food.getFoodMenuPrice().toString());
		this.id = food.getFoodMenuId();
		
	}
    	
}

