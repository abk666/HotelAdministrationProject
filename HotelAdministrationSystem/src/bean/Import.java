package bean;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Import {
	private IntegerProperty importId;
	private StringProperty importItemName;
	private StringProperty importItemCategory;
	private DoubleProperty importItemPrice;
	private IntegerProperty importItemQty;
	private StringProperty importItemUnit;
	private StringProperty importDate;
	private SimpleStringProperty importItemExpiredDate;
	private StringProperty accountantUserName;
	private DoubleProperty totalPrice;
	private StringProperty itemStatus;

	public Import(String importItemName,Integer importItemQty) {
		super();
		this.importItemName = new SimpleStringProperty(importItemName);
		this.importItemQty = new SimpleIntegerProperty(importItemQty);

		
	}
	public Import(String importItemName, String importItemCategory, Double importItemPrice, Integer importItemQty,
			String importItemUnit, String importDate, String importItemExpiredDate, String accountantUserName,Double totalPrice,String itemStatus) {
		super();
		this.importItemName = new SimpleStringProperty(importItemName);
		this.importItemCategory =new SimpleStringProperty(importItemCategory) ;
		this.importItemPrice = new SimpleDoubleProperty(importItemPrice);
		this.importItemQty = new SimpleIntegerProperty(importItemQty);
		this.importItemUnit =new SimpleStringProperty(importItemUnit) ;
		this.importDate =new SimpleStringProperty(importDate) ;
		this.importItemExpiredDate =new SimpleStringProperty(importItemExpiredDate) ;
		this.accountantUserName =new SimpleStringProperty(accountantUserName) ;
		this.totalPrice=new SimpleDoubleProperty(totalPrice);
		this.itemStatus=new SimpleStringProperty(itemStatus);
	}
	public Import(Integer importId, String importItemName, String importItemCategory, Double importItemPrice,
			Integer importItemQty, String importItemUnit, String importDate, String importItemExpiredDate,
			String accountantUserName,Double totalPrice,String itemStatus) {
		super();
		this.importId = new SimpleIntegerProperty(importId);
		this.importItemName = new SimpleStringProperty(importItemName);
		this.importItemCategory =new SimpleStringProperty(importItemCategory) ;
		this.importItemPrice = new SimpleDoubleProperty(importItemPrice);
		this.importItemQty = new SimpleIntegerProperty(importItemQty);
		this.importItemUnit =new SimpleStringProperty(importItemUnit) ;
		this.importDate =new SimpleStringProperty(importDate) ;
		this.importItemExpiredDate =new SimpleStringProperty(importItemExpiredDate) ;
		this.accountantUserName =new SimpleStringProperty(accountantUserName) ;
		this.totalPrice=new SimpleDoubleProperty(totalPrice);
		this.itemStatus=new SimpleStringProperty(itemStatus);

	}


	public Integer getImportId() {
		return importId.get();
	}
	public void setImportId(Integer importId) {
		this.importId = new SimpleIntegerProperty(importId);;
	}
	public String getImportItemName() {
		return importItemName.get();
	}
	public void setImportItemName(String importItemName) {
		this.importItemName = new SimpleStringProperty(importItemName);
	}
	public String getImportItemCategory() {
		return importItemCategory.get();
	}
	public void setImportItemCategory(String importItemCategory) {
		this.importItemCategory = new SimpleStringProperty(importItemCategory) ;
	}
	public Double getImportItemPrice() {
		return importItemPrice.get();
	}
	public void setImportItemPrice(Double importItemPrice) {
		this.importItemPrice =  new SimpleDoubleProperty(importItemPrice);
	}
	public Integer getImportItemQty() {
		return importItemQty.get();
	}
	public void setImportItemQty(Integer importItemQty) {
		this.importItemQty = new SimpleIntegerProperty(importItemQty);
	}
	public String getImportItemUnit() {
		return importItemUnit.get();
	}
	public void setImportItemUnit(String importItemUnit) {
		this.importItemUnit = new SimpleStringProperty(importItemUnit);
	}
	public String getImportDate() {
		return importDate.get();
	}
	public void setImportDate(String importDate) {
		this.importDate = new SimpleStringProperty(importDate);
	}
	public String getImportItemExpiredDate() {
		return importItemExpiredDate.get();
	}
	public void setImportItemExpiredDate(String importItemExpiredDate) {
		this.importItemExpiredDate = new SimpleStringProperty(importItemExpiredDate);
	}
	public String getAccountantUserName() {
		return accountantUserName.get();
	}
	public void setAccountantUserName(String accountantUserName) {
		this.accountantUserName = new SimpleStringProperty(accountantUserName);
	}

	public Double getTotalPrice() {
		return totalPrice.get();
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = new SimpleDoubleProperty(totalPrice);
	}
	public String getItemStatus() {
		return itemStatus.get();
	}
	public void setItemStatus(String itemStatus) {
		this.itemStatus = new SimpleStringProperty(itemStatus);
	}
}
