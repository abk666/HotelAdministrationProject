package utility;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.Import;
import bean.ItemUsage;
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
	private final ItemUsageDataUtils itemUsageDataUtils=new ItemUsageDataUtils();

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
	public void saveStock() throws SQLException {
       Integer updatedRows=0;
		connection=dbConnection.getConnection();
		ObservableList<Import>importList=importDataUtils.getTotalQty("select importItemName,sum(importItemQty) as TOTAL_QUANTITY from import where itemStatus='Good' group by importItemName ;");
		for(Import importItem:importList) {
			statement=connection.createStatement();
			Integer tempStockId;
			StockDataUtils stockDataUtils=new StockDataUtils();
			TableAutoIncrementsUtils autoIncrementsUtils=new TableAutoIncrementsUtils();
	    	ObservableList<Stock>stockList=stockDataUtils.getAllStock("select * from stock;");
	    	if(stockList.size()==0) {
	    		tempStockId=1;
	    	}else {
	    		Integer index=stockList.size();
	    		Stock tempStock=stockList.get(index-1);
	    		tempStockId=tempStock.getStockId();
	    	}
	    	autoIncrementsUtils.setAutocrementId("stock", tempStockId);
          updatedRows=statement.executeUpdate("insert into stock(itemName,itemStock) values('"+importItem.getImportItemName()+"','"+importItem.getImportItemQty()+"') on duplicate key update itemName='"+importItem.getImportItemName()+"',itemStock='"+importItem.getImportItemQty()+"';");

		}
//		if(updatedRows<1) {
		if(itemUsageDataUtils.getTotalUsage("select itemName,sum(itemQty) as TOTAL_QUANTITY from itemusage group by itemName ;").size()>=0) {
			ObservableList<ItemUsage>itemUsageList=itemUsageDataUtils.getTotalUsage("select itemName,sum(itemQty) as TOTAL_QUANTITY from itemusage group by itemName ;");
			for(ItemUsage itemUsage: itemUsageList) {
				StockDataUtils stockDataUtils=new StockDataUtils();
				Stock stock=stockDataUtils.getAllStock("select * from stock where itemName = '"+itemUsage.getItemName()+"';").get(0);
				connection=dbConnection.getConnection();
				Integer totalStock;
				Integer newStock=stock.getItemStock()-itemUsage.getItemQty();
				if(newStock<0) {
					totalStock=0;
				}else {
					totalStock=newStock;
				}
				statement=connection.createStatement();
				statement.execute("update `stock` set `itemStock` = '"+totalStock+"' where (`stockId` = '"+stock.getStockId()+"');");
			}
		}
			
//		}
	
		
		
		connection.close();

		}
	public boolean reduceStock(Integer stockQty,Integer itemQty,Integer stockId) throws SQLException {
		Integer newStock=stockQty-itemQty;
		Integer itemStock=0;
		if(newStock<0) {
			itemStock=0;
		}else {
			itemStock=newStock;
		}
		connection=dbConnection.getConnection();
		statement=connection.createStatement();
		boolean isReducedOk=statement.execute("update `stock` set `itemStock` = '"+itemStock+"' where (`stockId` = '"+stockId+"');");
		
		connection.close();
		return isReducedOk;
		
	}
////	public void reduceExpiredItem() throws SQLException {
////		connection=dbConnection.getConnection();
////
////		ObservableList<Import>importList=importDataUtils.getTotalQty("select importItemName,sum(importItemQty) as TOTAL_QUANTITY from import where itemStatus='Expired' group by importItemName ;");
////		for(Import importItem:importList) {
////			statement=connection.createStatement();
////          statement.executeUpdate("update `stock` set `itemStock` = '0' where (`itemName` = '"+importItem.getImportItemName()+"');");
////		  connection.close();
////		  
////		}
//
//	}
}
