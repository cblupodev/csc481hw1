package csc481hw1.section2;

// Demonstrating multithreading and thread synchronization in Java
public class ForkExampleMod2 implements Runnable {

	int i; // the ID of the thread, so we can control behavior
	boolean busy; // the flag, Thread 1 will wait until Thread 0 is no longer busy before continuing
	ForkExampleMod2 other; // reference to the other thread we will synchronize on. This is needed so we can control behavior.

	// create the runnable object
	public ForkExampleMod2(int i, ForkExampleMod2 other) {
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
				synchronized(this) {
					notify(); // notify() will only notify threads waiting on *this* object;
				}
				Thread.sleep(4000); // What happens if you put this sleep inside the synchronized block?
				synchronized(this) {
					busy = false; // must synchronize while editing the flag
					notify(); // notify() will only notify threads waiting on *this* object;
				}
			}
			catch(InterruptedException tie) { tie.printStackTrace(); }
		}
		else {
			while(other.isBusy()) { // check if other thread is still working
				System.out.println("Waiting!");
				// must sychnronize to wait on other object
				try { synchronized(other) { other.wait(); } } // note we have synchronized on the object we are going to wait on
				catch(InterruptedException tie) { tie.printStackTrace(); }
			}
		}
	}

	public static void main(String[] args) {
		ForkExampleMod2 t1 = new ForkExampleMod2(0, null);
		ForkExampleMod2 t2 = new ForkExampleMod2(1, t1);
		Thread thread1 = new Thread(t2);
		thread1.start();
		Thread thread2 = new Thread(t1);
		thread2.start();
		
		try {
			thread1.join();
			thread2.join();
			System.out.println("Finished!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}