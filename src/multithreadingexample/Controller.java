package multithreadingexample;

import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Controllerklass som hanterar logik och som agerar länk/brygga mellan trådar och GUI
 * @author Filip Heidfors
 *
 */
public class Controller {
	private GUIAssignment1 gui;
	private DisplayThread dpThread;
	private RectangleThread recThread;
	
	public Controller() {
		
	}
	
	/**
	 * Metoden skapar en instans av DisplayThread om dpThread är null (inte redan skapad och igång) och
	 * startar sedan tråden
	 */
	public void displayThreadStart() {
		System.out.println("DISPLAY THREAD START");

		if(dpThread == null) {
			dpThread = new DisplayThread(gui);
			dpThread.start();
			//dpThread.join();
		}
		
	}
	
	/**
	 * Metoden stoppar tråden DisplayThread om den är skapad och är igång. Sätter den sen till null
	 */
	public void displayThreadStop() {
		System.out.println("DISPLAY THREAD STOP");
		//if(dpThread.isRunning()) {
		if(dpThread != null) {
			dpThread.stopThread();
			dpThread = null;
		}
		
	}
	
	/**
	 * Metoden skapar en instans av RectangleThread om recThread är null (inte redan skapad och igång) och
	 * startar sedan tråden
	 */
	public void rectangleThreadStart() {
		System.out.println("RECTANGLE THREAD START");
		if(recThread == null) {
			recThread = new RectangleThread(gui);
			recThread.start();
		}
	}
	
	/**
	 * Metoden stoppar RectanleThread om tråden inte är null, det vill säga om den är igång och körs
	 */
	public void rectangleThreadStop() {
		System.out.println("RECTANGLE THREAD STOP");
		if(recThread != null) {
			recThread.stopThread();
			recThread = null;
		}
		
	}
	
	/**
	 * Metoden ger klassen en instans av gui som behövs för att skicka med till de
	 * olika trådarna
	 * @param gui En instans av klassen GUIAssignment1
	 */
	public void setGUI(GUIAssignment1 gui) {
		this.gui = gui;
	}
	
}
