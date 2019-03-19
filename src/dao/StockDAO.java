package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import pojo.Stock;

/**
 * @author anax
 * this is the Dao for stock, which allows to create, delete, update or find a stock in the data base
 */
public class StockDAO extends Dao<Stock>{

	private Connection con;
	
    /**
     * this is the StockDAO constructor. This use a connection in the Connection pool to have access to the database
     * @param conn
     */
	public StockDAO(Connection conn) {
		super(conn);
		this.con = conn;
		}

	/**
	 * public method to get @con attribute
	 * @return Connection
	 */
	public Connection getCon() {
		return con;
	}

	@Override
	public boolean create(Stock obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Stock obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Stock obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Stock find(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * this method allows to find a Stock list in the database with the sector
	 * of store
	 * 
	 * @param type
	 * @return Collection
	 */
	public Collection<Stock> find(String type) {
		Collection <Stock> Stocks = new ArrayList<Stock>();
		try{
            ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT S.storeId, S.productId, S.quantity, S.arrivalDate, S.arrivalReason FROM Stock as S, KeyWord as K, Product as P Where K.nameKeyWord='"+ type +"'and K.idKeyWord = P.keyword and P.productId = S.productId and arrivalReason = 'retourclient' and (month(now()) - month(S.arrivalDate)) = 1");
            while(result.next()){
                Stock stock = new Stock(result.getInt("storeId"),result.getInt("productId"), result.getInt("Quantity"), result.getString("arrivalDate"), null, result.getString("arrivalReason"));
                Stocks.add(stock);        
            }
            return Stocks;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
	}
}
