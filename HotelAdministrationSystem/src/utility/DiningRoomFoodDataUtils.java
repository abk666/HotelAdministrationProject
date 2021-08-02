package utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import bean.DiningRoomFood;
import database1.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DiningRoomFoodDataUtils {
	
	private Connection connection;
	private Statement statement;
	private PreparedStatement preStmt;
	private ResultSet resultSet;
		
	
	private final DBConnection dbConnection = new DBConnection();
	
	//Read r
	public ObservableList<DiningRoomFood> getAllDiningRoomFood(String sql) throws SQLException{
		
		ObservableList<DiningRoomFood> diningRoomFoodList = FXCollections.observableArrayList();
		
		connection = dbConnection.getConnection();
		statement = connection.createStatement();
		resultSet = statement.executeQuery(sql);
		
		while(resultSet.next()) {
			
			diningRoomFoodList.add(new DiningRoomFood(resultSet.getInt("foodMenuId"), 
					resultSet.getString("foodMenuName"), 
					resultSet.getString("foodMenuCategory"), 
					resultSet.getDouble("foodMenuPrice"), 
					resultSet.getString("foodMenuImage")
					));
			
		}
		connection.close();
		return diningRoomFoodList;
	}
	
	
	
	
	
	//Create C
	public Boolean saveDiningRoomFood(DiningRoomFood food) throws SQLException {
		
		connection = dbConnection.getConnection();
		preStmt = connection.prepareStatement("INSERT INTO `hoteldb`.`foodmenu` (`foodMenuName`, `foodMenuCategory`, `foodMenuPrice`, `foodMenuImage`) VALUES (?,?,?,?);");
			

		preStmt.setString(1,food.getFoodMenuName());
		preStmt.setString(2,food.getFoodMenuCategory());
		preStmt.setDouble(3,food.getFoodMenuPrice());
		preStmt.setString(4,food.getFoodMenuImage());
		
		Boolean isSaveOK = preStmt.execute();
		
		connection.close();
		return isSaveOK;
		
	}
	
	//Update u
	public Integer updateDiningRoomFood(DiningRoomFood food) throws SQLException  {
		
		connection = dbConnection.getConnection();
		
		preStmt = connection.prepareStatement("UPDATE `hoteldb`.`foodmenu` SET `foodMenuName` = ?, "
				+ "`foodMenuCategory` = ?, "
				+ "`foodMenuPrice` = ?, "
				+ "`foodMenuImage` = ? WHERE (`foodMenuId` = ?);");
		
		preStmt.setString(1,food.getFoodMenuName());
		preStmt.setString(2,food.getFoodMenuCategory());
		preStmt.setDouble(3,food.getFoodMenuPrice());
		preStmt.setString(4,food.getFoodMenuImage());
		preStmt.setInt(5,food.getFoodMenuId());
		
		Integer rowUpdated = preStmt.executeUpdate();
		
		connection.close();
		return rowUpdated;
	}
	
	
	//Delete D
	public Boolean deleteDiningRoomFood(Integer foodMenuId) throws SQLException {
		
		connection = dbConnection.getConnection();
		
		preStmt = connection.prepareStatement("delete from hoteldb.foodmenu where foodMenuId = ?;");
		preStmt.setInt(1, foodMenuId);
		
		Boolean isDeleteOk = preStmt.execute();
		connection.close();
		 return isDeleteOk;
	}
	
	//Read ColumnLabel For AdminDiningRoom
	public ObservableList<String> getAllColumnLabel() throws SQLException{
		
		ObservableList<String> columnLabelList = FXCollections.observableArrayList();
		connection = dbConnection.getConnection();
		statement = connection.createStatement();
		resultSet = statement.executeQuery("select * from hoteldb.foodmenu;");
		//foodMenuId as Id,foodMenuName as Name,foodMenuCategory as Category,foodMenuPrice as Price,foodMenuImage as ImageName
		
		ResultSetMetaData metaData = resultSet.getMetaData();
		
		Integer count = metaData.getColumnCount();
		
		for(int x = 1 ; x <= count ; x++) {
			
			columnLabelList.add(metaData.getColumnLabel(x));
		}
		return columnLabelList;
	}
	
	
	//Read ColumnLabel For Food Menu
	public ObservableList<String> getFoodMenuColumnLabel() throws SQLException{
		
		ObservableList<String> columnLabelList = FXCollections.observableArrayList();
		connection = dbConnection.getConnection();
		statement = connection.createStatement();
		resultSet = statement.executeQuery("select foodMenuName,foodMenuPrice from hoteldb.foodmenu;");
		
		ResultSetMetaData metaData = resultSet.getMetaData();
		
		Integer count = metaData.getColumnCount();
		
		for(int x = 1 ; x <= count ; x++) {
			
			columnLabelList.add(metaData.getColumnLabel(x));
		}
		return columnLabelList;
		
		
	}

}
