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
			/*ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.flush();
			System.out.println("at this point both object out streams have been create and flushed before either of the object in streams where created");
			Thread.sleep(3000);
			ObjectInputStream  ois = new ObjectInputStream(socket.getInputStream());
			System.out.println("object in stream created on client");
			oos.write(25);
			oos.write(78);
			oos.write(-1);*/
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
