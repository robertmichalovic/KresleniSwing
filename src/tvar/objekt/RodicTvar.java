package tvar.objekt;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
public abstract class RodicTvar {	//	Rodic vsech potomku - nutny kvuli polymorfismu
	protected abstract short getIdentifikatorTvaru();
	public abstract void kresliTvarPracovni(Graphics2D g,MouseEvent e);	//	- metoda vykresluje objekt behem jeho vytvareni
	public abstract void kresliTvarCely(Graphics2D g);		//	- metoda vykresluje cely objekt 
	public abstract void pridejDoKolekce();					//	metoda prida objekt do kolekce
	public abstract void setXaY(int x,int y,MouseEvent arg0);//	metoda do objektu vlozi souradnice X a Y
	public abstract boolean zjistiKonecObjektu(); 		//	zjistime jestli je objekt ukoncen
}
