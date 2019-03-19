package server;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
//import java.sql.SQLException;
import java.sql.Connection;
import java.util.Collection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import connection.ConnectionPool;
import dao.*;
import pojo.*;

/**
 * @author anax Class with all the Server sockets and operation with database
 */
public class ServerProcessor implements Runnable {

	private Socket sock;
	private PrintWriter writer = null;
	private BufferedInputStream reader = null;
	private ConnectionPool conP;
	private Connection con;

	/**
	 * this is the ServerProcessor constructor
	 * 
	 * @param Socket
	 *            s
	 * @param ConnectionPool
	 *            conP
	 */
	public ServerProcessor(Socket s, ConnectionPool conP) {
		this.conP = conP;
		this.sock = s;

	}

	@Override
	/**
	 * Public method to run the ServerProcessor thread
	 */
	public void run() {
		try {
			writer = new PrintWriter(sock.getOutputStream());
			reader = new BufferedInputStream(sock.getInputStream());
			while (!sock.isClosed()) {
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String demand = read();
				switch (demand.toUpperCase()) {
				case "TEST\n":
					// Server understands the action asked, he returns "OK"
					String toSend = "OK for insert\n";
					// the Server send the response to the demand
					writer.write(toSend);
					writer.flush();
					// the server read the data
					String request = read();
					// the server convert the Json to a test object
					Test t1 = gson.fromJson(request, Test.class);
					TestDAO testInsert = new TestDAO(this.con);
					// the DAO check if the person to insert exist in the database
					Test eCheck = testInsert.find(t1.getLastName(), t1.getFirstName());
					if (eCheck == null) {
						testInsert.create(t1);
						String reponseServ = "" + t1.getFirstName() + " " + t1.getLastName()
								+ " a bien été ajouté, merci !\n";
						writer.write(reponseServ);
						writer.flush();

					} else {
						String reponseServ = "Impossible d'ajouter la personne, " + t1.getLastName() + " "
								+ t1.getFirstName() + " est déjà  enregistrée\n";
						writer.write(reponseServ);
						writer.flush();
					}
					break;

				case "CONNECTION\n":
					// Server understands the action asked, he returns "OK"
					String toConnect = "OK for connect\n";
					// the Server send the response to the demand
					writer.write(toConnect);
					writer.flush();
					Connection conC = conP.getConnection();
					if (conC == null) {
						String json = "No connection available, try later\n";
						writer.write(json);
						writer.flush();
						sock.close();
					} else {
						this.con = conC;
						String json = "Connection Done\n";
						writer.write(json);
						writer.flush();
						TestDAO categories = new TestDAO(this.con);
						Collection<String> cats = categories.findCategories();
						String jsonC = gson.toJson(cats);
						writer.write(jsonC);
						writer.flush();
						Collection<String> types = categories.findTypes();
						String jsonT = gson.toJson(types);
						writer.write(jsonT);
						writer.flush();
						System.out.println(conP.getListDispo().size() + " available connections");
						System.out.println(conP.getListUsed().size() + " used connections");
					}
					break;
				case "FINDTURNOVERS\n":
					// Server understands the action asked, he returns "OK"
					String toSendT = "OK for find\n";
					// the Server waits for the data
					writer.write(toSendT);
					writer.flush();
					// the server read the data
					String toFindT = read();
					System.out.println("Donnée reçue sur le server: " + toFindT);
					TurnoverDAO tDaoFind = new TurnoverDAO(con);
					Collection<Turnover> tFind = tDaoFind.find(toFindT);
					String jsonFindT = gson.toJson(tFind);
					// the server looks and find (or not) the data asked and return his answer to
					// the client
					if (jsonFindT == null) {
						String failFind = "";
						writer.write(failFind);
						writer.flush();

					} else {
						writer.write(jsonFindT);
						writer.flush();

					}
					break;
				case "FINDSTORE\n":
					// Server understands the action asked, he returns "OK"
					String toSendS = "OK for find\n";
					// the Server waits for the data
					writer.write(toSendS);
					writer.flush();
					// the server read the data
					String toFindS = read();
					int sTId = Integer.valueOf(toFindS);
					System.out.println("Donnée reçue sur le server: " + sTId);
					StoreDAO sDaoFind = new StoreDAO(con);
					Store sFind = sDaoFind.find(sTId);
					String jsonFindS = gson.toJson(sFind);
					// the server looks and find (or not) the data asked and return his answer to
					// the client
					if (jsonFindS == null) {
						String failFind = "";
						writer.write(failFind);
						writer.flush();

					} else {
						writer.write(jsonFindS);
						writer.flush();

					}
					break;
				case "FINDATTENDANCE\n":
					//Server understands the action asked, he returns "OK"
					String toSendA = "OK for find\n";
					//the Server waits for the data
					writer.write(toSendA);
					writer.flush();
					//the server read the data
					String toFindA = read();
					System.out.println("Donnée reçue sur le server: "+toFindA);
					AttendanceDAO aDaoFind = new AttendanceDAO(con);
					Collection<Attendance> aFind = aDaoFind.find(toFindA);
					String jsonFindF = gson.toJson(aFind);
					//the server looks and find (or not) the data asked and return his answer to the client
					if(jsonFindF == null){
						String failFind = "";
						writer.write(failFind);
						writer.flush();

					}else {
						writer.write(jsonFindF);
						writer.flush();

					}
					break;
				case "FINDRETURNS\n":
					//Server understands the action asked, he returns "OK"
					String toSendR = "OK for find\n";
					//the Server waits for the data
					writer.write(toSendR);
					writer.flush();
					//the server read the data
					String toFindR = read();
					System.out.println("Donnée reçue sur le server: "+toFindR);
					StockDAO rDaoFind = new StockDAO(con);
					Collection<Stock> rFind = rDaoFind.find(toFindR);
					String jsonFindR = gson.toJson(rFind);
					//the server looks and find (or not) the data asked and return his answer to the client
					if(jsonFindR == null){
						String failFind = "";
						writer.write(failFind);
						writer.flush();

					}else {
						writer.write(jsonFindR);
						writer.flush();

					}
					break;
					
				case "FINDPRODUCT\n":
					//Server understands the action asked, he returns "OK"
					String toSendP = "OK for find\n";
					//the Server waits for the data
					writer.write(toSendP);
					writer.flush();
					//the server read the data
					String toFindP = read();
					System.out.println("Donnée reçue sur le server: "+toFindP);
					ProductDAO pDaoFind = new ProductDAO(con);
					Product pFind = pDaoFind.find(Integer.valueOf(toFindP));
					String jsonFindP = gson.toJson(pFind);
					//the server looks and find (or not) the data asked and return his answer to the client
					if(jsonFindP == null){
						String failFind = "";
						writer.write(failFind);
						writer.flush();
					}else {
						writer.write(jsonFindP);
						writer.flush();
					}
					break;
					
				case "FINDOCCUPATION\n":
					//Server understands the action asked, he returns "OK"
					String toSendO = "OK for find";
					//the Server waits for the data
					writer.write(toSendO);
					writer.flush();
					//the server read the data
					System.out.println("Count in progress: ");
					OccupationDAO oDaoFind = new OccupationDAO(con);
					Collection<Integer> oFind = oDaoFind.findOccupation();
					String jsonFindO = gson.toJson(oFind);
					//the server looks and find (or not) the data asked and return his answer to the client
					if(jsonFindO == null){
						String failFind = "";
						writer.write(failFind);
						writer.flush();

					}else {
						writer.write(jsonFindO);
						writer.flush();

					}
					break;
					
				case "FINDLOCATIONNB\n":
					//Server understands the action asked, he returns "OK"
					String toSendE = "OK for count";
					//the Server waits for the data
					writer.write(toSendE);
					writer.flush();
					//the server read the data
					System.out.println("Count in progress: ");
					LocationDAO lDaoFind = new LocationDAO(con);
					int nbLFind = lDaoFind.locationNb();
					String jsonFindL = gson.toJson(nbLFind);
					//the server looks and find (or not) the data asked and return his answer to the client
					if(jsonFindL == null){
						String failFind = "";
						writer.write(failFind);
						writer.flush();

					}else {
						writer.write(jsonFindL);
						writer.flush();

					}
					break;
				}			
				}
		}
		// if a client is closed
		catch (IOException /** | SQLException */
		e) {
			System.out.println("DECONNECTION");
			if (!conP.getListUsed().isEmpty()) {
				conP.releaseConnection(con);
				System.out.println(conP.getListDispo().size() + " available connections");
				System.out.println(conP.getListUsed().size() + " used connections");
			}
		}
	}

	/**
	 * This methods read the message from the client
	 * 
	 * @return String
	 * @throws IOException
	 */
	private String read() throws IOException {
		String response = "";
		int stream;
		byte[] b = new byte[4096];
		stream = reader.read(b);
		response = new String(b, 0, stream);
		return response;
	}

}
