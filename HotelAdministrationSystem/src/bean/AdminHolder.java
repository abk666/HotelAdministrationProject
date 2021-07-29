package bean;

public class AdminHolder {
	
	private Admin admin;
	
	private final static AdminHolder ADMIN_INSTANCE = new AdminHolder();


	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public static AdminHolder getAdminInstance() {
		return ADMIN_INSTANCE;
	}

}
