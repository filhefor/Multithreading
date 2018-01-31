package characterstream;

import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 * Klassen är en tråd som hanterar skrivning av bokstäver till buffern
 * @author filip heidfors
 */
public class Writer extends Thread{
	private CharacterBuffer buffer;
	private char[] chars;
	private JTextArea ta;
	private boolean sync;
	private JLabel lblTrans;
	private Random rand = new Random();
	
	/**
	 * Konstruktor
	 * @param buffer Buffern som det ska skrivas till
	 * @param txt Texten vars bokstäver ska skrivas till buffern. En åt gången
	 * @param ta TextArea som ska ändras
	 * @param sync Boolean som berättar om skrivningen ska ske synkront eller asynkront
	 * @param trans Label som visar bokstäverna som skrivs till buffern
	 */
	public Writer(CharacterBuffer buffer, String txt, JTextArea ta, boolean sync, JLabel trans) {
		this.buffer = buffer;
		chars = txt.toCharArray();
		this.ta = ta;
		this.sync = sync;
		lblTrans = trans;
		//start();
	}
	
	/**
	 * Trådens runmetod
	 */
	public void run() {
		lblTrans.setText("");
		if(sync) { //Om det ska ske synkront
			for(int i = 0; i < chars.length; i++) {
				try {
					buffer.put(chars[i]);
					ta.append("Writing " + chars[i] + "\n");
					lblTrans.setText(lblTrans.getText() + chars[i]);
					sleep(rand.nextInt(500) + 500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else { //Om det ska ske asynkront
			for(int i = 0; i < chars.length; i++) {
				try {
					buffer.setChar(chars[i]);
					lblTrans.setText(lblTrans.getText() + chars[i]);
					ta.append("Writing " + chars[i] + "\n");
					sleep(rand.nextInt(500) + 500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			buffer.setDone(true); //När det inte finns fler bokstäver så sätt done till true
		}
	}
}
