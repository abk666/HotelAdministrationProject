package utiltiy;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.Import;
import bean.Stock;
import database.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class StockDataUtils {
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;


	private final DbConnection dbConnection=new DbConnection();
	private final ImportDataUtils importDataUtils=new ImportDataUtils();

	public ObservableList<Stock>getAllStock(String sql) throws SQLException{
		ObservableList<Stock>stockLists=FXCollections.observableArrayList();
		connection=dbConnection.getConnection();
		statement=connection.createStatement();
		resultSet=statement.executeQuery(sql);
		while(resultSet.next()) {
			stockLists.add(new Stock(resultSet.getInt("stockId"),
					resultSet.getString("itemName"),
					resultSet.getInt("itemStock")));
		}
		connection.close();
		return stockLists;
	}
	public Integer saveStock() throws SQLException {
		Integer isSaveOk=0;
		connection=dbConnection.getConnection();
		ObservableList<Import>importList=importDataUtils.getTotalQty("select importItemName,sum(importItemQty) as TOTAL_QUANTITY from import where itemStatus='Good' group by importItemName ;");
		for(Import importItem:importList) {
			statement=connection.createStatement();
			isSaveOk=statement.executeUpdate("insert into stock(itemName,itemStock) values('"+importItem.getImportItemName()+"','"+importItem.getImportItemQty()+"') on duplicate key update itemName='"+importItem.getImportItemName()+"',itemStock='"+importItem.getImportItemQty()+"';");

		}
		connection.close();
		return isSaveOk;
		}
	public boolean reduceStock(Integer stockQty,Integer itemQty,Integer stockId) throws SQLException {
		Integer newStock=stockQty-itemQty;
		connection=dbConnection.getConnection();
		statement=connection.createStatement();
		boolean isReducedOk=statement.execute("update `stock` set `itemStock` = '"+newStock+"' where (`stockId` = '"+stockId+"');");
		
		connection.close();
		return isReducedOk;
		
	}
	public void reduceExpiredItem() throws SQLException {
		connection=dbConnection.getConnection();

		ObservableList<Import>importList=importDataUtils.getTotalQty("select importItemName,sum(importItemQty) as TOTAL_QUANTITY from import where itemStatus='Expired' group by importItemName ;");
		for(Import importItem:importList) {
			statement=connection.createStatement();
          statement.executeUpdate("update `stock` set `itemStock` = '0' where (`itemName` = '"+importItem.getImportItemName()+"');");
		  connection.close();
		  
		}

	}
}
