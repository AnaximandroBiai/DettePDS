package socket;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import pojo.Royalties;

/**
 * @author anax
 * @version 1.0
 * This RoyaltiesSocket class is used to return royalties datas
 */
public class RoyaltiesSocket extends AbstractSocket{

	public Collection<Royalties> getRoyaltiesPaid(Socket s, String cat, String name, String month){
		try {
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();
			PrintWriter w1 = new PrintWriter(s.getOutputStream(), true);
			BufferedInputStream b2 = new BufferedInputStream(s.getInputStream());
			// We inform the server that we want to find data in database
			String demand = "FINDROYALTIESPAID\n";
			w1.write(demand);
			w1.flush();
			// we wait for server's response
			String reponse = read(b2);
			System.out.println(reponse);
			// Now we send to server the JSON file, with the data to insert
			if (cat.equals("")) {
				String info = "ByName\n";
				w1.write(info);
				w1.flush();
				String reponseI = read(b2);
				System.out.println(reponseI);
				w1.write(name);
				w1.flush();
				String reponseN = read(b2);
				System.out.println(reponseN);
				w1.write(month);
				w1.flush();
				String reponseM = read(b2);
				System.out.println(reponseM);
			}
			else {
				String info = "ByCategory\n";
				w1.write(info);
				w1.flush();
				String reponseI = read(b2);
				System.out.println(reponseI);
				w1.write(cat);
				w1.flush();
				String reponseC = read(b2);
				System.out.println(reponseC);
				w1.write(month);
				w1.flush();
				String reponseM = read(b2);
				System.out.println(reponseM);
			}
			// we read the response from the server
			String retourServer = read(b2);
			System.out.println("retour du serveur:" + retourServer);
			Type getRSP = new TypeToken<ArrayList<Royalties>>(){}.getType();
			Collection <Royalties> rSP = gson.fromJson(retourServer, getRSP);
			return rSP;
		} catch (IOException e) {
			return null;
		}
	}
	
	public Royalties getRoyaltiesAsked(Socket s, int id) {
		try {
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();
			PrintWriter w1 = new PrintWriter(s.getOutputStream(), true);
			BufferedInputStream b2 = new BufferedInputStream(s.getInputStream());
			// We inform the server that we want to find data in database
			String demand = "FINDROYALTIESASKED\n";
			w1.write(demand);
			w1.flush();
			// we wait for server's response
			String reponse = read(b2);
			System.out.println(reponse);
			// Now we send to server the JSON file, with the data to insert
			w1.write(String.valueOf(id));
			w1.flush();
			// we read the response from the server
			String retourServer = read(b2);
			System.out.println("retour du serveur:" + retourServer);
			Type getRSP = new TypeToken<Royalties>(){}.getType();
			Royalties rSD = gson.fromJson(retourServer, getRSP);
			return rSD;
		} catch (IOException e) {
			return null;
		}
	}
}
