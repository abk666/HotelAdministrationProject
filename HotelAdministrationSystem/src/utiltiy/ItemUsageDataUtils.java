package utiltiy;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import bean.ItemUsage;
import database.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ItemUsageDataUtils {
	private Connection connection;
	private Statement statement;
	private PreparedStatement preStatement;
	private ResultSet resultSet;

	private final DbConnection dbConnection=new DbConnection();
	public ObservableList<ItemUsage>getAllItemUsage(String sql) throws SQLException{
		ObservableList<ItemUsage>itemUsageList=FXCollections.observableArrayList();
		connection=dbConnection.getConnection();
		statement=connection.createStatement();
		resultSet=statement.executeQuery(sql);
		while(resultSet.next()) {
			itemUsageList.add(new ItemUsage(resultSet.getInt("itemUsageId"),
					resultSet.getString("itemName"), 
					resultSet.getInt("itemQty"), 
					resultSet.getString("itemUsageDate")));
		}
		connection.close();
		return itemUsageList;
	}
	public Boolean saveItemUsage(ItemUsage itemUsage) throws SQLException {
		connection=dbConnection.getConnection();
		preStatement=connection.prepareStatement("INSERT INTO `itemusage` (`itemName`, `itemQty`, `itemUsageDate`) "
				+ "VALUES (?, ?, ?);");
		preStatement.setString(1, itemUsage.getItemName());
		preStatement.setInt(2, itemUsage.getItemQty());
		Date date=Date.valueOf(itemUsage.getItemUsageDate());
		preStatement.setDate(3, date);

		boolean isSaveOk=preStatement.execute();
		connection.close();
		return isSaveOk;
		
	}
	public Integer updateItemUsage(ItemUsage itemUsage) throws SQLException {
		connection=dbConnection.getConnection();
		preStatement=connection.prepareStatement("UPDATE `itemusage` SET "
				+ "`itemName` = ?,"
				+ " `itemQty` = ?, "
				+ "`itemUsageDate` = ? WHERE (`itemUsageId` = ?);");
		
		preStatement.setString(1, itemUsage.getItemName());
		preStatement.setInt(2, itemUsage.getItemQty());
		Date date=Date.valueOf(itemUsage.getItemUsageDate());
		preStatement.setDate(3, date);
		preStatement.setInt(4, itemUsage.getItemUsageId());
		Integer updatedRows=preStatement.executeUpdate();
		connection.close();
		return updatedRows;
		}
	public boolean deleteItemUsage(Integer itemUsageId) throws SQLException {
		connection=dbConnection.getConnection();
		statement=connection.createStatement();
		boolean isDeleteOk=statement.execute("delete from itemUsage where itemUsageId ='"+itemUsageId+"';");
		connection.close();
		return isDeleteOk;
		
	}
	public ObservableList<String>getAllItemUsageColumn() throws SQLException{
		ObservableList<String>columnNameLists=FXCollections.observableArrayList();
		connection=dbConnection.getConnection();
		statement=connection.createStatement();
		resultSet=statement.executeQuery("select * from itemusage;");
		ResultSetMetaData metadata=resultSet.getMetaData();
		Integer count=metadata.getColumnCount();
		for(int x=1;x<=count;x++) {
			columnNameLists.add(metadata.getColumnName(x));
		}
		connection.close();
		return columnNameLists;
	}

}
