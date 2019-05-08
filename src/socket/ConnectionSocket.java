package socket;

import java.awt.BorderLayout;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

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
            //We inform the server that we are waiting to connect to the database
            String wait = "Waiting...\n";
            System.out.println(wait);
            w1.write(wait);
            w1.flush();
            //we wait for server's response
            String serverReturn = read(b2);
            System.out.println(serverReturn);
            //connection available
            if(serverReturn.equals("ACCEPTED\n")) {
            	Type getdatas = new TypeToken<ArrayList<String>>(){}.getType();
            	//String jCats = read(b2);
            	//System.out.println(jCats);
            	Collection<String> cats = gson.fromJson(reader, getdatas);
            	//String jTypes = read(b2);
            	Collection<String> types = gson.fromJson(reader, getdatas);
            	//String jNamess = read(b2);
            	Collection<String> names = gson.fromJson(reader, getdatas);
            	new view.MainMenuView(s, cats, types, names);
            }
            //connection unavailable
            else {
				JFrame fenResp = new JFrame();
				JPanel containerResp = new JPanel();
				fenResp.setSize(300, 150);
				fenResp.setLocationRelativeTo(null);
				JLabel jlabResp = new JLabel("No connection available, try later");
				containerResp.add(jlabResp, BorderLayout.CENTER);
				fenResp.setContentPane(containerResp);
				fenResp.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				fenResp.setVisible(true);
            	s.close();
            }
        }catch(IOException e){ }
        
    }

}
