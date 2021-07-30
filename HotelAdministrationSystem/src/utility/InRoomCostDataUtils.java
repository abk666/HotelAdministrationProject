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
	public boolean saveInRoomCost(InRoomCost inroomcost) throws SQLException {
		connection=dbconnection.getConnection();
		preStatement=connection.prepareStatement("INSERT INTO `inroomcost` (`inRoomItemName`, `inRoomItemCategory`, `inRoomItemPrice`, `inRoomItemQty`, `guestRoomNo`, `inRoomTotalCost`) "
				                                 + "VALUES ( ?, ?, ?, ?, ?, ?);");
		preStatement.setString(1,inroomcost.getInRoomItemName());
		preStatement.setString(2, inroomcost.getInRoomItemCategory());
		preStatement.setDouble(3, inroomcost.getInRoomItemPrice());
		preStatement.setInt(4, inroomcost.getInRoomItemQuantity());
		preStatement.setInt(5, inroomcost.getGuestRoomNo());
		preStatement.setDouble(6, inroomcost.getInRoomTotalCost());
		boolean isSaveOk=preStatement.execute();
		connection.close();
		return isSaveOk;
	}
}
