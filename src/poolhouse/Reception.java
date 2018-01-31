package poolhouse;

import java.util.Random;

/**
 * Klassen är en tråd som skapar Customers och sätter i de olika köerna för 
 * att komma in i adventure pool och common pool
 * @author filip heidfors
 *
 */
public class Reception extends Thread {
	private Buffer buffer;
	private Random rand = new Random();
	
	/**
	 * Konstruktor
	 * @param buffer Buffern som hanterar köerna
	 */
	public Reception(Buffer buffer) {
		this.buffer = buffer;
		start();
	}
	
	/**
	 * Runmetod
	 */
	public void run() {
		while(true) {
			boolean b = rand.nextBoolean();
			try {
				if(b) {
					buffer.putApWait(new Customer(rand.nextBoolean()));
				}else{
					buffer.putCpWait(new Customer(rand.nextBoolean()));
				}
//				sleep(700);
				sleep(rand.nextInt(500) + 500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
