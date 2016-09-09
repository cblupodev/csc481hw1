package csc481hw1.section3;

import java.io.*;
import java.net.*;

public class Client extends Thread {
	
	private Socket socket;
	
	public Client(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		
			try {
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
				oos.flush();
				System.out.println("at this point both object out streams have been create and flushed before either of the object in streams where created");
				Thread.sleep(3000);
				ObjectInputStream  ois = new ObjectInputStream(socket.getInputStream());
				System.out.println("object in stream created on client");
				oos.write(25);
				oos.write(78);
				oos.write(-1);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
