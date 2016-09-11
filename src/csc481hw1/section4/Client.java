package csc481hw1.section4;

import java.io.*;
import java.net.*;

public class Client {
	
	public int id; // so i know what client im talking to, mostly for debugging
	
	public Client(int id) {
		this.id = id;
		run();
	}
	
	public void run() {
		try {
			Socket socket = new Socket(InetAddress.getLocalHost(), ServerAccept.PORT);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		    BufferedReader in = new BufferedReader(
		        new InputStreamReader(socket.getInputStream()));
		    out.printf("im client %d\n", id);
		    /*synchronized (Server.waitObject) {
				Server.waitObject.notify(); // call this immediatley after something was written
			}*/
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
