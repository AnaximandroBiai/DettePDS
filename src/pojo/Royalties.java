package pojo;


/**
 * @author anax
 * @version 1
 * This is the royalties data model
 */
public class Royalties {
	
	private int amount;
	private int storeId;
	
	/**
     * this is the royalties constructor
     * @param int amount
     * @param int storeId
     */
	public Royalties(int amount, int storeId) {
		super();
		this.amount = amount;
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
	
	
}
