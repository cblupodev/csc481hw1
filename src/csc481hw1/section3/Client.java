package csc481hw1.section3;

import java.io.*;
import java.net.*;

public class Client {
	
	private Socket socket;
	
	public Client() {
		run();
	}
	
	public void run() {
		try {
			socket = new Socket(InetAddress.getLocalHost(), Server.PORT);
			System.out.println("is client connected? " + socket.isConnected());
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		    BufferedReader in = new BufferedReader(
		        new InputStreamReader(socket.getInputStream()));
		    out.println(1);
		    out.println(2);
		    socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
