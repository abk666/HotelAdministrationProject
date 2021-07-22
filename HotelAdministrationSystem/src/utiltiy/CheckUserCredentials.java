package utiltiy;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.DbConnection;

public class CheckUserCredentials {
	
private Connection connection;
	
	private Statement statement;
	
	private ResultSet resultSet;
	
	private final DbConnection dbconnection = new DbConnection();
	
	Boolean isLoginOk = false;
	
	public Boolean isUserValid(String email,String password,String role,String loginType) throws SQLException {
		
		
		
		connection = dbconnection.getConnection();
		
		statement = connection.createStatement();
		
		if(role=="Admin") {
			resultSet= statement.executeQuery("select admin"+loginType+",adminPassword from admin where admin"+loginType+" = '"+email+"' and "+"adminPassword = '"+password+"';");
			
		}else if(role=="Manager") {
			resultSet= statement.executeQuery("select staff"+loginType+",staffPassword from staff where staff"+loginType+" = '"+email+" ,staffPassword = '"+password+"' and "+"staffRoll = '"+role+"';");
			
		}else {
			
		}
	
		if(resultSet.next()) {
		isLoginOk = true;
		}
		
		connection.close();

		return isLoginOk;
	}
	

}
