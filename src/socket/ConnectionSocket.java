package socket;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;




public class ConnectionSocket extends AbstractSocket {
	/**
	 * this is the ConnectionSocket constructor
	 */
	public ConnectionSocket(){
        try {
            Socket s = new Socket(InetAddress.getLocalHost(), 5000);
            PrintWriter w1 = new PrintWriter(s.getOutputStream(), true);
            BufferedInputStream b2 = new BufferedInputStream(s.getInputStream());
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonReader reader = new JsonReader(new InputStreamReader(b2));
            reader.setLenient(true);
            //We inform the server that we want to connect to the database
            String demand = "CONNECTION\n";
            System.out.println(demand);
            w1.write(demand);
            w1.flush();
            //we wait for server's response to the demand
            String reponse = read(b2);
            System.out.println(reponse);
            //we wait for server's response
            String serverReturn = read(b2);
            System.out.println(serverReturn);
            //connection available
            if(serverReturn.equals("Connection Done\n")) {
            	Type getCats = new TypeToken<ArrayList<String>>(){}.getType();
            	//String jCats = read(b2);
            	//System.out.println(jCats);
            	Collection<String> cats = gson.fromJson(reader, getCats);
            	//String jTypes = read(b2);
            	Collection<String> types = gson.fromJson(reader, getCats);
            	System.out.println(types);
            	new view.MainMenuView(s, cats, types);
            }
            //connection unavailable
            else {
            	s.close();
            }
        }catch(IOException e){ }
        
    }

}
