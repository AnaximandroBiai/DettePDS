package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Month;
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

	// Old method
	/**
	 * this method allows to find a attendance list in the database with the sector
	 * of store
	 * 
	 * @param type
	 * @param month 
	 * @return Collection
	 */
	// public Collection<Attendance> find(String type) {
	// Collection<Attendance> attendances = new ArrayList<Attendance>();
	// try {
	// ResultSet result = this.connect
	// .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
	// ResultSet.CONCUR_READ_ONLY).executeQuery(
	// "SELECT A.attendanceDate, A.storeId, A.attendanceLevel FROM Attendance as A,
	// Store as S Where S.storeCategory='"
	// + type
	// + "'and S.storeId = A.storeId and (month(now()) - month(A.attendanceDate)) =
	// 1 ");
	// while (result.next()) {
	// Attendance att = new Attendance(result.getInt("storeId"),
	// result.getInt("attendanceLevel"));
	// attendances.add(att);
	// }
	// return attendances;
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// return null;
	// }

	public Collection<Attendance> find(String type, String month) {
		Collection<Attendance> Attendances = new ArrayList<Attendance>();
		ResultSet result = null;
		Month monthM = Month.valueOf(month);
		int m = monthM.getValue();
		try {
			if (type.equals("All")) {
				result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
						.executeQuery(
								"SELECT DISTINCT l.storeId FROM landmark as l, store as S Where S.storeId = l.storeId");
			} else {
				result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
						.executeQuery("SELECT DISTINCT l.storeId FROM landmark as l, store as S Where S.storeCategory='"
								+ type + "'and S.storeId = l.storeId");
			}
			while (result.next()) {
				Attendance att = new Attendance(result.getInt("storeId"), 0);
				Attendances.add(att);
			}
			for (Attendance a : Attendances) {
				int at = 0;
				ResultSet result2 = this.connect
						.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
						.executeQuery("SELECT passing FROM landmark Where storeId='" + a.getStoreId()
								+ "'and month(passingDate) = '" + m + "' and year(passingDate) = year(now())");
				while (result2.next()) {
					at += result2.getInt("passing");
				}
				a.setAttendanceLevel(at);
			}
			return Attendances;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
