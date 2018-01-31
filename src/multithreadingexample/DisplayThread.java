package multithreadingexample;

import java.util.Random;

import javax.swing.*;

/**
 * Klassen är en tråd för att hantera slumpmässigt positionsbyte av texten i Display-Panelen
 * @author Filip Heidfors
 *
 */
public class DisplayThread extends Thread{
	private boolean running = true;
	private int panelHeight;
	private int panelWidth;
	private JLabel lbl;
	private Random rand = new Random();
	
	/**
	 * Konstruktorn tar emot vårt GUI för att hämta viktiga saker som labeln som ska ändras
	 * samt panelens bredd och höjd (panelen som labeln är i)
	 * @param gui En instans av klassen GUIAssignment1
	 */
	public DisplayThread(GUIAssignment1 gui) {
		panelHeight = gui.getPnlMove().getHeight();
		panelWidth = gui.getPnlMove().getWidth();
		lbl = gui.getLblDisplay();
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(isRunning()) {
			//Byta position på lblDisplay i GUI't till en slumpmässig position inom panelen
			lbl.setLocation(rand.nextInt(panelWidth-lbl.getWidth()), rand.nextInt(panelHeight-lbl.getHeight()));
			try {
				sleep(1000); //Kör en gång i sekunden
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * Kollar om tråden är igång
	 * @return running True om tråden är igång, annars false
	 */
	public boolean isRunning() {
		return running;
	}
	
	/**
	 * Metoden sätter running till false vilket gör att tråden stoppas
	 */
	public void stopThread() {
		running = false;
	}
	

}
