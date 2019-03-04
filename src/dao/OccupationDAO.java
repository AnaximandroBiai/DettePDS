package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import pojo.Occupation;

/**
 * @author anax this is the Dao for occupation, which allows to create, delete,
 *         update or find an occupation in the data base
 */
public class OccupationDAO extends Dao<Occupation> {

	private Connection con;

	/**
	 * this is the OccupationDAO constructor. This use a connection in the
	 * Connection pool to have access to the database
	 * 
	 * @param conn
	 */
	public OccupationDAO(Connection conn) {
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
	public boolean create(Occupation obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Occupation obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Occupation obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Occupation find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * this method allows to find an Occupation row in the database
	 * 
	 * @param storeId
	 * @param locationId
	 * @return Occupation
	 */
	public Occupation find(int storeId, int locationId) {

		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(
							"SELECT locationId, storeId, arrivalDate, departureDate FROM Occupation Where storeId="
									+ storeId + "AND locationId=" + locationId);
			while (result.next()) {
				Occupation occup = new Occupation(result.getInt("locationId"), result.getInt("storeId"),
						result.getString("arrivalDate"), result.getString("departureDate"));
				return occup;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public Collection<Integer> findOccupation() throws SQLException {
		Collection<Integer> Occupations = new ArrayList<Integer>();
		try {
			ResultSet compteur = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT DISTINCT COUNT(arrivalDate) as count FROM Occupation");
			int cpt = compteur.getInt("count");
			for (int i = 1; i < cpt; i++) {
				ResultSet result = this.connect
						.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(
								"SELECT DISTINCT COUNT(arrivalDate) as count2 FROM Occupation Where departureDate = null and (month(now()) - month(arrivalDate)) = "
										+ i);
				while (result.next()) {
					int cpt2 = compteur.getInt("count2");
					Occupations.add(cpt2);
				}
			}
			return Occupations;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
