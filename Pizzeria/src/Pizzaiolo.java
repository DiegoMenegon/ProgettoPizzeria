import java.awt.List;

import org.eclipse.swt.widgets.Display;

public class Pizzaiolo extends Thread {
	Lista lista;
	private org.eclipse.swt.widgets.List list;
	private org.eclipse.swt.widgets.List list2;


	public Pizzaiolo(Lista lista2, org.eclipse.swt.widgets.List list3, org.eclipse.swt.widgets.List list4) {
		this.lista = lista2;
		this.list = list3;
		this.list2 = list4;
		
	}

	public void run() {

		while (true) {
			lista.inizia();
			// if(lista.numpizze>0){
			System.out.println("Cuocendo");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			lista.pizzaPronta();
			Display.getDefault().asyncExec(new Runnable(){
				public void run(){
					list2.add(list.getItem(0));
					list.remove(0);
				}
			});
			/*
								 * }else{ System.out.println("Sto aspettando");
								 * try { Thread.sleep(1000); } catch
								 * (InterruptedException e) { // TODO
								 * Auto-generated catch block
								 * e.printStackTrace(); } }
								 */
		}
	}

}