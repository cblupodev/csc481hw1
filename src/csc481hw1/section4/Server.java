package csc481hw1.section4;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class Server {
	
	public static CopyOnWriteArrayList<BufferedReader> inStream = new CopyOnWriteArrayList<>();
	public static CopyOnWriteArrayList<PrintWriter> outStream = new CopyOnWriteArrayList<>();
	
	public static Object waitObject;
	
	public static void main(String[] args) {
		Server m = new Server();
		m.run();
	}
	
	public void run() {
		Thread t = new Thread(new ServerAccept());
		t.start();
		
		Random rand = new Random();
		int rannum = rand.nextInt(10) + 1;
		for (int i = 0; i < rannum; i++) {
			Client c = new Client(i);
		}
		
		// read from clients
		while (true) { // never stop looking
				/*try {
					waitObject.wait(); // wait here until at least one client notifies you
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}*/
				for (int i = 0; i < inStream.size(); i++) { // iterate over the clients
					String line;
					try {
						while (inStream.get(i).ready()) { // loop until read everything from the client stream
							line = inStream.get(i).readLine();
							System.out.printf("client %d:   %s\n", i, line);
						}
						outStream.get(i).printf("the server read all of client %d messages", i);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				break; // temp
		}
	}
}
