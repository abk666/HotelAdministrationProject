package admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import bean.RefrigeratorFood;
import bean.RefrigeratorFoodHolder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class RefrigeratorFoodProfileController implements Initializable {

	@FXML
    private ImageView itemImage;

    @FXML
    private Label lblItemName;

    @FXML
    private Label lblItemCategory;

    @FXML
    private Label lblItemPrice;

    @FXML
    private Label lblItemQty;

    @FXML
    void processBack(MouseEvent event) throws IOException {
    	
    	Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	primaryStage.setResizable(false);
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("RefrigeratorFoodDetailsUI.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("RefrigeratorFoodUI");
		primaryStage.setScene(scene);
		primaryStage.show();
    	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		RefrigeratorFoodHolder holder = RefrigeratorFoodHolder.getRefrigeratorfoodInstance();
		RefrigeratorFood refrigeratorFood = holder.getRefrigeratorFood();
		
		
		itemImage.setImage(new Image(getClass().getResourceAsStream("../img/RefrigeratorFood/"+refrigeratorFood.getItemImage())));
		
		lblItemName.setText(refrigeratorFood.getItemName());
		lblItemCategory.setText(refrigeratorFood.getItemCategory());
		lblItemPrice.setText(refrigeratorFood.getItemPrice().toString());
		lblItemQty.setText(refrigeratorFood.getItemQty().toString());
	}
	
}
