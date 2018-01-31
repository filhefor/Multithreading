package encryption;

import javax.swing.JButton;
import javax.swing.JTextArea;

/**
 * Klassen är en tråd som hämtar output från Buffern
 * @author filip heidfors
 *
 */
public class Reader extends Thread{
	private Buffer buffer;
	private JTextArea text;
	private String[] encryptedStringList;
	private JButton btnDec,btnEnc;
	
	/**
	 * Konstruktor
	 * @param buffer Buffern som tråden hämtar ifrån
	 * @param txt Textyta som outputen ska läggas in i
	 * @param list Array som outputen läggs till i
	 * @param btnDec Knapp från GUI som ska bli klickbar när tråden är klar
	 * @param btnEnc Knapp från GUI som ska bli klickbar när tråden är klar
	 */
	public Reader(Buffer buffer, JTextArea txt, String[] list, JButton btnDec, JButton btnEnc) {
		this.buffer = buffer;
		text = txt;
		encryptedStringList = list;
		this.btnDec = btnDec;
		this.btnEnc = btnEnc;
		start();
	}
	
	public void run() {
		for(int i = 0; i < encryptedStringList.length; i++) {
			try {
				String str = buffer.getOutput();
				encryptedStringList[i] = str;
				text.append(str + "\n");
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
