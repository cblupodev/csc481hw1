package csc481hw1.section4;

import java.io.*;
import java.net.*;
import java.util.Arrays;

public class Client {
	
	private String address;

	public Client(String address) {
		this.address = address;
		// TODO Auto-generated constructor stub
	}

	public void run() {
		try {
			Socket socket = new Socket(address, 7834);
			
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.flush();
			ObjectInputStream  ois = new ObjectInputStream(socket.getInputStream());
			
			oos.writeInt(1); // write a simple message to server
			oos.flush();
			oos.writeInt(2); // write a simple message to server
			oos.flush();
			
			int timeout = 1;
			
			while (true) {
				Thread.sleep(1000);
				if (timeout++ == 5) break; // if haven't read anything new from the server for a certain amount of time then quit
				System.out.println();
				
				try {
					if (ois.available() != 0) {
						timeout = 1;
						int line = ois.readInt(); // read from the server
						System.out.println("message from server:   " + line);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		System.out.println(Arrays.toString(args));
		Client c = new Client(args[0]);
		c.run();
	}
}