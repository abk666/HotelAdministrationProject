package bean;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Toggle;

public class Staff {
	
	private IntegerProperty id;
	private StringProperty firstName;
	private StringProperty lastName;
	private StringProperty username;
	private StringProperty email;
	private StringProperty password;
	private StringProperty role;
	private StringProperty gender;
	private StringProperty phone;
	private StringProperty address;
	private StringProperty dob;
	private StringProperty status;
	private StringProperty imageName;
	private DoubleProperty genderCount;
	private DoubleProperty  roleCount;
	
	public Staff(Integer id, String firstName, String lastName, String username, String email, String password,
			String role, String gender, String phone, String address, String dob, String status, String imageName) {
		super();
		this.id = new SimpleIntegerProperty(id);
		this.firstName =new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.username = new SimpleStringProperty(username);
		this.email = new SimpleStringProperty(email) ;
		this.password = new SimpleStringProperty(password);
		this.role = new SimpleStringProperty(role);
		this.gender =new SimpleStringProperty(gender);
		this.phone =new SimpleStringProperty(phone);
		this.address =new SimpleStringProperty(address);
		this.dob =new SimpleStringProperty(dob);
		this.status =new SimpleStringProperty(status);
		this.imageName = new SimpleStringProperty(imageName);
	}


	public Staff(String firstName, String lastName, String username, String email, String password, String role,
			String gender, String phone, String address, String dob, String status, String imageName) {
		super();
		this.firstName =new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.username = new SimpleStringProperty(username);
		this.email = new SimpleStringProperty(email) ;
		this.password = new SimpleStringProperty(password);
		this.role = new SimpleStringProperty(role);
		this.gender =new SimpleStringProperty(gender);
		this.phone =new SimpleStringProperty(phone);
		this.address =new SimpleStringProperty(address);
		this.dob =new SimpleStringProperty(dob);
		this.status =new SimpleStringProperty(status);
		this.imageName = new SimpleStringProperty(imageName);
	}
	public Staff(String staffGender, Double genderCount) {
		super();
		this.gender = new SimpleStringProperty(staffGender);
		this.genderCount = new SimpleDoubleProperty(genderCount);
	}

	

	public Staff(String staffRole, Double roleCount,Integer staffId) {
		super();
		this.role = new SimpleStringProperty(staffRole);
		this.roleCount = new SimpleDoubleProperty(roleCount);
		this.id = new SimpleIntegerProperty(staffId);
	}


	public Integer getId() {
		return id.get();
	}


	public void setId(Integer id) {
		this.id = new SimpleIntegerProperty(id);
	}


	public String getFirstName() {
		return firstName.get();
	}


	public void setFirstName(String firstName) {
		this.firstName = new SimpleStringProperty(firstName);
	}


	public String getLastName() {
		return lastName.get();
	}


	public void setLastName(String lastName) {
		this.lastName = new SimpleStringProperty(lastName);
	}


	public String getUsername() {
		return username.get();
	}


	public void setUsername(String username) {
		this.username =new SimpleStringProperty(username); 
	}


	public String getEmail() {
		return email.get();
	}


	public void setEmail(String email) {
		this.email = new SimpleStringProperty(email) ;
	}


	public String getPassword() {
		return password.get();
	}


	public void setPassword(String password) {
		this.password =new SimpleStringProperty(password) ; 
	}


	public String getRole() {
		return role.get();
	}


	public void setRole(String role) {
		this.role = new SimpleStringProperty(role);
	}


	public String getGender() {
		return gender.get();
	}


	public void setGender(String gender) {
		this.gender =new SimpleStringProperty(gender);
	}


	public String getPhone() {
		return phone.get();
	}


	public void setPhone(String phone) {
		this.phone =  new SimpleStringProperty(phone);
	}


	public String getAddress() {
		return address.get();
	}


	public void setAddress(String address) {
		this.address =new SimpleStringProperty(address);
	}


	public String getDob() {
		return dob.get();
	}


	public void setDob(String dob) {
		this.dob =new SimpleStringProperty(dob);
	}


	public String getStatus() {
		return status.get();
	}


	public void setStatus(String status) {
		this.status =new SimpleStringProperty(status); 
	}


	public String getImageName() {
		return imageName.get();
	}


	public void setImageName(String imageName) {
		this.imageName = new SimpleStringProperty(imageName);
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
