package bean;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Room {
	public IntegerProperty roomId;
	public StringProperty roomType;
	public IntegerProperty roomNumber;
	public DoubleProperty roomPrice;
	public IntegerProperty roomMaxNo;
	public StringProperty roomStatus;
	public Room(Integer roomId,String roomType, Integer roomNumber,  Double roomPrice, Integer roomMaxNo,
			String roomStatus) {
		super();
		this.roomId = new SimpleIntegerProperty(roomId);
		this.roomType = new SimpleStringProperty(roomType);
		this.roomNumber = new SimpleIntegerProperty(roomNumber);
		this.roomPrice = new SimpleDoubleProperty(roomPrice);
		this.roomMaxNo = new SimpleIntegerProperty(roomMaxNo);
		this.roomStatus = new SimpleStringProperty(roomStatus);
	}
	public Room(String roomType,Integer roomNumber,  Double roomPrice, Integer roomMaxNo, String roomStatus) {
		super();
		
		this.roomType = new SimpleStringProperty(roomType);
		this.roomNumber = new SimpleIntegerProperty(roomNumber);
		this.roomPrice = new SimpleDoubleProperty(roomPrice);
		this.roomMaxNo = new SimpleIntegerProperty(roomMaxNo);
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
	public Integer getRoomMaxNo() {
		return roomMaxNo.get();
	}
	public void setRoomMaxNo(Integer roomMaxNo) {
		this.roomMaxNo = new SimpleIntegerProperty(roomMaxNo);
	}
	public String getRoomStatus() {
		return roomStatus.get();
	}
	public void setRoomStatus(String roomStatus) {
		this.roomStatus = new SimpleStringProperty(roomStatus);
	}

}
