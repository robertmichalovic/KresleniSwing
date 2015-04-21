package scena;
import java.awt.*;
import java.awt.print.*;
import java.util.*;
import scena.objekty.*;
import tvar.objekt.*;
import udalosti.*;
public class VykreslujiciScena extends Canvas implements Printable {						//	navrhovy vzor singleton
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
	private void nastaveniVelikostiPanelu(){
		Dimension dimension = getSize(); 						//	nastaveni promennych z velikosti okna
		ukazujiciKriz.setMaxVyska(dimension.height); 			//	nutne pro kriz
		ukazujiciKriz.setMaxSirka(dimension.width);  }			//	nutne pro kriz
	private void vykreslovaniSeznamuObjektu(Graphics2D g) {		//	vykreslime celou kolekci
		for(Iterator<RodicTvar> iter = KolekceKreslicichObjektu.seznamTvaru.iterator();iter.hasNext();){
			RodicTvar temp = iter.next();						//	docasny objekt rodice do ktereho vlozime potomky
			temp.kresliTvarCely(g);	}	}						//	vykreslime aktualni objekt
	//	kreslici metoda - povinna pokud dedime od Canvas
	public void paint(Graphics g2d) {
		Graphics2D g = (Graphics2D) g2d;
		nastaveniVelikostiPanelu(); 							// nutne pro ziskani maxHodnot z velikosti okna kvuli Krizi
		ukazujiciKriz.kresliKriz(g);
		ctverecekKriz.kresliKrizCtverecek(g);
		vykreslovaniSeznamuObjektu(g);	}
	//	tisknouci metoda - povinna pokud implementujeme Printable 
	public int print(Graphics arg0, PageFormat pageFormat, int pageIndex) throws PrinterException {
		if (pageIndex > 0)	return NO_SUCH_PAGE;
		Graphics2D gObjekt = (Graphics2D) arg0;
		vykreslovaniSeznamuObjektu(gObjekt);	
		return PAGE_EXISTS;	}
}