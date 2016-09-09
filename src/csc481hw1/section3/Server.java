package csc481hw1.section3;

import java.io.*;
import java.net.*;

public class Server {
	
	public static final int PORT = 6789;
	
	// TODO create arrays to store input and output streams
	
	public static void main (String args[]) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        
        try {
			serverSocket = new ServerSocket(PORT);
			socket = new Socket(InetAddress.getLocalHost(), PORT);
			new Client(socket).start();
			
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.flush();
			ObjectInputStream  ois = new ObjectInputStream(socket.getInputStream());
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

}
