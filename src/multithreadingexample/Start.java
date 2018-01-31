package multithreadingexample;

public class Start {
	private static GUIAssignment1 gui;
	
	public static void main(String[] args) {
		
		//Starta programmet, skicka med kontroller till GUI't
		gui = new GUIAssignment1(new Controller());
		
	}
}
