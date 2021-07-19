package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

private String url="jdbc:mysql://localhost:3308/hoteldb?useSSL=false";
private String user="root";
private String password="12345";

public Connection getConnection() {
	Connection connection=null;
	try {
		connection=DriverManager.getConnection(url, user, password);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return connection;
}
}
