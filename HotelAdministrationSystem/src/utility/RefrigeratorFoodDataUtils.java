package utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import bean.RefrigeratorFood;
import database1.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RefrigeratorFoodDataUtils {
	
	private Connection connection;
	private Statement statement;
	private PreparedStatement preStmt;
	private ResultSet resultSet;
		
	
	private final DBConnection dbConnection = new DBConnection();
	
	//Read r
	public ObservableList<RefrigeratorFood> getAllRefrigeratorFood(String sql) throws SQLException{
		
		ObservableList<RefrigeratorFood> refrigeratorFoodList = FXCollections.observableArrayList();
		
		connection = dbConnection.getConnection();
		statement = connection.createStatement();
		resultSet = statement.executeQuery(sql);
		
		while(resultSet.next()) {
			
			refrigeratorFoodList.add(new RefrigeratorFood(resultSet.getInt("itemId"), 
					resultSet.getString("itemName"), 
					resultSet.getString("itemCategory"), 
					resultSet.getDouble("itemPrice"), 
					resultSet.getInt("itemQty"),
					resultSet.getString("itemImage")
					));
			
		}
		connection.close();
		return refrigeratorFoodList;
	}

	
	//Create C
		public Boolean saveRefrigeratorFood(RefrigeratorFood food) throws SQLException {
			
			connection = dbConnection.getConnection();
			preStmt = connection.prepareStatement("INSERT INTO `hoteldb`.`refrigeratoritem` (`itemName`, `itemCategory`, `itemPrice`, `itemQty`, `itemImage`) "
					+ "VALUES (?,?,?,?,?);"
					);
				

			preStmt.setString(1,food.getItemName());
			preStmt.setString(2,food.getItemCategory());
			preStmt.setDouble(3,food.getItemPrice());
			preStmt.setInt(4, food.getItemQty());
			preStmt.setString(5,food.getItemImage());
			
			Boolean isSaveOK = preStmt.execute();
			
			connection.close();
			return isSaveOK;
			
		}
		
		//Update u
		public Integer updateRefrigeratorFood(RefrigeratorFood food) throws SQLException  {
			
			connection = dbConnection.getConnection();
			
			preStmt = connection.prepareStatement("UPDATE `hoteldb`.`refrigeratoritem` SET "
					+ "`itemName` = ?, `itemCategory` = ?, `itemPrice` = ?, `itemQty` = ?, `itemImage` = ? "
					+ "WHERE (`itemId` = ?);" 
					);
			
			preStmt.setString(1,food.getItemName());
			preStmt.setString(2,food.getItemCategory());
			preStmt.setDouble(3,food.getItemPrice());
			preStmt.setInt(4, food.getItemQty());
			preStmt.setString(5,food.getItemImage());
			preStmt.setInt(6,food.getItemId());
			
			Integer rowUpdated = preStmt.executeUpdate();
			
			connection.close();
			return rowUpdated;
		}
		
		//Delete D
		public Boolean deleteRefrigeratorFood(Integer itemId) throws SQLException {
			
			connection = dbConnection.getConnection();
			
			preStmt = connection.prepareStatement("delete from hoteldb.refrigeratoritem where itemId = ?;");
			preStmt.setInt(1, itemId);
			
			Boolean isDeleteOk = preStmt.execute();
			connection.close();
			return isDeleteOk;
		}
		
		//Read ColumnLabel For Admin
		public ObservableList<String> getAllColumnLabel() throws SQLException{
			
			ObservableList<String> columnLabelList = FXCollections.observableArrayList();
			connection = dbConnection.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from hoteldb.refrigeratoritem;");
			
			ResultSetMetaData metaData = resultSet.getMetaData();
			
			Integer count = metaData.getColumnCount();
			
			for(int x = 1 ; x <= count ; x++) {
				
				columnLabelList.add(metaData.getColumnLabel(x));
			}
			return columnLabelList;
		}
		
}
