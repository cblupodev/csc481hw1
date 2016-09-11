package csc481hw1.section3;

public class Main {
	
	public static void main(String[] args) {
		Main m = new Main();
		m.run();
	}
	
	public void run() {
		Thread t = new Thread(new Server());
		t.start();
		
		Client c1 = new Client();
		Client c2 = new Client();
		Client c3 = new Client();
	}
}
