package vn.topica.itlab4.work.thread;

import java.util.concurrent.ThreadLocalRandom;

import vn.topica.itlab4.work.thread.Lamp.STATUS;

public class Main {
	private static Store store; // store to save lamp
	private static Trash trash; // trash to remove lamp
	private static int MIN_LAMP = 1;
	private static int MAX_LAMP = 20;

	private static boolean isRunning = true;// lock thread 1
	private static boolean isRemove = true; // lock thread 2

	public static final int SLEEP_ADD_LAMP = 100;
	public static final int SLEEP_REMOVE_LAMP = 200;

	private static final int RUNNING_TIMES = 20000;

	/**
	 * Thread1-Runnable to add lamp to store
	 * 
	 * @author NoName
	 *
	 */

	static class ThreadAddLamp implements Runnable { // lamp create

		public void run() { // thread entry point
			ThreadLocalRandom random = ThreadLocalRandom.current(); // create a random to generator random value
			while (isRunning) { // when running
				try {
					int number = MIN_LAMP + random.nextInt(MAX_LAMP); // number random from 1 to 20
					// number random from 1 to 20
					System.out.println("Creating " + number + " lamp"); // info create lamp
					for (int i = 0; i < number; ++i) { // create number lamp
						Lamp.STATUS status = new STATUS[] { Lamp.STATUS.off, Lamp.STATUS.on }[random.nextInt(2)]; // random

						Lamp lamp = new Lamp(status); // create a lamp
						synchronized (store) { // lock store to thread-safe
							store.addLamp(lamp); // add to store
						}
						// Print info lamp after add to store
						System.out
								.println("Lamp have index = " + lamp.getIndex() + " and status = " + lamp.getStatus());
					}
					Thread.sleep(SLEEP_ADD_LAMP); // sleep
				} catch (InterruptedException e) { // catch interrupt exception to stop thread
					System.out.println("Thread create lamp stopped"); // info that stop thread
					isRunning = false; // use thread.interrupt() to stop that dame thing
				} catch (Exception e) { // handle all exception to keep thread running
					e.printStackTrace(); // print error
				}
			}
		}

	}

	/**
	 * Thread2-Runnable to remove lamp have status == off to trash
	 * 
	 * @author NoName
	 *
	 */
	static class ThreadRemoveLamp implements Runnable { // lamp remove

		public void run() { // thread entry point
			while (isRemove) { // when running
				try {
					System.out.println("Remove check");
					synchronized (store) { // lock store to thread safe
						for (int i = 0; i < store.size(); i++) { // for all of store
							Lamp lamp = store.getLampAtIndex(i); // get lam at index i
							if (lamp.getStatus() == Lamp.STATUS.off) { // check if off status or repair
								store.removeLampAtIndex(i); // remove from store and decrement i to get next lamp
								trash.addLamp(lamp); // add to trash
								System.out.println("Remove lamp at index = " + lamp.getIndex()); // lamp remove
							}
						}
					}
					Thread.sleep(SLEEP_REMOVE_LAMP); // sleep 200ms
				} catch (InterruptedException e) { // catch interrupt exception to stop thread
					System.out.println("Thread remove stop"); // alert that stop thread
					isRemove = false; // use thread.interrupt() to stop
				} catch (Exception e) { // catch all exception to avoid crash thread
					e.printStackTrace(); // print error
				}
			}
		}

	}

	public static void main(String[] args) { // main function
		// create store and trash
		store = new Store();
		trash = new Trash();

		// set running flags
		isRunning = true;
		isRemove = true;

		// run two thread
		Thread add, remove; // two thread control
		(add = new Thread(new ThreadAddLamp())).start(); // start thread 1
		(remove = new Thread(new ThreadRemoveLamp())).start(); // start thread 2

		try {
			Thread.sleep(RUNNING_TIMES); // sleep for thread running
		} catch (InterruptedException e1) {
		}

		add.interrupt();
		remove.interrupt(); // stop all thread

		try {
			add.join();
			remove.join(); // wait 3 threads to die
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
