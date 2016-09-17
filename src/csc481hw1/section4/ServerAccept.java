package csc481hw1.section4;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

// Class to receive incoming connections. break into a new thread because accept() is blocking
public class ServerAccept extends Thread {
	
	public void run() {
        ServerSocket serverSocket = null;
        Socket client = null;
        
        try {
			serverSocket = new ServerSocket(7834);
			while (true) { // never stop listening
				System.out.println("server waiting to connect");
				client = serverSocket.accept();
				System.out.println("a client was connected");
				
				ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
				oos.flush(); // stackoverflow said just do it
				ObjectInputStream  ois = new ObjectInputStream(client.getInputStream());
			    
				// pass the object streams to the server
				Server.inStream.add(ois); 
			    Server.outStream.add(oos);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
