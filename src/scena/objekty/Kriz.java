package scena.objekty;
import java.awt.Graphics;
public class Kriz {
	private int MaxVyska,MaxSirka;
	public void setMaxVyska(int vyska) {	this.MaxVyska = vyska;	} 					// potrebujem
	public void setMaxSirka(int sirka) {	this.MaxSirka = sirka;	} 					// potrebujem
	public void kresliKriz(Graphics g) {
		g.drawLine(SourAktuXY.getX(),0,SourAktuXY.getX(),this.MaxVyska);  					// vertikala
		g.drawLine(0,SourAktuXY.getY(),this.MaxSirka,SourAktuXY.getY()); } 					// horizont
}
