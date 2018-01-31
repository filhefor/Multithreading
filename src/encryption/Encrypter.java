package encryption;
/**
 * Klassen är en tråd som tar emot data från Buffern, krypterar den och skickar sedan tillbaka till Buffern
 * @author filip heidfors
 *
 */
public class Encrypter extends Thread{
	private Buffer buffer;
	private int size;
	
	/**
	 * Konstruktor
	 * @param buffer Buffer den ska ta emot och skicka till
	 * @param listSize Storleken på data
	 */
	public Encrypter(Buffer buffer, int listSize) {
		this.buffer = buffer;
		size = listSize;
		start();
	}
	
	public void run() {
		int count = 0;
		while(count < size) {
			char[] array;
			StringBuilder sb = new StringBuilder();
			try {
				array = buffer.getInput().toCharArray();
				sleep(500);
				for(int i = array.length-1; i >= 0; i--) {
					sb.append(array[i]);
				}
				//System.out.println("Encryptat " + sb.toString());
				buffer.putOutput(sb.toString());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			count++;
		}
		interrupt();
	}
}
