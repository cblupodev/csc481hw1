package csc481hw1.section4;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class ServerAccept extends Thread {
	
	public static final int PORT = 7856;
	
	// TODO create arrays to store input and output streams
	
	public void run() {
        ServerSocket serverSocket = null;
        Socket client = null;
        
        try {
			serverSocket = new ServerSocket(PORT);
			while (true) {
				client = serverSocket.accept();
			    BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			    PrintWriter out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
			    Server.inStream.add(in);
			    Server.outStream.add(out);
			}
			/*ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.flush();
			ObjectInputStream  ois = new ObjectInputStream(socket.getInputStream());*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main (String args[]) {
	}

}
