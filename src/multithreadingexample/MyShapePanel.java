package multithreadingexample;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

/**
 * Klassen är en panel som ritar en rektangel med slumpmässig position och storlek
 * @author Filip Heidfors
 *
 */
public class MyShapePanel extends JPanel {
	private Random rand = new Random();
	private int width = 200,height = 200;
	
	/**
	 * Konstuktorn tar emot bredd och höjd och sätter panelen storlek till detta
	 * @param width Panelens bredd
	 * @param height Panelens Höjd
	 */
	public MyShapePanel(int width, int height) {
		setPreferredSize(new Dimension(width,height));
		width = this.getWidth();
		height = this.getHeight();
		setLayout(null);
		repaint();
	}
	
	/**
	 * Metoden ritar ut en röd rektangel på panelen med slumpmässig position, bredd och höjd
	 */
	protected void paintComponent(Graphics g) {
		g.setColor(Color.RED);
		g.drawRect(rand.nextInt(width/2), rand.nextInt(height/2), rand.nextInt(width/2), rand.nextInt(height/2));
	}
}
