package utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import bean.Staff;
import database1.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ManagerViewStaffDataUtils {

	private Connection connection;
	private Statement statement;
	private PreparedStatement preStmt;
	private ResultSet resultSet;
		
	private final DBConnection dbConnection = new DBConnection();
	
	//Read R
	public ObservableList<Staff>getAllStaff(String sql) throws SQLException{
		
		ObservableList<Staff> staffList=FXCollections.observableArrayList();
	
		connection = dbConnection.getConnection();
		statement = connection.createStatement();
		resultSet = statement.executeQuery(sql);
		
		while (resultSet.next()) {
			
			staffList.add(new Staff(resultSet.getInt("staffId"),
					resultSet.getString("staffFName"), 
					resultSet.getString("staffLName"), 
					resultSet.getString("staffUserName"), 
					resultSet.getString("staffEmail"), 
					resultSet.getString("staffPassword"), 
					resultSet.getString("staffRole"), 
					resultSet.getString("staffGender"), 
					resultSet.getString("staffPhNo"), 
					resultSet.getString("staffAddress"), 
					resultSet.getDate("staffDOB").toString(), 
					resultSet.getString("staffStatus"), 
					resultSet.getString("staffImageName")
					));
	}
		connection.close();
		
		return staffList;
	}
	
	
	//Read ColumnLabel For Staff
	public ObservableList<String> getAllStaffColumnLabel() throws SQLException{
		
		ObservableList<String> columnLabelList = FXCollections.observableArrayList();
		connection = dbConnection.getConnection();
		statement = connection.createStatement();
		resultSet = statement.executeQuery("select * from hoteldb.staff;");
		
		ResultSetMetaData metaData = resultSet.getMetaData();
		
		Integer count = metaData.getColumnCount();
		
		for(int x = 1 ; x <= count ; x++) {
			
			columnLabelList.add(metaData.getColumnLabel(x));
		}
		return columnLabelList;
		
		
	}
	
	//Read ColumnLabel For ManagerViewStaff
	public ObservableList<String> getStaffColumnLabel() throws SQLException{
		
		ObservableList<String> columnLabelList = FXCollections.observableArrayList();
		connection = dbConnection.getConnection();
		statement = connection.createStatement();
		resultSet = statement.executeQuery("select * from hoteldb.staff;");
		//staffId as Id,staffUserName as Name,staffEmail as Email,staffPhNo as Phone,staffAddress as Address,staffDOB as DateOfBirth,staffGender as Gender,staffRole as Role,staffImageName as ImageName
		
		ResultSetMetaData metaData = resultSet.getMetaData();
		
		Integer count = metaData.getColumnCount();
		
		for(int x = 1 ; x <= count ; x++) {
			
			columnLabelList.add(metaData.getColumnLabel(x));
		}
		return columnLabelList;
		
		
	}
	
	//Delete ColumnLabel For ManagerViewStaff
	public Boolean deleteFlaskStaff(Integer staffId) throws SQLException {
		connection = dbConnection.getConnection();
		preStmt = connection.prepareStatement("UPDATE `hoteldb`.`staff` SET `staffStatus` = ? WHERE (`staffId` = ?);");
		preStmt.setString(1,"Inactive");
		preStmt.setInt(2,staffId);
		
		Boolean isDeleteOk = preStmt.execute();
		connection.close();
		
		return isDeleteOk;
		
		
	}
	
}
