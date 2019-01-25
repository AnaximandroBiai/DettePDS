package server;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
//import java.sql.SQLException;
import java.sql.Connection;

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
	private ConnectionPool connectionP;

	public ServerProcessor(Socket s, ConnectionPool connectionP){
		this.connectionP = connectionP;
		this.sock=s;

	}

	@Override
	public void run() {
		//boolean closeConnection = false;
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
					TestDAO testInsert = new TestDAO(connectionP.getConnection());
					Test eCheck = testInsert.find(t1.getLastName(),t1.getFirstName());
					if(eCheck == null)
					{
						testInsert.create(t1);
						String reponseServ = "" + t1.getFirstName()+ " "+t1.getLastName()+" a bien été ajouté, merci !";
						writer.write(reponseServ);
						writer.flush();

					}
					else
					{
						String reponseServ = "Impossible d'ajouter la personne, "+ t1.getLastName() +" "+t1.getFirstName()+ " est déjà  enregistrée";
						writer.write(reponseServ);
						writer.flush();
					}
					connectionP.releaseConnection(connectionP.getListUsed().get(connectionP.getListUsed().size()-1));
					break;
					
				case "CONNECTION":
					//Server understands the action asked, he returns "OK"
					String toConnect = "OK for connect";
					//the Server waits for the data
					writer.write(toConnect);
					writer.flush();
					Connection con = connectionP.getConnection();
					String json = gson.toJson(con);
					System.out.println("Message transmis : "+json);
					writer.write(json);
					writer.flush();
					System.out.println(connectionP.getListDispo().size()+" available connections");
                    System.out.println(connectionP.getListUsed().size()+" used connections");
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
