package socket;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class LocationSocket extends AbstractSocket{

	public Integer getLocationNB(Socket s) {
		try {

			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();
			PrintWriter w1 = new PrintWriter(s.getOutputStream(), true);
			BufferedInputStream b2 = new BufferedInputStream(s.getInputStream());
			// We inform the server that we want to find data in database
			String demand = "FINDLOCATIONNB\n";
			w1.write(demand);
			w1.flush();
			// we wait for server's response
			String reponse = read(b2);
			System.out.println(reponse);
			// Now we send to server the JSON file, with the data to insert
			// we read the response from the server
			String retourServer = read(b2);
			System.out.println("retour du serveur:" + retourServer);
			int nbL = gson.fromJson(retourServer, Integer.class);
			return nbL;

		} catch (IOException e) {
			return null;
		}
	}
}
