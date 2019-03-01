package pojo;

/**
 * @author anax
 * @version 2
 * This is the stock data model
 */
public class Stock {
	
	private int storeId = 0;
	private int productId = 0;
	private int quantity = 0;
	private String arrivalDate;
	private String departureDate;
	private String arrivalReason = "";
	
	/**
     * this is the stock constructor
     * @param int storeId
     * @param int productId
     * @param int quantity
     * @param String arrivalDate
     * @param String departureDate
     */
	public Stock(int storeId, int productId, int quantity, String arrivalDate, String departureDate,
			String arrivalReason) {
		super();
		this.storeId = storeId;
		this.productId = productId;
		this.quantity = quantity;
		this.arrivalDate = arrivalDate;
		this.departureDate = departureDate;
		this.arrivalReason = arrivalReason;
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
     * @param int productId
     */
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	/**
     * public method to get @productId attribute
     * @return int
     */
	public int getProductId() {
		return productId;
	}

	/**
     * public method to set a value to @productId attribute
     * @param int productId
     */
	public void setProductId(int productId) {
		this.productId = productId;
	}

	/**
     * public method to get @quantity attribute
     * @return int
     */
	public int getQuantity() {
		return quantity;
	}

	/**
     * public method to set a value to @quantity attribute
     * @param int quantity
     */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
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

	/**
     * public method to get @arrivalReason attribute
     * @return String
     */
	public String getArrivalReason() {
		return arrivalReason;
	}

	/**
     * public method to set a value to @arrivalReason attribute
     * @param String arrivalReason
     */
	public void setArrivalReason(String arrivalReason) {
		this.arrivalReason = arrivalReason;
	}

}
