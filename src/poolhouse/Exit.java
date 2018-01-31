package poolhouse;

/**
 * Klassen är en tråd som hämtar Customers från de både poolerna
 * och sätter in dom exitkö för respektive pool
 * @author filip heidfors
 *
 */
public class Exit extends Thread{
	private Buffer buffer;
	
	/**
	 * Konstruktor
	 * @param buffer Buffern som hanterar köerna
	 */
	public Exit(Buffer buffer) {
		this.buffer = buffer;
		start();
	}
	
	/**
	 * Runmetod
	 */
	public void run() {
		while(true) {
			try {
				sleep(5000);
				Customer ap = buffer.getAp();
				Customer cp = buffer.getCp();
				buffer.putApExit(ap);
				buffer.putCpExit(cp);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
