package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import pojo.Location;

/**
 * @author anax this is the Dao for locations, which allows to create, delete,
 *         update or find a location in the data base
 */
public class LocationDAO extends Dao<Location> {

	private Connection con;

	/**
	 * this is the LocationDAO constructor. This use a connection in the Connection
	 * pool to have access to the database
	 * 
	 * @param conn
	 */
	public LocationDAO(Connection conn) {
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
	public boolean create(Location obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Location obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Location obj) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * this method allows to find an location row in the database with its id
	 * 
	 * @param locationId
	 * @return Location
	 */
	@Override
	public Location find(int locationId) {
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(
							"SELECT locationId, location, area, category, occupancyRate FROM Location Where locationId="
									+ locationId);
			while (result.next()) {
				Location loc = new Location(result.getInt("locationId"), result.getString("location"),
						result.getInt("area"), result.getString("category"), result.getFloat("occupancyRate"));
				return loc;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * this method allows to count the number of location row in the database
	 * 
	 * @return int
	 */
	public Integer locationNb() {
		try {
			ResultSet compteur = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT DISTINCT COUNT(*) as count FROM Loaction");
			int cpt = compteur.getInt("count");
			return cpt;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
