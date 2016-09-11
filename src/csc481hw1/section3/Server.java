package csc481hw1.section3;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server extends Thread {
	
	public static final int PORT = 6789;
	
	// TODO create arrays to store input and output streams
	
	public void run() {
        ServerSocket serverSocket = null;
        Socket client = null;
        
        try {
			serverSocket = new ServerSocket(PORT);
			System.out.println("server started");
			//while (true) {
				client = serverSocket.accept();
			    BufferedReader in1 = new BufferedReader(new InputStreamReader(client.getInputStream()));
				client = serverSocket.accept();
			    BufferedReader in2 = new BufferedReader(new InputStreamReader(client.getInputStream()));
				client = serverSocket.accept();
			    BufferedReader in3 = new BufferedReader(new InputStreamReader(client.getInputStream()));
			    System.out.println("all the client buffers are in");
			    
				String inline;
				System.out.println("in1:");
				while ((inline = in1.readLine()) != null) {
					System.out.println(inline);
				}
				
				System.out.println("in2:");
				while ((inline = in2.readLine()) != null) {
					System.out.println(inline);
				}

				System.out.println("in3:");
				while ((inline = in3.readLine()) != null) {
					System.out.println(inline);
				}
				/*while ((inputLine = in.readLine()) != null) {
					System.out.println(inputLine);
			        if (Integer.parseInt(inputLine) == -1)
			            break;
			    }*/
			//}
			/*ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.flush();
			ObjectInputStream  ois = new ObjectInputStream(socket.getInputStream());*/
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Throwable t) {
			t.printStackTrace();
		}
		
	}
	
	public static void main (String args[]) {
	}

}
