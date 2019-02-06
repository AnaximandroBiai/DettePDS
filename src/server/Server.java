package server;

import connection.ConnectionPool;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author anax
 * Class which instance the Connection Pool and the thread pool
 */

public class Server {
	 	private int port =5000;
	    private ServerSocket server = null;
	    private boolean isRunning = true;
	    /**
	     * this is the Server constructor
	     */
	    public Server(){
	        try{
	        	//To test in local machine please uncomment the lines below:
	        	//InetAddress bindAddr = InetAddress.getByName("127.0.0.1");
	        	//server = new ServerSocket(port, 100, bindAddr);
	        	server = new ServerSocket(port, 100, InetAddress.getLocalHost());
	            System.out.println("the Server"+InetAddress.getLocalHost()+ "is listening on port" + port);
	            ConnectionPool conP = new ConnectionPool(2);
	            System.out.println(conP.getListDispo().size()+" available connections");
                System.out.println(conP.getListUsed().size()+" used connections");
	            this.open(conP);
	        } catch (UnknownHostException e){
	            e.printStackTrace();
	        } catch (IOException e1){
	            e1.printStackTrace();
	        }
	    }
	    /**
	     * Public method to open the server
	     * @param ConnectionPool con
	     */
	    public void open(ConnectionPool con){

	        Thread t = new Thread(new Runnable() {

	            public void run() {
	                
	                while (isRunning){
	                    try{
	                        Socket s1 = server.accept();
	        	            Thread t = new Thread(new server.ServerProcessor(s1, con));
	    	                t.start();
	                    	
	                    }catch(IOException e){
	                        e.printStackTrace();
	                    }
	                }
	                try{
	                    server.close();
	                }catch (IOException e){
	                    e.printStackTrace();
	                }
	            }
	        });
	        t.start();
	    }
	    /**
	     * Public method to close the server
	     */
	    public void close(){
	        isRunning = false;
	    }
}
