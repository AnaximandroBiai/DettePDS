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

import pojo.Occupation;
import pojo.Store;

public class OccupationSocket extends AbstractSocket {

	public Collection<Store> getStores(Socket s, String year) {
		try {
			Collection<Store> stores = new ArrayList<Store>();
			StoreSocket STS = new StoreSocket();
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();
			PrintWriter w1 = new PrintWriter(s.getOutputStream(), true);
			BufferedInputStream b2 = new BufferedInputStream(s.getInputStream());
			// We inform the server that we want to find data in database
			String demand = "FINDSTORES\n";
			w1.write(demand);
			w1.flush();
			// we wait for server's response
			String reponse = read(b2);
			System.out.println(reponse);
			// Now we send to server the JSON file, with the data to insert
			w1.write(year);
			System.out.println(year);
			w1.flush();
			// we read the response from the server
			String retourServer = read(b2);
			System.out.println("retour du serveur:" + retourServer);
			Type getOccupation = new TypeToken<ArrayList<Occupation>>() {
			}.getType();
			Collection<Occupation> occupation = gson.fromJson(retourServer, getOccupation);
			for(Occupation o : occupation) {
				Store sT = STS.getStore(s, o.getStoreId());
				stores.add(sT);
			}
			return stores;

		} catch (IOException e) {
			return null;
		}

	}

	public Collection<Store> getSigns(Socket s, String year) {
		try {
			Collection<Store> signs = new ArrayList<Store>();
			StoreSocket STS = new StoreSocket();
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();
			PrintWriter w1 = new PrintWriter(s.getOutputStream(), true);
			BufferedInputStream b2 = new BufferedInputStream(s.getInputStream());
			// We inform the server that we want to find data in database
			String demand = "FINDSTORES\n";
			w1.write(demand);
			w1.flush();
			// we wait for server's response
			String reponse = read(b2);
			System.out.println(reponse);
			// Now we send to server the JSON file, with the data to insert
			w1.print(year);
			w1.flush();
			// we read the response from the server
			String retourServer = read(b2);
			System.out.println("retour du serveur:" + retourServer);
			Type getOccupation = new TypeToken<ArrayList<Occupation>>() {
			}.getType();
			Collection<Occupation> occupation = gson.fromJson(retourServer, getOccupation);
			for (Occupation o : occupation) {
				boolean same = false;
				Store sT = STS.getStore(s, o.getStoreId());
				if (signs.isEmpty()) {
					signs.add(sT);
				} else {
					for (Store sS : signs) {
						if (sT.getStoreName().equals(sS.getStoreName())) {
							same = true;
						}
					}
				}
				if (!same) {
					signs.add(sT);
				}
			}
			return signs;

		} catch (IOException e) {
			return null;
		}

	}
}
