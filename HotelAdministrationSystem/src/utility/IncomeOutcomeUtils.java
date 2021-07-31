package utility;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.Income;
import bean.Outcome;
import database.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class IncomeOutcomeUtils {
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	private final DbConnection dbConnection=new DbConnection();
	
	public ObservableList<Income>getIncome(String sql) throws SQLException{
		ObservableList<Income>incomeList=FXCollections.observableArrayList();
		connection=dbConnection.getConnection();
		statement=connection.createStatement();
		resultSet=statement.executeQuery(sql);
		while(resultSet.next()) {
			incomeList.add(new Income(resultSet.getString("date"), resultSet.getDouble("totalAmount")));
		}
		connection.close();
		return incomeList;
		
	}
	public ObservableList<Outcome>getOutcome(String sql) throws SQLException{
		ObservableList<Outcome>outcomeList=FXCollections.observableArrayList();
		connection=dbConnection.getConnection();
		statement=connection.createStatement();
		resultSet=statement.executeQuery(sql);
		while(resultSet.next()) {
			outcomeList.add(new Outcome(resultSet.getString("date"), resultSet.getDouble("totalAmount")));
		}
		connection.close();
		return outcomeList;
		
	}
}
