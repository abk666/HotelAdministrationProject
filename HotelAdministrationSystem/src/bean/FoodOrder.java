package bean;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FoodOrder {
	
	private IntegerProperty foodOrderId;
	private StringProperty foodOrderName;
	private DoubleProperty foodOrderPrice;
	private StringProperty foodOrderDate;
	private IntegerProperty foodOrderQty;
	private IntegerProperty guestRoomNo;
	private DoubleProperty foodOrderTotalPrice;
	
	public FoodOrder(Integer foodOrderId, String foodOrderName, Double foodOrderPrice, String foodOrderDate,
			Integer foodOrderQty, Integer guestRoomNo, Double foodOrderTotalPrice) {
		super();
		this.foodOrderId = new SimpleIntegerProperty(foodOrderId);
		this.foodOrderName = new SimpleStringProperty(foodOrderName);
		this.foodOrderPrice = new SimpleDoubleProperty(foodOrderPrice);
		this.foodOrderDate = new SimpleStringProperty(foodOrderDate);
		this.foodOrderQty = new SimpleIntegerProperty(foodOrderQty);
		this.guestRoomNo = new SimpleIntegerProperty(guestRoomNo);
		this.foodOrderTotalPrice = new SimpleDoubleProperty(foodOrderTotalPrice);
	}
	public FoodOrder(String foodOrderName, Double foodOrderPrice, String foodOrderDate, Integer foodOrderQty,
			Integer guestRoomNo, Double foodOrderTotalPrice) {
		super();
		this.foodOrderName = new SimpleStringProperty(foodOrderName);
		this.foodOrderPrice = new SimpleDoubleProperty(foodOrderPrice);
		this.foodOrderDate = new SimpleStringProperty(foodOrderDate);
		this.foodOrderQty = new SimpleIntegerProperty(foodOrderQty);
		this.guestRoomNo = new SimpleIntegerProperty(guestRoomNo);
		this.foodOrderTotalPrice = new SimpleDoubleProperty(foodOrderTotalPrice);
	}
	public Integer getFoodOrderId() {
		return foodOrderId.get();
	}
	public void setFoodOrderId(Integer foodOrderId) {
		this.foodOrderId = new SimpleIntegerProperty(foodOrderId);
	}
	public String getFoodOrderName() {
		return foodOrderName.get();
	}
	public void setFoodOrderName(String foodOrderName) {
		this.foodOrderName = new SimpleStringProperty(foodOrderName);
	}
	public Double getFoodOrderPrice() {
		return foodOrderPrice.get();
	}
	public void setFoodOrderPrice(Double foodOrderPrice) {
		this.foodOrderPrice = new SimpleDoubleProperty(foodOrderPrice);
	}
	public String getFoodOrderDate() {
		return foodOrderDate.get();
	}
	public void setFoodOrderDate(String foodOrderDate) {
		this.foodOrderDate = new SimpleStringProperty(foodOrderDate);
	}
	public Integer getFoodOrderQty() {
		return foodOrderQty.get();
	}
	public void setFoodOrderQty(Integer foodOrderQty) {
		this.foodOrderQty = new SimpleIntegerProperty(foodOrderQty);
	}
	public Integer getGuestRoomNo() {
		return guestRoomNo.get();
	}
	public void setGuestRoomNo(Integer guestRoomNo) {
		this.guestRoomNo = new SimpleIntegerProperty(guestRoomNo);
	}
	public Double getFoodOrderTotalPrice() {
		return foodOrderTotalPrice.get();
	}
	public void setFoodOrderTotalPrice(Double foodOrderTotalPrice) {
		this.foodOrderTotalPrice = new SimpleDoubleProperty(foodOrderTotalPrice);
	}

}
