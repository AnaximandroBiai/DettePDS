package pojo;

/**
 * @author anax
 * @version 2
 * This is the store data model
 */
public class Store {
	
	private int storeId = 0;
	private String storeName ="";
	private String storeCategory ="";
	private int storeArea = 0;
	
	/**
     * this is the store constructor
     * @param int storeId
     * @param String storeName
     * @param String storeCategory
     * @param int storeArea
     */
	public Store(int storeId, String storeName, String storeCategory, int storeArea) {
		super();
		this.storeId = storeId;
		this.storeName = storeName;
		this.storeCategory = storeCategory;
		this.storeArea = storeArea;
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
     * public method to get @storeName attribute
     * @return String
     */
	public String getStoreName() {
		return storeName;
	}

	/**
     * public method to set a value to @storeName attribute
     * @param String storeName
     */
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	/**
     * public method to get @storeCategory attribute
     * @return String
     */
	public String getStoreCategory() {
		return storeCategory;
	}

	/**
     * public method to get @storeCategory attribute
     * @return String
     */
	public void setStoreCategory(String storeCategory) {
		this.storeCategory = storeCategory;
	}

	/**
     * public method to get @storeArea attribute
     * @return int
     */
	public int getStoreArea() {
		return storeArea;
	}

	/**
     * public method to get @storeArea attribute
     * @return int
     */
	public void setStoreArea(int storeArea) {
		this.storeArea = storeArea;
	}

	
}
