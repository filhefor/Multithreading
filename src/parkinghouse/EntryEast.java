package parkinghouse;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/**
 * Klassen representerar östra entren till parkeringshuset och är 
 * samtidigt en task som körs i trådpoolen
 * @author filip heidfors
 *
 */
public class EntryEast implements Runnable {
	private LinkedList<Car> buffer = new LinkedList<Car>();
	private Semaphore parkingSem;
	private ParkingHouse phouse;
	private static final int max_cars = 5;
	
	/**
	 * Konstruktor
	 * @param phouse Instans av parkeringshuset. Bilar ska köra in där från den här entren
	 */
	public EntryEast(ParkingHouse phouse) {
		this.phouse = phouse;
		parkingSem = phouse.getSem();
	}
	
	/**
	 * Metoden lägger till en bil från den här entren till parkeringshuset
	 * @param car Bilen ska läggas till
	 */
	public synchronized void put(Car car) {
		if(buffer.size() < max_cars) {
			buffer.add(car);
			notify();
			System.out.println("EntryEast: bil har kommit till entren. Count: " + buffer.size());
		}else {
			System.out.println("ENTRYEAST: För mycket bilar vid entren. Bilen får parkera någon annanstans. count: " + buffer.size());
		}
		
	}
	
	/**
	 * Metoden hämtar och tar bort en bil från entrekön och returnerar den
	 * @return Car Bilen som står först i kön
	 * @throws InterruptedException
	 */
	public synchronized Car getCar() throws InterruptedException {
		while(buffer.isEmpty()) wait();
		return buffer.removeFirst();
	}
	
	/**
	 * run-metod.
	 * Försöker hämta bilen som står först i kön, försöker ta ett lås till parkeringshuset,
	 * lägger sedan till bilen till parkeringshuset.
	 */
	public void run() {
		Car car;
		while (true) {
			try {
				car = getCar();
				parkingSem.acquire();
				phouse.put(car);
				System.out.println(
						"EntryEast: Bil körde in i parkinghouse. Platser kvar: " + parkingSem.availablePermits());

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
