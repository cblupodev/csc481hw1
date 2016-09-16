
import java.io.*;
import java.net.*;

public class Client {
	
	private Socket socket;
	private String address;
	
	public Client() {
		run();
	}
	
	public Client(String address) {
		this.address = address;
	}

	public void run() {
		try {
			socket = new Socket(address, 6789);
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
	
	public static void main(String[] args) {
		Client c = new Client(args[0]);
		c.run();
	}
	
}
