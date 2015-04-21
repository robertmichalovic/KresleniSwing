package udalosti;
import java.awt.FileDialog;
import java.awt.event.*;
import java.io.*;
import javax.swing.JFrame;
import scena.KolekceKreslicichObjektu;
public class UdalostMenuUloz implements ActionListener { 
	private JFrame ramecek;								//	mainJFrame
	private static String jmeno;  						// ulozen nazev souboru + cesta
	private static FileDialog souborDialog; 			// nazev souboru
	private static FileOutputStream soubor; 			// bajtovy proud objekty
	private static ObjectOutputStream proudObjekt;		// ukladani objektu objekty
	public UdalostMenuUloz(JFrame temp){						// konstruktor
		this.ramecek=temp;	}
	public void actionPerformed(ActionEvent arg0) {
		this.jmenoSoubor(FileDialog.SAVE);
		try {	
			ulozeniDoSouboru();	}
		catch(Exception e) {System.out.println("Nepodarilo se ulozit ");	}	}
	private void jmenoSoubor(int mod){
		souborDialog = new FileDialog(ramecek,"Uloz Soubor ...  *.gui",mod); // konstruktor objektu akceptuje pouze dialog typu JFrame
		souborDialog.setVisible(true);
		jmeno = souborDialog.getDirectory()+souborDialog.getFile();	}
	private void ulozeniDoSouboru() {	
		vytvoreniSouboruProudu();
		ulozeniKolekce();
		ukonceniProuduSouboru();	}
	private static void vytvoreniSouboruProudu() {
		try{
			soubor = new FileOutputStream(jmeno);
			proudObjekt = new ObjectOutputStream(soubor);	}
		catch(Exception e){	e.printStackTrace();
		System.out.println("Vytvoreni proudu se nezdarilo");}	}
	private static void ulozeniKolekce() {
		try {
			proudObjekt.writeObject(KolekceKreslicichObjektu.seznamTvaru);	}		//	ulozime kolekci
		catch (IOException e) {	e.printStackTrace();
		System.out.println("Vlozeni objektu kolekci do proudu se nezdarilo");	}	}
	private static void ukonceniProuduSouboru() {
		try{
			soubor.close();
			proudObjekt.close();	}
		catch(Exception e){	e.printStackTrace();
		System.out.println("Ukonceni proudu se nezdarilo");}	}
}
