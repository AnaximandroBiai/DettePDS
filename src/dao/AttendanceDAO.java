package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import pojo.Attendance;

/**
 * @author anax this is the Dao for attendance, which allows to create, delete,
 *         update or find an attendance in the data base
 */
public class AttendanceDAO extends Dao<Attendance> {

	private Connection con;

	/**
	 * this is the AttendanceDAO constructor. This use a connection in the
	 * Connection pool to have access to the database
	 * 
	 * @param conn
	 */
	public AttendanceDAO(Connection conn) {
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
	public boolean create(Attendance obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Attendance obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Attendance obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Attendance find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * this method allows to find a attendance list in the database with the sector
	 * of store
	 * 
	 * @param type
	 * @return Collection
	 */
	public Collection<Attendance> find(String type) {
		Collection<Attendance> attendances = new ArrayList<Attendance>();
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(
							"SELECT A.attendanceDate, A.storeId, A.attendanceLevel FROM Attendance as A, Store as S Where S.storeCategory='"
									+ type
									+ "'and S.storeId = A.storeId and (month(now()) - month(A.attendanceDate)) = 1 ");
			while (result.next()) {
				Attendance att = new Attendance(result.getString("attendanceDate"), result.getInt("storeId"),
						result.getInt("attendanceLevel"));
				attendances.add(att);
			}
			return attendances;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
