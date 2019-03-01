package pojo;

/**
 * @author anax
 * @version 2
 * This is the product data model
 */
public class Product {

    private int productId;
    private String productReference;
    private float price;
    private int keyWord;
    
	/**
     * this is the product constructor
     * @param int productId
     * @param String productReference
     * @param float price
     * @param int keyWord
     */
	public Product(int productId, String productReference, float price, int keyWord) {
		super();
		this.productId = productId;
		this.productReference = productReference;
		this.price = price;
		this.keyWord = keyWord;
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
     * public method to get @productReference attribute
     * @return String
     */
	public String getProductReference() {
		return productReference;
	}

	/**
     * public method to set a value to @productReference attribute
     * @param String productReference
     */
	public void setProductReference(String productReference) {
		this.productReference = productReference;
	}

	/**
     * public method to get @price attribute
     * @return float
     */
	public float getPrice() {
		return price;
	}

	/**
     * public method to set a value to @price attribute
     * @param float price
     */
	public void setPrice(float price) {
		this.price = price;
	}

	/**
     * public method to get @keyWord attribute
     * @return int
     */
	public int getKeyWord() {
		return keyWord;
	}

	/**
     * public method to set a value to @keyWord attribute
     * @param int keyWord
     */
	public void setKeyWord(int keyWord) {
		this.keyWord = keyWord;
	}
  
}
