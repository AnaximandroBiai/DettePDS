package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import pojo.Turnover;

/**
 * @author anax this is the Dao for turnovers, which allows to create, delete,
 *         update or find a turnover in the data base
 */
public class TurnoverDAO extends Dao<Turnover> {

	private Connection con;

	/**
	 * this is the TurnoverDAO constructor. This use a connection in the Connection
	 * pool to have access to the database
	 * 
	 * @param conn
	 */
	public TurnoverDAO(Connection conn) {
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
	public boolean create(Turnover obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Turnover obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Turnover obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Turnover find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * this method allows to find a turnover list in the database with the sector of
	 * store
	 * 
	 * @param type
	 * @return Collection
	 */
	public Collection<Turnover> find(String type) {
		Collection<Turnover> Turnovers = new ArrayList<Turnover>();
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(
							"SELECT T.turnoverDate, T.storeId, T.amount FROM Turnover as T, Store as S Where S.storeCategory='"
									+ type
									+ "'and S.storeId = T.storeId and (month(now()) - month(T.turnoverDate)) = 1");
			while (result.next()) {
				Turnover turnO = new Turnover(result.getString("turnoverDate"), result.getInt("storeId"),
						result.getInt("amount"));
				Turnovers.add(turnO);
			}
			return Turnovers;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
