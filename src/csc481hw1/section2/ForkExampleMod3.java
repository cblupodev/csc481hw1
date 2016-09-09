package csc481hw1.section2;

import java.util.concurrent.Semaphore;

// Demonstrating multithreading and thread synchronization in Java
public class ForkExampleMod3 implements Runnable {

	int i; // the ID of the thread, so we can control behavior
	boolean busy; // the flag, Thread 1 will wait until Thread 0 is no longer busy before continuing
	ForkExampleMod3 other; // reference to the other thread we will synchronize on. This is needed so we can control behavior.
	public static Semaphore sem = new Semaphore(0);

	// create the runnable object
	public ForkExampleMod3(int i, ForkExampleMod3 other) {
		this.i = i; // set the thread ID (0 or 1)
		if(i==0) { busy = true; } // set the busy flag so Thread 1 waits for Thread 0
		else { this.other = other; }
	}

	// synchronized method to test if thread is busy or not
	public synchronized boolean isBusy() { return busy; } // What happens if this isn't synchronized? 

	// run method needed by runnable interface
	public void run() {
		if(i==0) { // 1st thread, sleep for a while, then notify threads waiting
			try {
				Thread.sleep(4000); // What happens if you put this sleep inside the synchronized block?
				sem.release();
				Thread.sleep(4000); // What happens if you put this sleep inside the synchronized block?
					busy = false; // must synchronize while editing the flag
				sem.release();
			}
			catch(InterruptedException tie) { tie.printStackTrace(); }
		}
		else {
			try {
				while (other.isBusy()) {
					System.out.println("Waiting!");
					sem.acquire();
				}
				System.out.println("Finished!");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		ForkExampleMod3 t1 = new ForkExampleMod3(0, null);
		ForkExampleMod3 t2 = new ForkExampleMod3(1, t1);
		(new Thread(t2)).start();
		(new Thread(t1)).start();
	}

}