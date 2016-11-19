import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;

import javax.swing.JOptionPane;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.custom.CCombo;
import javax.swing.JComboBox;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.wb.swt.SWTResourceManager;

public class PizzeriaGrafica {
	
	protected Shell shlPizzapp;
	
	Lista lista = new Lista();
	
	Cliente[] c= new Cliente[100];
	int i = 0;
	String[] s = new String[200];
	int t = 0;
	boolean b = false;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Text txtp;
	private Text txtq;
	

	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		try {
			PizzeriaGrafica window = new PizzeriaGrafica();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlPizzapp.open();
		shlPizzapp.layout();
		while (!shlPizzapp.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		
		shlPizzapp = new Shell();
		shlPizzapp.setBackground(SWTResourceManager.getColor(204, 255, 255));
		shlPizzapp.setImage(SWTResourceManager.getImage(PizzeriaGrafica.class, "/images/pizza.jpg"));
		shlPizzapp.setSize(450, 300);
		shlPizzapp.setText("PizzApp");
		
		List list = new List(shlPizzapp, SWT.BORDER);
		list.setBounds(10, 82, 114, 170);
		formToolkit.adapt(list, true, true);
		
		List list2 = new List(shlPizzapp, SWT.BORDER);
		list2.setBounds(149, 82, 114, 170);
		formToolkit.adapt(list2, true, true);
		
		Pizzaiolo p = new Pizzaiolo(lista,list,list2);
		Pizzaiolo p2 = new Pizzaiolo(lista,list,list2);
		
		Label lblNewLabel = new Label(shlPizzapp, SWT.NONE);
		lblNewLabel.setImage(SWTResourceManager.getImage(PizzeriaGrafica.class, "/images/closed.png"));
		lblNewLabel.setBounds(292, 200, 142, 35);
		
		
		Button btnApri = new Button(shlPizzapp, SWT.NONE);
		btnApri.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(b == false){
					p.start();
					p2.start();
					b=true;
					lblNewLabel.setImage(SWTResourceManager.getImage(PizzeriaGrafica.class, "/images/open.png"));
				} else{
					p.resume();
					p2.resume();
					lblNewLabel.setImage(SWTResourceManager.getImage(PizzeriaGrafica.class, "/images/open.png"));
				}
				
			}
		});
		btnApri.setBounds(310, 121, 114, 25);
		btnApri.setText("Apri");
		
		txtp = formToolkit.createText(shlPizzapp, "New Text", SWT.NONE);
		txtp.setText("");
		txtp.setBounds(10, 30, 95, 21);
		
		txtq = formToolkit.createText(shlPizzapp, "New Text", SWT.NONE);
		txtq.setText("");
		txtq.setBounds(149, 30, 61, 21);
		
		Label lblPizza = formToolkit.createLabel(shlPizzapp, "Pizza:", SWT.NONE);
		lblPizza.setBackground(SWTResourceManager.getColor(204, 255, 255));
		lblPizza.setBounds(10, 9, 55, 15);
		
		Label lblQuantit = formToolkit.createLabel(shlPizzapp, "Quantit\u00E0:", SWT.NONE);
		lblQuantit.setBackground(SWTResourceManager.getColor(204, 255, 255));
		lblQuantit.setBounds(149, 10, 55, 15);
		

		
		Label label = new Label(shlPizzapp, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(204, 255, 255));
		label.setBounds(282, 33, 22, 15);
		label.setText("0");
		
		
		
		Button btnAggiungiPizze = formToolkit.createButton(shlPizzapp, "Aggiungi pizze", SWT.NONE);
		btnAggiungiPizze.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int n = Integer.parseInt(txtq.getText());
				String nome = txtp.getText();
				for(t=0; t!=n+t; t++, n--){
					list.add(nome);
					
				}
				int q = Integer.parseInt(label.getText())+t;
				
				label.setText(""+ q);
			}
		});
		btnAggiungiPizze.setBounds(310, 28, 114, 25);
		
		Button btnNuovoOrdine = new Button(shlPizzapp, SWT.NONE);
		btnNuovoOrdine.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				s=list.getItems();
				int n=Integer.parseInt(label.getText());
				Ordine o=new Ordine(n,i,s);
				c[i] = new Cliente(lista, o);
				c[i].start();
				i++;
			}
		});
		btnNuovoOrdine.setBounds(310, 59, 114, 25);
		btnNuovoOrdine.setText("Nuovo ordine!!");
		
		Label lblTotPizze = new Label(shlPizzapp, SWT.NONE);
		lblTotPizze.setBackground(SWTResourceManager.getColor(204, 255, 255));
		lblTotPizze.setBounds(216, 33, 55, 15);
		lblTotPizze.setText("Tot pizze:");
		
		
		
		Button btnCancella = new Button(shlPizzapp, SWT.NONE);
		btnCancella.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				i = 0;
				s = new String[200];
				t = 0;
				list.removeAll();
				txtp.setText("");
				txtq.setText("");
				label.setText("0");
			}
		});
		btnCancella.setBounds(310, 90, 114, 25);
		formToolkit.adapt(btnCancella, true, true);
		btnCancella.setText("Svuota");
		
		Button btnChiudi = new Button(shlPizzapp, SWT.NONE);
		btnChiudi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				p.suspend();
				p2.suspend();
				lblNewLabel.setImage(SWTResourceManager.getImage(PizzeriaGrafica.class, "/images/closed.png"));
			}
		});
		btnChiudi.setBounds(310, 154, 114, 25);
		formToolkit.adapt(btnChiudi, true, true);
		btnChiudi.setText("Chiudi");
	}
}
