package server;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import connection.ConnectionPool;
import dao.*;
import pojo.*;


/**
 * @author anax
 * Class with all the Server sockets and operation with database
 */
public class ServerProcessor implements Runnable {
	
	private Socket sock;
	private PrintWriter writer = null;
	private BufferedInputStream reader=null;
	private ConnectionPool connection;

	public ServerProcessor(Socket s, ConnectionPool connection){
		this.connection = connection;
		this.sock=s;

	}

	@Override
	public void run() {
		boolean closeConnection = false;
		while(!sock.isClosed()) {
			try {
				writer = new PrintWriter(sock.getOutputStream());
				reader = new BufferedInputStream(sock.getInputStream());
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String demand = read();
				switch(demand.toUpperCase()){
				
				}
			}catch (IOException /**| SQLException*/ e){
				e.printStackTrace();
			}
		}
	}
	/**
	 * This methods read the message from the client
	 * @return String
	 * @throws IOException
	 */
	private String read() throws IOException {
		String response ="";
		int stream;
		byte[] b = new byte[4096];
		stream = reader.read(b);
		response = new String(b, 0, stream);
		return response;
	}
	
}
