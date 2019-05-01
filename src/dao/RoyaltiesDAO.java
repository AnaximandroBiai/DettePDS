package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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

	public Royalties findRoyaltiesDue(int id) {
		ResultSet result = null;
		try {
		result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
						.executeQuery(
								"SELECT L.area, L.occupancyRate, O.storeId FROM Location as L, Occupation as O, Store as S, RoyaltiesHistory as R Where R.storeId = "
										+ id + " and R.storeId = S.storeId and S.storeId = O.storeId and O.locationId = L.locationId");
			while (result.next()) {
				int amount = (int) (166 * Math.sqrt(result.getInt("L.area")));
				amount += amount * result.getInt("L.occupancyRate");
				amount += amount * 0.2;
				Royalties rD = new Royalties(amount, result.getInt("S.storeId"));
				return rD;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}
	

	public Collection<Royalties> findRoyaltiesPaid(String type) {
		Collection<Royalties> royaltiesPaid = new ArrayList<Royalties>();
		ResultSet result = null;
		try {
			if (type.equals("All")) {
				result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
						.executeQuery(
								"SELECT R.storeId, R.royaltiesPaid FROM RoyaltiesHistory as R, Store as S Where"
										+ " S.storeId = R.storeId and (month(now()) - month(R.royaltiesDate)) = 1");
			} else {
				result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
						.executeQuery(
								"SELECT R.storeId, R.royaltiesPaid FROM RoyaltiesHistory as R, Store as S Where S.storeCategory = " 
								+type
								+" and S.storeId = R.storeId and (month(now()) - month(R.royaltiesDate)) = 1");
			}
			while (result.next()) {
				Royalties rP = new Royalties(result.getInt("R.royaltiesPaid"), result.getInt("S.storeId"));
				royaltiesPaid.add(rP);
			}
			return royaltiesPaid;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}
	
	public Collection<Royalties> findRoyaltiesPaidByName(String name) {
		Collection<Royalties> royaltiesPaid = new ArrayList<Royalties>();
		ResultSet result = null;
		try {
				result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
						.executeQuery(
								"SELECT R.storeId, R.royaltiesPaid FROM RoyaltiesHistory as R, Store as S Where S.storeName = " 
								+name
								+" and S.storeId = R.storeId and (month(now()) - month(R.royaltiesDate)) = 1");
			while (result.next()) {
				Royalties rP = new Royalties(result.getInt("R.royaltiesPaid"), result.getInt("S.storeId"));
				royaltiesPaid.add(rP);
			}
			return royaltiesPaid;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}
}
