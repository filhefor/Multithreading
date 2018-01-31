package parkinghouse;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class EntryWest implements Runnable {
	private LinkedList<Car> buffer = new LinkedList<Car>();
	private Semaphore parkingSem;
	private ParkingHouse phouse;
	private static final int max_cars = 5;

	public EntryWest(ParkingHouse phouse) {
		this.phouse = phouse;
		parkingSem = phouse.getSem();
//		start();
	}

	public synchronized void put(Car car) {
		if(buffer.size() < max_cars) {
			buffer.add(car);
			notify();
			System.out.println("ENTRYWEST: bil har kommit till entren. Count: " + buffer.size());
		}else {
			System.out.println("ENTRYWEST: För mycket bilar vid entren. Bilen får parkera någon annanstans. count: " + buffer.size());
		}
	}
	
	public synchronized Car getCar() throws InterruptedException {
		while(buffer.isEmpty()) wait();
		return buffer.removeFirst();
	}

	public void run() {
		Car car;
		while (true) {
			try {
				car = getCar();
				parkingSem.acquire();
				phouse.put(car);
				System.out.println(
						"EntryWest: Bil körde in i parkinghouse. Platser kvar: " + parkingSem.availablePermits());

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
