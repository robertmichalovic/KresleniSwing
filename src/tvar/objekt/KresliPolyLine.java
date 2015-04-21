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
package tvar.objekt;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.*;
import java.io.Serializable;
import java.util.*;
import scena.KolekceKreslicichObjektu;
import scena.objekty.SourAktuXY;
public class KresliPolyLine extends RodicTvar implements Serializable{
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
	public boolean zjistiKonecObjektu() 	{	return konecObjektu;	}		//	getter
	//****** pomocne pro kresleni polylinie - docasne
	private boolean kresliLinie=false;				//  kresli linii
	private boolean kresliViceLinie=false;			//	nutne pro vicelinii
	private int X,Y;								//	zajisti presun poslednich ulozenych souradnic do docasneho kresleni
	private Collection<Integer> kolekceX = new ArrayList<Integer>();
	private Collection<Integer> kolekceY = new ArrayList<Integer>();
	private boolean souradnicePrvni = true;				//	nutne kvuli poznani prvnich nebo druhych souradnic
	public String toString() {return "Souradnice x1:"+x1+"\ty1:"+y1+"\tx2:"+x2+"\ty2:"+y2;	}
	public KresliPolyLine(short cisloIdentifikator,int Plus, Color barva){		//	konstruktor	
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
		if(kresliViceLinie == true) vytvoreniPoleZKolekce(arg0,x,y);	
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
	private void vytvoreniPoleZKolekce(MouseEvent e,int x,int y) {
		Integer [] tempPole;
		//System.out.println(x1+" test 1\t"+y1);
		//System.out.println(x2+" test 2\t"+y2);
		if (this.kresliLinie == true) {							//	pokud kreslime linii pak pridame prvky souradnice do kolekce
			this.kolekceX.add(x);
			this.kolekceY.add(y);	}
		this.velikostPole = kolekceX.size();  							// zjistime velikost kolekce
		tempPole = kolekceX.toArray(new Integer[velikostPole]); 		// kolekci prevedem na pole objektu Integer
		this.pole1X = prevodIntegerInt(tempPole,velikostPole); 			// prevedem pole objektu Integer na pole int
		tempPole = kolekceY.toArray(new Integer[velikostPole]); 		// kolekci prevedem na pole objektu Integer
		this.pole1Y = prevodIntegerInt(tempPole,velikostPole); }		// prevedem pole objektu Integer na pole int
	private int [] prevodIntegerInt(Integer [] poleTemp,int velikost) {	 // metoda prevadejici pole objektu Integeru na pole int 
		int [] pole = new int [velikost];
		for(int i=0;i<pole.length;i++) {
			pole[i]= poleTemp[i].intValue(); }
		//System.out.println("Pole :"+Arrays.toString(pole));
		return pole;	}
	public void kresliTvarPracovni(Graphics2D g,MouseEvent e) {	
		//System.out.println("Zde jsou souradnice :"+x1+"\t"+y1+"\t"+SourAktuXY.getX()+"\t"+SourAktuXY.getY());
		//***** kresli jenom pokud x1 a y1 se nerovnaji nule a neni konec objektu a ma se kreslit linie
		if(this.kresliLinie == true & this.X != 0 & this.Y != 0 & this.konecObjektu == false ) {
			g.setColor(this.barvaPolyLine);
			g.drawLine(this.X,this.Y, SourAktuXY.getX(), SourAktuXY.getY());	}
		//	docasny objekt polyline
		if(this.kresliViceLinie == true) {
			g.setColor(this.barvaPolyLine);
			g.drawPolyline(this.pole1X, this.pole1Y, this.velikostPole);		}	}
	public void kresliTvarCely(Graphics2D g) {
		g.setColor(this.barvaPolyLine);
		g.drawPolyline(this.pole1X, this.pole1Y, this.velikostPole);	}
	public void pridejDoKolekce() {
		//	System.out.println("Pridame objekt do kolekce - bod 5");
		if (this.konecObjektu == true)	KolekceKreslicichObjektu.seznamTvaru.add(this);	}
}
