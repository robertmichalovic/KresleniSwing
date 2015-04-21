package udalosti;
import gui.panel.VypisSouradnicPohybu;

import java.awt.Graphics2D;
import java.awt.event.*;

import scena.*;
import scena.objekty.SourAktuXY;
public class PohybMysi implements MouseMotionListener { 			// udalost od pohybu
	private VykreslujiciScena scena;
	public PohybMysi(VykreslujiciScena platno){
		this.scena = platno;	}
	public void mouseDragged(MouseEvent arg0) { // udalost od pohybu se stisknutym tlacitkem
		zmenaSouradnic(arg0);								//	zmena textu souradnice v dolnim Labelu
		ulozeniSouradnic(arg0);								//	ulozi souradnice
		this.scena.repaint();	}
	public void mouseMoved(MouseEvent arg0) { 	// udalost od pohybu bez stisknuteho tlacitka
		zmenaSouradnic(arg0); 								//	zmena textu souradnice v dolnim Labelu
		ulozeniSouradnic(arg0);
		if(VytvareniObjektu.getInstance().getObjekt() !=null )		// pokud existuje objekt proved - kresli docasny objekt
			VytvareniObjektu.getInstance().getObjekt().kresliTvarPracovni(((Graphics2D)this.scena.getGraphics()),arg0);	//VytvareniObjektu.getInstance().getObjekt().KresliTvarPracovni(this.scena.getGraphics(),arg0);
		this.scena.repaint();	}
	private void zmenaSouradnic(MouseEvent e) {				// metoda ktera meni label dole
		VypisSouradnicPohybu.nastaveniLabel(e);	}
	private void ulozeniSouradnic(MouseEvent e){ 			// metoda ukladajici aktualni souradnice do staticke tridy souradnic
		SourAktuXY.setX(e.getX());
		SourAktuXY.setY(e.getY());	}
}
