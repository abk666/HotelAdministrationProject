package bean;

public class StaffHolder {
	
	private Staff staff;
	
	private final static StaffHolder STAFF_INSTANCE = new StaffHolder();


	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public static StaffHolder getStaffInstance() {
		return STAFF_INSTANCE;
	}

}
