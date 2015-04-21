package tvar.objekt;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.io.Serializable;

import scena.KolekceKreslicichObjektu;
import scena.objekty.SourAktuXY;
public class KresliKruznice extends RodicTvar  implements Serializable{
	private static final long serialVersionUID = -309625135026204718L;
	private short identifikatorTvaru=(short)0;									//	uklada identifikator objektu
	protected short getIdentifikatorTvaru() {	return identifikatorTvaru;	}	//	getter
	private int x1,y1,x2,y2;							//	souradnice Kruznice
	private Color barvaKruznice;						//	nastavime barvu objektu
	@SuppressWarnings("unused")
	private static int cisloPocet=0;							//	uklada poradove cislo objektu
	private boolean souradnicePrvni = true;				//	nutne kvuli poznani prvnich nebo druhych souradnic
	private boolean konecObjektu;						//	nutne kvuli poznani ukonceni objektu
	public boolean zjistiKonecObjektu() 	{	return konecObjektu;	}		//	getter
	public String toString() {return "Souradnice x1:"+x1+"\ty1:"+y1+"\tx2:"+x2+"\ty2:"+y2;	}
	public KresliKruznice(short cisloIdentifikator,int Plus, Color barva){		//	konstruktor	
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
	public void kresliTvarPracovni(Graphics2D g,MouseEvent e) {
		//System.out.println("Zde jsou souradnice :"+x1+"\t"+y1+"\t"+SourAktuXY.getX()+"\t"+SourAktuXY.getY());
		//***** kresli jenom pokud x1 a y1 a neni konec objektu se nerovnaji nule
		if(this.x1 != 0 & this.y1 != 0 & this.konecObjektu == false) {	
			g.setColor(barvaKruznice);
			g.drawArc(this.x1, this.y1, (SourAktuXY.getX()-this.x1), (SourAktuXY.getY()-this.y1),0,360);	}	}
	public void kresliTvarCely(Graphics2D g) {
		g.setColor(barvaKruznice);
		g.drawArc(this.x1, this.y1, this.x2, this.y2,0,360);	}
	public void pridejDoKolekce() {
		//System.out.println("Pridame objekt do kolekce - bod 5");
		KolekceKreslicichObjektu.seznamTvaru.add(this);	}
}
