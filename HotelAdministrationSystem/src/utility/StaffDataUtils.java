package utility;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import bean.Admin;
import bean.Staff;
import database.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StaffDataUtils {

	private Connection connection;
	private Statement statement;
	private PreparedStatement preStmt;
	private ResultSet resultSet;
	
	private final DbConnection dbConnection = new DbConnection();
	
	//Save
			public Boolean saveStaff(Staff staff) throws SQLException {
				
				connection = dbConnection.getConnection();
				
				preStmt = connection.prepareStatement("INSERT INTO `staff` (`staffFName`, `staffLName`, `staffUserName`,"
						+ " `staffEmail`, `staffPassword`, `staffRole`,"
						+ " `staffGender`, `staffPhNo`, `staffAddress`,"
						+ " `staffDOB`, `staffStatus`, `staffImageName`) "
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);" 
						
						);
				
				preStmt.setString(1, staff.getFirstName());
				preStmt.setString(2, staff.getLastName());
				preStmt.setString(3, staff.getUsername());
				preStmt.setString(4, staff.getEmail());
				preStmt.setString(5, staff.getPassword());
				preStmt.setString(6, staff.getRole());
				preStmt.setString(7, staff.getGender());
				preStmt.setString(8, staff.getPhone());
				preStmt.setString(9, staff.getAddress());
				Date date = Date.valueOf(staff.getDob());
				preStmt.setDate(10, date);
				preStmt.setString(11, staff.getStatus());
				preStmt.setString(12, staff.getImageName());
				
				Boolean isSaveOK = preStmt.execute();
				connection.close();
				return isSaveOK;
				
				
			}
			
			//Update
			
			public Integer updatestaff (Staff staff) throws SQLException {
				
				connection = dbConnection.getConnection();
				
				preStmt = connection.prepareStatement("UPDATE `staff` SET `staffFName` = ?,"
						+ " `staffLName` = ?, "
						+ "`staffUserName` = ?,"
						+ " `staffEmail` = ?, "
						+ "`staffPassword` = ?, "
						+ "`staffRole` = ?,"
						+ " `staffGender` = ?,"
						+ " `staffPhNo` = ?,"
						+ " `staffAddress` = ?,"
						+ " `staffDOB` = ?,"
						+ " `staffStatus` = ?, "
						+ "`staffImageName` = ? WHERE (`staffId` = ?);" 
				
						);
				
				preStmt.setString(1, staff.getFirstName());
				preStmt.setString(2, staff.getLastName());
				preStmt.setString(3, staff.getUsername());
				preStmt.setString(4, staff.getEmail());
				preStmt.setString(5, staff.getPassword());
				preStmt.setString(6, staff.getRole());
				preStmt.setString(7, staff.getGender());
				preStmt.setString(8, staff.getPhone());
				preStmt.setString(9, staff.getAddress());
				Date date = Date.valueOf(staff.getDob());
				preStmt.setDate(10, date);
				preStmt.setString(11, staff.getStatus());
				preStmt.setString(12, staff.getImageName());
				preStmt.setInt(13, staff.getId());
				
				Integer rowUpdated = preStmt.executeUpdate();
				
				connection.close();
				return rowUpdated;
			}
			
			//Read
			public ObservableList<Staff> getAllStaff(String sql) throws SQLException {
				
				ObservableList<Staff> staffList = FXCollections.observableArrayList();
				
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
			
			
			//Delete 
			public Boolean deleteStaff(Integer staffId) throws SQLException {
						
						connection = dbConnection.getConnection();
						
						preStmt = connection.prepareStatement("delete from staff where staffId = ?;");
						preStmt.setInt(1, staffId);
						
						Boolean isDeleteOk = preStmt.execute();
						connection.close();
						 return isDeleteOk;
					}
			
			
			// Read column label
			
			public ObservableList<String> getAllColumnLabel() throws SQLException {
						
					ObservableList<String> columnLabelList = FXCollections.observableArrayList();
						
					connection = dbConnection.getConnection();
						
					statement = connection.createStatement();
						
					resultSet = statement.executeQuery("select * from staff;");
						
					ResultSetMetaData metaData = resultSet.getMetaData();
						
					Integer count = metaData.getColumnCount();
						
						for(int x=1; x<= count ; x++) {
							
							columnLabelList.add(metaData.getColumnLabel(x));
							
						}
						return columnLabelList;
						
						
					}
}
