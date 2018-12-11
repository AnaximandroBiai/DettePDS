package pojo;

/**
 * @author anax
 * This is the test data model
 */
public class Test {
	private int id;
	private String lastname;
	private String firstname;
	
	public Test (String nom, String prenom) {
		this.lastname = nom;
		this.firstname = prenom;
	}
	
	public Test (int id, String nom, String prenom) {
		this.id = id;
		this.lastname = nom;
		this.firstname = prenom;
	}
	
	public String getLastName() {
		return lastname;
	}
	public void setLastName(String nom) {
		this.lastname = nom;
	}
	public String getFirstName() {
		return firstname;
	}
	public void setFirstName(String prenom) {
		this.firstname = prenom;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
