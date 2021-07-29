package bean;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class InRoomCost {
private IntegerProperty inRoomItemId;
private StringProperty inRoomItemName;
private StringProperty inRoomItemCategory;
private DoubleProperty inRoomItemPrice;
private IntegerProperty inRoomItemQuantity;
private IntegerProperty guestRoomNo;
private DoubleProperty inRoomTotalCost;
public InRoomCost(Integer inRoomItemId, String inRoomItemName, String inRoomItemCategory, Double inRoomItemPrice,
		Integer inRoomItemQuantity, Integer guestRoomNo,Double inRoomTotalCost) {
	super();
	this.inRoomItemId = new SimpleIntegerProperty(inRoomItemId);
	this.inRoomItemName = new SimpleStringProperty(inRoomItemName);
	this.inRoomItemCategory = new SimpleStringProperty(inRoomItemCategory);
	this.inRoomItemPrice = new SimpleDoubleProperty(inRoomItemPrice);
	this.inRoomItemQuantity = new SimpleIntegerProperty(inRoomItemQuantity);
	this.guestRoomNo = new SimpleIntegerProperty(guestRoomNo);
	this.inRoomTotalCost=new SimpleDoubleProperty(inRoomTotalCost);
}
public InRoomCost(String inRoomItemName, String inRoomItemCategory, Double inRoomItemPrice, Integer inRoomItemQuantity,
		Integer guestRoomNo,Double inRoomTotalCost) {
	super();
	this.inRoomItemName = new SimpleStringProperty(inRoomItemName);
	this.inRoomItemCategory = new SimpleStringProperty(inRoomItemCategory);
	this.inRoomItemPrice = new SimpleDoubleProperty(inRoomItemPrice);
	this.inRoomItemQuantity = new SimpleIntegerProperty(inRoomItemQuantity);
	this.guestRoomNo = new SimpleIntegerProperty(guestRoomNo);
	this.inRoomTotalCost=new SimpleDoubleProperty(inRoomTotalCost);
}
public Integer getInRoomItemId() {
	return inRoomItemId.get();
}
public void setInRoomItemId(Integer inRoomItemId) {
	this.inRoomItemId = new SimpleIntegerProperty(inRoomItemId);
}
public String getInRoomItemName() {
	return inRoomItemName.get();
}
public void setInRoomItemName(String inRoomItemName) {
	this.inRoomItemName = new SimpleStringProperty(inRoomItemName);
}
public String getInRoomItemCategory() {
	return inRoomItemCategory.get();
}
public void setInRoomItemCategory(String inRoomItemCategory) {
	this.inRoomItemCategory = new SimpleStringProperty(inRoomItemCategory);
}
public Double getInRoomItemPrice() {
	return inRoomItemPrice.get();
}
public void setInRoomItemPrice(Double inRoomItemPrice) {
	this.inRoomItemPrice = new SimpleDoubleProperty(inRoomItemPrice);
}
public Integer getInRoomItemQuantity() {
	return inRoomItemQuantity.get();
}
public void setInRoomItemQuantity(Integer inRoomItemQuantity) {
	this.inRoomItemQuantity =  new SimpleIntegerProperty(inRoomItemQuantity);
}
public Integer getGuestRoomNo() {
	return guestRoomNo.get();
}
public void setGuestRoomNo(Integer guestRoomNo) {
	this.guestRoomNo = new SimpleIntegerProperty(guestRoomNo);
}
public Double getInRoomTotalCost() {
	return inRoomTotalCost.get();
}
public void setInRoomTotalCost(Double inRoomTotalCost) {
	this.inRoomTotalCost = new SimpleDoubleProperty(inRoomTotalCost);
}




}
