package bean;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Booking {
	private IntegerProperty bookingId;
	private StringProperty guestName;
	private StringProperty guestPhNo;
	private StringProperty roomType;
	private IntegerProperty roomNo;
	private DoubleProperty roomPrice;
	private IntegerProperty noOfGuest;
	private StringProperty bookedDate;
	private StringProperty checkInDate;
	private IntegerProperty numberOfDays;
	private StringProperty checkOutDate;
	public Booking(Integer bookingId, String guestName, String guestPhNo, String roomType, Integer roomNo,
			Double roomPrice, Integer noOfGuest, String bookedDate, String checkInDate, Integer numberOfDays,
			String checkOutDate) {
		super();
		this.bookingId = new SimpleIntegerProperty(bookingId);
		this.guestName = new SimpleStringProperty(guestName);
		this.guestPhNo =new SimpleStringProperty(guestPhNo);
		this.roomType = new SimpleStringProperty(roomType);
		this.roomNo = new SimpleIntegerProperty(roomNo);
		this.roomPrice =new SimpleDoubleProperty(roomPrice);
		this.noOfGuest = new SimpleIntegerProperty(noOfGuest);
		this.bookedDate = new SimpleStringProperty(bookedDate);
		this.checkInDate = new SimpleStringProperty(checkInDate);
		this.numberOfDays = new SimpleIntegerProperty(numberOfDays);
		this.checkOutDate =new SimpleStringProperty(checkOutDate);
	}
	public Booking(String guestName, String guestPhNo, String roomType, Integer roomNo, Double roomPrice,
			Integer noOfGuest, String bookedDate, String checkInDate, Integer numberOfDays, String checkOutDate) {
		super();
		this.guestName = new SimpleStringProperty(guestName);
		this.guestPhNo =new SimpleStringProperty(guestPhNo);
		this.roomType = new SimpleStringProperty(roomType);
		this.roomNo = new SimpleIntegerProperty(roomNo);
		this.roomPrice =new SimpleDoubleProperty(roomPrice);
		this.noOfGuest = new SimpleIntegerProperty(noOfGuest);
		this.bookedDate = new SimpleStringProperty(bookedDate);
		this.checkInDate = new SimpleStringProperty(checkInDate);
		this.numberOfDays = new SimpleIntegerProperty(numberOfDays);
		this.checkOutDate =new SimpleStringProperty(checkOutDate);
	}
	public Integer getBookingId() {
		return bookingId.get();
	}
	public void setBookingId(Integer bookingId) {
		this.bookingId = new SimpleIntegerProperty(bookingId);
	}
	public String getGuestName() {
		return guestName.get();
	}
	public void setGuestName(String guestName) {
		this.guestName = new SimpleStringProperty(guestName);
	}
	public String getGuestPhNo() {
		return guestPhNo.get();
	}
	public void setGuestPhNo(String guestPhNo) {
		this.guestPhNo =new SimpleStringProperty(guestPhNo);
	}
	public String getRoomType() {
		return roomType.get();
	}
	public void setRoomType(String roomType) {
		this.roomType = new SimpleStringProperty(roomType);
	}
	public Integer getRoomNo() {
		return roomNo.get();
	}
	public void setRoomNo(Integer roomNo) {
		this.roomNo = new SimpleIntegerProperty(roomNo);
	}
	public Double getRoomPrice() {
		return roomPrice.get();
	}
	public void setRoomPrice(Double roomPrice) {
		this.roomPrice =new SimpleDoubleProperty(roomPrice);
	}
	public Integer getNoOfGuest() {
		return noOfGuest.get();
	}
	public void setNoOfGuest(Integer noOfGuest) {
		this.noOfGuest = new SimpleIntegerProperty(noOfGuest);
	}
	public String getBookedDate() {
		return bookedDate.get();
	}
	public void setBookedDate(String bookedDate) {
		this.bookedDate = new SimpleStringProperty(bookedDate);
	}
	public String getCheckInDate() {
		return checkInDate.get();
	}
	public void setCheckInDate(String checkInDate) {
		this.checkInDate = new SimpleStringProperty(checkInDate);
	}
	public Integer getNumberOfDays() {
		return numberOfDays.get();
	}
	public void setNumberOfDays(Integer numberOfDays) {
		this.numberOfDays = new SimpleIntegerProperty(numberOfDays);
	}
	public String getCheckOutDate() {
		return checkOutDate.get();
	}
	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate =new SimpleStringProperty(checkOutDate);
	}
	
	
}
