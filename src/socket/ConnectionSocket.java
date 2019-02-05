package socket;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;



public class ConnectionSocket extends AbstractSocket {
	public ConnectionSocket(){
        try {
            Socket s = new Socket(InetAddress.getLocalHost(), 5000);
            PrintWriter w1 = new PrintWriter(s.getOutputStream(), true);
            BufferedInputStream b2 = new BufferedInputStream(s.getInputStream());
            //We inform the server that we want to insert data in database
            String demand = "CONNECTION\n";
            System.out.println(demand);
            w1.write(demand);
            w1.flush();
            //we wait for server's response
            String reponse = read(b2);
            System.out.println(reponse);
            String serverReturn = read(b2);
            System.out.println(serverReturn);
            if(serverReturn.equals("Connection Done")) {
            	new view.TestView(s);
            }
            //s.close();
        }catch(IOException e){ }
        
    }

}
