/*	Spousteci trida
 * 
 * 
 */
package frame.spusteni;
import gui.menu.MenuHlavniOkno;
import gui.panel.*;
import java.awt.*;
import javax.swing.*;
import scena.VykreslujiciScena;
public class ProgramOkno extends JFrame{
	private static final long serialVersionUID = 1L;
	private void zmenaGUI() {
		String LOOKANDFEEL = "javax.swing.plaf.nimbus.NimbusLookAndFeel"; 			// zvolen NIMBUS
		try{
			UIManager.setLookAndFeel(LOOKANDFEEL);
			SwingUtilities.updateComponentTreeUI(this);
			this.pack();	}
		catch(Exception e){	
			e.printStackTrace();	}	}
	private void nastaveniKonstruktoru(){
		this.setSize(800,600);this.setLocation(300,150);
		JFrame.setDefaultLookAndFeelDecorated(true); 					// pro hezci vzhled
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); }			// zajisti ukonceni aplikace
	private void nastaveniMenu() {
		MenuHlavniOkno menu = new MenuHlavniOkno();
		menu.panelNastrojeMenu(this);	}
	private void horniPanel() {
		PanelKreslicíchTvaru horniPanel = new PanelKreslicíchTvaru();
		horniPanel.setBackground(Color.gray);
		this.add(horniPanel,BorderLayout.NORTH);	}
	private void stredniPanel(){
		VykreslujiciScena.getInstance().setBackground(Color.white);
		this.add(VykreslujiciScena.getInstance(),BorderLayout.CENTER);	}
	private void dolniPanel(){
		VypisSouradnicPohybu dolniPanel = new VypisSouradnicPohybu();
		dolniPanel.setBackground(Color.gray);
		this.add(dolniPanel,BorderLayout.SOUTH);	}
	ProgramOkno(){ 														// konstruktor
		super.setTitle("Kreslici okno Polymorfismus");
		zmenaGUI();  													// tato metoda musi byt vzdy pred nastavenim JFRAME
		nastaveniKonstruktoru();
		nastaveniMenu();
		horniPanel();
		stredniPanel();
		dolniPanel();	}
	public static void main(String [] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run(){
				new ProgramOkno().setVisible(true);	}	});	}
	
	/*
	 * public static void main(String [] args) {
		new KresleniUlozeniPoly().setVisible(true);	}
	*/
}
