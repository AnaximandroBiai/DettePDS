package test;




import java.sql.Connection;
import java.util.Collection;

import connection.ConnectionPool;
import dao.StockDAO;
import dao.TurnoverDAO;
import pojo.Stock;
import pojo.Turnover;


public class Test {
	
	public boolean poolTest() {
		ConnectionPool p = new ConnectionPool(4);
		if(p.getListDispo().size()==4) {
			System.out.println("Success\n");
			return true;
		}
		else {
			System.out.println("Failure\n");
			return false;
		}
	}
	
	public boolean poolTest2() {
		ConnectionPool p = new ConnectionPool(4);
		p.getConnection();
		p.getConnection();
		if(p.getListDispo().size()!=3) {
			System.out.println("Failure\n");
			return false;
		}
		else {
			System.out.println("Success\n");
			return true;
		}
	}
	
	public boolean poolTest3() {
		ConnectionPool p = new ConnectionPool(0);
		if(p.getConnection() == null) {
			System.out.println("Success\n");
			return true;
		}
		else {
			System.out.println("Failure\n");
			return false;
		}
	}
	
	public boolean turnoverTest() {
		ConnectionPool p = new ConnectionPool(1);
		TurnoverDAO testDao = new TurnoverDAO(p.getConnection());
		Collection<Turnover> testTurnO= testDao.find("Sport");
		if(testTurnO != null) {
			System.out.println("Success\n");
			return true;
		}
		else {
			System.out.println("Failure\n");
			return false;
		}
	}
	
	public boolean clientReturnTest() {
		ConnectionPool p = new ConnectionPool(1);
		StockDAO testDao = new StockDAO(p.getConnection());
		Collection<Stock> testClientR= testDao.find("FastFood");
		if(testClientR != null) {
			System.out.println("Success\n");
			return true;
		}
		else {
			System.out.println("Failure\n");
			return false;
		}
	}
}
