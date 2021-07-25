package bean;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ItemUsage {
	private IntegerProperty itemUsageId;
	private StringProperty itemName;
	private IntegerProperty itemQty;
	private StringProperty itemUsageDate;
	public ItemUsage(String itemName, Integer itemQty, String itemUsageDate) {
		super();
		this.itemName =new SimpleStringProperty(itemName);
		this.itemQty = new SimpleIntegerProperty(itemQty);
		this.itemUsageDate = new SimpleStringProperty(itemUsageDate);
	}
	public ItemUsage(Integer itemUsageId, String itemName, Integer itemQty, String itemUsageDate) {
		super();
		this.itemUsageId = new SimpleIntegerProperty(itemUsageId);
		this.itemName =new SimpleStringProperty(itemName);
		this.itemQty = new SimpleIntegerProperty(itemQty);
		this.itemUsageDate = new SimpleStringProperty(itemUsageDate);
	}
	public Integer getItemUsageId() {
		return itemUsageId.get();
	}
	public void setItemUsageId(Integer itemUsageId) {
		this.itemUsageId = new SimpleIntegerProperty(itemUsageId);
	}
	public String getItemName() {
		return itemName.get();
	}
	public void setItemName(String itemName) {
		this.itemName = new SimpleStringProperty(itemName);
	}
	public Integer getItemQty() {
		return itemQty.get();
	}
	public void setItemQty(Integer itemQty) {
		this.itemQty = new SimpleIntegerProperty(itemQty);
	}
	public String getItemUsageDate() {
		return itemUsageDate.get();
	}
	public void setItemUsageDate(String itemUsageDate) {
		this.itemUsageDate = new SimpleStringProperty(itemUsageDate);
	}
}
