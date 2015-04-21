package udalosti;
import java.awt.event.*;
import javax.swing.JOptionPane;
public class UdalostMenuExit implements ActionListener {  // Button Exit
	public void actionPerformed(ActionEvent e) {
		Object [] volba = {"Ano","Ne"};
		int n = JOptionPane.showOptionDialog(null, "Chcete opravdu skoncit", "Ukonceni programu", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null,volba,volba[0]);
		if (n == JOptionPane.YES_OPTION) System.exit(1);	}
}
