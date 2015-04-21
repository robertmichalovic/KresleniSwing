package udalosti;
import java.awt.FileDialog;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.JFrame;
import scena.KolekceKreslicichObjektu;
import tvar.objekt.RodicTvar;
public class UdalostMenuNacti implements ActionListener {
	private JFrame ramecek;
	private static String jmeno;
	private static FileInputStream soubor;
	private static ObjectInputStream proudObjekt;
	public UdalostMenuNacti(JFrame temp){
		this.ramecek=temp;	}
	public void actionPerformed(ActionEvent arg0) {
		jmenoSouboru(FileDialog.LOAD);
		try {	
			NacteniSouboru();	}
		catch(Exception e) {System.out.println("Nepodarilo se nacist ");	}	}
	private void jmenoSouboru(int mod){
		FileDialog souborDialog = new FileDialog(ramecek,"Nacti Soubor ...  *.gui",mod); // konstruktor objektu akceptuje pouze dialog typu JFrame
		souborDialog.setVisible(true);
		jmeno = souborDialog.getDirectory()+souborDialog.getFile();	}
	private void NacteniSouboru(){
		new UdalostMenuNovy(); 						// vymazeme stavajici stav
		vytvoreniSouboruProudu();
		nacteniKolekciZeSouboru();
		ukonceniProuduSouboru();	}
	private static void vytvoreniSouboruProudu() {
		try{
			soubor = new FileInputStream(jmeno);
			proudObjekt = new ObjectInputStream(soubor);	}
		catch(Exception e){	e.printStackTrace();
		System.out.println("Vytvoreni proudu se nezdarilo");}	}
	@SuppressWarnings("unchecked")
	private static void nacteniKolekciZeSouboru() {
		try {
			KolekceKreslicichObjektu.seznamTvaru = (ArrayList<RodicTvar>) proudObjekt.readObject();	}
		catch (ClassNotFoundException e) {	e.printStackTrace();	} 
		catch (IOException e) {	e.printStackTrace();
		System.out.println("Nacteni objektu kolekci do proudu se nezdarilo");	}	}
	private static void ukonceniProuduSouboru() {
		try{
			soubor.close();
			proudObjekt.close();	}
		catch(Exception e){	e.printStackTrace();
		System.out.println("Ukonceni proudu se nezdarilo");}	}		
}
