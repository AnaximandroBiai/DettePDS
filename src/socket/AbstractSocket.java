package socket;

import java.io.BufferedInputStream;
import java.io.IOException;

/**
 * @author anax
 * This abstract class defines the methods used by all the client socket classes
 */
public class AbstractSocket {

	    /**
	     * This method read what the server send to client, and return its through a String
	     * @param reader
	     * @return String
	     * @throws IOException
	     */
	     public String read(BufferedInputStream reader) throws IOException {

	        String response = "";

	        int stream;

	        byte[] b = new byte[4096];

	        stream = reader.read(b);

	        response = new String(b, 0, stream);

	        return response;

	    }
	}
