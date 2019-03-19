package test;




import connection.ConnectionPool;


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
}
