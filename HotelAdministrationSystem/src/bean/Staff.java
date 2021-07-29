package bean;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Staff {

	private IntegerProperty staffId;
	private StringProperty staffFName;
	private StringProperty staffLName;
	private StringProperty staffUserName;
	private StringProperty staffEmail;
	private StringProperty staffPassword;
	private StringProperty staffRole;
	private StringProperty staffGender;
	private StringProperty staffPhNo;
	private StringProperty staffAddress;
	private StringProperty staffDOB;
	private StringProperty staffStatus;
	private StringProperty staffImageName;
	private DoubleProperty genderCount;
	private DoubleProperty  roleCount;
	
	public Staff(Integer staffId, String staffFName, String staffLName, String staffUserName, String staffEmail,
			String staffPassword, String staffRole, String staffGender, String staffPhNo, String staffAddress,
			String staffDOB, String staffStatus, String staffImageName) {
		super();
		this.staffId = new SimpleIntegerProperty(staffId);
		this.staffFName = new SimpleStringProperty(staffFName);
		this.staffLName = new SimpleStringProperty(staffLName);
		this.staffUserName = new SimpleStringProperty(staffUserName);
		this.staffEmail = new SimpleStringProperty(staffEmail);
		this.staffPassword = new SimpleStringProperty(staffPassword);
		this.staffRole = new SimpleStringProperty(staffRole);
		this.staffGender = new SimpleStringProperty(staffGender);
		this.staffPhNo = new SimpleStringProperty(staffPhNo);
		this.staffAddress = new SimpleStringProperty(staffAddress);
		this.staffDOB = new SimpleStringProperty(staffDOB);
		this.staffStatus = new SimpleStringProperty(staffStatus);
		this.staffImageName = new SimpleStringProperty(staffImageName);
	}

	public Staff(String staffFName, String staffLName, String staffUserName, String staffEmail, String staffPassword,
			String staffRole, String staffGender, String staffPhNo, String staffAddress, String staffDOB,
			String staffStatus, String staffImageName) {
		super();
		this.staffFName = new SimpleStringProperty(staffFName);
		this.staffLName = new SimpleStringProperty(staffLName);
		this.staffUserName = new SimpleStringProperty(staffUserName);
		this.staffEmail = new SimpleStringProperty(staffEmail);
		this.staffPassword = new SimpleStringProperty(staffPassword);
		this.staffRole = new SimpleStringProperty(staffRole);
		this.staffGender = new SimpleStringProperty(staffGender);
		this.staffPhNo = new SimpleStringProperty(staffPhNo);
		this.staffAddress = new SimpleStringProperty(staffAddress);
		this.staffDOB = new SimpleStringProperty(staffDOB);
		this.staffStatus = new SimpleStringProperty(staffStatus);
		this.staffImageName = new SimpleStringProperty(staffImageName);
	}
	
	

	public Staff(String staffGender, Double genderCount) {
		super();
		this.staffGender = new SimpleStringProperty(staffGender);
		this.genderCount = new SimpleDoubleProperty(genderCount);
	}

	

	public Staff(String staffRole, Double roleCount,Integer staffId) {
		super();
		this.staffRole = new SimpleStringProperty(staffRole);
		this.roleCount = new SimpleDoubleProperty(roleCount);
		this.staffId = new SimpleIntegerProperty(staffId);
	}

	public Integer getStaffId() {
		return staffId.get();
	}

	public void setStaffId(Integer staffId) {
		this.staffId = new SimpleIntegerProperty(staffId);
	}

	public String getStaffFName() {
		return staffFName.get();
	}

	public void setStaffFName(String staffFName) {
		this.staffFName = new SimpleStringProperty(staffFName);
	}

	public String getStaffLName() {
		return staffLName.get();
	}

	public void setStaffLName(String staffLName) {
		this.staffLName = new SimpleStringProperty(staffLName);
	}

	public String getStaffUserName() {
		return staffUserName.get();
	}

	public void setStaffUserName(String staffUserName) {
		this.staffUserName = new SimpleStringProperty(staffUserName);
	}

	public String getStaffEmail() {
		return staffEmail.get();
	}

	public void setStaffEmail(String staffEmail) {
		this.staffEmail = new SimpleStringProperty(staffEmail);
	}

	public String getStaffPassword() {
		return staffPassword.get();
	}

	public void setStaffPassword(String staffPassword) {
		this.staffPassword = new SimpleStringProperty(staffPassword);
	}

	public String getStaffRole() {
		return staffRole.get();
	}

	public void setStaffRole(String staffRole) {
		this.staffRole = new SimpleStringProperty(staffRole);
	}

	public String getStaffGender() {
		return staffGender.get();
	}

	public void setStaffGender(String staffGender) {
		this.staffGender = new SimpleStringProperty(staffGender);
	}

	public String getStaffPhNo() {
		return staffPhNo.get();
	}

	public void setStaffPhNo(String staffPhNo) {
		this.staffPhNo = new SimpleStringProperty(staffPhNo);
	}

	public String getStaffAddress() {
		return staffAddress.get();
	}

	public void setStaffAddress(String staffAddress) {
		this.staffAddress = new SimpleStringProperty(staffAddress);
	}

	public String getStaffDOB() {
		return staffDOB.get();
	}

	public void setStaffDOB(String staffDOB) {
		this.staffDOB = new SimpleStringProperty(staffDOB);
	}

	public String getStaffStatus() {
		return staffStatus.get();
	}

	public void setStaffStatus(String staffStatus) {
		this.staffStatus = new SimpleStringProperty(staffStatus);
	}

	public String getStaffImageName() {
		return staffImageName.get();
	}

	public void setStaffImageName(String staffImageName) {
		this.staffImageName = new SimpleStringProperty(staffImageName);
	}

	public Double getGenderCount() {
		return genderCount.get();
	}

	public void setGenderCount(Double genderCount) {
		this.genderCount = new SimpleDoubleProperty(genderCount);
	}

	public Double getRoleCount() {
		return roleCount.get();
	}

	public void setRoleCount(Double roleCount) {
		this.roleCount = new SimpleDoubleProperty(roleCount);
	}
	
	

}
