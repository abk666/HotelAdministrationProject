package utility;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;


import bean.Guest;
import bean.Room;
import database.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GuestDataUtils {
	private Connection connection;
	private Statement statement;
	private PreparedStatement preStmt;
	private ResultSet resultSet;
	
	private final DbConnection dbConnection=new DbConnection();
	private final RoomDataUtils roomDataUtils=new RoomDataUtils();
	
	//Read R
		public ObservableList<Guest>getAllGuest(String sql) throws SQLException{
			ObservableList<Guest> guestList=FXCollections.observableArrayList();
			
			connection = dbConnection.getConnection();
			
			statement = connection.createStatement();
			
			resultSet = statement.executeQuery(sql);
			
			while (resultSet.next()) {
				guestList.add(new Guest(resultSet.getInt("guestId"),
						resultSet.getString("guestName"), 
						resultSet.getString("guestNRC"), 
						resultSet.getInt("noOfGuests"), 
						resultSet.getString("guestPhNo"), 
						resultSet.getString("guestRoomType"), 
						resultSet.getInt("guestRoomNo"), 
						resultSet.getDouble("guestRoomPrice"), 
						resultSet.getDouble("guestfoodOrderPrice"), 
						resultSet.getDouble("guestInRoomCost"), 
						resultSet.getDate("guestCheckInDate").toString(), 
						resultSet.getDate("guestCheckOutDate").toString(), 
						resultSet.getInt("numberOfDays"),
						resultSet.getString("guestStatus")
						));
		}
			connection.close();
			
			return guestList;
		}
		//Create C
				public Boolean saveGuest(Guest guest) throws SQLException {
					
					connection = dbConnection.getConnection();
					
					preStmt = connection.prepareStatement("INSERT INTO `guest` "
							+ "(`guestName`, `guestNRC`, `noOfGuests`,"
							+ " `guestPhNo`, `guestRoomType`, `guestRoomNo`,"
							+ " `guestRoomPrice`, `guestFoodOrderPrice`, `guestInRoomCost`,"
							+ " `guestCheckInDate`, `guestCheckOutDate`, `numberOfDays`,`guestStatus`) "
							+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);" 
							);
					
					preStmt.setString(1,guest.getGuestName());
					preStmt.setString(2,guest.getGuestNRC());
					preStmt.setInt(3,guest.getNoOfGuests());
					preStmt.setString(4,guest.getGuestPhNo());
					preStmt.setString(5,guest.getGuestRoomType());
					preStmt.setInt(6,guest.getGuestRoomNo());
					preStmt.setDouble(7,guest.getGuestRoomPrice());
					preStmt.setDouble(8,guest.getFoodOrderPrice());
					preStmt.setDouble(9,guest.getGuestInRoomCost());
					
					Date checkIndate = Date.valueOf(guest.getGuestCheckInDate());
					preStmt.setDate(10,checkIndate);
					
					Date checkOutdate = Date.valueOf(guest.getGuestCheckOutDate());
					preStmt.setDate(11,checkOutdate);
					
					preStmt.setInt(12,guest.getNumberOfDays());
					preStmt.setString(13,guest.getGuestStatus());
					
					Boolean isSaveOk = preStmt.execute();
					
					connection.close();
					return isSaveOk;
					
					
				}
				
				//Update U
				public Integer updateGuest(Guest guest) throws SQLException {
					connection = dbConnection.getConnection();
					
					preStmt = connection.prepareStatement("UPDATE `guest` SET "
							+ "`guestName` = ?, "
							+ "`guestNRC` = ?, "
							+ "`noOfGuests` = ?, "
							+ "`guestPhNo` = ?, "
							+ "`guestRoomType` = ?, "
							+ "`guestRoomNo` = ?, "
							+ "`guestRoomPrice` = ?, "
							+ "`guestFoodOrderPrice` = ?, "
							+ "`guestInRoomCost` = ?, "
							+ "`guestCheckInDate` = ?, "
						
							+ "`guestCheckOutDate` = ?, "
							+ "`numberOfDays` = ?, "
							
							+ "`guestStatus` = ? WHERE (`guestId` = ?);" 
							);
//					UPDATE `hoteldb`.`guest` SET `guestRoomNo` = '301' WHERE (`guestId` = '2');
					preStmt.setString(1,guest.getGuestName());
					preStmt.setString(2,guest.getGuestNRC());
					preStmt.setInt(3,guest.getNoOfGuests());
					preStmt.setString(4,guest.getGuestPhNo());
					preStmt.setString(5,guest.getGuestRoomType());
					preStmt.setInt(6,guest.getGuestRoomNo());
					preStmt.setDouble(7,guest.getGuestRoomPrice());
					preStmt.setDouble(8,guest.getFoodOrderPrice());
					preStmt.setDouble(9,guest.getGuestInRoomCost());
					
					Date checkIndate = Date.valueOf(guest.getGuestCheckInDate());
					preStmt.setDate(10,checkIndate);
					
					Date checkOutdate = Date.valueOf(guest.getGuestCheckOutDate());
					preStmt.setDate(11,checkOutdate);
					
					preStmt.setInt(12,guest.getNumberOfDays());
					preStmt.setString(13,guest.getGuestStatus());
					preStmt.setInt(14,guest.getGuestId());
					
					Integer rowUpdated = preStmt.executeUpdate();
					
					connection.close();
					return rowUpdated;

				}
				
				
				public void UpdateRoomStatus(Integer guestRoomNo) throws SQLException {
					Room roomList=roomDataUtils.getAllRoom("select * from room where roomNumber = '"+guestRoomNo+"'").get(0);
					connection = dbConnection.getConnection();
					statement=connection.createStatement();
					statement.execute("update `room` set `roomStatus` = 'CheckIn' where (`roomId` = '"+roomList.getRoomId()+"');");
				}
				//Delete D
				public Boolean deleteGuest(Integer guestId) throws SQLException {
					connection = dbConnection.getConnection();
					preStmt = connection.prepareStatement("delete from booking where guestId = ?;");
					
					preStmt.setInt(1,guestId);
					
					Boolean isDeleteOk = preStmt.execute();
					connection.close();
					return isDeleteOk;
					
				}
				//R  ColumnLabel
				public ObservableList<String> getAllColumnLabel() throws SQLException{
					
					ObservableList<String> columnLabelList = FXCollections.observableArrayList();
					
					connection = dbConnection.getConnection();
					
					statement = connection.createStatement();
					
					resultSet = statement.executeQuery("select * from guest;");
					
					ResultSetMetaData metaData = resultSet.getMetaData();
					
					Integer count = metaData.getColumnCount();
					
					for(int x = 1; x <= count;x++) {
						
						columnLabelList.add(metaData.getColumnLabel(x));
						
					}
					return columnLabelList;
					
					
					
					
				}
}
