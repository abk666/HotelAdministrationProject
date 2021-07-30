package utility;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import bean.CheckOut;
import database1.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ManagerViewGuestCheckOutDataUtils {

	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
		
	private final DBConnection dbConnection = new DBConnection();
	
	public ObservableList<CheckOut>getAllGuestCheckOut(String sql) throws SQLException{
		
		ObservableList<CheckOut> guestCheckOutList=FXCollections.observableArrayList();
	
		connection = dbConnection.getConnection();
		statement = connection.createStatement();
		resultSet = statement.executeQuery(sql);
		
		while (resultSet.next()) {
			
			guestCheckOutList.add(new CheckOut(resultSet.getInt("checkOutId"),
					resultSet.getString("checkOutGuestName"), 
					resultSet.getString("checkOutGuestNRC"), 
					resultSet.getString("checkOutGuestPhNo"), 
					resultSet.getInt("roomNo"), 
					resultSet.getString("checkOutDate").toString(), 
					resultSet.getDouble("totalPrice")
					));
			
				
		}
		connection.close();
		
		return guestCheckOutList;
	}

	public ObservableList<String> getGuestCheckoutColumnLabel() throws SQLException{
		
		ObservableList<String> columnLabelList = FXCollections.observableArrayList();
		
		connection = dbConnection.getConnection();
		statement = connection.createStatement();
		resultSet = statement.executeQuery("select checkoutId as Id,checkoutDate as Date,totalPrice as Amount from hoteldb.checkout;");
		
		ResultSetMetaData metaData = resultSet.getMetaData();
		
		Integer count = metaData.getColumnCount();
		
		for (int x = 1; x <=count; x++) {
			
			columnLabelList.add(metaData.getColumnLabel(x));
		}
		return columnLabelList;	
		
	}
	
}
