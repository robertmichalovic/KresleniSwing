package KresleniVerze3;
import static java.awt.event.InputEvent.CTRL_DOWN_MASK;

import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;
import java.io.*;
import java.util.*;

import javax.print.attribute.*;
import javax.swing.*;
class UdalostBarvaCary implements ActionListener {
	private JFrame frame;
	UdalostBarvaCary(JFrame frame) {	this.frame=frame;	}
	public void actionPerformed(ActionEvent arg0) {
		Color barva=Color.black;									//	vychozi barva bude cerna
		barva = JColorChooser.showDialog(frame, "Vyber barvy - cary",barva);
		if(barva == null) barva = Color.black;						//	kontrola kvuli tlacitku Cancel = vytvari nulovou referenci
		VytvareniObjektu.getInstance().setBarvaObjektu(barva);
		this.frame.setVisible(true);	}
}
class UdalostBarvaPlatna implements ActionListener {
	private JFrame frame;
	UdalostBarvaPlatna(JFrame frame) {	this.frame=frame;	}
	public void actionPerformed(ActionEvent arg0) {
		Color barva = Color.white;									//	nacteme pozadi do Barvy
		barva = JColorChooser.showDialog(frame, "Vyber barvy - pozadi",barva);
		if(barva == null) barva = Color.white;						//	kontrola kvuli tlacitku Cancel = vytvari nulovou referenci
		VykreslujiciScena.getInstance().setBackground(barva);
		this.frame.setVisible(true);	}
}
class UdalostMenuUloz implements ActionListener { 
	private JFrame ramecek;								//	mainJFrame
	private static String jmeno;  						// ulozen nazev souboru + cesta
	private static FileDialog souborDialog; 			// nazev souboru
	private static FileOutputStream soubor; 			// bajtovy proud objekty
	private static ObjectOutputStream proudObjekt;		// ukladani objektu objekty
	UdalostMenuUloz(JFrame temp){						// konstruktor
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
		VytvoreniSouboruProudu();
		UlozeniKolekce();
		UkonceniProuduSouboru();	}
	private static void VytvoreniSouboruProudu() {
		try{
			soubor = new FileOutputStream(jmeno);
			proudObjekt = new ObjectOutputStream(soubor);	}
		catch(Exception e){	e.printStackTrace();
		System.out.println("Vytvoreni proudu se nezdarilo");}	}
	private static void UlozeniKolekce() {
		try {
			proudObjekt.writeObject(KolekceKreslicichObjektu.seznamTvaru);	}		//	ulozime kolekci
		catch (IOException e) {	e.printStackTrace();
		System.out.println("Vlozeni objektu kolekci do proudu se nezdarilo");	}	}
	private static void UkonceniProuduSouboru() {
		try{
			soubor.close();
			proudObjekt.close();	}
		catch(Exception e){	e.printStackTrace();
		System.out.println("Ukonceni proudu se nezdarilo");}	}
}
class UdalostMenuNacti implements ActionListener {
	private JFrame ramecek;
	private static String jmeno;
	private static FileInputStream soubor;
	private static ObjectInputStream proudObjekt;
	UdalostMenuNacti(JFrame temp){
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
		VytvoreniSouboruProudu();
		NacteniKolekciZeSouboru();
		UkonceniProuduSouboru();	}
	private static void VytvoreniSouboruProudu() {
		try{
			soubor = new FileInputStream(jmeno);
			proudObjekt = new ObjectInputStream(soubor);	}
		catch(Exception e){	e.printStackTrace();
		System.out.println("Vytvoreni proudu se nezdarilo");}	}
	@SuppressWarnings("unchecked")
	private static void NacteniKolekciZeSouboru() {
		try {
			KolekceKreslicichObjektu.seznamTvaru =(ArrayList<RodicTvar>) proudObjekt.readObject();	}
		catch (ClassNotFoundException e) {	e.printStackTrace();	} 
		catch (IOException e) {	e.printStackTrace();
		System.out.println("Nacteni objektu kolekci do proudu se nezdarilo");	}	}
	private static void UkonceniProuduSouboru() {
		try{
			soubor.close();
			proudObjekt.close();	}
		catch(Exception e){	e.printStackTrace();
		System.out.println("Ukonceni proudu se nezdarilo");}	}		
}
class UdalostMenuTisk implements ActionListener {
	public void actionPerformed(ActionEvent arg0) {
		PrinterJob tiskUloha = PrinterJob.getPrinterJob();
		VykreslujiciScena.getInstance().getGraphics();
		tiskUloha.setPrintable(VykreslujiciScena.getInstance());
		PrintRequestAttributeSet moznostiTisku = new HashPrintRequestAttributeSet();
		try {
			if (tiskUloha.printDialog(moznostiTisku)) tiskUloha.print(moznostiTisku);	} 
		catch (PrinterException ex) {
			System.out.println("Nelze tisknout");
			ex.printStackTrace();	}	}
}
class UdalostMenuExit implements ActionListener {  // Button Exit
	public void actionPerformed(ActionEvent e) {
		Object [] volba = {"Ano","Ne"};
		int n = JOptionPane.showOptionDialog(null, "Chcete opravdu skoncit", "Ukonceni programu", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null,volba,volba[0]);
		if (n == JOptionPane.YES_OPTION) System.exit(1);	}
}
class UdalostMenuNovy implements ActionListener{
	public void actionPerformed(ActionEvent arg0) {
		KolekceKreslicichObjektu.seznamTvaru.clear();	}
}
abstract class RodicTvar {	//	Rodic vsech potomku - nutny kvuli polymorfismu
	protected abstract short getIdentifikatorTvaru();
	public abstract void KresliTvarPracovni(Graphics2D g,MouseEvent e);	//	- metoda vykresluje objekt behem jeho vytvareni
	public abstract void KresliTvarCely(Graphics2D g);		//	- metoda vykresluje cely objekt 
	public abstract void PridejDoKolekce();					//	metoda prida objekt do kolekce
	public abstract void setXaY(int x,int y,MouseEvent arg0);//	metoda do objektu vlozi souradnice X a Y
	protected abstract boolean zjistiKonecObjektu(); 		//	zjistime jestli je objekt ukoncen
}
class KolekceKreslicichObjektu implements Serializable {
	private static final long serialVersionUID = -3647966151996506201L;
	protected static ArrayList<RodicTvar> seznamTvaru = new ArrayList<RodicTvar>();
}
class KresliCara extends RodicTvar implements Serializable {
	private static final long serialVersionUID = -5625209653648514632L;
	private short identifikatorTvaru=(short)0;									//	uklada identifikator objektu
	protected short getIdentifikatorTvaru() {	return identifikatorTvaru;	}	//	getter
	private int x1,y1,x2,y2;							//	souradnice cary
	private Color barvaCary;							//	nastavime barvu objektu
	@SuppressWarnings("unused")
	private static int cisloPocet=0;					//	uklada poradove cislo objektu
	private boolean souradnicePrvni = true;				//	nutne kvuli poznani prvnich nebo druhych souradnic
	private boolean konecObjektu;						//	nutne kvuli poznani ukonceni objektu
	protected boolean zjistiKonecObjektu() 	{	return konecObjektu;	}		//	getter
	public String toString() {return "Souradnice x1:"+x1+"\ty1:"+y1+"\tx2:"+x2+"\ty2:"+y2;	}
	KresliCara(short cisloIdentifikator,int Plus,Color barva){		//	konstruktor	
		this.identifikatorTvaru=cisloIdentifikator;
		this.x1=0;this.y1=0;this.x2=0;this.y2=0;
		cisloPocet=Plus;
		this.barvaCary=barva;
		//System.out.println("Konstruktor objektu KresliCara"+identifikatorTvaru+"\t"+cisloPocet);
		this.konecObjektu=false;	}
	public void setXaY(int x,int y,MouseEvent arg0) 	{
		//System.out.println("Ulozeni udalosti :"+x+"\t"+y);
		if(this.souradnicePrvni)	{		//	pokud je boolean nastaven na true - plati pouze pro prvni souradnice
			this.souradnicePrvni=false;	//	zmenime na false - logicky dalsi budou nasledovat druhe souradnice
			this.x1 = x;			//	provedem ulozeni x1 a y1 pro dany objekt
			this.y1 = y;	}
			//	System.out.println("Ulozeni udalosti :"+x1+"\t"+y1+"\t"+souradnicePrvni);
		else {
			this.souradnicePrvni=true;	//	zmenime logiku na true a ulozime souradnice x2 a y2
			this.konecObjektu=true;		//	nastavime provedeni zrelizovani objektu - pozname tak ze byli nastaveny vsechny paramtery
			this.x2 = x;
			this.y2 = y;	}	}
			//System.out.println("Ulozeni udalosti :"+x2+"\t"+y2+"\t"+souradnicePrvni);
	public void KresliTvarPracovni(Graphics2D g,MouseEvent e) {
		//System.out.println("Zde jsou souradnice :"+x1+"\t"+y1+"\t"+SourAktuXY.getX()+"\t"+SourAktuXY.getY());
		//***** kresli jenom pokud x1 a y1 a neni konec objektu se nerovnaji nule
		if(this.x1 != 0 & this.y1 != 0 & this.konecObjektu == false) {	
			g.setColor(this.barvaCary); 
			g.drawLine(this.x1, this.y1, SourAktuXY.getX(), SourAktuXY.getY());	}	}
	public void KresliTvarCely(Graphics2D g) {
		g.setColor(this.barvaCary); 								// nastavime barvu cary
		g.drawLine(this.x1, this.y1, this.x2, this.y2);	}
	public void PridejDoKolekce() {
		//System.out.println("Pridame objekt do kolekce - bod 5");
		KolekceKreslicichObjektu.seznamTvaru.add(this);	}
}
class KresliCtverec extends RodicTvar implements Serializable {
	private static final long serialVersionUID = 5788854981263859003L;
	private short identifikatorTvaru=(short)0;									//	uklada identifikator objektu
	protected short getIdentifikatorTvaru() {	return identifikatorTvaru;	}	//	getter
	private int x1,y1,x2,y2;							//	souradnice ctverce
	private Color barvaCtverce;							//	nastavime barvu objektu
	@SuppressWarnings("unused")
	private static int cisloPocet=0;					//	uklada poradove cislo objektu
	private boolean souradnicePrvni = true;				//	nutne kvuli poznani prvnich nebo druhych souradnic
	private boolean konecObjektu;						//	nutne kvuli poznani ukonceni objektu
	protected boolean zjistiKonecObjektu() 	{	return konecObjektu;	}		//	getter
	public String toString() {return "Souradnice x1:"+x1+"\ty1:"+y1+"\tx2:"+x2+"\ty2:"+y2;	}
	KresliCtverec(short cisloIdentifikator,int Plus, Color barva){		//	konstruktor	
		this.identifikatorTvaru=cisloIdentifikator;
		this.x1=0;this.y1=0;this.x2=0;this.y2=0;
		this.barvaCtverce=barva;
		cisloPocet=Plus;
		//System.out.println("Konstruktor objektu KresliCara"+identifikatorTvaru+"\t"+cisloPocet);
		this.konecObjektu=false;	}
	public void setXaY(int x,int y,MouseEvent arg0) 	{
		//System.out.println("Ulozeni udalosti :"+x+"\t"+y);
		if(this.souradnicePrvni)	{		//	pokud je boolean nastaven na true - plati pouze pro prvni souradnice
			this.souradnicePrvni=false;	//	zmenime na false - logicky dalsi budou nasledovat druhe souradnice
			this.x1 = x;			//	provedem ulozeni x1 a y1 pro dany objekt
			this.y1 = y;	}
			//	System.out.println("Ulozeni udalosti :"+x1+"\t"+y1+"\t"+souradnicePrvni);
		else {
			this.souradnicePrvni=true;	//	zmenime logiku na true a ulozime souradnice x2 a y2
			this.konecObjektu=true;		//	nastavime provedeni zrelizovani objektu - pozname tak ze byli nastaveny vsechny paramtery
			this.x2 = x-x1;
			this.y2 = y-y1;	}	}
			//System.out.println("Ulozeni udalosti :"+x2+"\t"+y2+"\t"+souradnicePrvni);
	public void KresliTvarPracovni(Graphics2D g,MouseEvent e) {
		//System.out.println("Zde jsou souradnice :"+x1+"\t"+y1+"\t"+SourAktuXY.getX()+"\t"+SourAktuXY.getY());
		//***** kresli jenom pokud x1 a y1 a neni konec objektu se nerovnaji nule
		if(this.x1 != 0 & this.y1 != 0 & this.konecObjektu == false) {
			g.setColor(this.barvaCtverce);
			g.drawRect(this.x1, this.y1, (SourAktuXY.getX()-this.x1), (SourAktuXY.getY()-this.y1));	}	}
	public void KresliTvarCely(Graphics2D g) {
		g.setColor(this.barvaCtverce);
		g.drawRect(this.x1, this.y1, this.x2, this.y2);	}
	public void PridejDoKolekce() {
		//System.out.println("Pridame objekt do kolekce - bod 5");
		KolekceKreslicichObjektu.seznamTvaru.add(this);	}
}
class KresliElipsa extends RodicTvar implements Serializable{
	private static final long serialVersionUID = -3919073061558669761L;
	private short identifikatorTvaru=(short)0;									//	uklada identifikator objektu
	protected short getIdentifikatorTvaru() {	return identifikatorTvaru;	}	//	getter
	private int x1,y1,x2,y2;							//	souradnice Elipsa
	private Color barvaElipsy;							//	nastavime barvu objektu
	@SuppressWarnings("unused")
	private static int cisloPocet=0;					//	uklada poradove cislo objektu
	private boolean souradnicePrvni = true;				//	nutne kvuli poznani prvnich nebo druhych souradnic
	private boolean konecObjektu;						//	nutne kvuli poznani ukonceni objektu
	protected boolean zjistiKonecObjektu() 	{	return konecObjektu;	}		//	getter
	public String toString() {return "Souradnice x1:"+x1+"\ty1:"+y1+"\tx2:"+x2+"\ty2:"+y2;	}
	KresliElipsa(short cisloIdentifikator,int Plus, Color barva){		//	konstruktor	
		this.identifikatorTvaru=cisloIdentifikator;
		this.x1=0;this.y1=0;this.x2=0;this.y2=0;
		this.barvaElipsy=barva;
		cisloPocet=Plus;
		//System.out.println("Konstruktor objektu KresliCara"+identifikatorTvaru+"\t"+cisloPocet);
		this.konecObjektu=false;	}
	public void setXaY(int x,int y,MouseEvent arg0) 	{
		//System.out.println("Ulozeni udalosti :"+x+"\t"+y);
		if(this.souradnicePrvni)	{		//	pokud je boolean nastaven na true - plati pouze pro prvni souradnice
			this.souradnicePrvni=false;	//	zmenime na false - logicky dalsi budou nasledovat druhe souradnice
			this.x1 = x;			//	provedem ulozeni x1 a y1 pro dany objekt
			this.y1 = y;	}
			//	System.out.println("Ulozeni udalosti :"+x1+"\t"+y1+"\t"+souradnicePrvni);
		else {
			this.souradnicePrvni=true;	//	zmenime logiku na true a ulozime souradnice x2 a y2
			this.konecObjektu=true;		//	nastavime provedeni zrelizovani objektu - pozname tak ze byli nastaveny vsechny paramtery
			this.x2 = x-x1;
			this.y2 = y-y1;	}	}
			//System.out.println("Ulozeni udalosti :"+x2+"\t"+y2+"\t"+souradnicePrvni);
	public void KresliTvarPracovni(Graphics2D g,MouseEvent e) {
		//System.out.println("Zde jsou souradnice :"+x1+"\t"+y1+"\t"+SourAktuXY.getX()+"\t"+SourAktuXY.getY());
		//***** kresli jenom pokud x1 a y1 a neni konec objektu se nerovnaji nule
		if(this.x1 != 0 & this.y1 != 0 & this.konecObjektu == false) {
			g.setColor(this.barvaElipsy);
			g.drawOval(this.x1, this.y1, (SourAktuXY.getX()-this.x1), (SourAktuXY.getY()-this.y1));	}	}
	public void KresliTvarCely(Graphics2D g) {
		g.setColor(this.barvaElipsy);
		g.drawOval(this.x1, this.y1, this.x2, this.y2);	}
	public void PridejDoKolekce() {
		//System.out.println("Pridame objekt do kolekce - bod 5");
		KolekceKreslicichObjektu.seznamTvaru.add(this);	}	
}
class KresliKruznice extends RodicTvar  implements Serializable{
	private static final long serialVersionUID = -309625135026204718L;
	private short identifikatorTvaru=(short)0;									//	uklada identifikator objektu
	protected short getIdentifikatorTvaru() {	return identifikatorTvaru;	}	//	getter
	private int x1,y1,x2,y2;							//	souradnice Kruznice
	private Color barvaKruznice;						//	nastavime barvu objektu
	@SuppressWarnings("unused")
	private static int cisloPocet=0;							//	uklada poradove cislo objektu
	private boolean souradnicePrvni = true;				//	nutne kvuli poznani prvnich nebo druhych souradnic
	private boolean konecObjektu;						//	nutne kvuli poznani ukonceni objektu
	protected boolean zjistiKonecObjektu() 	{	return konecObjektu;	}		//	getter
	public String toString() {return "Souradnice x1:"+x1+"\ty1:"+y1+"\tx2:"+x2+"\ty2:"+y2;	}
	KresliKruznice(short cisloIdentifikator,int Plus, Color barva){		//	konstruktor	
		this.identifikatorTvaru=cisloIdentifikator;
		this.x1=0;this.y1=0;this.x2=0;this.y2=0;
		this.barvaKruznice=barva;
		cisloPocet=Plus;
		//System.out.println("Konstruktor objektu KresliCara"+identifikatorTvaru+"\t"+cisloPocet);
		konecObjektu=false;	}
	public void setXaY(int x,int y,MouseEvent arg0) 	{
		//System.out.println("Ulozeni udalosti :"+x+"\t"+y);
		if(souradnicePrvni)	{		//	pokud je boolean nastaven na true - plati pouze pro prvni souradnice
			souradnicePrvni=false;	//	zmenime na false - logicky dalsi budou nasledovat druhe souradnice
			this.x1 = x;			//	provedem ulozeni x1 a y1 pro dany objekt
			this.y1 = y;	}
			//	System.out.println("Ulozeni udalosti :"+x1+"\t"+y1+"\t"+souradnicePrvni);
		else {
			souradnicePrvni=true;	//	zmenime logiku na true a ulozime souradnice x2 a y2
			konecObjektu=true;		//	nastavime provedeni zrelizovani objektu - pozname tak ze byli nastaveny vsechny paramtery
			this.x2 = x-x1;
			this.y2 = y-y1;	}	}
			//System.out.println("Ulozeni udalosti :"+x2+"\t"+y2+"\t"+souradnicePrvni);
	public void KresliTvarPracovni(Graphics2D g,MouseEvent e) {
		//System.out.println("Zde jsou souradnice :"+x1+"\t"+y1+"\t"+SourAktuXY.getX()+"\t"+SourAktuXY.getY());
		//***** kresli jenom pokud x1 a y1 a neni konec objektu se nerovnaji nule
		if(this.x1 != 0 & this.y1 != 0 & this.konecObjektu == false) {	
			g.setColor(barvaKruznice);
			g.drawArc(this.x1, this.y1, (SourAktuXY.getX()-this.x1), (SourAktuXY.getY()-this.y1),0,360);	}	}
	public void KresliTvarCely(Graphics2D g) {
		g.setColor(barvaKruznice);
		g.drawArc(this.x1, this.y1, this.x2, this.y2,0,360);	}
	public void PridejDoKolekce() {
		//System.out.println("Pridame objekt do kolekce - bod 5");
		KolekceKreslicichObjektu.seznamTvaru.add(this);	}
}
/* 0]  v tride VytvareniObjektu vytvorime nekompletni objekt a nastavime logiku konce objektu = false
 * 1]  v tride StisknutiTlacMouse v metode stisknuti mysi zavolame metodu setXaY - ziskame souradnice
 * 2]  v metode setXaY 1) ulozime souradnice x a y do X a Y .... nutne pro kresleni docasnych car - neboli caru kreslime vzdy od 
 * posledniho stisku mysi, druhe souradnice ziskavame z pohybu mysi po platne
 * 3]  v metode setXaY 2) nastavime ze chceme kreslit linii a vicelinii = true
 * 4]  v metode setXaY 3) pokud stisknu prave tlacitko mysi nastavim kresleni linie na false ---- nebude se zobrazovat
 * 5]  v metode setXaY 4) pokud kreslime vicelinii provedeme metody VytvoreniPoleZKolekce a PrevodIntegerInt, ktere souradnice
 * stisknute mysi x,y davaji do kolekce - to prevedou na pole jenz jsou paramtrem pri kresleni polyline
 * 6]  v metode setXaY 5) pokud stiskneme prave tlacitko pak se vymazou kolekce( nevadi pole uz ulozene mame ) a nastavime
 * konec objektu
 * 7]  v metode setXaY 6) je standardni pojeti kresleni car a vymena souradnic pro jejich kresleni
 * 8]  v metode KresliTvarPracovni kreslime prakticky vzdy 1 caru a celou docasnou polyline, krera se meni po pridavani dalsich bodu
 * 9]  pridame posledni polyline do kolekce objektu pokud je objekt ukoncen
 */
class KresliPolyLine extends RodicTvar implements Serializable{
	private static final long serialVersionUID = 1371587865476671766L;
	//****** hlavni promenne tridy
	private short identifikatorTvaru=(short)0;									//	uklada identifikator objektu
	protected short getIdentifikatorTvaru() {	return identifikatorTvaru;	}	//	getter
	@SuppressWarnings("unused")
	private static int cisloPocet=0;					//	uklada poradove cislo objektu
	private int x1,y1,x2,y2;							//	souradnice pro linii ktera se vykresluje
	private int [] pole1X,pole1Y; 						//	pole pro realizaci objektu
	private int velikostPole;							//	velikostPole
	private Color barvaPolyLine;						//	barva objektu
	private boolean konecObjektu;						//	nutne kvuli poznani ukonceni objektu
	protected boolean zjistiKonecObjektu() 	{	return konecObjektu;	}		//	getter
	//****** pomocne pro kresleni polylinie - docasne
	private boolean kresliLinie=false;				//  kresli linii
	private boolean kresliViceLinie=false;			//	nutne pro vicelinii
	private int X,Y;								//	zajisti presun poslednich ulozenych souradnic do docasneho kresleni
	private Collection<Integer> kolekceX = new ArrayList<Integer>();
	private Collection<Integer> kolekceY = new ArrayList<Integer>();
	private boolean souradnicePrvni = true;				//	nutne kvuli poznani prvnich nebo druhych souradnic
	public String toString() {return "Souradnice x1:"+x1+"\ty1:"+y1+"\tx2:"+x2+"\ty2:"+y2;	}
	KresliPolyLine(short cisloIdentifikator,int Plus, Color barva){		//	konstruktor	
		this.identifikatorTvaru=cisloIdentifikator;
		cisloPocet=Plus;
		this.barvaPolyLine=barva;
		//System.out.println("Konstruktor objektu KresliViceCara"+identifikatorTvaru+"\t"+cisloPocet);
		this.konecObjektu=false;	}
	public void setXaY(int x,int y,MouseEvent arg0) 	{
		//System.out.println("Ulozeni udalosti :"+x+"\t"+y);
		this.X=x;this.Y=y;
		this.kresliLinie=true;									// umozni kreslit linii
		this.kresliViceLinie=true;								// umozni kreslit vicelinii
		if(arg0.getModifiers()==InputEvent.BUTTON3_MASK)  { //	pokud stisknut prave tlacitko mysi
			this.kresliLinie=false;
			this.kresliViceLinie=true;	}							// ukonci kresleni linie
		if(kresliViceLinie == true) VytvoreniPoleZKolekce(arg0,x,y);	
		if(arg0.getModifiers()==InputEvent.BUTTON3_MASK && this.kresliLinie == false && this.kresliViceLinie == true){
			this.konecObjektu=true;		//	nastavime provedeni zrelizovani objektu - pozname tak ze byli nastaveny vsechny paramtery
			this.kolekceX.clear();
			this.kolekceY.clear();
			this.kolekceX=null;
			this.kolekceY=null;	}		// vymazu kolekci
		if(this.souradnicePrvni)	{		//	pokud je boolean nastaven na true - plati pouze pro prvni souradnice
			this.souradnicePrvni=false;	//	zmenime na false - logicky dalsi budou nasledovat druhe souradnice
			//System.out.println("Ulozeni udalosti 1 :"+x+"\t"+y+"\t"+souradnicePrvni);
			this.x1 = x;			//	provedem ulozeni x1 a y1 pro dany objekt
			this.y1 = y;	}
		else {
			this.souradnicePrvni=true;	//	zmenime logiku na true a ulozime souradnice x2 a y2
			//System.out.println("Ulozeni udalosti 2:"+x+"\t"+y+"\t"+souradnicePrvni);
			this.x2 = x;
			this.y2 = y;	}	}
	private void VytvoreniPoleZKolekce(MouseEvent e,int x,int y) {
		Integer [] tempPole;
		//System.out.println(x1+" test 1\t"+y1);
		//System.out.println(x2+" test 2\t"+y2);
		if (this.kresliLinie == true) {							//	pokud kreslime linii pak pridame prvky souradnice do kolekce
			this.kolekceX.add(x);
			this.kolekceY.add(y);	}
		this.velikostPole = kolekceX.size();  							// zjistime velikost kolekce
		tempPole = kolekceX.toArray(new Integer[velikostPole]); 		// kolekci prevedem na pole objektu Integer
		this.pole1X = PrevodIntegerInt(tempPole,velikostPole); 			// prevedem pole objektu Integer na pole int
		tempPole = kolekceY.toArray(new Integer[velikostPole]); 		// kolekci prevedem na pole objektu Integer
		this.pole1Y = PrevodIntegerInt(tempPole,velikostPole); }		// prevedem pole objektu Integer na pole int
	private int [] PrevodIntegerInt(Integer [] poleTemp,int velikost) {	 // metoda prevadejici pole objektu Integeru na pole int 
		int [] pole = new int [velikost];
		for(int i=0;i<pole.length;i++) {
			pole[i]= poleTemp[i].intValue(); }
		//System.out.println("Pole :"+Arrays.toString(pole));
		return pole;	}
	public void KresliTvarPracovni(Graphics2D g,MouseEvent e) {	
		//System.out.println("Zde jsou souradnice :"+x1+"\t"+y1+"\t"+SourAktuXY.getX()+"\t"+SourAktuXY.getY());
		//***** kresli jenom pokud x1 a y1 se nerovnaji nule a neni konec objektu a ma se kreslit linie
		if(this.kresliLinie == true & this.X != 0 & this.Y != 0 & this.konecObjektu == false ) {
			g.setColor(this.barvaPolyLine);
			g.drawLine(this.X,this.Y, SourAktuXY.getX(), SourAktuXY.getY());	}
		//	docasny objekt polyline
		if(this.kresliViceLinie == true) {
			g.setColor(this.barvaPolyLine);
			g.drawPolyline(this.pole1X, this.pole1Y, this.velikostPole);		}	}
	public void KresliTvarCely(Graphics2D g) {
		g.setColor(this.barvaPolyLine);
		g.drawPolyline(this.pole1X, this.pole1Y, this.velikostPole);	}
	public void PridejDoKolekce() {
		//	System.out.println("Pridame objekt do kolekce - bod 5");
		if (this.konecObjektu == true)	KolekceKreslicichObjektu.seznamTvaru.add(this);	}
}
/* 				Vytvareni objektu 
 * 0] v tride PanelKreslicíchTvaru vynulujeme referenci na predchozi objekt (znicime objekt)
 * 1] v tride PanelKreslicíchTvaru se zjisti nazev typu objektu
 * 2] v tride VytvareniObjektu vytvorime neuplny objekt ktery je docasny a pres ulozemim mu doplnime atributy
 * 3] v tride kliknuti mysi ziskame prvni dve souradnice X a Y
 * 4] v tride PohybMysi v udalosti mouseDragger kreslime rozpracovany objekt - 
 * 5] ulozime objekt do kolekce pri uvolneni tlacitka mysi + znicime objekt 
 * 6] znicime objekt v tride PanelKreslicíchTvaru - v udalosti na button
 */
class VytvareniObjektu {															//	singleton neni nutne this
	private static final VytvareniObjektu INSTANCE = new VytvareniObjektu();		//	staticke pole ktere vytvori objekt tridy
	public static VytvareniObjektu getInstance() {
		return INSTANCE;	}
	private short cisloIdent=0;						//	cislo oznacujici typ objektu Cara,Ctverec,Kruh, atd...
	public short getCislo() 				{	return cisloIdent;		}
	public void setCislo(short cislo) 		{	this.cisloIdent = cislo;	}
	private int pocetLinie=0,pocetCtverec=0,pocetElipsa=0,pocetKruznice=0,pocetPolyLine=0;
	private RodicTvar objekt=null;
	protected RodicTvar getObjekt()			{	return objekt;		}
	private Color barvaObjektu;
	protected void setBarvaObjektu(Color barva) {	this.barvaObjektu=barva;	}
	protected void VytvorObjekt() {
		if(cisloIdent ==0)	{	ZnicObjekt();	}		//	je-li cislo rovne 0 -----> prerusime vytvareni
		if(cisloIdent ==1) 	{							//	je-li cislo rovne 1 -----> jedna se o Linii
			pocetLinie++;
			objekt = new KresliCara(getCislo(),pocetLinie,barvaObjektu);	}
		if(cisloIdent ==2) 	{							//	je-li cislo rovne 2 -----> jedna se o Ctverec
			pocetCtverec++;
			objekt = new KresliCtverec(getCislo(),pocetCtverec,barvaObjektu);	}
		if(cisloIdent ==3) 	{							//	je-li cislo rovne 3 -----> jedna se o Elipsu
			pocetElipsa++;
			objekt = new KresliElipsa(getCislo(),pocetElipsa,barvaObjektu);	}
		if(cisloIdent ==4) 	{							//	je-li cislo rovne 4 -----> jedna se o Kruznice
			pocetKruznice++;
			objekt = new KresliKruznice(getCislo(),pocetKruznice,barvaObjektu);	}
		if(cisloIdent ==5) 	{							//	je-li cislo rovne 4 -----> jedna se o PolyLine
			pocetPolyLine++;
			objekt = new KresliPolyLine(getCislo(),pocetPolyLine,barvaObjektu);	}	}
	protected void ZnicObjekt() {
		//System.out.println("Znicime objekt - bod 6");
		objekt=null;									//	vymazeme referenci
		cisloIdent=0;	}								//	vynulujeme i identifikator
}
class PanelKreslicíchTvaru extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1247887267587008485L;
	private JButton buttonCara,buttonObdelnik,buttonElipsa,buttonKruznice,buttonViceCara;
	void NastaveniKonstruktoru() {
		this.setLayout(new GridLayout(1,4,0,40));	}
	void RozlozeniPanel() {
		this.buttonCara = new JButton("Linie(Èára)");this.add(this.buttonCara);
		this.buttonCara.addActionListener(this); 								// buttonu priradim udalost
		this.buttonObdelnik = new JButton("Obdelnik");this.add(this.buttonObdelnik);
		this.buttonObdelnik.addActionListener(this); 							// buttonu priradim udalost
		this.buttonElipsa = new JButton("Elipsa");this.add(this.buttonElipsa);
		this.buttonElipsa.addActionListener(this); 								// buttonu priradim udalost
		this.buttonKruznice = new JButton("Kružnice");this.add(this.buttonKruznice);
		this.buttonKruznice.addActionListener(this); 							// buttonu priradim udalost
		this.buttonViceCara = new JButton("PolyLinie");this.add(this.buttonViceCara);
		this.buttonViceCara.addActionListener(this); } 							// buttonu priradim udalost
	PanelKreslicíchTvaru(){ 												// konstruktor
		NastaveniKonstruktoru();
		RozlozeniPanel();	}
	public void actionPerformed(ActionEvent e) {
		VytvareniObjektu.getInstance().ZnicObjekt(); 						//	znicime predchozi objekt
		VytvareniObjektu.getInstance().setCislo((short)NastaveniTvaru(e));	//	zjistime typ grafickeho objektu 								
		VytvareniObjektu.getInstance().VytvorObjekt();	}					//	vytvorime neuplny objekt
	private short NastaveniTvaru(ActionEvent e){
		String nazevTlacitka = e.getActionCommand();
		//System.out.println("Zjistime typ objektu - bod 1"+nazevTlacitka);  	// kontrolni testovaci vypis
		if(nazevTlacitka.equals("Linie(Èára)")) 	return 1	;
		if(nazevTlacitka.equals("Obdelnik"))		return 2	;
		if(nazevTlacitka.equals("Elipsa"))			return 3	;
		if(nazevTlacitka.equals("Kružnice"))		return 4	;
		if(nazevTlacitka.equals("PolyLinie"))		return 5	;
		else return 0;	}
}
final class SourAktuXY {			//	pouze staticka trida - zajistuje souradnice - navrhovy vzor utility servant 
	private SourAktuXY() {}									//	privatni konstruktor - nelze vytvorit objektu
	private static int X,Y;									//	souradnice X,Y .... z polohy	
	protected static int getX() {	return X;	}			//	getter X
	protected static void setX( int x) { X=x;	}			//	setter X
	protected static int getY() {	return Y;	}			//	getter Y
	protected static void setY(int y) {	Y = y;	}			//	setter Y
}
class VypisSouradnicPohybu extends JPanel {
	private static final long serialVersionUID = 6394627171360116277L;
	private static JLabel souradniceX,souradniceY;
	static void NastaveniLabel(MouseEvent e){
		//System.out.println("X : "+e.getX()+"\tY : "+e.getY()); 		// z duvodu testu- vypis do konzole
		souradniceX.setText("[ "+String.valueOf(e.getX())+" ]");		// prevod souradnic na string
		souradniceY.setText("[ "+String.valueOf(e.getY())+" ]"); }		// prevod souradnic na string
	private void RozlozeniPanel(){
		JLabel infoX = new JLabel("Souøadnice X:");this.add(infoX);
		souradniceX = new JLabel("[ 0.00 ]");this.add(souradniceX);
		JLabel infoY = new JLabel("Souøadnice Y:");this.add(infoY);
		souradniceY = new JLabel("[ 0.00 ]");this.add(souradniceY);	}
	private void NastaveniKonstruktoru(){
		this.setLayout(new GridLayout(1,4,0,40));	}
	VypisSouradnicPohybu(){												 // konstruktor
		NastaveniKonstruktoru();
		RozlozeniPanel();	}
}
class PohybMysi implements MouseMotionListener { 			// udalost od pohybu
	private VykreslujiciScena scena;
	PohybMysi(VykreslujiciScena platno){
		this.scena = platno;	}
	public void mouseDragged(MouseEvent arg0) { // udalost od pohybu se stisknutym tlacitkem
		ZmenaSouradnic(arg0);								//	zmena textu souradnice v dolnim Labelu
		UlozeniSouradnic(arg0);								//	ulozi souradnice
		this.scena.repaint();	}
	public void mouseMoved(MouseEvent arg0) { 	// udalost od pohybu bez stisknuteho tlacitka
		ZmenaSouradnic(arg0); 								//	zmena textu souradnice v dolnim Labelu
		UlozeniSouradnic(arg0);
		if(VytvareniObjektu.getInstance().getObjekt() !=null )		// pokud existuje objekt proved - kresli docasny objekt
			VytvareniObjektu.getInstance().getObjekt().KresliTvarPracovni(((Graphics2D)this.scena.getGraphics()),arg0);	//VytvareniObjektu.getInstance().getObjekt().KresliTvarPracovni(this.scena.getGraphics(),arg0);
		this.scena.repaint();	}
	private void ZmenaSouradnic(MouseEvent e) {				// metoda ktera meni label dole
		VypisSouradnicPohybu.NastaveniLabel(e);	}
	private void UlozeniSouradnic(MouseEvent e){ 			// metoda ukladajici aktualni souradnice do staticke tridy souradnic
		SourAktuXY.setX(e.getX());
		SourAktuXY.setY(e.getY());	}
}
class StisknutiTlacMouse extends MouseAdapter {				// udalost od stisknuti mysi
	public void mouseEntered(MouseEvent arg0) {				//	prichod na pripojenou komponenty 
	}
	public void mouseExited(MouseEvent arg0) {				//	odchod z pripojene komponenty
	}
	public void mousePressed(MouseEvent arg0) {				//	stisknuti mysi - 1 faze
		//	System.out.println("Stisknuti mysi : 1 faze");
		if(VytvareniObjektu.getInstance().getObjekt() !=null) {	//	pokud existuje reference na objekt
			VytvareniObjektu.getInstance().getObjekt().setXaY(SourAktuXY.getX(),SourAktuXY.getY(),arg0);	}
	}
	public void mouseReleased(MouseEvent arg0) {			//	uvolneni mysi - 2 faze
		if(VytvareniObjektu.getInstance().getObjekt() !=null) {		//	pokud existuje reference na objekt
			if(VytvareniObjektu.getInstance().getObjekt().zjistiKonecObjektu()) {		//	jestli je objekt ukoncen pak
				//System.out.println("Uvolneni tlacitka - vlozeni do kolekce :"+VytvareniObjektu.getInstance().getObjekt());
				VytvareniObjektu.getInstance().getObjekt().PridejDoKolekce();	//	pridame do kolekce
				VytvareniObjektu.getInstance().ZnicObjekt(); }	}				//	znicime objekt
	}
	public void mouseClicked(MouseEvent arg0) {				//	klik mysi - 3 faze
	}
}
class KrizCtverecek {
	protected void kresliKrizCtverecek(Graphics g) {
		final int rozmer=10;			// definujeme velikost krizoveho ctverecku	
		// horni cara
		g.drawLine(SourAktuXY.getX()-rozmer,SourAktuXY.getY()-rozmer,SourAktuXY.getX()+rozmer,SourAktuXY.getY()-rozmer);
		// leva cara
		g.drawLine(SourAktuXY.getX()-rozmer,SourAktuXY.getY()-rozmer,SourAktuXY.getX()-rozmer,SourAktuXY.getY()+rozmer);
		// prava cara
		g.drawLine(SourAktuXY.getX()+rozmer,SourAktuXY.getY()-rozmer,SourAktuXY.getX()+rozmer,SourAktuXY.getY()+rozmer);
		// spodni cara
		g.drawLine(SourAktuXY.getX()-rozmer,SourAktuXY.getY()+rozmer,SourAktuXY.getX()+rozmer,SourAktuXY.getY()+rozmer); }  
}
class Kriz {
	private int MaxVyska,MaxSirka;
	protected void setMaxVyska(int vyska) {	this.MaxVyska = vyska;	} 					// potrebujem
	protected void setMaxSirka(int sirka) {	this.MaxSirka = sirka;	} 					// potrebujem
	protected void kresliKriz(Graphics g) {
		g.drawLine(SourAktuXY.getX(),0,SourAktuXY.getX(),this.MaxVyska);  					// vertikala
		g.drawLine(0,SourAktuXY.getY(),this.MaxSirka,SourAktuXY.getY()); } 					// horizont
}
class VykreslujiciScena extends Canvas implements Printable {						//	navrhovy vzor singleton
	private static final long serialVersionUID = 6864669397534656251L;
	private static Kriz ukazujiciKriz;
	private static KrizCtverecek ctverecekKriz;
	private static final VykreslujiciScena INSTANCE = new VykreslujiciScena();		//	staticke pole ktere vytvori objekt tridy
	public static VykreslujiciScena getInstance() {
		return INSTANCE;	}
	private VykreslujiciScena(){								// konstruktor
		this.addMouseMotionListener(new PohybMysi(this));  		// prichytneme udalost pohybu
		this.addMouseListener(new StisknutiTlacMouse());		// prichytneme udalost od kliku
		ctverecekKriz = new KrizCtverecek();
		ukazujiciKriz = new Kriz();	}
	private void NastaveniVelikostiPanelu(){
		Dimension dimension = getSize(); 						//	nastaveni promennych z velikosti okna
		ukazujiciKriz.setMaxVyska(dimension.height); 			//	nutne pro kriz
		ukazujiciKriz.setMaxSirka(dimension.width);  }			//	nutne pro kriz
	private void VykreslovaniSeznamuObjektu(Graphics2D g) {		//	vykreslime celou kolekci
		for(Iterator<RodicTvar> iter = KolekceKreslicichObjektu.seznamTvaru.iterator();iter.hasNext();){
			RodicTvar temp = iter.next();						//	docasny objekt rodice do ktereho vlozime potomky
			temp.KresliTvarCely(g);	}	}						//	vykreslime aktualni objekt
	//	kreslici metoda - povinna pokud dedime od Canvas
	public void paint(Graphics g2d) {
		Graphics2D g = (Graphics2D) g2d;
		NastaveniVelikostiPanelu(); 							// nutne pro ziskani maxHodnot z velikosti okna kvuli Krizi
		ukazujiciKriz.kresliKriz(g);
		ctverecekKriz.kresliKrizCtverecek(g);
		VykreslovaniSeznamuObjektu(g);	}
	//	tisknouci metoda - povinna pokud implementujeme Printable 
	public int print(Graphics arg0, PageFormat pageFormat, int pageIndex) throws PrinterException {
		if (pageIndex > 0)	return NO_SUCH_PAGE;
		Graphics2D gObjekt = (Graphics2D) arg0;
		VykreslovaniSeznamuObjektu(gObjekt);	
		return PAGE_EXISTS;	}
}
class MenuHlavniOkno extends JMenuBar{
	private static final long serialVersionUID = -6636702122049440440L;
	private void NastaveniMenu1(JMenu menu1,KresleniUlozeniPoly hlavniFrame){
		JMenuItem menu1volba1 = new JMenuItem("Novy ...");menu1.add(menu1volba1);
		menu1volba1.addActionListener(new UdalostMenuNovy());
		menu1volba1.setAccelerator(KeyStroke.getKeyStroke('N', CTRL_DOWN_MASK)); 
		JMenuItem menu1volba2 = new JMenuItem("Ulozit ...");menu1.add(menu1volba2);
		menu1volba2.addActionListener(new UdalostMenuUloz(hlavniFrame));
		menu1volba2.setAccelerator(KeyStroke.getKeyStroke('S', CTRL_DOWN_MASK));
		JMenuItem menu1volba3 = new JMenuItem("Nacist ...");menu1.add(menu1volba3);
		menu1volba3.setAccelerator(KeyStroke.getKeyStroke('L', CTRL_DOWN_MASK));
		menu1volba3.addActionListener(new UdalostMenuNacti(hlavniFrame));
		menu1.addSeparator();
		JMenuItem menu1volba4 = new JMenuItem("Tisk");menu1.add(menu1volba4);
		menu1volba4.setAccelerator(KeyStroke.getKeyStroke('P', CTRL_DOWN_MASK));
		menu1volba4.addActionListener(new UdalostMenuTisk());
		menu1.addSeparator();
		JMenuItem menu1volba5 = new JMenuItem("Konec");menu1.add(menu1volba5);
		menu1volba5.setAccelerator(KeyStroke.getKeyStroke('Q', CTRL_DOWN_MASK));
		menu1volba5.addActionListener(new UdalostMenuExit());	}
	private void NastaveniMenu2(JMenu menu2,KresleniUlozeniPoly hlavniFrame){
		JMenuItem menu2volba1 = new JMenuItem("Barva cary ...");menu2.add(menu2volba1);
		menu2volba1.addActionListener(new UdalostBarvaCary(hlavniFrame));
		menu2volba1.setAccelerator(KeyStroke.getKeyStroke('C', CTRL_DOWN_MASK));
		JMenuItem menu2volba2 = new JMenuItem("Barva sceny ...");menu2.add(menu2volba2);
		menu2volba2.addActionListener(new UdalostBarvaPlatna(hlavniFrame));
		menu2volba2.setAccelerator(KeyStroke.getKeyStroke('G', CTRL_DOWN_MASK));	}
	public void PanelNastrojeMenu(KresleniUlozeniPoly hlavniFrame){
		hlavniFrame.setJMenuBar(this); 				// str.100 Herout GUI
		JMenu menu1 = new JMenu("Soubor");
		JMenu menu2 = new JMenu("Nastaveni");
		this.add(menu1);
		this.add(menu2);
		menu1.setMnemonic('S');						//	klavesova zkratka ALT+S
		menu2.setMnemonic('N');						//	klavesova zkratka ALT+N
		NastaveniMenu1(menu1,hlavniFrame);
		NastaveniMenu2(menu2,hlavniFrame);	}
}
public class KresleniUlozeniPoly extends JFrame{
	private static final long serialVersionUID = 1L;
	private void ZmenaGUI() {
		String LOOKANDFEEL = "javax.swing.plaf.nimbus.NimbusLookAndFeel"; 			// zvolen NIMBUS
		try{
			UIManager.setLookAndFeel(LOOKANDFEEL);
			SwingUtilities.updateComponentTreeUI(this);
			this.pack();	}
		catch(Exception e){	
			e.printStackTrace();	}	}
	private void NastaveniKonstruktoru(){
		this.setSize(800,600);this.setLocation(300,150);
		JFrame.setDefaultLookAndFeelDecorated(true); 					// pro hezci vzhled
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); }			// zajisti ukonceni aplikace
	private void NastaveniMenu() {
		MenuHlavniOkno menu = new MenuHlavniOkno();
		menu.PanelNastrojeMenu(this);	}
	private void HorniPanel() {
		PanelKreslicíchTvaru horniPanel = new PanelKreslicíchTvaru();
		horniPanel.setBackground(Color.gray);
		this.add(horniPanel,BorderLayout.NORTH);	}
	private void StredniPanel(){
		VykreslujiciScena.getInstance().setBackground(Color.white);
		this.add(VykreslujiciScena.getInstance(),BorderLayout.CENTER);	}
	private void DolniPanel(){
		VypisSouradnicPohybu dolniPanel = new VypisSouradnicPohybu();
		dolniPanel.setBackground(Color.gray);
		this.add(dolniPanel,BorderLayout.SOUTH);	}
	KresleniUlozeniPoly(){ 										// konstruktor
		super.setTitle("Kreslici okno Polymorfismus");
		ZmenaGUI();  													// tato metoda musi byt vzdy pred nastavenim JFRAME
		NastaveniKonstruktoru();
		NastaveniMenu();
		HorniPanel();
		StredniPanel();
		DolniPanel();	}
	public static void main(String [] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run(){
				new KresleniUlozeniPoly().setVisible(true);	}	});	}
	
	/*
	 * public static void main(String [] args) {
		new KresleniUlozeniPoly().setVisible(true);	}
	*/
}
