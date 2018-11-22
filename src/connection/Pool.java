package connection;

import java.sql.Connection;

/**
 * @author anax
 * this interface define the methods needed in Pool classes
 */
public interface Pool {
    public Connection getConnection();
    public boolean releaseConnection(Connection c);
}
