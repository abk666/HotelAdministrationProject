package utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import bean.Room;

import database.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RoomUtils {
	
	private Connection connection ;
	private Statement statement;
	private PreparedStatement preStmt;
	private ResultSet resultSet;
	
	private final DbConnection dbConnection = new DbConnection();
	
	public ObservableList<Room> getAllRoom(String sql) throws SQLException{
		
		ObservableList<Room> roomList = FXCollections.observableArrayList();
		
		connection = dbConnection.getConnection();
		
		statement = connection.createStatement();
		
		resultSet = statement.executeQuery(sql);
		
		while (resultSet.next()) {
			roomList.add(new Room(
					resultSet.getInt("roomId"),
					resultSet.getString("roomType"),
					resultSet.getInt("roomNumber"),
					resultSet.getDouble("roomPrice"),
					resultSet.getString("roomStatus")));
		}
		connection.close();
		
		return roomList;
	}
	
	public Boolean saveRoom(Room room) throws SQLException {
		
		connection = dbConnection.getConnection();
		
		preStmt = connection.prepareStatement("INSERT INTO `room` (`roomNumber`,"
				+ " `roomType`,"
				+ " `roomPrice`,"
				+ " `roomStatus`)"
				+ " VALUES (?, ?, ?, ?) on duplicate key update roomNumber = ?,roomType = ?,roomPrice = ?, roomStatus = ? ;");
		
		preStmt.setInt(1, room.getRoomNumber());
		preStmt.setString(2, room.getRoomType());
		preStmt.setDouble(3, room.getRoomPrice());
		preStmt.setString(4, room.getRoomStatus());
		preStmt.setInt(5, room.getRoomNumber());
		preStmt.setString(6, room.getRoomType());
		preStmt.setDouble(7, room.getRoomPrice());
		preStmt.setString(8, room.getRoomStatus());
		
		Boolean isSaveOk = preStmt.execute();
		
		connection.close();
		return isSaveOk;
		
	}

	public Boolean deleteRoom(Integer roomId) throws SQLException {
		
		connection = dbConnection.getConnection();
		
		preStmt= connection.prepareStatement("delete from room where roomId = ?;");
		
		preStmt.setInt(1, roomId);
		
		Boolean isDeleteOk = preStmt.execute();
		
		connection.close();
		
		return isDeleteOk;
		
	}
	
	//UpdateRoom
	public Integer updateRoom(Room room) throws SQLException {
		
		connection = dbConnection.getConnection();
		
		preStmt = connection.prepareStatement("UPDATE `room` SET"
				+ " `roomNumber` = ?,"
				+ " `roomType` = ?,"
				+ " `roomPrice` = ?,"
				+ " `roomStatus` = ?"
				+ " WHERE (`roomId` = ?);");
		
		preStmt.setInt(1, room.getRoomNumber());
		preStmt.setString(2, room.getRoomType());
		preStmt.setDouble(3, room.getRoomPrice());
		preStmt.setString(4, room.getRoomStatus());
		preStmt.setInt(5, room.getRoomId());
		
		Integer rowUpdated = preStmt.executeUpdate();
		
		connection.close();
		
		return rowUpdated;
		
	}
	
	
	//WaitingRoom
	public Boolean UpdateStatus(Integer roomId,String roomStatus) throws SQLException {
		
		connection = dbConnection.getConnection();
		preStmt = connection.prepareStatement("UPDATE `hoteldb`.`room` SET `roomStatus` = ? WHERE (`roomId` = ?);");
		
		preStmt.setString(1, roomStatus);
		preStmt.setInt(2, roomId);
		
		Boolean isDeleteOk = preStmt.execute();
		connection.close();
		return isDeleteOk;
		
	}
	
	public ObservableList<String> getAllColumnLabel() throws SQLException{
		
		ObservableList<String> columnLabelList = FXCollections.observableArrayList();
		
		connection = dbConnection.getConnection();
		
		statement = connection.createStatement();
		
		resultSet = statement.executeQuery("select * from room;");
		
		ResultSetMetaData metaData = resultSet.getMetaData();
		
		Integer count = metaData.getColumnCount();
		
		for(int x = 1 ; x <= count ; x++) {
			
			columnLabelList.add(metaData.getColumnLabel(x));
			
		}
		return columnLabelList;
		
	}
}
