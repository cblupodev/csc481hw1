package csc481hw1.section4;

import java.io.*;
import java.net.*;

public class Client {
	
	public void run() {
		try {
			Socket socket = new Socket("127.0.0.1", 7834);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		    out.printf("im the client here\n");
		    
		    int timeout = 1;

			while (true) { // never stop looking
				if (timeout++ == 5) break;
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				String line;
				try {
					System.out.println();
					while (in.ready()) { // loop until read everything from the client stream
						System.out.printf("this was your message:   %s\n", in.readLine());
						out.flush();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		    /*synchronized (Server.waitObject) {
		    	System.out.println("notified");
				Server.waitObject.notifyAll(); // call this immediatley after something was written
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
	
	public static void main(String[] args) {
		Client c = new Client();
		c.run();
	}
}