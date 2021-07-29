package utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.InRoomCost;
import database.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InRoomCostDataUtils {
	private Connection connection;
	private Statement statement;
	private PreparedStatement preStatement;
	private ResultSet resultSet;
	private final DbConnection dbconnection=new DbConnection();
	
	public ObservableList<InRoomCost> getAllInRoomCost(String sql) throws SQLException{
		ObservableList<InRoomCost>inRoomCostList=FXCollections.observableArrayList();
		connection=dbconnection.getConnection();
		statement=connection.createStatement();
		resultSet=statement.executeQuery(sql);
		while(resultSet.next()) {
			inRoomCostList.add(new InRoomCost(resultSet.getInt("inRoomItemId"),
					resultSet.getString("inRoomItemName"),
					resultSet.getString("inRoomItemCategory"), 
					resultSet.getDouble("inRoomItemPrice"), 
					resultSet.getInt("inRoomItemQty"),
					resultSet.getInt("guestRoomNo"),
					resultSet.getDouble("inRoomTotalCost")));
		}
		connection.close();
		return inRoomCostList;
	}
}
