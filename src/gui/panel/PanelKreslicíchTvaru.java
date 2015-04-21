package gui.panel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import scena.VytvareniObjektu;
public class PanelKreslicíchTvaru extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1247887267587008485L;
	private JButton buttonCara,buttonObdelnik,buttonElipsa,buttonKruznice,buttonViceCara;
	private void nastaveniKonstruktoru() {
		this.setLayout(new GridLayout(1,4,0,40));	}
	private void rozlozeniPanel() {
		this.buttonCara = new JButton("Linie(Èára)");
		this.add(this.buttonCara);
		this.buttonCara.addActionListener(this); 								// buttonu priradim udalost
		this.buttonObdelnik = new JButton("Obdelnik");
		this.add(this.buttonObdelnik);
		this.buttonObdelnik.addActionListener(this); 							// buttonu priradim udalost
		this.buttonElipsa = new JButton("Elipsa");
		this.add(this.buttonElipsa);
		this.buttonElipsa.addActionListener(this); 								// buttonu priradim udalost
		this.buttonKruznice = new JButton("Kružnice");
		this.add(this.buttonKruznice);
		this.buttonKruznice.addActionListener(this); 							// buttonu priradim udalost
		this.buttonViceCara = new JButton("PolyLinie");
		this.add(this.buttonViceCara);
		this.buttonViceCara.addActionListener(this); } 							// buttonu priradim udalost
	public PanelKreslicíchTvaru(){ 												// konstruktor
		nastaveniKonstruktoru();
		rozlozeniPanel();	}
	public void actionPerformed(ActionEvent e) {
		VytvareniObjektu.getInstance().znicObjekt(); 						//	znicime predchozi objekt
		VytvareniObjektu.getInstance().setCislo((short)nastaveniTvaru(e));	//	zjistime typ grafickeho objektu 								
		VytvareniObjektu.getInstance().vytvorObjekt();	}					//	vytvorime neuplny objekt
	private short nastaveniTvaru(ActionEvent e){
		String nazevTlacitka = e.getActionCommand();
		//System.out.println("Zjistime typ objektu - bod 1"+nazevTlacitka);  	// kontrolni testovaci vypis
		if(nazevTlacitka.equals("Linie(Èára)")) 	return 1	;
		if(nazevTlacitka.equals("Obdelnik"))		return 2	;
		if(nazevTlacitka.equals("Elipsa"))			return 3	;
		if(nazevTlacitka.equals("Kružnice"))		return 4	;
		if(nazevTlacitka.equals("PolyLinie"))		return 5	;
		else return 0;	}
}
