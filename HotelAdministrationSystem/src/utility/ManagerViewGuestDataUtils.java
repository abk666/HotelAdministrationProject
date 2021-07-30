package utility;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import bean.Guest;
import database1.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ManagerViewGuestDataUtils {

	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
		
	private final DBConnection dbConnection = new DBConnection();
	
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
					resultSet.getDouble("guestFoodOrderPrice"), 
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
	
	public Guest getManagerViewGuest(String sql) throws SQLException{
		
		Guest guest = new Guest();
	
		connection = dbConnection.getConnection();
		statement = connection.createStatement();
		resultSet = statement.executeQuery(sql);
		
		while (resultSet.next()) {
			
			guest.setGuestId(resultSet.getInt("guestId"));
			guest.setGuestName(resultSet.getString("guestName"));
			guest.setGuestNRC(resultSet.getString("guestNRC"));
			guest.setNoOfGuests(resultSet.getInt("noOfGuests"));
			guest.setGuestPhNo(resultSet.getString("guestPhNo"));
			guest.setGuestRoomType(resultSet.getString("guestRoomType"));
			guest.setGuestRoomNo(resultSet.getInt("guestRoomNo"));
			guest.setGuestRoomPrice(resultSet.getDouble("guestRoomPrice"));
			guest.setFoodOrderPrice(resultSet.getDouble("guestFoodOrderPrice"));
			guest.setGuestInRoomCost(resultSet.getDouble("guestInRoomCost"));
			guest.setGuestCheckInDate(resultSet.getDate("guestCheckInDate").toString());
			guest.setGuestCheckOutDate(resultSet.getDate("guestCheckOutDate").toString());
			guest.setNumberOfDays(resultSet.getInt("numberOfDays"));
			guest.setGuestStatus(resultSet.getString("guestStatus"));
			
	}
		connection.close();
		
		return guest;
	}

	/*public Guest getGuestCheckInDate() throws SQLException {
		
		String date = null;
		Integer roomNo;
		
		Guest guest = null;
		
		connection = dbConnection.getConnection();
		
		statement = connection.createStatement();
		
		resultSet = statement.executeQuery("select guestRoomNo,guestCheckInDate from hoteldb.guest where guestStatus = 'CheckOut';");
		
		while(resultSet.next()) {

			roomNo = resultSet.getInt("guestRoomNo");
			date = resultSet.getDate("guestCheckInDate").toString();
			
			guest = new Guest(date,roomNo);
			
		}
		
		return guest;
		
		
	}*/
	
	/*public Guest getGuestCheckOutDate() throws SQLException {
		
		String date = null;
		Integer roomNo;
		
		Guest guest = null;
		
		connection = dbConnection.getConnection();
		
		statement = connection.createStatement();
		
		resultSet = statement.executeQuery("select roomNo,checkOutDate from hoteldb.checkout;");
		
		while(resultSet.next()) {

			roomNo = resultSet.getInt("roomNo");
			date = resultSet.getDate("checkOutDate").toString();
			
			guest = new Guest(date,roomNo);
			
		}
		
		return guest;
		
		
	}*/
	
	/*public String getGuestCheckOutDate(Integer roomNo) throws SQLException {
		
		String date = null;
		
		connection = dbConnection.getConnection();
		
		statement = connection.createStatement();
		
		resultSet = statement.executeQuery("select checkOutDate from hoteldb.checkout where roomNo = '"+roomNo+"';");
		
		while(resultSet.next()) {

			date = resultSet.getDate("checkOutDate").toString();
					
		}
		
		return date;
		
		
	}*/
	
	
	//Read ColumnLabel For ManagerViewGuest
	public ObservableList<String> getGuestColumnLabel() throws SQLException{
		
		ObservableList<String> columnLabelList = FXCollections.observableArrayList();
		connection = dbConnection.getConnection();
		statement = connection.createStatement();
		resultSet = statement.executeQuery("select * from hoteldb.guest;");
		
		ResultSetMetaData metaData = resultSet.getMetaData();
		
		Integer count = metaData.getColumnCount();
		
		for (int x = 1; x <=count; x++) {
			if (metaData.getColumnLabel(x).equals("guestStatus")) {
				continue;
			}
			
			columnLabelList.add(metaData.getColumnLabel(x));
		}
		return columnLabelList;	
		
	}
	
	
}
