package parkinghouse;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Klassen startar programmet
 * @author filip heidfors
 *
 */
public class Start implements Runnable{
	private ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(20);
	private EntrySouth es;
	private EntryNorth en;
	private EntryWest ew;
	private EntryEast ee;
	private Random random = new Random();
	
	/**
	 * Konstruktor
	 */
	public Start() {
		Semaphore enterParking = new Semaphore(20); //Skapar en semafor som initieras till antal lediga platser i parkeringshuset.
		ParkingHouse phouse = new ParkingHouse(enterParking, executor); //Skapar en instans av parkeringshuset och skickar 
																		//med både trådpoolen och semaforen
		
		//Skapar mina 4 ingångar som är tasks och exekverar dem med hjälp av trådpoolen.
		es = new EntrySouth(phouse);
		en = new EntryNorth(phouse);
		ew = new EntryWest(phouse);
		ee = new EntryEast(phouse);
		executor.execute(es);
		executor.execute(en);
		executor.execute(ew);
		executor.execute(ee);
		
		executor.execute(this); //Exekverar denna klassen genom trådpoolen också
		
	}
	
	public void run() {
		Car car;
		int index = 0;
		while(true) {
			/*
			 * Den här bilen ska stå parkerad mellan 1 och 100 sekunder,
			 * efter den har fått en plats.
			 */
			car = new Car((random.nextInt(100)+10)*1000, index); 
			
			//Slumpa vilken entre bilen ska köra till
			switch(random.nextInt(4)) {
			case 0:
				es.put(car);
				break;
			case 1:
				en.put(car);
				break;
			case 2:
				ew.put(car);
				break;
			case 3:
				ee.put(car);
				break;
			}
			try {
				//Tråden sover mellan 1-5 sekunder. Detta är hur ofta en ny bil kör till en entre
				Thread.sleep((random.nextInt(5)+1)*1000); 
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			index++;
		}
	}
		
	public static void main(String[] args) {
		
		new Start();
	}
}
