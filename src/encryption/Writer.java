package encryption;

import javax.swing.JButton;

/**
 * Klassen är en tråd som skickar input till buffern
 * @author filip heidfors
 *
 */
public class Writer extends Thread{
	private String[] list;
	private Buffer buffer;
	private JButton btnDec,btnEnc;
	
	/**
	 * Konstruktor
	 * @param list array som innehåller inputen som ska skickas
	 * @param buffer Buffern det ska skickas till
	 * @param btnDec Knapp från GUI som ska bli klickbar när tråden är klar
	 * @param btnEnc Knapp från GUI som ska bli klickbar när tråden är klar
	 */
	public Writer(String[] list, Buffer buffer, JButton btnDec, JButton btnEnc) {
		this.list = list;
		this.buffer = buffer;
		this.btnDec = btnDec;
		this.btnEnc = btnEnc;
		start();
	}
	
	public void run() {
		for(int i = 0; i < list.length; i++) {
			try {
				buffer.putInput(list[i]);
				sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		btnEnc.setEnabled(true);
		btnDec.setEnabled(true);
		interrupt();
	}
}
