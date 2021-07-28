package utility;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import bean.Admin;
import database.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AdminDataUtils {
	
	private Connection connection;
	private Statement statement;
	private PreparedStatement preStmt;
	private ResultSet resultSet;
	
	private final DbConnection dbConnection = new DbConnection();
	
		//Save
		public Boolean saveAdmin(Admin admin) throws SQLException {
			
			connection = dbConnection.getConnection();
			
			preStmt = connection.prepareStatement("INSERT INTO `admin` (`adminFName`, `adminLName`, `adminUserName`, "
					+ "`adminEmail`, `adminPassword`, `adminPhNo`,"
					+ " `adminAddress`, `adminDOB`, `adminStatus`, `adminImageName`)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);"
					);
			
			preStmt.setString(1, admin.getFirstName());
			preStmt.setString(2, admin.getLastName());
			preStmt.setString(3, admin.getUsername());
			preStmt.setString(4, admin.getEmail());
			preStmt.setString(5, admin.getPassword());
			preStmt.setString(6, admin.getPhone());
			preStmt.setString(7, admin.getAddress());
			Date date = Date.valueOf(admin.getDob());
			preStmt.setDate(8, date);
			preStmt.setString(9, admin.getStatus());
			preStmt.setString(10, admin.getImageName());
			
			Boolean isSaveOK = preStmt.execute();
			connection.close();
			return isSaveOK;
			
			
		}
		
		//Update
		
		public Integer updateAdmin (Admin admin) throws SQLException {
			
			connection = dbConnection.getConnection();
			
			preStmt = connection.prepareStatement("UPDATE `admin` SET `adminFName` = ?,"
					+ " `adminLName` = ?,"
					+ " `adminUserName` = ?,"
					+ " `adminEmail` = ?, "
					+ "`adminPassword` = ?,"
					+ " `adminPhNo` = ?,"
					+ " `adminAddress` = ?,"
					+ " `adminDOB` = ?,"
					+ " `adminStatus` = ?,"
					+ " `adminImageName` = ? WHERE (`adminId` = ?);"
			
					);
			
			preStmt.setString(1, admin.getFirstName());
			preStmt.setString(2, admin.getLastName());
			preStmt.setString(3, admin.getUsername());
			preStmt.setString(4, admin.getEmail());
			preStmt.setString(5, admin.getPassword());
			preStmt.setString(6, admin.getPhone());
			preStmt.setString(7, admin.getAddress());
			Date date = Date.valueOf(admin.getDob());
			preStmt.setDate(8, date);
			preStmt.setString(9, admin.getStatus());
			preStmt.setString(10, admin.getImageName());
			preStmt.setInt(11, admin.getId());
			
			Integer rowUpdated = preStmt.executeUpdate();
			
			connection.close();
			return rowUpdated;
		}
		
		//Read
		public ObservableList<Admin> getAllAdmin(String sql) throws SQLException {
			
			ObservableList<Admin> adminList = FXCollections.observableArrayList();
			
			connection = dbConnection.getConnection();
			
			statement = connection.createStatement();
			
			resultSet = statement.executeQuery(sql);
			
			while (resultSet.next()) {
				
				adminList.add(new Admin(resultSet.getInt("adminId"),
						resultSet.getString("adminFName"),
						resultSet.getString("adminLName"),
						resultSet.getString("adminUserName"),
						resultSet.getString("adminEmail"),
						resultSet.getString("adminPassword"),
						resultSet.getString("adminPhNo"),
						resultSet.getString("adminAddress"),
						resultSet.getDate("adminDOB").toString(),
						resultSet.getString("adminStatus"),
						resultSet.getString("adminImageName")

						));
				
			}
			
			connection.close();
			return adminList;
		}
		
		
		//Delete 
		public Boolean deleteAdmin(Integer adminId) throws SQLException {
					
					connection = dbConnection.getConnection();
					
					preStmt = connection.prepareStatement("delete from admin where adminId = ?;");
					preStmt.setInt(1, adminId);
					
					Boolean isDeleteOk = preStmt.execute();
					connection.close();
					 return isDeleteOk;
				}
		
		
		// Read column label
		
		public ObservableList<String> getAllColumnLabel() throws SQLException {
					
				ObservableList<String> columnLabelList = FXCollections.observableArrayList();
					
				connection = dbConnection.getConnection();
					
				statement = connection.createStatement();
					
				resultSet = statement.executeQuery("select * from admin;");
					
				ResultSetMetaData metaData = resultSet.getMetaData();
					
				Integer count = metaData.getColumnCount();
					
					for(int x=1; x<= count ; x++) {
						
						columnLabelList.add(metaData.getColumnLabel(x));
						
					}
					return columnLabelList;
					
					
				}
		

}
