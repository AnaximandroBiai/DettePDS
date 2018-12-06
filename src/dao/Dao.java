package dao;

import java.sql.Connection;
/**
 * @author anax
 * @param <T>
 * this abstract class defines all the methods and parameters needed on DAO classes
 */
public abstract class Dao<T> {

protected Connection connect = null;
	
	public Dao(Connection conn){
		this.connect= conn;
	}
	/**
	 * This method adds a line on the table
	 * @param <T>
	 * @param obj
	 * @return boolean
	 */
	public abstract boolean create(T obj);

	/**
	 * This method deletes a line on the table
	 * @param obj
	 * @return boolean
	 */
	public abstract boolean delete(T obj);

	/**
	 * This method updata the data for a choosen line on the table
	 * @param obj
	 * @return boolean
	 */
	public abstract boolean update(T obj);

	/**
	 * This method looks and find on the table an asked line
	 * @param id
	 * @return
	 */
	public abstract T find(int id);
}
