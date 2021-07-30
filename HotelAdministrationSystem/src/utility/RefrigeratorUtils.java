package utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import bean.RefrigeratorFood;
import database.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RefrigeratorUtils {

	private Connection connection ;
	private Statement statement;
	private PreparedStatement preStmt;
	private ResultSet resultSet;
	
	private final DbConnection dbConnection = new DbConnection();
	

	
}
