package socket;

import java.awt.BorderLayout;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.Connection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



public class ConnectionSocket extends AbstractSocket {
	public ConnectionSocket(){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        try {
            Socket s = new Socket(InetAddress.getLocalHost(), 5000);
            PrintWriter w1 = new PrintWriter(s.getOutputStream(), true);
            BufferedInputStream b2 = new BufferedInputStream(s.getInputStream());
            //We inform the server that we want to insert data in database
            String demand = "CONNECTION";
            System.out.println(demand);
            w1.write(demand);
            w1.flush();
            //we wait for server's response
            String reponse = read(b2);
            System.out.println(reponse);
            String serverReturn = read(b2);
            Connection con = gson.fromJson(serverReturn, Connection.class);
            s.close();
        }catch(IOException e){ }
    }

}
