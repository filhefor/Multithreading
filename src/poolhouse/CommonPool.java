package poolhouse;

import java.util.Random;

/**
 * Klassen är en tråd som hämtar Customers från väntekön till common pool
 * och sätter in dem i common pool
 * @author filip heidfors
 *
 */
public class CommonPool extends Thread{
	private Buffer buffer;
	private Random rand = new Random();
	
	/**
	 * Konstruktor
	 * @param buffer Buffern som hanterar köerna
	 */
	public CommonPool(Buffer buffer) {
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
				c = buffer.getCpWait();
				buffer.putCp(c);
//				sleep(1000);
				sleep(rand.nextInt(500) + 500);
				v = buffer.getAp();
				
				if(v.isVip()) {
					buffer.putCp(v);
					System.out.println("Customer switched from adventure pool to common pool");
				}else {
					buffer.putAp(v);
				}
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
