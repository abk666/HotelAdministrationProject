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

public class FinancialUtils {
private Connection connection;
private Statement statement;
private ResultSet resultSet;
private final DbConnection dbConnection=new DbConnection();
private final IncomeOutcomeUtils incomeOutcomeUtils=new IncomeOutcomeUtils();

public void saveIncome() throws SQLException {
	connection=dbConnection.getConnection();
	statement=connection.createStatement();
	ObservableList<Income> incomelist=incomeOutcomeUtils.getIncome("select checkOutDate as date,sum(totalPrice)as totalAmount from checkout group by checkOutDate order by checkOutDate asc;");
	for(Income income:incomelist) {
		statement.execute("insert into income(date,totalAmount) values('"+income.getDate()+"','"+income.getTotalAmount()+"') on duplicate key update date='"+income.getDate()+"',totalAmount='"+income.getTotalAmount()+"';");
	}
	connection.close();
}
public void saveOutcome() throws SQLException {
	connection=dbConnection.getConnection();
	statement=connection.createStatement();
	ObservableList<Outcome>outcomeList=incomeOutcomeUtils.getOutcome("select importDate as date,sum(totalPrice)as totalAmount from import group by importDate order by importDate asc;");
	for(Outcome outcome:outcomeList) {
		statement.execute("insert into outcome(date,totalAmount) values('"+outcome.getDate()+"','"+outcome.getTotalAmount()+"') on duplicate key update date='"+outcome.getDate()+"',totalAmount='"+outcome.getTotalAmount()+"';");
	}
	
}

}
