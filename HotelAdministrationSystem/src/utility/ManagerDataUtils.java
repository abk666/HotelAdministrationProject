package utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.Staff;
import database.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ManagerDataUtils {
	private Connection connection;
	private Statement statement;

	private ResultSet resultSet;
	private final DbConnection dbConnection=new DbConnection();
	
	public ObservableList<Staff>getStaffGender(String sql) throws SQLException{
		ObservableList<Staff>staffGenderList=FXCollections.observableArrayList();
		connection=dbConnection.getConnection();
		statement=connection.createStatement();
		resultSet=statement.executeQuery(sql);
		while(resultSet.next()) {
			staffGenderList.add(new Staff(
					resultSet.getString("staffGender"),
					resultSet.getDouble("genderCount")));
		}
		connection.close();
		return staffGenderList;
		
	}
	public ObservableList<Staff>getStaffRole(String sql) throws SQLException{
		ObservableList<Staff>staffGenderList=FXCollections.observableArrayList();
		connection=dbConnection.getConnection();
		statement=connection.createStatement();
		resultSet=statement.executeQuery(sql);
		while(resultSet.next()) {
			staffGenderList.add(new Staff(
					resultSet.getString("staffRole"),
					resultSet.getDouble("roleCount"),
					resultSet.getInt("staffId")));
		}
		connection.close();
		return staffGenderList;
		
	}

}
