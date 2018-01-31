package multithreadingexample;

/**
 * Klassen är en tråd som hanterar slumpmässig ändring av position och storlek på en rektangel
 * @author filipheidfors
 *
 */
public class RectangleThread extends Thread {
	private GUIAssignment1 gui;
	private boolean running = true;
	
	/**
	 * Konstruktorn tar emot vårt GUI
	 * @param gui En instans av klassen GUIAssigmnent1
	 */
	public RectangleThread(GUIAssignment1 gui) {
		this.gui = gui;
	}
	
	public void run() {
		while(isRunning()) {
			try {
				gui.getPnlShape().repaint(); //Hämta "pnlShape" som är en instans av MyShapePanel och kör metoden
											//repaint() som ritar om och byter position på rektangeln
				Thread.sleep(1000); //Kör en gång i sekunden
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Metoden returnerar värdet på running
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
