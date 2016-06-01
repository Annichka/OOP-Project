package connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/* 
 * A singleton database access.
 */
public class DataBase {
	
	private Connection conn;
	private Statement statement;
	public static DataBase db;
	 
	public DataBase() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.conn = (Connection) DriverManager.getConnection(
					MyDBInfo.MYSQL_DATABASE_SERVER, MyDBInfo.MYSQL_USERNAME,
					MyDBInfo.MYSQL_PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public Connection getConnection() {
		return this.conn;
	}
	public static synchronized DataBase getDbConnection() {
		if(db == null)
			db = new DataBase();
		return db;
	}
 
	public ResultSet runSql(String sql) throws SQLException {
		statement = (Statement) db.conn.createStatement();
		ResultSet res = statement.executeQuery(sql);
		return res;
	}
}
