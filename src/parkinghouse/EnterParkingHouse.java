package parkinghouse;

import java.util.Random;

public class EnterParkingHouse implements Runnable {
	private EntrySouth es; EntryNorth en; EntryWest ew; EntryEast ee;
	//private int entry;
	private Car car;
	private Random random = new Random();
	
	public EnterParkingHouse(Car car, EntrySouth es, EntryNorth en, EntryWest ew, EntryEast ee) {
		this.car = car;
		this.es = es;
		this.en = en;
		this.ew = ew;
		this.ee = ee;
	}
	
	@Override
	public void run() {
		int entry = random.nextInt(4);
		switch(entry){
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
	}

}
