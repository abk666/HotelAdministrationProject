package dining_room;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import bean.DiningRoomFood;
import bean.DiningRoomFoodHolder;
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

public class FoodMenuProfileController implements Initializable{

    @FXML
    private ImageView foodMenuImage;

    @FXML
    private Label lblFoodMenuName;

    @FXML
    private Label lblFoodMenuCategory;

    @FXML
    private Label lblFoodMenuPrice;
    
    @FXML
    void processBack(MouseEvent event) throws IOException {
    	
    	Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	primaryStage.setResizable(false);
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("FoodMenuListUI.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("FoodMenuListUI");
		primaryStage.setScene(scene);
		primaryStage.show();
    	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		DiningRoomFoodHolder holder = DiningRoomFoodHolder.getDiningRoomFoodInstance();
		DiningRoomFood diningRoomFood = holder.getDiningRoomFood();
		
		
		foodMenuImage.setImage(new Image(getClass().getResourceAsStream("../img/DiningRoomFood/"+diningRoomFood.getFoodMenuImage())));
		
		lblFoodMenuName.setText(diningRoomFood.getFoodMenuName());
		lblFoodMenuCategory.setText(diningRoomFood.getFoodMenuCategory());
		lblFoodMenuPrice.setText(diningRoomFood.getFoodMenuPrice().toString());
	}

}
