package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import pojo.Store;

/**
 * @author anax this is the Dao for store, which allows to create, delete,
 *         update or find a store in the data base
 */
public class StoreDAO extends Dao<Store> {

	private Connection con;

	/**
	 * this is the StoreDAO constructor. This use a connection in the Connection
	 * pool to have access to the database
	 * 
	 * @param conn
	 */
	public StoreDAO(Connection conn) {
		super(conn);
		this.con = conn;
	}

	/**
	 * public method to get @con attribute
	 * 
	 * @return Connection
	 */
	public Connection getCon() {
		return con;
	}

	@Override
	public boolean create(Store obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Store obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Store obj) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * this method allows to find a Store row in the database
	 * 
	 * @param storeId
	 * @return Store
	 */
	public Store find(int storeId) {
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(
							"SELECT storeId, storeName, storeCategory, storeArea FROM Store Where storeId="
									+ storeId);
			while (result.next()) {
				Store st = new Store(result.getInt("storeId"), result.getString("storeName"),
						result.getString("storeCategory"), result.getInt("storeArea"));
				return st;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

}
