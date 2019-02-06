package socket;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;



public class ConnectionSocket extends AbstractSocket {
	/**
	 * this is the ConnectionSocket constructor
	 */
	public ConnectionSocket(){
        try {
            Socket s = new Socket(InetAddress.getLocalHost(), 5000);
            PrintWriter w1 = new PrintWriter(s.getOutputStream(), true);
            BufferedInputStream b2 = new BufferedInputStream(s.getInputStream());
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
            	new view.TestView(s);
            }
            //connection unavailable
            else {
            	s.close();
            }
        }catch(IOException e){ }
        
    }

}
