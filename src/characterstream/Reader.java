package characterstream;

import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 * Klassen är en tråd som hanterar läsning av bokstäver från en buffer
 * @author filip heidfors
 */
public class Reader extends Thread {
	private CharacterBuffer buffer;
	private JTextArea ta;
	private boolean sync;
	private JLabel lblRec;
	private Random rand = new Random();
	
	/**
	 * Konstruktor
	 * @param buffer Buffern som bokstäverna hämtas ifrån
	 * @param ta TextArea som ska ändras i
	 * @param sync Boolean som berättar om det ska vara synkront eller asynkront
	 * @param rec Label som skriver ut bokstäverna som lästs
	 */
	public Reader(CharacterBuffer buffer, JTextArea ta, boolean sync, JLabel rec) {
		this.buffer = buffer;
		this.ta = ta;
		this.sync = sync;
		lblRec = rec;
	}
	
	/**
	 * Trådens runmetod
	 */
	public void run() {
		lblRec.setText("");
		if (sync) { //Om läsningen ska ske synkront
			while (true) {
				try {
					char c = buffer.get();
					ta.append("Reading " + c + "\n");
					lblRec.setText(lblRec.getText() + c);
					sleep(rand.nextInt(500) + 500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else { //Om det ska ske asynkront
			while(!buffer.done()) {
				try {
					char c = buffer.getChar();
					ta.append("Reading " + c + "\n");
					lblRec.setText(lblRec.getText() + c);
					sleep(rand.nextInt(500) + 500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}

	}
}
