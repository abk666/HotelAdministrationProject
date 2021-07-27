package bean;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RefrigeratorFood {
	
	private IntegerProperty itemId;
	private StringProperty itemName;
	private StringProperty itemCategory;
	private DoubleProperty itemPrice;
	private IntegerProperty itemQty;
	private StringProperty itemImage;
	
	public RefrigeratorFood(Integer itemId, String itemName, String itemCategory, Double itemPrice, Integer itemQty,
			String itemImage) {
		super();
		this.itemId = new SimpleIntegerProperty(itemId);
		this.itemName = new SimpleStringProperty(itemName);
		this.itemCategory = new SimpleStringProperty(itemCategory);
		this.itemPrice = new SimpleDoubleProperty(itemPrice);
		this.itemQty = new SimpleIntegerProperty(itemQty);
		this.itemImage = new SimpleStringProperty(itemImage);
	}

	public RefrigeratorFood(String itemName, String itemCategory, Double itemPrice, Integer itemQty, String itemImage) {
		super();
		this.itemName = new SimpleStringProperty(itemName);
		this.itemCategory = new SimpleStringProperty(itemCategory);
		this.itemPrice = new SimpleDoubleProperty(itemPrice);
		this.itemQty = new SimpleIntegerProperty(itemQty);
		this.itemImage = new SimpleStringProperty(itemImage);
	}

	public Integer getItemId() {
		return itemId.get();
	}

	public void setItemId(Integer itemId) {
		this.itemId = new SimpleIntegerProperty(itemId);
	}

	public String getItemName() {
		return itemName.get();
	}

	public void setItemName(String itemName) {
		this.itemName = new SimpleStringProperty(itemName);
	}

	public String getItemCategory() {
		return itemCategory.get();
	}

	public void setItemCategory(String itemCategory) {
		this.itemCategory = new SimpleStringProperty(itemCategory);
	}

	public Double getItemPrice() {
		return itemPrice.get();
	}

	public void setItemPrice(Double itemPrice) {
		this.itemPrice = new SimpleDoubleProperty(itemPrice);
	}

	public Integer getItemQty() {
		return itemQty.get();
	}

	public void setItemQty(Integer itemQty) {
		this.itemQty = new SimpleIntegerProperty(itemQty);
	}

	public String getItemImage() {
		return itemImage.get();
	}

	public void setItemImage(String itemImage) {
		this.itemImage = new SimpleStringProperty(itemImage);
	}
	

}
