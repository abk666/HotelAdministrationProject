package bean;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CheckOut {
	private IntegerProperty checkOutId;
	private StringProperty checkOutGuestName;
	private StringProperty checkOutGuestNRC;
	private StringProperty checkOutGuestPhNo;
	private IntegerProperty roomNo;
	private StringProperty checkOutDate;
	private DoubleProperty totalPrice;
	
	
	public CheckOut(Integer checkOutId, String checkOutGuestName, String checkOutGuestNRC, String checkOutGuestPhNo,
			Integer roomNo, String checkOutDate, Double totalPrice) {
		super();
		this.checkOutId = new SimpleIntegerProperty(checkOutId);
		this.checkOutGuestName = new SimpleStringProperty(checkOutGuestName);
		this.checkOutGuestNRC = new SimpleStringProperty(checkOutGuestNRC);
		this.checkOutGuestPhNo = new SimpleStringProperty(checkOutGuestPhNo);
		this.roomNo =  new SimpleIntegerProperty(roomNo);
		this.checkOutDate = new SimpleStringProperty(checkOutDate);
		this.totalPrice = new SimpleDoubleProperty(totalPrice);
	}
	
	public CheckOut(String checkOutGuestName, String checkOutGuestNRC, String checkOutGuestPhNo, Integer roomNo,
			String checkOutDate, Double totalPrice) {
		super();
		this.checkOutGuestName = new SimpleStringProperty(checkOutGuestName);
		this.checkOutGuestNRC = new SimpleStringProperty(checkOutGuestNRC);
		this.checkOutGuestPhNo = new SimpleStringProperty(checkOutGuestPhNo);
		this.roomNo =  new SimpleIntegerProperty(roomNo);
		this.checkOutDate = new SimpleStringProperty(checkOutDate);
		this.totalPrice = new SimpleDoubleProperty(totalPrice);
	}

	public Integer getCheckOutId() {
		return checkOutId.get();
	}
	public void setCheckOutId(Integer checkOutId) {
		this.checkOutId = new SimpleIntegerProperty(checkOutId);
	}
	public String getCheckOutGuestName() {
		return checkOutGuestName.get();
	}
	public void setCheckOutGuestName(String checkOutGuestName) {
		this.checkOutGuestName = new SimpleStringProperty(checkOutGuestName);
	}
	public String getCheckOutGuestNRC() {
		return checkOutGuestNRC.get();
	}
	public void setCheckOutGuestNRC(String checkOutGuestNRC) {
		this.checkOutGuestNRC = new SimpleStringProperty(checkOutGuestNRC);
	}
	public String getCheckOutGuestPhNo() {
		return checkOutGuestPhNo.get();
	}
	public void setCheckOutGuestPhNo(String checkOutGuestPhNo) {
		this.checkOutGuestPhNo = new SimpleStringProperty(checkOutGuestPhNo);
	}
	public Integer getRoomNo() {
		return roomNo.get();
	}
	public void setRoomNo(Integer roomNo) {
		this.roomNo = new SimpleIntegerProperty(roomNo);
	}
	public String getCheckOutDate() {
		return checkOutDate.get();
	}
	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = new SimpleStringProperty(checkOutDate);
	}
	public Double getTotalPrice() {
		return totalPrice.get();
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = new SimpleDoubleProperty(totalPrice);
	}
	
}
