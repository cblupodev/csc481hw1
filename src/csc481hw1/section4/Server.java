package csc481hw1.section4;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class Server {
	
	public static CopyOnWriteArrayList<BufferedReader> inStream = new CopyOnWriteArrayList<>();
	public static CopyOnWriteArrayList<PrintWriter> outStream = new CopyOnWriteArrayList<>();
	
	public static void main(String[] args) {
		Server m = new Server();
		m.run();
	}
	
	public void run() {
		Thread t = new Thread(new ServerAccept());
		t.start();
		
		// read from clients
		while (true) { // never stop looking
				/*synchronized (waitObject) {
					try {
						System.out.println("waited");
						waitObject.wait(); // wait here until at least one client notifies you
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}*/
				for (int i = 0; i < inStream.size(); i++) { // iterate over the clients
					String line;
					try {
						while (inStream.get(i).ready()) { // loop until read everything from the client stream
							line = inStream.get(i).readLine();
							System.out.println("message from client:   " + line);
							outStream.get(i).println(line);
							outStream.get(i).flush();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
		}
		
		/*Random rand = new Random();
		int rannum = rand.nextInt(10) + 1;
		for (int i = 0; i < rannum; i++) {
			Client c = new Client(i);
		}*/
	}
}
