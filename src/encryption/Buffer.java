package encryption;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/**
 * Klassen är en buffer som håller strängar, en för input och en för output
 * @author filip heidfors
 *
 */
public class Buffer {
	private LinkedList<String> inputList = new LinkedList<String>();
	private LinkedList<String> outputList = new LinkedList<String>();
	private Semaphore sem = new Semaphore(1, true);
	private int buffersize;
	
	/**
	 * Konstruktorn tar emot Stringarrayens storlek och sätter buffern mindre än den storleken
	 * @param size Inputarrayens storlek
	 */
	public Buffer(int size) {
		buffersize = size/2;
		System.out.println("bufferize = " + buffersize + " number of strings = " + size);
	}
	
	/**
	 * Metoden tar emot en Sträng från Writer och lägger till i inputList om den inte är full
	 * @param str Strängen som läggs till
	 * @throws InterruptedException
	 */
	public void putInput(String str) throws InterruptedException {
		sem.acquire();
		while(inputList.size() >= buffersize) {
			sem.release();
		}
		inputList.addLast(str);
		System.out.println("WRITER to buffer: " + str);
		sem.release();
	}
	
	/**
	 * Metoden returnerar och tar bort första Strängen i inputlist
	 * @return
	 * @throws InterruptedException
	 */
	public String getInput() throws InterruptedException {
		sem.acquire();
		while(inputList.size() == 0) {
			sem.release();
		}
		
		String str = inputList.removeFirst();
		System.out.println("ENCRYPTER from buffer: " + str);
		sem.release();
		return str;
	}
	
	/**
	 * Metoden tar emot en krypterad sträng från Encrypter och lägger till i outputlist
	 * @param str Den krypterade strängen
	 * @throws InterruptedException
	 */
	public void putOutput(String str) throws InterruptedException {
		sem.acquire();
		while(outputList.size() >= buffersize) {
			sem.release();
		}

		outputList.addLast(str);
		System.out.println("ENCRYPTER to buffer: " + str);
		sem.release();
	}
	
	/**
	 * Metoden returnerar och tar bort första strängen i outputlist
	 * @return
	 * @throws InterruptedException
	 */
	public String getOutput() throws InterruptedException {
		sem.acquire();
		while(outputList.size() == 0) {

			sem.release();
		}
		String str = outputList.removeFirst();
		System.out.println("READER from buffer: " + str);
		sem.release();
		return str;
	}

}
