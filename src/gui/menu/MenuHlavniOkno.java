package gui.menu;
import static java.awt.event.InputEvent.CTRL_DOWN_MASK;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import udalosti.*;
import frame.spusteni.ProgramOkno;
public class MenuHlavniOkno extends JMenuBar{
	private static final long serialVersionUID = -6636702122049440440L;
	private void nastaveniMenu1(JMenu menu1,ProgramOkno hlavniFrame){
		JMenuItem menu1volba1 = new JMenuItem("Novy ...");menu1.add(menu1volba1);
		menu1volba1.addActionListener(new UdalostMenuNovy());
		menu1volba1.setAccelerator(KeyStroke.getKeyStroke('N', CTRL_DOWN_MASK)); 
		JMenuItem menu1volba2 = new JMenuItem("Ulozit ...");menu1.add(menu1volba2);
		menu1volba2.addActionListener(new UdalostMenuUloz(hlavniFrame));
		menu1volba2.setAccelerator(KeyStroke.getKeyStroke('S', CTRL_DOWN_MASK));
		JMenuItem menu1volba3 = new JMenuItem("Nacist ...");menu1.add(menu1volba3);
		menu1volba3.setAccelerator(KeyStroke.getKeyStroke('L', CTRL_DOWN_MASK));
		menu1volba3.addActionListener(new UdalostMenuNacti(hlavniFrame));
		menu1.addSeparator();
		JMenuItem menu1volba4 = new JMenuItem("Tisk");menu1.add(menu1volba4);
		menu1volba4.setAccelerator(KeyStroke.getKeyStroke('P', CTRL_DOWN_MASK));
		menu1volba4.addActionListener(new UdalostMenuTisk());
		menu1.addSeparator();
		JMenuItem menu1volba5 = new JMenuItem("Konec");menu1.add(menu1volba5);
		menu1volba5.setAccelerator(KeyStroke.getKeyStroke('Q', CTRL_DOWN_MASK));
		menu1volba5.addActionListener(new UdalostMenuExit());	}
	private void nastaveniMenu2(JMenu menu2,ProgramOkno hlavniFrame){
		JMenuItem menu2volba1 = new JMenuItem("Barva cary ...");menu2.add(menu2volba1);
		menu2volba1.addActionListener(new UdalostBarvaCary(hlavniFrame));
		menu2volba1.setAccelerator(KeyStroke.getKeyStroke('C', CTRL_DOWN_MASK));
		JMenuItem menu2volba2 = new JMenuItem("Barva sceny ...");menu2.add(menu2volba2);
		menu2volba2.addActionListener(new UdalostBarvaPlatna(hlavniFrame));
		menu2volba2.setAccelerator(KeyStroke.getKeyStroke('G', CTRL_DOWN_MASK));	}
	public void panelNastrojeMenu(ProgramOkno hlavniFrame){
		hlavniFrame.setJMenuBar(this); 				// str.100 Herout GUI
		JMenu menu1 = new JMenu("Soubor");
		JMenu menu2 = new JMenu("Nastaveni");
		this.add(menu1);
		this.add(menu2);
		menu1.setMnemonic('S');						//	klavesova zkratka ALT+S
		menu2.setMnemonic('N');						//	klavesova zkratka ALT+N
		nastaveniMenu1(menu1,hlavniFrame);
		nastaveniMenu2(menu2,hlavniFrame);	}
}
