package udalosti;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import scena.VykreslujiciScena;
public class UdalostBarvaPlatna implements ActionListener {
	private JFrame frame;
	public UdalostBarvaPlatna(JFrame frame) {	this.frame=frame;	}
	public void actionPerformed(ActionEvent arg0) {
		Color barva = Color.white;									//	nacteme pozadi do Barvy
		barva = JColorChooser.showDialog(frame, "Vyber barvy - pozadi",barva);
		if(barva == null) barva = Color.white;						//	kontrola kvuli tlacitku Cancel = vytvari nulovou referenci
		VykreslujiciScena.getInstance().setBackground(barva);
		this.frame.setVisible(true);	}
}
