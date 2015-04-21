package udalosti;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import scena.VytvareniObjektu;
public class UdalostBarvaCary implements ActionListener {
	private JFrame frame;
	public UdalostBarvaCary(JFrame frame) {	this.frame=frame;	}
	public void actionPerformed(ActionEvent arg0) {
		Color barva=Color.black;									//	vychozi barva bude cerna
		barva = JColorChooser.showDialog(frame, "Vyber barvy - cary",barva);
		if(barva == null) barva = Color.black;						//	kontrola kvuli tlacitku Cancel = vytvari nulovou referenci
		VytvareniObjektu.getInstance().setBarvaObjektu(barva);
		this.frame.setVisible(true);	}
}
