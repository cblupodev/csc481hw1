package csc481hw1.section4;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class Server {
	
	public static CopyOnWriteArrayList<ObjectInputStream> inStream = new CopyOnWriteArrayList<>();
	public static CopyOnWriteArrayList<ObjectOutputStream> outStream = new CopyOnWriteArrayList<>();
	
	public static void main(String[] args) {
		Server m = new Server();
		m.run();
	}
	
	public void run() {
		
		// start the thread that accepts incoming connections
		Thread t = new Thread(new ServerAccept());
		t.start();
		
		// read from clients
		while (true) { // never stop looking
			for (int i = 0; i < inStream.size(); i++) { // iterate over the client streams
				int read;
				try {
					if (inStream.get(i).available() != 0) { // check if there is anything to read
						read = inStream.get(i).readInt(); // read a simple message from a client
						System.out.println("message from client:   " + read);
						outStream.get(i).writeInt(read); // write a message to a client
						outStream.get(i).flush(); // make sure every message gets sent out immediately
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
