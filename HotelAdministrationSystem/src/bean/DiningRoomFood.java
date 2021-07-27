package bean;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DiningRoomFood {

	private IntegerProperty foodMenuId;
	private StringProperty foodMenuName;
	private StringProperty foodMenuCategory;
	private DoubleProperty foodMenuPrice;
	private StringProperty foodMenuImage;
	
	public DiningRoomFood(Integer foodMenuId, String foodMenuName, String foodMenuCategory, Double foodMenuPrice,
			String foodMenuImage) {
		super();
		this.foodMenuId = new SimpleIntegerProperty(foodMenuId);
		this.foodMenuName = new SimpleStringProperty(foodMenuName);
		this.foodMenuCategory = new SimpleStringProperty(foodMenuCategory);
		this.foodMenuPrice = new SimpleDoubleProperty(foodMenuPrice);
		this.foodMenuImage = new SimpleStringProperty(foodMenuImage);
	}

	public DiningRoomFood(String foodMenuName, String foodMenuCategory, Double foodMenuPrice, String foodMenuImage) {
		super();
		this.foodMenuName = new SimpleStringProperty(foodMenuName);
		this.foodMenuCategory = new SimpleStringProperty(foodMenuCategory);
		this.foodMenuPrice = new SimpleDoubleProperty(foodMenuPrice);
		this.foodMenuImage = new SimpleStringProperty(foodMenuImage);
	}

	public Integer getFoodMenuId() {
		return foodMenuId.get();
	}

	public void setFoodMenuId(Integer foodMenuId) {
		this.foodMenuId = new SimpleIntegerProperty(foodMenuId);
	}

	public String getFoodMenuName() {
		return foodMenuName.get();
	}

	public void setFoodMenuName(String foodMenuName) {
		this.foodMenuName = new SimpleStringProperty(foodMenuName);
	}

	public String getFoodMenuCategory() {
		return foodMenuCategory.get();
	}

	public void setFoodMenuCategory(String foodMenuCategory) {
		this.foodMenuCategory = new SimpleStringProperty(foodMenuCategory);
	}

	public Double getFoodMenuPrice() {
		return foodMenuPrice.get();
	}

	public void setFoodMenuPrice(Double foodMenuPrice) {
		this.foodMenuPrice = new SimpleDoubleProperty(foodMenuPrice);
	}

	public String getFoodMenuImage() {
		return foodMenuImage.get();
	}

	public void setFoodMenuImage(String foodMenuImage) {
		this.foodMenuImage = new SimpleStringProperty(foodMenuImage);
	}	
	
}
