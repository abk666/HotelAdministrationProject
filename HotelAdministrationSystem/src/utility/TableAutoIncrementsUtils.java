package utility;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import database.DbConnection;

public class TableAutoIncrementsUtils {
	private Connection connection;
	private Statement statement;
	
	private final DbConnection dbConnection=new DbConnection();
	public void setAutocrementId(String tableName,Integer id) throws SQLException {
		connection=dbConnection.getConnection();
		statement=connection.createStatement();
		statement.execute("alter table "+tableName+" auto_increment = "+id+";");
	}
}
