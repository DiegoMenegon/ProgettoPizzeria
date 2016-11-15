
public class Ordine {
	int cl, np;
	String[] s = new String[100];
	public Ordine(int np, int cl, String[] s){
		this.cl = cl;
		this.np = np;
		this.s = s;
	}
	@Override
	public String toString() {
		return "Ordine del cliente " + cl + " di " + np + " pizza/e";
	}
	
	
}
