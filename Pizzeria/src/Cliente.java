
public class Cliente extends Thread{
	Lista lista;
	Ordine o;
	public Cliente(Lista lista, Ordine o){
		this.lista = lista;
		this.o = o;
	}
	public void run(){
		lista.ordine(o);
		// preleva ordine
		// deve aspettare finché è pronto l'ordine (l'attesa viene gestita dalla lista)
		lista.prelevaOrdine(o);
	}
}
