package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;

import pojo.Royalties;

public class RoyaltiesDAO extends Dao<Royalties> {

	private Connection con;

	/**
	 * this is the RoyaltiesDAO constructor. This use a connection in the Connection
	 * pool to have access to the database
	 * 
	 * @param conn
	 */
	public RoyaltiesDAO(Connection conn) {
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
	public boolean create(Royalties obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Royalties obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Royalties obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Royalties find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Royalties findRoyaltiesAsked(int id) {
		ResultSet result = null;
		try {
		result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
						.executeQuery(
								"SELECT L.area, L.occupancyRate, O.storeId FROM Location as L, Occupation as O, Store as S, RoyaltiesHistory as R Where R.storeId = "
										+ id + " and R.storeId = S.storeId and S.storeId = O.storeId and O.locationId = L.locationId");
			while (result.next()) {
				int amount = (int) (166 * Math.sqrt(result.getInt("area")));
				amount += amount * result.getFloat("occupancyRate");
				amount += amount * 0.2;
				Royalties rD = new Royalties(amount, result.getInt("storeId"));
				return rD;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}
	

	public Collection<Royalties> findRoyaltiesPaid(String type, String month) {
		Collection<Royalties> royaltiesPaid = new ArrayList<Royalties>();
		ResultSet result = null;
		Month monthM = Month.valueOf(month);
		int m = monthM.getValue();
		try {
			if (type.equals("All")) {
				result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
						.executeQuery(
								"SELECT R.storeId, R.royaltiesPaid FROM RoyaltiesHistory as R, Store as S Where S.storeId = R.storeId and month(R.royaltiesDate) = '" + m + "'");
			} else {
				result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
						.executeQuery(
								"SELECT R.storeId, R.royaltiesPaid FROM RoyaltiesHistory as R, Store as S Where S.storeCategory ='" 
								+type
								+"' and S.storeId = R.storeId and month(R.royaltiesDate) = '" + m + "'");
			}
			while (result.next()) {
				Royalties rP = new Royalties(result.getInt("royaltiesPaid"), result.getInt("storeId"));
				royaltiesPaid.add(rP);
			}
			return royaltiesPaid;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}
	
	public Collection<Royalties> findRoyaltiesPaidByName(String name, String month) {
		Collection<Royalties> royaltiesPaid = new ArrayList<Royalties>();
		ResultSet result = null;
		Month monthM = Month.valueOf(month);
		int m = monthM.getValue();
		try {
				result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
						.executeQuery(
								"SELECT R.storeId, R.royaltiesPaid FROM RoyaltiesHistory as R, Store as S Where S.storeName = '" 
								+name
								+"' and S.storeId = R.storeId and month(R.royaltiesDate) = '" + m + "'");
			while (result.next()) {
				Royalties rP = new Royalties(result.getInt("royaltiesPaid"), result.getInt("storeId"));
				royaltiesPaid.add(rP);
			}
			return royaltiesPaid;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}
}
