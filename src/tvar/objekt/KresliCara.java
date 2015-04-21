package tvar.objekt;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.Serializable;

import scena.KolekceKreslicichObjektu;
import scena.objekty.SourAktuXY;
public class KresliCara extends RodicTvar implements Serializable {
	private static final long serialVersionUID = -5625209653648514632L;
	private short identifikatorTvaru=(short)0;									//	uklada identifikator objektu
	protected short getIdentifikatorTvaru() {	return identifikatorTvaru;	}	//	getter
	private int x1,y1,x2,y2;							//	souradnice cary
	private Color barvaCary;							//	nastavime barvu objektu
	@SuppressWarnings("unused")
	private static int cisloPocet=0;					//	uklada poradove cislo objektu
	private boolean souradnicePrvni = true;				//	nutne kvuli poznani prvnich nebo druhych souradnic
	private boolean konecObjektu;						//	nutne kvuli poznani ukonceni objektu
	public boolean zjistiKonecObjektu() 	{	return konecObjektu;	}		//	getter
	public String toString() {return "Souradnice x1:"+x1+"\ty1:"+y1+"\tx2:"+x2+"\ty2:"+y2;	}
	public KresliCara(short cisloIdentifikator,int Plus,Color barva){		//	konstruktor	
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
	public void kresliTvarPracovni(Graphics2D g,MouseEvent e) {
		//System.out.println("Zde jsou souradnice :"+x1+"\t"+y1+"\t"+SourAktuXY.getX()+"\t"+SourAktuXY.getY());
		//***** kresli jenom pokud x1 a y1 a neni konec objektu se nerovnaji nule
		if(this.x1 != 0 & this.y1 != 0 & this.konecObjektu == false) {	
			g.setColor(this.barvaCary); 
			g.drawLine(this.x1, this.y1, SourAktuXY.getX(), SourAktuXY.getY());	}	}
	public void kresliTvarCely(Graphics2D g) {
		g.setColor(this.barvaCary); 								// nastavime barvu cary
		g.drawLine(this.x1, this.y1, this.x2, this.y2);	}
	public void pridejDoKolekce() {
		//System.out.println("Pridame objekt do kolekce - bod 5");
		KolekceKreslicichObjektu.seznamTvaru.add(this);	}
}
