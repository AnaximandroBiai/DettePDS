package pojo;

/**
 * @author anax
 * @version 2
 * This is the turnover data model
 */
public class Turnover {
	private String turnoverDate;
	private int storeId;
	private int amount;
	
	
	
	/**
     * this is the turnover constructor
     * @param String turnoverDate
     * @param int storeId
     * @param int amount
     */
	public Turnover(String turnoverDate, int storeId, int amount) {
		super();
		this.turnoverDate = turnoverDate;
		this.storeId = storeId;
		this.amount = amount;
	}

	/**
     * public method to get @turnoverDate attribute
     * @return String
     */
	public String getTurnoverDate() {
		return turnoverDate;
	}
	
	/**
     * public method to set a value to @turnoverDate attribute
     * @param String turnoverDate
     */
	public void setTurnoverDate(String turnoverDate) {
		this.turnoverDate = turnoverDate;
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
     * public method to get @amount attribute
     * @return int
     */
	public int getAmount() {
		return amount;
	}

	/**
     * public method to set a value to @amount attribute
     * @param int amount
     */
	public void setAmount(int amount) {
		this.amount = amount;
	}
}
