package pojo;

/**
 * @author anax
 * @version 2
 * This is the location data model
 */
public class Location {
	private int locationId=0;
	private String location="";
    private int area=0;
    private String category="";
    private float occupancyRate=0;
    
    
    /**
     * this is the location constructor
     * @param int locationId
     * @param String location
     * @param int area
     * @param String category
     * @param float occupancyRate
     */
    public Location(int locationId, String location, int area, String category, float occupancyRate) {
		super();
		this.locationId = locationId;
		this.location = location;
		this.area = area;
		this.category = category;
		this.occupancyRate = occupancyRate;
		
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
     * public method to get @location attribute
     * @return String
     */
	public String getLocation() {
		return location;
	}
	
	/**
     * public method to set a value to @location attribute
     * @param String location
     */
	public void setLocation(String location) {
		this.location = location;
	}
	
	/**
     * public method to get @area attribute
     * @return int
     */
	public int getArea() {
		return area;
	}
	
	/**
     * public method to set a value to @area attribute
     * @param int area
     */
	public void setArea(int area) {
		this.area = area;
	}
	
	/**
     * public method to get @category attribute
     * @return String
     */
	public String getCategory() {
		return category;
	}
	
	/**
     * public method to set a value to @category attribute
     * @param String category
     */
	public void setCategory(String category) {
		this.category = category;
	}
	
	/**
     * public method to get @occupancyRate attribute
     * @return float
     */
	public float getOccupancyRate() {
		return occupancyRate;
	}
	
	/**
     * public method to set a value to @occupancyRate attribute
     * @param float occupancyRate
     */
	public void setOccupancyRate(float occupancyRate) {
		this.occupancyRate = occupancyRate;
	}

}
