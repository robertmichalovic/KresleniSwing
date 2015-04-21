package gui.panel;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import javax.swing.*;
public class VypisSouradnicPohybu extends JPanel {
	private static final long serialVersionUID = 6394627171360116277L;
	private static JLabel souradniceX,souradniceY;
	public static void nastaveniLabel(MouseEvent e){
		//System.out.println("X : "+e.getX()+"\tY : "+e.getY()); 		// z duvodu testu- vypis do konzole
		souradniceX.setText("[ "+String.valueOf(e.getX())+" ]");		// prevod souradnic na string
		souradniceY.setText("[ "+String.valueOf(e.getY())+" ]"); }		// prevod souradnic na string
	private void rozlozeniPanel(){
		JLabel infoX = new JLabel("Souøadnice X:");this.add(infoX);
		souradniceX = new JLabel("[ 0.00 ]");this.add(souradniceX);
		JLabel infoY = new JLabel("Souøadnice Y:");this.add(infoY);
		souradniceY = new JLabel("[ 0.00 ]");this.add(souradniceY);	}
	private void nastaveniKonstruktoru(){
		this.setLayout(new GridLayout(1,4,0,40));	}
	public VypisSouradnicPohybu(){												 // konstruktor
		nastaveniKonstruktoru();
		rozlozeniPanel();	}
}
