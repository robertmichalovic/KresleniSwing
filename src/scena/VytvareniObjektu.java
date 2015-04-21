/* 				Vytvareni objektu 
 * 0] v tride PanelKreslicíchTvaru vynulujeme referenci na predchozi objekt (znicime objekt)
 * 1] v tride PanelKreslicíchTvaru se zjisti nazev typu objektu
 * 2] v tride VytvareniObjektu vytvorime neuplny objekt ktery je docasny a pres ulozemim mu doplnime atributy
 * 3] v tride kliknuti mysi ziskame prvni dve souradnice X a Y
 * 4] v tride PohybMysi v udalosti mouseDragger kreslime rozpracovany objekt - 
 * 5] ulozime objekt do kolekce pri uvolneni tlacitka mysi + znicime objekt 
 * 6] znicime objekt v tride PanelKreslicíchTvaru - v udalosti na button
 */
package scena;
import java.awt.Color;
import tvar.objekt.*;
public class VytvareniObjektu {															//	singleton neni nutne this
	private static final VytvareniObjektu INSTANCE = new VytvareniObjektu();		//	staticke pole ktere vytvori objekt tridy
	public static VytvareniObjektu getInstance() {
		return INSTANCE;	}
	private short cisloIdent=0;						//	cislo oznacujici typ objektu Cara,Ctverec,Kruh, atd...
	public short getCislo() 						{	return cisloIdent;		}
	public void setCislo(short cislo) 				{	this.cisloIdent = cislo;	}
	private int pocetLinie=0,pocetCtverec=0,pocetElipsa=0,pocetKruznice=0,pocetPolyLine=0;
	private RodicTvar objekt=null;
	public RodicTvar getObjekt()					{	return objekt;		}
	private Color barvaObjektu;
	public void setBarvaObjektu(Color barva) 		{	this.barvaObjektu=barva;	}
	public void vytvorObjekt() {
		if(cisloIdent ==0)	{	znicObjekt();	}		//	je-li cislo rovne 0 -----> prerusime vytvareni
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
	public void znicObjekt() {
		//System.out.println("Znicime objekt - bod 6");
		objekt=null;									//	vymazeme referenci
		cisloIdent=0;	}								//	vynulujeme i identifikator
}
