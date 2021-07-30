package bean;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Guest {
	public IntegerProperty guestId;
	public StringProperty guestName;
	public StringProperty guestNRC;
	public IntegerProperty noOfGuests;
	public StringProperty guestPhNo;
	public StringProperty guestRoomType;
	public IntegerProperty guestRoomNo;
	public DoubleProperty guestRoomPrice;
	public DoubleProperty foodOrderPrice;
	public DoubleProperty guestInRoomCost;
	public StringProperty guestCheckInDate;
	public StringProperty guestCheckOutDate;
	public IntegerProperty numberOfDays;
	public StringProperty guestStatus;
;
	public Guest(Integer guestId, String guestName, String guestNRC, Integer noOfGuests, String guestPhNo,
			String guestRoomType, Integer guestRoomNo, Double guestRoomPrice, Double foodOrderPrice,
			Double guestInRoomCost, String guestCheckInDate, String guestCheckOutDate, Integer numberOfDays,String guestStatus) {
		super();
		this.guestId = new SimpleIntegerProperty(guestId);
		this.guestName =new SimpleStringProperty(guestName);
		this.guestNRC = new SimpleStringProperty(guestNRC);
		this.noOfGuests =  new SimpleIntegerProperty(noOfGuests);
		this.guestPhNo = new SimpleStringProperty(guestPhNo);
		this.guestRoomType = new SimpleStringProperty(guestRoomType);
		this.guestRoomNo =  new SimpleIntegerProperty(guestRoomNo);
		this.guestRoomPrice = new SimpleDoubleProperty(guestRoomPrice);
		this.foodOrderPrice = new SimpleDoubleProperty(foodOrderPrice);
		this.guestInRoomCost = new SimpleDoubleProperty(guestInRoomCost);
		this.guestCheckInDate = new SimpleStringProperty(guestCheckInDate);
		this.guestCheckOutDate =new SimpleStringProperty(guestCheckOutDate);
		this.numberOfDays =  new SimpleIntegerProperty(numberOfDays);
		this.guestStatus=new SimpleStringProperty(guestStatus);
	}
	public Guest(String guestName, String guestNRC, Integer noOfGuests, String guestPhNo, String guestRoomType,
			Integer guestRoomNo, Double guestRoomPrice, Double foodOrderPrice, Double guestInRoomCost,
			String guestCheckInDate, String guestCheckOutDate, Integer numberOfDays,String guestStatus) {
		super();
		this.guestName =new SimpleStringProperty(guestName);
		this.guestNRC = new SimpleStringProperty(guestNRC);
		this.noOfGuests =  new SimpleIntegerProperty(noOfGuests);
		this.guestPhNo = new SimpleStringProperty(guestPhNo);
		this.guestRoomType = new SimpleStringProperty(guestRoomType);
		this.guestRoomNo =  new SimpleIntegerProperty(guestRoomNo);
		this.guestRoomPrice = new SimpleDoubleProperty(guestRoomPrice);
		this.foodOrderPrice = new SimpleDoubleProperty(foodOrderPrice);
		this.guestInRoomCost = new SimpleDoubleProperty(guestInRoomCost);
		this.guestCheckInDate = new SimpleStringProperty(guestCheckInDate);
		this.guestCheckOutDate =new SimpleStringProperty(guestCheckOutDate);
		this.numberOfDays =  new SimpleIntegerProperty(numberOfDays);
		this.guestStatus=new SimpleStringProperty(guestStatus);
	}
	
	
	
	
	public Integer getGuestId() {
		return guestId.get();
	}
	public void setGuestId(Integer guestId) {
		this.guestId = new SimpleIntegerProperty(guestId);
	}
	public String getGuestName() {
		return guestName.get();
	}
	public void setGuestName(String guestName) {
		this.guestName =new SimpleStringProperty(guestName);
	}
	public String getGuestNRC() {
		return guestNRC.get();
	}
	public void setGuestNRC(String guestNRC) {
		this.guestNRC = new SimpleStringProperty(guestNRC);
	}
	public Integer getNoOfGuests() {
		return noOfGuests.get();
	}
	public void setNoOfGuests(Integer noOfGuests) {
		this.noOfGuests =  new SimpleIntegerProperty(noOfGuests);
	}
	public String getGuestPhNo() {
		return guestPhNo.get();
	}
	public void setGuestPhNo(String guestPhNo) {
		this.guestPhNo = new SimpleStringProperty(guestPhNo);
	}
	public String getGuestRoomType() {
		return guestRoomType.get();
	}
	public void setGuestRoomType(String guestRoomType) {
		this.guestRoomType = new SimpleStringProperty(guestRoomType);
	}
	public Integer getGuestRoomNo() {
		return guestRoomNo.get();
	}
	public void setGuestRoomNo(Integer guestRoomNo) {
		this.guestRoomNo =  new SimpleIntegerProperty(guestRoomNo);
	}
	public Double getGuestRoomPrice() {
		return guestRoomPrice.get();
	}
	public void setGuestRoomPrice(Double guestRoomPrice) {
		this.guestRoomPrice = new SimpleDoubleProperty(guestRoomPrice);
	}
	public Double getFoodOrderPrice() {
		return foodOrderPrice.get();
	}
	public void setFoodOrderPrice(Double foodOrderPrice) {
		this.foodOrderPrice = new SimpleDoubleProperty(foodOrderPrice);
	}
	public Double getGuestInRoomCost() {
		return guestInRoomCost.get();
	}
	public void setGuestInRoomCost(Double guestInRoomCost) {
		this.guestInRoomCost = new SimpleDoubleProperty(guestInRoomCost);
	}
	public String getGuestCheckInDate() {
		return guestCheckInDate.get();
	}
	public void setGuestCheckInDate(String guestCheckInDate) {
		this.guestCheckInDate = new SimpleStringProperty(guestCheckInDate);
	}
	public String getGuestCheckOutDate() {
		return guestCheckOutDate.get();
	}
	public void setGuestCheckOutDate(String guestCheckOutDate) {
		this.guestCheckOutDate =new SimpleStringProperty(guestCheckOutDate);
	}
	public Integer getNumberOfDays() {
		return numberOfDays.get();
	}
	public void setNumberOfDays(Integer numberOfDays) {
		this.numberOfDays =  new SimpleIntegerProperty(numberOfDays);
	}
	public String getGuestStatus() {
		return guestStatus.get();
	}
	public void setGuestStatus(String guestStatus) {
		this.guestStatus = new SimpleStringProperty(guestStatus);
	}

	
	
	
	
}
