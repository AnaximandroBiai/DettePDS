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
				case "TEST":
					//Server understands the action asked, he returns "OK"
					String toSend = "OK for insert";
					//the Server waits for the data
					writer.write(toSend);
					writer.flush();
					//the server read the data
					String request = read();
					Test t1 = gson.fromJson(request, Test.class);
					TestDAO testInsert = new TestDAO(connection.getConnection());
					Test eCheck = testInsert.find(t1.getId());
					if(eCheck == null)
					{
						testInsert.create(t1);
						Test t2 = testInsert.find(t1.getId());
						String reponseServ = "Nouvel enregistrement numéro" + t1.getId()+ " bien ajouté, merci !";
						writer.write(reponseServ);
						writer.flush();

					}
					else
					{
						String reponseServ = "Impossible de créer l'objet en question, l'id "+ t1.getId() +" est déjà utilisé";
						writer.write(reponseServ);
						writer.flush();
					}
					connection.releaseConnection(connection.getListUsed().get(connection.getListUsed().size()-1));
					break;
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
