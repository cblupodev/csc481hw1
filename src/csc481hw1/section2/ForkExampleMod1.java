// example to read and write to data structure

package csc481hw1.section2;

import java.util.concurrent.ConcurrentLinkedQueue;

// Demonstrating multithreading and thread synchronization in Java
public class ForkExampleMod1 extends Thread {
	
	public static ConcurrentLinkedQueue<Integer> q = new ConcurrentLinkedQueue<>(); // create a thread safe message queue
	private ForkExampleMod1 writerref; // allow the reader to reference the writer so it can wait on it
	private String name;
	
	public ForkExampleMod1 (ForkExampleMod1 writerref, String name) {
		this.writerref = writerref;
		this.name = name;
	}

	// run method needed by runnable interface
	public void run() {
		if (this.name.equals("writer")) { // enter this block of the thread is a writer
			try {
				synchronized (this) { q.add(1); } // add message to the queue
				Thread.sleep(1000); // wait a second
				synchronized (this) { notifyAll(); } // notify reader thread a new message was added
				synchronized (this) { q.add(2); }
				Thread.sleep(1000);
				synchronized (this) { notifyAll(); }
				synchronized (this) { q.add(3); }
				Thread.sleep(1000);
				synchronized (this) { notifyAll(); }

				synchronized (this) { q.add(-1); } // send a quit message
				Thread.sleep(1000);
				synchronized (this) { notifyAll(); }
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else if (this.name.equals("reader")) { // enter this block of the thread is a reader
			while (true) {
				synchronized (this.writerref) {
					if (q.isEmpty()) { // wait if the queue is empty
						try {
							this.writerref.wait(); // receive signal to continue
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} else {
						int elem = q.remove();
						System.out.println(elem);
						if (elem == -1) {
							break;
						}
					}
				}
			}
			System.out.println("done");
		}
	}

	public static void main(String[] args) {
		ForkExampleMod1 writer = new ForkExampleMod1(null, "writer");
		ForkExampleMod1 reader = new ForkExampleMod1(writer, "reader");
		(new Thread(writer)).start();
		(new Thread(reader)).start();
	}

}