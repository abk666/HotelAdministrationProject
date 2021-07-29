package bean;

public class BookingHolder {
private Booking booking;

	private final static BookingHolder BOOKING_INSTANCE=new BookingHolder();

	public BookingHolder() {
		// TODO Auto-generated constructor stub
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public static BookingHolder getBookingInstance() {
		return BOOKING_INSTANCE;
	}
	
}
