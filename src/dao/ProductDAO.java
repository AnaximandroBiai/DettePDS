package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import pojo.Product;

/**
 * @author anax this is the Dao for product, which allows to create, delete,
 *         update or find a product in the data base
 */
public class ProductDAO extends Dao<Product> {

	private Connection con;

	/**
	 * this is the ProductDAO constructor. This use a connection in the Connection
	 * pool to have access to the database
	 * 
	 * @param conn
	 */
	public ProductDAO(Connection conn) {
		super(conn);
		this.con = conn;
	}

	/**
	 * public method to get @con attribute
	 * 
	 * @return Connection
	 */
	public Connection getCon() {
		return con;
	}

	@Override
	public boolean create(Product obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Product obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Product obj) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * this method allows to find a Product row in the database with its id
	 * 
	 * @param id
	 * @return Product
	 */
	@Override
	public Product find(int id) {
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(
							"SELECT productId, productReference, price, keyword FROM Product Where productIdt=" + id);
			while (result.next()) {
				Product product = new Product(result.getInt("productId"), result.getString("productReference"),
						result.getInt("price"), result.getInt("keyWord"));
				return product;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
