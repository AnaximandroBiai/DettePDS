package connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

/**
 * @author anax
 * Database connection class. Used by the connection pool
 */

public class Database {
	private static final String driver_name ="com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost/dette";
	private static final String user = "root";
	private static final String password = "";
	
	/**
	 * static methods with create an instance of the jdbc driver.
	 * In case of failure, an exception is rised
	 */
	static {
		try {
			Class.forName(driver_name).newInstance();
			System.out.println("*** Driver loaded.");
		}
		catch (ClassNotFoundException e) {
			System.err.println("*** ERROR: Driver " + driver_name + "not found");
		}
		catch (InstantiationException e) {
			System.err.println("*** ERROR: Impossible to create an instance of " + driver_name);
			System.err.println(e.getMessage());
		}
		catch (IllegalAccessException e) {
			System.err.println("*** ERROR: Impossible to create an instance of " + driver_name);
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * this method create a connection with the setting on Database's class
	 * @return Connection
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}
	
}
