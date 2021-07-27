package utility;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import bean.FoodOrder;
import database1.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FoodOrderDataUtils {

	private Connection connection;
	private Statement statement;
	private PreparedStatement preStmt;
	private ResultSet resultSet;
		
	
	private final DBConnection dbConnection = new DBConnection();
	
	//Read r
	public ObservableList<FoodOrder> getAllFoodOrder(String sql) throws SQLException{
		
		ObservableList<FoodOrder> foodOrderList = FXCollections.observableArrayList();
		
		connection = dbConnection.getConnection();
		statement = connection.createStatement();
		resultSet = statement.executeQuery(sql);
		
		while(resultSet.next()) {
			
			foodOrderList.add(new FoodOrder(resultSet.getInt("foodOrderId"), 
					resultSet.getString("foodOrderName"),  
					resultSet.getDouble("foodOrderPrice"), 
					resultSet.getString("foodOrderDate"),
					resultSet.getInt("foodOrderQty"),
					resultSet.getInt("guestRoomNo"),
					resultSet.getDouble("foodOrderTotalPrice")
					));
			
		}
		connection.close();
		return foodOrderList;
	}
	
	
	//Create C
	public Boolean saveFoodOrder(FoodOrder food) throws SQLException {
			
		connection = dbConnection.getConnection();
		preStmt = connection.prepareStatement("INSERT INTO `hoteldb`.`foodorder` "
				+ "(`foodOrderName`, `foodOrderPrice`, `foodOrderDate`, `foodOrderQty`, `guestRoomNo`, `foodOrderTotalPrice`) "
				+ "VALUES (?, ?, ?, ?, ?, ?);" 
				);
				

		preStmt.setString(1,food.getFoodOrderName());
		preStmt.setDouble(2,food.getFoodOrderPrice());
		preStmt.setString(3,food.getFoodOrderDate());
		preStmt.setInt(4,food.getFoodOrderQty());
		preStmt.setInt(5,food.getGuestRoomNo());
		preStmt.setDouble(6,food.getFoodOrderTotalPrice());
			
		Boolean isSaveOK = preStmt.execute();
			
		connection.close();
		return isSaveOK;
			
		}
	
	
	//Read ColumnLabel
	public ObservableList<String> getAllColumnLabel() throws SQLException{
			
		ObservableList<String> columnLabelList = FXCollections.observableArrayList();
		connection = dbConnection.getConnection();
		statement = connection.createStatement();
		resultSet = statement.executeQuery("select * from hoteldb.foodorder;");
			
		ResultSetMetaData metaData = resultSet.getMetaData();
			
		Integer count = metaData.getColumnCount();
			
		for(int x = 1 ; x <= count ; x++) {
				
			columnLabelList.add(metaData.getColumnLabel(x));
		}
		return columnLabelList;
			
	}
	
	//Delete D
	public Boolean deleteFoodOrder(Integer foodOrderId) throws SQLException {
			
		connection = dbConnection.getConnection();
			
		preStmt = connection.prepareStatement("delete from hoteldb.foodorder where foodOrderId = ?;");
		preStmt.setInt(1, foodOrderId);
			
		Boolean isDeleteOk = preStmt.execute();
		connection.close();
		return isDeleteOk;
	}
	
	//Update u
	public Integer updateFoodOrder(FoodOrder food) throws SQLException  {
			
		connection = dbConnection.getConnection();
			
		preStmt = connection.prepareStatement("UPDATE `hoteldb`.`foodorder`"
				+ " SET `foodOrderName` = ?, "
				+ "`foodOrderPrice` = ?,"
				+ " `foodOrderDate` = ?, "
				+ "`foodOrderQty` = ?, "
				+ "`guestRoomNo` = ?, `foodOrderTotalPrice` = ? WHERE (`foodOrderId` = ?);" 
				);
			
		preStmt.setString(1,food.getFoodOrderName());
		preStmt.setDouble(2,food.getFoodOrderPrice());
		
		Date date = Date.valueOf(food.getFoodOrderDate());
		preStmt.setDate(3,date);
		
		preStmt.setInt(4,food.getFoodOrderQty());
		preStmt.setInt(5,food.getGuestRoomNo());
		preStmt.setDouble(6,food.getFoodOrderTotalPrice());
		preStmt.setInt(7,food.getFoodOrderId());
		
		Integer rowUpdated = preStmt.executeUpdate();
			
		connection.close();
		return rowUpdated;
	}
}
