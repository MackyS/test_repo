package pl.learning;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadTest {

	private volatile static int NTHREDS = 10;
	
	public static void main(String[] args) {

	    ExecutorService executor = Executors.newFixedThreadPool(NTHREDS);
		// We will store the threads so that we can check if they are done
		List<Thread> threads = new ArrayList<Thread>();
		// We will create 500 threads
		for (int i = 0; i < 500; i++) {
			Runnable task = new MyRunnable(10000000L + i);
			executor.execute(task);
			//Thread worker = new Thread(task);
			// We can set the name of the thread
			//worker.setName(String.valueOf(i));
			// Start the thread, never call method run() direct
			//worker.start();
			// Remember the thread for later usage
			//threads.add(worker);
		}
		executor.shutdown();
		int running = 0;
		do {
			running = 0;
			
			for (Thread thread : threads) {
				if (thread.isAlive()) { 
					running++;
				}
			}
			System.out.println("We have " + running + " running threads. ");
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (running > 0);

	}

}
