package utility;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;


import bean.Room;
import database.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RoomDataUtils {
	private Connection connection;
	private Statement statement;
	private PreparedStatement preStmt;
	private ResultSet resultSet;
	
	private final DbConnection dbConnection=new DbConnection();
	
	public ObservableList<Room>getAllRoom(String sql) throws SQLException{
		ObservableList<Room> roomList=FXCollections.observableArrayList();
		

		connection = dbConnection.getConnection();
		
		statement = connection.createStatement();
		
		resultSet = statement.executeQuery(sql);
		
		while (resultSet.next()) {
			roomList.add(new Room(resultSet.getInt("roomId"), 
					resultSet.getString("roomType"), 
					resultSet.getInt("roomNumber"), 
					resultSet.getDouble("roomPrice"), 
					resultSet.getInt("roomMaxNo"), 
					resultSet.getString("roomStatus")
					));
		}
		connection.close();
		
		return roomList;		
}
	
			public ObservableList<String> getAllColumnLabel() throws SQLException{
				
				ObservableList<String> columnLabelList = FXCollections.observableArrayList();
				
				connection = dbConnection.getConnection();
				
				statement = connection.createStatement();
				
				resultSet = statement.executeQuery("select * from room;");
				
				ResultSetMetaData metaData = resultSet.getMetaData();
				
				Integer count = metaData.getColumnCount();
				
				for(int x = 1; x <= count;x++) {
					
					columnLabelList.add(metaData.getColumnLabel(x));
					
				}
				return columnLabelList;
				
				
				
			}
}