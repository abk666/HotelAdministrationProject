package bean;

public class BookingStatusHolder {
	private static String buttonStatus;
	private static Integer bookingId;

	public static String getButtonStatus() {
		return buttonStatus;
	}

	public static void setButtonStatus(String buttonStatus) {
		BookingStatusHolder.buttonStatus = buttonStatus;
	}

	public static Integer getBookingId() {
		return bookingId;
	}

	public static void setBookingId(Integer bookingId) {
		BookingStatusHolder.bookingId = bookingId;
	}
	
	
}
