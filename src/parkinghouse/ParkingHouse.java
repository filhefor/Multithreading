package parkinghouse;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Klassen illustrerar ett parkeringshus med ett bestämt antal parkeringsplatser.
 * Antalet platser bestäms av available permits i semaforen som skickas till konstruktorn
 * @author filip heidfors
 *
 */
public class ParkingHouse {
	private Semaphore parkingSem;
	private ArrayList<Car> carsPark = new ArrayList<Car>();
	private LinkedList<Car> carsParked = new LinkedList<Car>();
	private ThreadPoolExecutor executor;
	
	/**
	 * Konstruktor
	 * @param sem Semafor för att hålla koll på lediga platser
	 * @param threadpool Trådpool för att exekvera task där en bil lämnar parkeringshuset
	 */
	public ParkingHouse(Semaphore sem, ThreadPoolExecutor threadpool) {
		parkingSem = sem;
		executor = threadpool;
	}
	
	/**
	 * Metoden lägger till en bil till parkeringshuset och exekverar task att en bil ska lämna
	 * efter en viss tid
	 * @param car Bilen som kommer in i parkeringshuset
	 */
	public synchronized void put(Car car) {
		carsParked.add(car);
		System.out.println("ParkingHouse: Bil nummer " + car.getIndex() + " har kommit in i parking house. count: " + carsParked.size());
		executor.execute(new CarExit(car, this));
	}
	
	/**
	 * Metoden tar bort en bil från p-huset (en bil lämnar p-huset)
	 * @param car Bilen som ska lämna
	 * @return Bilen som lämnat
	 */
	public synchronized Car getCar(Car car) {
		Boolean b = carsParked.remove(car);
		if(b) {
			parkingSem.release();
			System.out.println("Bil nummer " + car.getIndex() + " lämnade p-huset. Platser kvar: " + parkingSem.availablePermits());
		}else {
			System.out.println("FUCKKKKKK");
		}
		
		return car;
	}
	
	/**
	 * Metoden returnerar klassens semafor
	 * @return parkingSem Klassens semafor
	 */
	public Semaphore getSem() {
		return parkingSem;
	}
}
