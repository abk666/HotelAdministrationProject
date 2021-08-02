package utility;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import bean.Booking;
import bean.Room;
import database.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BookingDataUtils {
	private Connection connection;
	private Statement statement;
	private PreparedStatement preStmt;
	private ResultSet resultSet;
	
	private final RoomUtils roomDataUtils=new RoomUtils();
	private final DbConnection dbConnection=new DbConnection();
	
	//Read R
	public ObservableList<Booking>getAllBooking(String sql) throws SQLException{
		ObservableList<Booking> bookingList=FXCollections.observableArrayList();
		
		connection = dbConnection.getConnection();
		
		statement = connection.createStatement();
		
		resultSet = statement.executeQuery(sql);
		
		while (resultSet.next()) {
			bookingList.add(new Booking(resultSet.getInt("bookingId"), 
					resultSet.getString("guestName"),
					resultSet.getString("guestPhNo"),
					resultSet.getString("roomType"),
					resultSet.getInt("roomNo"), 
					resultSet.getDouble("roomPrice"),
					resultSet.getInt("noOfGuest"),
					resultSet.getDate("bookedDate").toString(),
					resultSet.getDate("checkInDate").toString(),
					resultSet.getInt("numberOfDays"),
					resultSet.getDate("checkOutDate").toString()
					));
					
		}
		connection.close();
		
		return bookingList;
	}
	//Create C
		public Boolean saveBooking(Booking booking) throws SQLException {
			
			connection = dbConnection.getConnection();
			
			preStmt = connection.prepareStatement("INSERT INTO `booking` "
					+ "(`guestName`, `guestPhNo`, `roomType`,"
					+ " `roomNo`, `roomPrice`, `noOfGuest`,"
					+ " `bookedDate`, `checkInDate`, `numberOfDays`, `checkOutDate`) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?);" 
					);
			
			preStmt.setString(1,booking.getGuestName());
			preStmt.setString(2,booking.getGuestPhNo());
			preStmt.setString(3,booking.getRoomType());
			preStmt.setInt(4,booking.getRoomNo());
			preStmt.setDouble(5,booking.getRoomPrice());
			preStmt.setInt(6,booking.getNoOfGuest());
			Date bookedDate=Date.valueOf(booking.getBookedDate());
			preStmt.setDate(7,bookedDate);
			
			Date checkIndate = Date.valueOf(booking.getCheckInDate());
			preStmt.setDate(8,checkIndate);
			preStmt.setInt(9,booking.getNumberOfDays());
			Date checkOutdate = Date.valueOf(booking.getCheckOutDate());
			preStmt.setDate(10,checkOutdate);
			
			Boolean isSaveOk = preStmt.execute();
			
			connection.close();
			return isSaveOk;
			
			
		}
		
		//Update U
				public Integer updateBooking(Booking booking) throws SQLException {
					connection = dbConnection.getConnection();
					
					preStmt = connection.prepareStatement("UPDATE `booking` SET "
							+ "`guestName` = ?, "
							+ "`guestPhNo` = ?, "
							+ "`roomType` = ?, "
							+ "`roomNo` = ?, "
							+ "`roomPrice` = ?, "
							+ "`noOfGuest` = ?, "
							+ "`bookedDate` = ?, "
							+ "`checkInDate` = ?, "
							+ "`numberOfDays` = ?, "
							
							
							
							+ "`checkOutDate` = ? WHERE (`bookingId` = ?);" 
							);
					
					preStmt.setString(1,booking.getGuestName());
					preStmt.setString(2,booking.getGuestPhNo());
					preStmt.setString(3,booking.getRoomType());
					preStmt.setInt(4,booking.getRoomNo());
					preStmt.setDouble(5,booking.getRoomPrice());
					preStmt.setInt(6,booking.getNoOfGuest());
					Date bookedDate=Date.valueOf(booking.getBookedDate());
					preStmt.setDate(7,bookedDate);
					
					Date checkIndate = Date.valueOf(booking.getCheckInDate());
					preStmt.setDate(8,checkIndate);
					preStmt.setInt(9,booking.getNumberOfDays());
					Date checkOutdate = Date.valueOf(booking.getCheckOutDate());
					preStmt.setDate(10,checkOutdate);
					preStmt.setInt(11,booking.getBookingId());
					
					Integer rowUpdated = preStmt.executeUpdate();
					
					connection.close();
					return rowUpdated;

				}
				
				
				public void UpdateRoomStatus(Integer RoomNo) throws SQLException {
				                    Room roomList=roomDataUtils.getAllRoom("select * from room where roomNumber = '"+RoomNo+"'").get(0);
				                    connection = dbConnection.getConnection();
				                    statement=connection.createStatement();
				                    statement.execute("update `room` set `roomStatus` = 'Booked' where (`roomId` = '"+roomList.getRoomId()+"');");
				                }
				public void updateDeletedBooking(Integer RoomNo) throws SQLException {
                    Room roomList=roomDataUtils.getAllRoom("select * from room where roomNumber = '"+RoomNo+"'").get(0);
                    connection = dbConnection.getConnection();
                    statement=connection.createStatement();
                    statement.execute("update `room` set `roomStatus` = 'Available' where (`roomId` = '"+roomList.getRoomId()+"');");
                }
		
		//Delete D
				public Boolean deleteBooking(Integer bookingId) throws SQLException {
					connection = dbConnection.getConnection();
					preStmt = connection.prepareStatement("delete from booking where bookingId = ?;");
					
					preStmt.setInt(1,bookingId);
					
					Boolean isDeleteOk = preStmt.execute();
					connection.close();
					return isDeleteOk;
					
				}
				

				//R  ColumnLabel
				public ObservableList<String> getAllColumnLabel() throws SQLException{
					
					ObservableList<String> columnLabelList = FXCollections.observableArrayList();
					
					connection = dbConnection.getConnection();
					
					statement = connection.createStatement();
					
					resultSet = statement.executeQuery("select * from booking;");
					
					ResultSetMetaData metaData = resultSet.getMetaData();
					
					Integer count = metaData.getColumnCount();
					
					for(int x = 1; x <= count;x++) {
						
						columnLabelList.add(metaData.getColumnLabel(x));
						
					}
					return columnLabelList;
					
					
					
				}
	
}
