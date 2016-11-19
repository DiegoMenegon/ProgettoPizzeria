import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.eclipse.swt.widgets.Shell;

public class Lista {
	private int numpizze = 0; // pizze da fare
	private ArrayList<Ordine> ordini;
	public Component s;
	
	public Lista() {
		ordini = new ArrayList<Ordine>();
		//this.s = s;
	}
	
	public synchronized void inizia(){
		while(numpizze==0){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		numpizze--;
		notifyAll();
	}
	public synchronized void pizzaPronta(){
		// tolgo dal primo ordine in lista la pizza da fare
		if(ordini.get(0).np!=0){
			ordini.get(0).np--;
		} else{
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// se get(0) è a zero aspetta
		System.out.println("Pizza pronta");
		notifyAll();
	}
	public synchronized void ordine(Ordine o){
		numpizze = numpizze + o.np;
		ordini.add(o);
		System.out.println(""+numpizze);
		notifyAll();
	}

	public synchronized void prelevaOrdine(Ordine o) {
		// fare attendere finché l'ordine numero o.i non è a zero
		 while (!(ordini.get(0).cl==o.cl && ordini.get(0).np==0)) {	
			 try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		 }
		 ordini.remove(0);
		 JOptionPane.showMessageDialog(s, "L'ordine del cliente " + o.cl + " è pronto");
		 notifyAll();
	}
}
