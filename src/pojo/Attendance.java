package pojo;

/**
 * @author anax
 * @version 2
 * This is the attendance data model
 */
public class Attendance {
	private String attendanceDate;
	private int storeId;
	private int attendanceLevel;
	
    /**
     * this is the attendance constructor
     * @param String attendanceDate
     * @param int storeId
     * @param int attendanceLevel
     */
	public Attendance(String attendanceDate, int storeId, int attendanceLevel) {
		super();
		this.attendanceDate = attendanceDate;
		this.storeId = storeId;
		this.attendanceLevel = attendanceLevel;
	}

	/**
     * public method to get @attendanceDate attribute
     * @return String
     */
	public String getAttendanceDate() {
		return attendanceDate;
	}

	/**
     * public method to set a value to @attendanceDate attribute
     * @param String attendanceDate
     */
	public void setAttendanceDate(String attendanceDate) {
		this.attendanceDate = attendanceDate;
	}

	/**
     * public method to get @storeId attribute
     * @return int
     */
	public int getStoreId() {
		return storeId;
	}

	/**
     * public method to set a value to @storeId attribute
     * @param int storeId
     */
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	/**
     * public method to get @attendanceLevel attribute
     * @return int
     */
	public int getAttendanceLevel() {
		return attendanceLevel;
	}

	/**
     * public method to set a value to @attendanceLevel attribute
     * @param int attendanceLevel
     */
	public void setAttendanceLevel(int attendanceLevel) {
		this.attendanceLevel = attendanceLevel;
	}
}
