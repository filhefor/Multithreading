package poolhouse;

import java.util.Random;

/**
 * Klassen är en tråd som hämtar Customers från väntekön till adventure pool
 * och sätter in dem i adventure pool
 * @author filip heidfors
 *
 */
public class AdventurePool extends Thread {
	private Buffer buffer;
	private Random rand = new Random();
	
	/**
	 * Konstruktor
	 * @param buffer Buffern som hanterar köerna
	 */
	public AdventurePool(Buffer buffer) {
		this.buffer = buffer;
		start();
	}
	
	/**
	 * Runmetod
	 */
	public void run() {
		while(true) {
			Customer c,v;
			try {
				c = buffer.getApWait();
				buffer.putAp(c);
//				sleep(1000);
				sleep(rand.nextInt(500) + 500);
				v = buffer.getCp();
				
				if(v.isVip()) {
					buffer.putAp(v);
					System.out.println("Customer switched from common pool to adventure pool");
				}else {
					buffer.putCp(v);
				}
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
