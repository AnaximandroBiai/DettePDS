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

import pojo.Stock;


/**
 * @author anax
 * @version 1.0
 * This StockRSocket class is used to return clientReturns datas
 */
public class StockRSocket extends AbstractSocket{

	public Collection<Stock> getReturns(Socket s, String cat) {
		try {
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();
			PrintWriter w1 = new PrintWriter(s.getOutputStream(), true);
			BufferedInputStream b2 = new BufferedInputStream(s.getInputStream());
			// We inform the server that we want to find data in database
			String demand = "FINDRETURNS\n";
			w1.write(demand);
			w1.flush();
			// we wait for server's response
			String reponse = read(b2);
			System.out.println(reponse);
			// Now we send to server the JSON file, with the data to insert
			w1.write(cat);
			w1.flush();
			// we read the response from the server
			String retourServer = read(b2);
			System.out.println("retour du serveur:" + retourServer);
			Type getReturns = new TypeToken<ArrayList<Stock>>() {
			}.getType();
			Collection<Stock> returns = gson.fromJson(retourServer, getReturns);
			return returns;
		} catch (IOException e) {
			return null;
		}

	}
}
