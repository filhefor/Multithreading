package characterstream;

import java.util.LinkedList;

import javax.swing.JTextArea;

/**
 * Klassen är en buffer som håller en lista av chars
 * @author filip heidfors
 */
public class CharacterBuffer {
	private LinkedList<Character> chars = new LinkedList<Character>();
	private JTextArea taReader,taWriter;
	private char ch;
	private boolean done = false;
	
	/**
	 * Konstruktor som tar emot två textareas som ska ändras
	 * @param taR Textarea för reader
	 * @param taW Textarea för writer
	 */
	public CharacterBuffer(JTextArea taR, JTextArea taW) {
		taReader = taR;
		taWriter = taW;
	}
	
	/**
	 * Metod för att sätta in en bokstav i buffern
	 * @param ch Bokstaven som ska sättas in
	 * @throws InterruptedException
	 */
	public synchronized void put(char ch) throws InterruptedException {
		while(!chars.isEmpty()) { //Om buffern inte är tom så låt tråden vänta (får endast innehåller max 1 bokstav)
			wait();
			taWriter.append("Writer waiting\n");
		}
		chars.add(ch);
		notifyAll();
	}
	
	/**
	 * Metod för att hämta och ta bort bokstav från buffern
	 * @return bokstaven som hämtas/tas bort
	 * @throws InterruptedException
	 */
	public synchronized char get() throws InterruptedException {
		while(chars.isEmpty()) { //Om buffern är tom så låt tråden vänta
			wait();
			taReader.append("Reader waiting\n");
		}
		notifyAll();
		return chars.removeFirst();
	}
	
	/**
	 * Metoden ändrar bokstav
	 * @param c Bokstaven som det ska ändras till
	 */
	public void setChar(char c) {
		ch = c;
	}
	
	/**
	 * Metoden hämtar bokstav
	 * @return Bokstaven som hämtas
	 */
	public char getChar() {
		return ch;
	}
	
	/**
	 * Metoden returnerar true om det är klart (när writer är klar så ändras till true)
	 * @return 
	 */
	public boolean done() {
		return done;
	}
	
	/**
	 * Metoden sätter done
	 * @param b värdet som done ska sättas till
	 */
	public void setDone(boolean b) {
		done = b;
	}
}
