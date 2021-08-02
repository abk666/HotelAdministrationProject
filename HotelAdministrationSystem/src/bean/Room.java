package bean;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Room {
	private IntegerProperty roomId;
	private StringProperty roomType;
	private IntegerProperty roomNumber;
	private DoubleProperty roomPrice;
	private StringProperty roomStatus;
	
	public Room(Integer roomId,String roomType, Integer roomNumber,  Double roomPrice,
			String roomStatus) {
		super();
		this.roomId = new SimpleIntegerProperty(roomId);
		this.roomType = new SimpleStringProperty(roomType);
		this.roomNumber = new SimpleIntegerProperty(roomNumber);
		this.roomPrice = new SimpleDoubleProperty(roomPrice);
		this.roomStatus = new SimpleStringProperty(roomStatus);
	}
	public Room(String roomType,Integer roomNumber,  Double roomPrice, String roomStatus) {
		super();
		
		this.roomType = new SimpleStringProperty(roomType);
		this.roomNumber = new SimpleIntegerProperty(roomNumber);
		this.roomPrice = new SimpleDoubleProperty(roomPrice);
		this.roomStatus = new SimpleStringProperty(roomStatus);
	}
	public Integer getRoomId() {
		return roomId.get();
	}
	public void setRoomId(Integer roomId) {
		this.roomId = new SimpleIntegerProperty(roomId);
	}
	
	public String getRoomType() {
		return roomType.get();
	}
	public void setRoomType(String roomType) {
		this.roomType = new SimpleStringProperty(roomType);
	}
	public Integer getRoomNumber() {
		return roomNumber.get();
	}
	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = new SimpleIntegerProperty(roomNumber);
	}
	public Double getRoomPrice() {
		return roomPrice.get();
	}
	public void setRoomPrice(Double roomPrice) {
		this.roomPrice = new SimpleDoubleProperty(roomPrice);
	}
	public String getRoomStatus() {
		return roomStatus.get();
	}
	public void setRoomStatus(String roomStatus) {
		this.roomStatus = new SimpleStringProperty(roomStatus);
	}

}