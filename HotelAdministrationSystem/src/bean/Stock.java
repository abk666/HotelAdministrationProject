package bean;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Stock {
	private IntegerProperty stockId;
	private StringProperty itemName;
	private IntegerProperty itemStock;
	public Stock(Integer stockId, String itemName, Integer itemStock) {
		super();
		this.stockId = new SimpleIntegerProperty(stockId);
		this.itemName = new SimpleStringProperty(itemName);
		this.itemStock =  new SimpleIntegerProperty(itemStock);
	}
	public Stock(String itemName, Integer itemStock) {
		super();
		this.itemName = new SimpleStringProperty(itemName);
		this.itemStock =  new SimpleIntegerProperty(itemStock);
	}
	public Integer getStockId() {
		return stockId.get();
	}
	public void setStockId(Integer stockId) {
		this.stockId = new SimpleIntegerProperty(stockId);
	}
	public String getItemName() {
		return itemName.get();
	}
	public void setItemName(String itemName) {
		this.itemName =  new SimpleStringProperty(itemName);
	}
	public Integer getItemStock() {
		return itemStock.get();
	}
	public void setItemStock(Integer itemStock) {
		this.itemStock =  new SimpleIntegerProperty(itemStock);
	}
}
