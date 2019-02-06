package socket;

import java.awt.BorderLayout;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import pojo.Test;


public class TestCreateSocket extends AbstractSocket{
/**
 * this is the TestCreateSocket constructor
 * @param String lName
 * @param String fName
 * @param Socket s
 */
	public TestCreateSocket(String lName, String fName, Socket s){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        
        //we create a Test object with the data received
        Test t1 = new Test(lName, fName);
        
        //we convert the test object to Json
        String json = gson.toJson(t1);
        try {
            PrintWriter w1 = new PrintWriter(s.getOutputStream(), true);
            BufferedInputStream b2 = new BufferedInputStream(s.getInputStream());
            //We inform the server that we want to insert data in database
            String demand = "TEST\n";
            w1.write(demand);
            w1.flush();
            //we wait for server's response
            String reponse = read(b2);
            System.out.println(reponse);
            w1.write(json);
            w1.flush();
            //we wait for server's response
            String retourServer = read(b2);
            System.out.println(retourServer);
            //display of the result
            JFrame fenResp = new JFrame();
            JPanel containerResp = new JPanel();
            fenResp.setSize(600, 300);
            fenResp.setLocationRelativeTo(null);
            JLabel jlabResp = new JLabel(retourServer);
            containerResp.add(jlabResp, BorderLayout.CENTER);
            fenResp.setContentPane(containerResp);
            fenResp.setVisible(true);
        }catch(IOException e){ }
    }
}
