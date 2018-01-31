package parkinghouse;

/**
 * Klassen representerar en bil med variabel som representerar hur länge 
 * bilen ska stå parkerad i parkeringshuset
 * @author filip heidfors
 *
 */
public class Car {
	private int parkingTime;
	private int index;
	
	/**
	 * Konstruktor
	 * @param time Tiden bilen ska stå parkerad
	 */
	public Car(int time, int index) {
		parkingTime = time;
		this.index = index;
	}
	
	/**
	 * Metoden returnerar tiden bilen ska stå parkerad
	 * @return parkingTime Tiden bilen ska stå parkerad
	 */
	public int getTime() {
		return parkingTime;
	}
	
	public int getIndex() {
		return index;
	}
}
