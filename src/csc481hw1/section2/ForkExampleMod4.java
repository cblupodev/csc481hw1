// switch the order of the notifies and waits, thus also moving the Sleeps to the other thread

package csc481hw1.section2;

// Demonstrating multithreading and thread synchronization in Java
public class ForkExampleMod4 implements Runnable {

	int i; // the ID of the thread, so we can control behavior
	boolean busy; // the flag, Thread 1 will wait until Thread 0 is no longer busy before continuing
	ForkExampleMod4 other; // reference to the other thread we will synchronize on. This is needed so we can control behavior.

	// create the runnable object
	public ForkExampleMod4(int i, ForkExampleMod4 other) {
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
				synchronized(this) {
					wait();
				}
				synchronized(this) {
					wait();
					busy = false; // must synchronize while editing the flag
				}
			}
			catch(InterruptedException tie) { tie.printStackTrace(); }
		}
		else {
			while(other.isBusy()) { // check if other thread is still working
				System.out.println("Waiting!");
				synchronized(other) {
					other.notify();
				}
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("Finished!");
		}
	}

	public static void main(String[] args) {
		ForkExampleMod4 t1 = new ForkExampleMod4(0, null);
		ForkExampleMod4 t2 = new ForkExampleMod4(1, t1);
		(new Thread(t2)).start();
		(new Thread(t1)).start();
	}

}