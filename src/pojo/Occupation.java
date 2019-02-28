package pojo;

/**
 * @author anax
 * @version 2
 * This is the occupation data model
 */
public class Occupation {

    private int locationId;
    private int storeId;
    private String arrivalDate;
    private String departureDate;
    
	/**
     * this is the occupation constructor
     * @param int locationId
     * @param int storeId
     * @param String arrivalDate
     * @param String departureDate
     */
	public Occupation(int locationId, int storeId, String arrivalDate, String departureDate) {
		super();
		this.locationId = locationId;
		this.storeId = storeId;
		this.arrivalDate = arrivalDate;
		this.departureDate = departureDate;
	}

	/**
     * public method to get @locationId attribute
     * @return int
     */
	public int getLocationId() {
		return locationId;
	}

	/**
     * public method to set a value to @locationId attribute
     * @param int locationId
     */
	public void setLocationId(int locationId) {
		this.locationId = locationId;
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
     * public method to get @arrivalDate attribute
     * @return String
     */
	public String getArrivalDate() {
		return arrivalDate;
	}

	/**
     * public method to set a value to @arrivalDate attribute
     * @param String arrivalDate
     */
	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	/**
     * public method to get @departureDate attribute
     * @return String
     */
	public String getDepartureDate() {
		return departureDate;
	}

	/**
     * public method to set a value to @departureDate attribute
     * @param String departureDate
     */
	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}
    
    
}
