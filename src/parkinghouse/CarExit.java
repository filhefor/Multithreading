package parkinghouse;

/**
 * Klassen är en task som gör att en bil lämnar parkeringshuset
 * @author filip heidfors
 *
 */
public class CarExit implements Runnable{
	private int parkingTime;
	private ParkingHouse phouse;
	private Car car;
	
	/**
	 * Konstruktor
	 * @param car Bilen som ska lämna p-huset
	 * @param phouse P-huset som bilen ska lämna
	 */
	public CarExit(Car car, ParkingHouse phouse) {
		this.parkingTime = car.getTime();
		this.phouse = phouse;
		this.car = car;
	}
	
	/**
	 * run-metod
	 */
	public void run() {
		try {
			System.out.println("Bil nr " + car.getIndex() + " ska stå parkerad i " + parkingTime + " sek");
			Thread.sleep(parkingTime); //Sov först så länge som bilen ska stå parkerad
			phouse.getCar(car); //Ta sedan bort denna bilen
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
