package scena.objekty;
import java.awt.Graphics;
public class KrizCtverecek {
	public void kresliKrizCtverecek(Graphics g) {
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
