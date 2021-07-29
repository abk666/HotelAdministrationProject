package bean;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Admin {
	
	private IntegerProperty id;
	private StringProperty firstName;
	private StringProperty lastName;
	private StringProperty username;
	private StringProperty email;
	private StringProperty password;
	private StringProperty phone;
	private StringProperty address;
	private StringProperty dob;
	private StringProperty status;
	private StringProperty imageName;
	
	
	public Admin(Integer id, String firstName, String lastName, String username, String email, String password,
			String phone, String address, String dob, String status, String imageName) {
		super();
		this.id =new SimpleIntegerProperty(id);
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.username =new SimpleStringProperty(username);
		this.email = new SimpleStringProperty(email);
		this.password =new SimpleStringProperty(password);
		this.phone =new SimpleStringProperty(phone);
		this.address =new SimpleStringProperty(address);
		this.dob = new SimpleStringProperty(dob);
		this.status = new SimpleStringProperty(status);
		this.imageName =new SimpleStringProperty(imageName);
	}


	public Admin(String firstName, String lastName, String username, String email, String password, String phone,
			String address, String dob, String status, String imageName) {
		super();
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.username =new SimpleStringProperty(username);
		this.email = new SimpleStringProperty(email);
		this.password =new SimpleStringProperty(password);
		this.phone = new SimpleStringProperty(phone);
		this.address =new SimpleStringProperty(address);
		this.dob = new SimpleStringProperty(dob);
		this.status = new SimpleStringProperty(status);
		this.imageName =new SimpleStringProperty(imageName);
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
		this.lastName =new SimpleStringProperty(lastName);
	}


	public String getUsername() {
		return username.get();
	}


	public void setUsername(String username) {
		this.username = new SimpleStringProperty(username);
	}


	public String getEmail() {
		return email.get();
	}


	public void setEmail(String email) {
		this.email = new SimpleStringProperty(email);
	}


	public String getPassword() {
		return password.get();
	}


	public void setPassword(String password) {
		this.password =new SimpleStringProperty(password);
	}


	public String getPhone() {
		return phone.get();
	}


	public void setPhone(String phone) {
		this.phone = new SimpleStringProperty(phone);
	}


	public String getAddress() {
		return address.get();
	}


	public void setAddress(String address) {
		this.address = new SimpleStringProperty(address);
	}


	public String getDob() {
		return dob.get();
	}


	public void setDob(String dob) {
		this.dob = new SimpleStringProperty(dob);
	}


	public String getStatus() {
		return status.get();
	}


	public void setStatus(String status) {
		this.status = new SimpleStringProperty(status);
	}


	public String getImageName() {
		return imageName.get();
	}


	public void setImageName(String imageName) {
		this.imageName =new SimpleStringProperty(imageName);
	}
	
	
}
//>>>>>>> branch 'master' of https://github.com/abk666/HotelAdministrationProject.git
