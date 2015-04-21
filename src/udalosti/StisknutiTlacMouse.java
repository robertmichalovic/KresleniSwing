package udalosti;
import java.awt.event.*;
import scena.VytvareniObjektu;
import scena.objekty.SourAktuXY;
public class StisknutiTlacMouse extends MouseAdapter {				// udalost od stisknuti mysi
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
				VytvareniObjektu.getInstance().getObjekt().pridejDoKolekce();	//	pridame do kolekce
				VytvareniObjektu.getInstance().znicObjekt(); }	}				//	znicime objekt
	}
	public void mouseClicked(MouseEvent arg0) {				//	klik mysi - 3 faze
	}
}
