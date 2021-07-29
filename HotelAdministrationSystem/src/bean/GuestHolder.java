package bean;

public class GuestHolder {
private Guest guest;

private final static GuestHolder GUEST_INSTANCE=new GuestHolder();

public GuestHolder() {
	// TODO Auto-generated constructor stub
}

public Guest getGuest() {
	return guest;
}

public void setGuest(Guest guest) {
	this.guest = guest;
}

public static GuestHolder getGuestInstance() {
	return GUEST_INSTANCE;
}
}
