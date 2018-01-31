package poolhouse;

import java.util.LinkedList;

import javax.swing.JLabel;

/**
 * Klassen är en buffer med olika köer
 * @author filip heidfors
 *
 */
public class Buffer {
	private LinkedList<Customer> apWait = new LinkedList<Customer>();
	private LinkedList<Customer> cpWait = new LinkedList<Customer>();
	private LinkedList<Customer> ap = new LinkedList<Customer>();
	private LinkedList<Customer> cp = new LinkedList<Customer>();
	private LinkedList<Customer> apExit = new LinkedList<Customer>();
	private LinkedList<Customer> cpExit = new LinkedList<Customer>();
	
	private JLabel lblApWait;
	private JLabel lblCpWait;
	private JLabel lblAp;
	private JLabel lblCp;
	private JLabel lblApExit;
	private JLabel lblCpExit;
	
	/**
	 * Konstruktor
	 * @param apLblWait Label för hur många som är i väntekön till adventure pool
	 * @param cpLblWait Label för hur många som är i väntekön till common pool
	 * @param apLbl Label för hur många som är i adventure pool
	 * @param cpLbl Label för hur många som är i common pool
	 * @param apExit Label för hur många som har lämnat adventure pool
	 * @param cpExit Label för hur många som har lämnat common pool
	 */
	public Buffer(JLabel apLblWait, JLabel cpLblWait, JLabel apLbl, JLabel cpLbl, JLabel apExit, JLabel cpExit) {
		lblApWait = apLblWait;
		lblApWait.setText(apWait.size() + "");
		lblCpWait = cpLblWait;
		lblCpWait.setText(cpWait.size() + "");
		lblAp = apLbl;
		lblAp.setText(ap.size() + "");
		lblCp = cpLbl;
		lblCp.setText(cp.size() + "");
		lblApExit = apExit;
		lblCpExit = cpExit;
	}
	
	/**
	 * Metoden sätter en customer i väntekön för att få tillträde till adventure pool
	 * @param c Customer som ska in i kön
	 * @throws InterruptedException
	 */
	public synchronized void putApWait(Customer c) throws InterruptedException {
//		while(apWait.size() >= 10) {
//			wait();
//		}
		apWait.add(c);
		System.out.println("AdventureQ: NY customer med vip: " + c.isVip() + " COUNT: " + apWait.size());
		lblApWait.setText(apWait.size() + "");
		notifyAll();
	}
	
	/**
	 * Metoden hämtar en Customer från kön till adventure pool
	 * @return Customer Customern som står först i kön
	 * @throws InterruptedException
	 */
	public synchronized Customer getApWait() throws InterruptedException {
		while(apWait.isEmpty()) {
			wait();
		}
		Customer c = apWait.removeFirst();
		System.out.println("AdventureQ: BORTTAGEN customer COUNT: " + apWait.size());
		lblApWait.setText(apWait.size() + "");
		notifyAll();
		return c;
	}
	
	/**
	 * Metoden sätter en Customer i väntekön för att få tillträde till common pool
	 * @param c Customer som ska in i kön
	 * @throws InterruptedException
	 */
	public synchronized void putCpWait(Customer c) throws InterruptedException {
//		while(cpWait.size() >= 15) {
//			wait();
//		}
		cpWait.add(c);
		System.out.println("CommonQ: NY customer med vip: " + c.isVip() + " COUNT: " + cpWait.size());
		lblCpWait.setText(cpWait.size() + "");
		notifyAll();
	}
	
	/**
	 * Metoden hämtar en Customer från väntekön till att komma in i common pool
	 * @return Customer Customer som står först i kön
	 * @throws InterruptedException
	 */
	public synchronized Customer getCpWait() throws InterruptedException {
		while(cpWait.isEmpty()) {
			wait();
		}
		Customer c = cpWait.removeFirst();
		System.out.println("CommonQ: BORTTAGEN customer COUNT: " + cpWait.size());
		lblCpWait.setText(cpWait.size() + "");
		notifyAll();
		return c;
	}
	
	/**
	 * Metoden sätter in en Customer i adventure pool
	 * @param c Customern som sätts in
	 * @throws InterruptedException
	 */
	public synchronized void putAp(Customer c) throws InterruptedException {
		while(ap.size() >= 10) {
			wait();
		}
		ap.add(c);
		System.out.println("AdventurePool: NY customer. COUNT: " + ap.size());
		lblAp.setText(ap.size() + "");
		notifyAll();
	}
	
	/**
	 * Metoden hämtar och tar bort en Customer från adventure pool
	 * @return Customer Customern som varit i poolen längst
	 * @throws InterruptedException
	 */
	public synchronized Customer getAp() throws InterruptedException {
		while(ap.isEmpty()) {
			wait();
		}
		Customer c = ap.removeFirst();
		System.out.println("AdventurePool: BORTTAGEN customer. COUNT: " + ap.size());
		lblAp.setText(ap.size() + "");
		notifyAll();
		return c;
	}
	
	/**
	 * Metoden sätter in en Customer i common pool
	 * @param c Customern som sätts in
	 * @throws InterruptedException
	 */
	public synchronized void putCp(Customer c) throws InterruptedException {
		while(cp.size() >= 15) {
			wait();
		}
		cp.add(c);
		System.out.println("CommonPool: NY customer. COUNT: " + cp.size());
		lblCp.setText(cp.size() + "");
		notifyAll();
	}
	
	/**
	 * Metoden hämtar och tar bort en Customer från common pool
	 * @return Customer Customern som varit längst i poolen
	 * @throws InterruptedException
	 */
	public synchronized Customer getCp() throws InterruptedException {
		while(cp.isEmpty()) {
			wait();
		}
		Customer c = cp.removeFirst();
		System.out.println("CommonPool: BORTTAGEN customer. COUNT: " + cp.size());
		lblCp.setText(cp.size() + "");
		notifyAll();
		return c;
	}
	
	/**
	 * Metoden sätter in en Customer i exitkön för adventure pool
	 * @param c Customer som sätts in i kön
	 */
	public synchronized void putApExit(Customer c) {
		apExit.add(c);
		lblApExit.setText(apExit.size() + "");
	}
	
	/**
	 * Metoden stter in en Customer i exitkön för common pool
	 * @param c Customern som sätts in i kön
	 */
	public synchronized void putCpExit(Customer c) {
		cpExit.add(c);
//		System.out.println("ExitCP: " + cpExit.size());
		lblCpExit.setText(cpExit.size() + "");
	}
}
