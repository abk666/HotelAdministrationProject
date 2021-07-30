package utility;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import bean.CheckOut;
import bean.Guest;
import bean.Room;
import database.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CheckOutUtils {
	private Connection connection;
	private Statement statement;
	private PreparedStatement preStatement;
	private ResultSet resultSet;
	private final DbConnection dbConnection=new DbConnection();
	private final RoomUtils roomDataUtils=new RoomUtils();

	public ObservableList<Guest> getAllGuests(String sql) throws SQLException{
		ObservableList<Guest> guestList=FXCollections.observableArrayList();
		connection = dbConnection.getConnection();
		statement=connection.createStatement();
		resultSet=statement.executeQuery(sql);
		
		while(resultSet.next()) {
			guestList.add(new Guest(resultSet.getInt("guestId"),
					resultSet.getString("guestName"),
					resultSet.getString("guestNRC"), 
					resultSet.getInt("noOfGuests"),
					resultSet.getString("guestPhNo"),
					resultSet.getString("guestRoomType"),
					resultSet.getInt("guestRoomNo"),
					resultSet.getDouble("guestRoomPrice"),
					resultSet.getDouble("guestFoodOrderPrice"), 
					resultSet.getDouble("guestInRoomCost"),
					resultSet.getString("guestCheckInDate"),
					resultSet.getString("guestCheckOutDate"),
					resultSet.getInt("numberOfDays"),
					resultSet.getString("guestStatus")));
		}
		connection.close();
		return guestList;
	}
	public void updateNumOfDays(LocalDate todayDate,LocalDate checkInDate,Integer guestId) throws SQLException {
		long dayDifference=ChronoUnit.DAYS.between( checkInDate,todayDate);
		connection = dbConnection.getConnection();
	    statement=connection.createStatement();
		if(dayDifference>=1) {
			statement.execute("update `guest` SET `numberOfDays` = '"+dayDifference+"' where (`guestId` = '"+guestId+"');");
		}else {
			statement.execute("update `guest` set `numberOfDays` = '1' where (`guestId` = '"+guestId+"');");
		}
	}
	public boolean SaveCheckOutResult(CheckOut checkOut) throws SQLException {
		connection = dbConnection.getConnection();
		preStatement=connection.prepareStatement("INSERT INTO `checkout` ( `checkOutGuestName`, `checkOutGuestNRC`, `checkOutGuestPhNo`, `roomNo`, `checkOutDate`, `totalPrice`) "
				                               + "VALUES ( ?, ?, ?, ?, ?, ?);");
		preStatement.setString(1, checkOut.getCheckOutGuestName());
		preStatement.setString(2, checkOut.getCheckOutGuestNRC());
		preStatement.setString(3, checkOut.getCheckOutGuestPhNo());
		preStatement.setInt(4, checkOut.getRoomNo());
		Date date=Date.valueOf(checkOut.getCheckOutDate());
		preStatement.setDate(5, date);
		preStatement.setDouble(6, checkOut.getTotalPrice());
		Boolean isSaveOk=preStatement.execute();
		connection.close();
		return isSaveOk;
		
		
	}
	public void DeleteFoodOrderList(Integer foodOrderId) throws SQLException {
		connection = dbConnection.getConnection();
		statement=connection.createStatement();
		statement.execute("delete from foodorder where foodOrderId= '"+foodOrderId+"';");
		connection.close();
		}
	public void DeleteInRoomCostList(Integer inRoomItemId) throws SQLException {
		connection = dbConnection.getConnection();
		statement=connection.createStatement();
		statement.execute("delete from inroomcost where inRoomItemId= '"+inRoomItemId+"';");
		connection.close();
		}
	public void UpdateGuestStatus(Integer guestId) throws SQLException {
		connection = dbConnection.getConnection();
		statement=connection.createStatement();
		statement.execute("update `guest` set `guestStatus` = 'CheckOut' where (`guestId` = '"+guestId+"');");
	}
	public void UpdateRoomStatus(Integer guestRoomNo) throws SQLException {
		Room roomList=roomDataUtils.getAllRoom("select * from room where roomNumber = '"+guestRoomNo+"'").get(0);
		connection = dbConnection.getConnection();
		statement=connection.createStatement();
		statement.execute("update `room` set `roomStatus` = 'CheckOut' where (`roomId` = '"+roomList.getRoomId()+"');");
	}
}
