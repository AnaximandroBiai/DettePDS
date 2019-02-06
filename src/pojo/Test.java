package pojo;

/**
 * @author anax
 * This is the test data model
 */
public class Test {
	private int id;
	private String lastname;
	private String firstname;
	
	 /**
     * this is a test constructor
     * @param String nom
     * @param String prenom
     */
	public Test (String nom, String prenom) {
		this.lastname = nom;
		this.firstname = prenom;
	}
	
	 /**
     * this is another test constructor
     * @param int id
     * @param String nom
     * @param String prenom
     */
	public Test (int id, String nom, String prenom) {
		this.id = id;
		this.lastname = nom;
		this.firstname = prenom;
	}
	
	 /**
     * public method to get @lastname attribute
     * @return String
     */
	public String getLastName() {
		return lastname;
	}
	
	/**
     * public method to set a value to @lastname attribute
     * @param String nom
     */
	public void setLastName(String nom) {
		this.lastname = nom;
	}
	
	/**
     * public method to get @firstname attribute
     * @return String
     */
	public String getFirstName() {
		return firstname;
	}
	
	/**
     * public method to set a value to @firstname attribute
     * @param String prenom
     */
	public void setFirstName(String prenom) {
		this.firstname = prenom;
	}
	
	/**
     * public method to get @id attribute
     * @return int
     */
	public int getId() {
		return id;
	}

	/**
     * public method to set a value to @id attribute
     * @param int id
     */
	public void setId(int id) {
		this.id = id;
	}
}
