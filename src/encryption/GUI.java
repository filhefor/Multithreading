package encryption;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.*;

/**
 * The GUI for assignment 2
 */
public class GUI 
{
	/**
	 * These are the components you need to handle.
	 * You have to add listeners and/or code
	 */
	private JFrame frame;			// The Main window
	private JLabel lblSrc;			// The source text
	private JLabel lblDst;			// The encrypted text

	private JTextArea txtSrc;		// The input field for source text
	private JTextArea txtDst;		// The input field for encrypted text
	private JButton btnEnc;         // The Encrypt button
	private JButton btnDec;			// The Decrypt button
	private JButton btnLoad;		// Load file button
	private JFileChooser fc;
	private String[] stringList;
	private String[] encryptedStringList;
	
	/**
	 * Constructor
	 */
	public GUI()
	{
	}
	
	/**
	 * Starts the application
	 */
	public void Start()
	{
		frame = new JFrame();
		frame.setBounds(0, 0, 893, 421);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setTitle("Simple Encryption");
		InitializeGUI();					// Fill in components
		frame.setVisible(true);
		frame.setResizable(false);			// Prevent user from change size
		frame.setLocationRelativeTo(null);	// Start middle screen
	}
	
	/**
	 * Sets up the GUI with components
	 */
	private void InitializeGUI()
	{
		// First, create the static components
		JLabel lab1 = new JLabel("Plain Text");
		lab1.setBounds(13, 13, 74, 13);
		frame.add(lab1);
		JLabel lab2 = new JLabel("Encrypted Text");
		lab2.setBounds(483, 13, 99, 13);
		frame.add(lab2);
		
		// Then add the two lists (of string) 
		txtSrc = new JTextArea();
		txtSrc.setEditable(false);
		JScrollPane s1 = new JScrollPane(txtSrc);
		s1.setBounds(12, 35, 356, 264);
		s1.setBorder(BorderFactory.createLineBorder(Color.black));		
		frame.add(s1);
		txtDst = new JTextArea();
		txtDst.setEditable(false);
		JScrollPane s2 = new JScrollPane(txtDst);
		s2.setBounds(486, 35, 393, 264);
		s2.setBorder(BorderFactory.createLineBorder(Color.black));		
		frame.add(s2);
		
		// The buttons
		btnEnc = new JButton("Encrypt ->");
		btnEnc.setBounds(374, 102, 106, 23);
		btnEnc.setEnabled(false);
		btnEnc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnDec.setEnabled(false);
				btnEnc.setEnabled(false);
				txtDst.setText("");
				Buffer buffer = new Buffer(stringList.length);
				new Writer(stringList, buffer, btnDec, btnEnc);
				new Encrypter(buffer, stringList.length);
				new Reader(buffer, txtDst, encryptedStringList, btnDec, btnEnc);
			}
		});
		frame.add(btnEnc);
		btnDec = new JButton("<- Decrypt");
		btnDec.setBounds(374, 141, 106, 23);
		btnDec.setEnabled(false);
		btnDec.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnDec.setEnabled(false);
				btnEnc.setEnabled(false);
				txtDst.setText("");
				Buffer buffer = new Buffer(stringList.length);
				new Writer(encryptedStringList, buffer, btnDec, btnEnc);
				new Encrypter(buffer, encryptedStringList.length);
				new Reader(buffer, txtDst, encryptedStringList, btnDec, btnEnc);
			}
		});
		frame.add(btnDec);
		btnLoad = new JButton("Load Working Text");
		btnLoad.setBounds(343, 319, 159, 23);
		btnLoad.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if(fc.showOpenDialog(btnLoad) == JFileChooser.APPROVE_OPTION) {
					
				}
				try {
					BufferedReader br = new BufferedReader(new FileReader(fc.getSelectedFile().getAbsolutePath()));
					ArrayList<String> list = new ArrayList<String>();
					
					String line = br.readLine();
					txtSrc.setText("");
					txtDst.setText("");
					while (line != null) {
						list.add(line);
						txtSrc.append(line + "\n");
						line = br.readLine();
					}
					stringList = new String[list.size()];
					encryptedStringList = new String[list.size()];
					for(int i = 0; i < list.size(); i++) {
						stringList[i] = list.get(i);
					}
					btnEnc.setEnabled(true);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		fc = new JFileChooser();
		fc.setCurrentDirectory(new java.io.File("/Users/filipheidfors/Documents/workspace/FlertrådadProgrammering/files"));
		fc.setDialogTitle("Chose textfile to encrypt");
		
		frame.add(btnLoad);
	}
	
	public static void main(String[] args) {
		new GUI().Start();
	}
}
