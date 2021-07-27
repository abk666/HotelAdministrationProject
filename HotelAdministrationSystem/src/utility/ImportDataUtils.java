package utility;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import bean.Import;
import database.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ImportDataUtils {
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	private PreparedStatement preStatement;
	private final DbConnection dbConnection=new DbConnection();

	//Extraction items from database
	public ObservableList<Import> getAllImportItems(String sql) throws SQLException{
		ObservableList<Import> importLists=FXCollections.observableArrayList();
		connection=dbConnection.getConnection();
		statement=connection.createStatement();
		resultSet=statement.executeQuery(sql);
		
		while(resultSet.next()) {
			importLists.add(new Import(resultSet.getInt("importId"),
					resultSet.getString("importItemName"),
					resultSet.getString("importItemCategory"), 
					resultSet.getDouble("importItemPrice"),
					resultSet.getInt("importItemQty"),
					resultSet.getString("importItemUnit"),
					resultSet.getString("importDate"),
					resultSet.getString("importItemExpiredDate"),
					resultSet.getString("accountantUserName"),
					resultSet.getDouble("totalPrice"),
					resultSet.getString("itemStatus")));
		}
		connection.close();
		return importLists;
		
	}
	public ObservableList<Import> getTotalQty(String sql) throws SQLException{
		ObservableList<Import> importLists=FXCollections.observableArrayList();
		connection=dbConnection.getConnection();
		statement=connection.createStatement();
		resultSet=statement.executeQuery(sql);
		
		while(resultSet.next()) {
			importLists.add(new Import(resultSet.getString("importItemName"), resultSet.getInt("TOTAL_QUANTITY")));
		}
		connection.close();
		return importLists;
		

		
	}
	// save items to database
	public Boolean saveImportItems(Import importItem) throws SQLException {
		connection=dbConnection.getConnection();
		preStatement=connection.prepareStatement("INSERT INTO `import` (`importItemName`, `importItemCategory`, `importItemPrice`, `importItemQty`, `importItemUnit`, `importDate`, `importItemExpiredDate`, `accountantUserName`, `totalPrice`,`itemStatus`)"
				                               + "  VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
		preStatement.setString(1, importItem.getImportItemName());
		preStatement.setString(2, importItem.getImportItemCategory());
		preStatement.setDouble(3, importItem.getImportItemPrice());
		preStatement.setInt(4, importItem.getImportItemQty());
		preStatement.setString(5, importItem.getImportItemUnit());
		Date date=Date.valueOf(importItem.getImportDate());
		preStatement.setDate(6,date);
		Date expiredDate=Date.valueOf(importItem.getImportItemExpiredDate());
		preStatement.setDate(7, expiredDate);
		preStatement.setString(8, importItem.getAccountantUserName());
		preStatement.setDouble(9, importItem.getTotalPrice());
		preStatement.setString(10, importItem.getItemStatus());
		
		Boolean isSaveOk=preStatement.execute();
		connection.close();
		return isSaveOk;
		
		}
	//update items from database
	public Integer updateImportItems(Import importItem) throws SQLException {
		connection=dbConnection.getConnection();
		preStatement=connection.prepareStatement("UPDATE `import` SET `importItemName` = ?,"
				+ " `importItemCategory` = ?,"
				+ " `importItemPrice` = ?,"
				+ " `importItemQty` = ?, "
				+ "`importItemUnit` = ?,"
				+ " `importDate` = ?,"
				+ " `importItemExpiredDate` = ?,"
				+ " `accountantUserName` = ?, "
				+ "`totalPrice` = ? WHERE (`importId` = ?);");
		
		preStatement.setString(1, importItem.getImportItemName());
		preStatement.setString(2, importItem.getImportItemCategory());
		preStatement.setDouble(3, importItem.getImportItemPrice());
		preStatement.setInt(4, importItem.getImportItemQty());
		preStatement.setString(5, importItem.getImportItemUnit());
		Date date=Date.valueOf(importItem.getImportDate());
		preStatement.setDate(6,date);
		Date expiredDate=Date.valueOf(importItem.getImportItemExpiredDate());
		preStatement.setDate(7, expiredDate);
		preStatement.setString(8, importItem.getAccountantUserName());
		preStatement.setDouble(9, importItem.getTotalPrice());
		preStatement.setInt(10, importItem.getImportId());
		
		Integer isUpdateOk=preStatement.executeUpdate();
		connection.close();
		return isUpdateOk;
		
	}
	//delete items from database
	public Boolean deleteImportItems(Import importItem) throws SQLException {
		connection=dbConnection.getConnection();
		statement=connection.createStatement();
		Boolean isDeleteOk=statement.execute("delete from import where importId ='"+importItem.getImportId()+"';");
		connection.close();
		return isDeleteOk;
	}
	public Boolean isExpired(LocalDate todayDate,String expiredDate,Integer importId) throws SQLException {
		long daysDifferece=ChronoUnit.DAYS.between(todayDate, LocalDate.parse(expiredDate));
		
		if(daysDifferece<0) {
			connection=dbConnection.getConnection();
			preStatement=connection.prepareStatement("UPDATE `import` SET `itemStatus` = ? WHERE (`importId` = ?);");
			preStatement.setString(1, "Expired");
			preStatement.setInt(2, importId);
			Boolean isExpired=preStatement.execute();
			connection.close();
			return isExpired;
		}
		return null;
		
	}

	//extraction column names
	public ObservableList<String> getAllImportColumn() throws SQLException{
		ObservableList<String>columnNameLists=FXCollections.observableArrayList();
		connection=dbConnection.getConnection();
		statement=connection.createStatement();
		resultSet=statement.executeQuery("select * from import;");
		ResultSetMetaData metadata=resultSet.getMetaData();
		Integer count=metadata.getColumnCount();
		for(int x=1;x<=count;x++) {
			columnNameLists.add(metadata.getColumnName(x));
		}
		connection.close();
		return columnNameLists;
	}
}
