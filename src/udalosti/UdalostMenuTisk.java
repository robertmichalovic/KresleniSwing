package udalosti;
import java.awt.event.*;
import java.awt.print.*;
import javax.print.attribute.*;
import scena.VykreslujiciScena;
public class UdalostMenuTisk implements ActionListener {
	public void actionPerformed(ActionEvent arg0) {
		PrinterJob tiskUloha = PrinterJob.getPrinterJob();
		VykreslujiciScena.getInstance().getGraphics();
		tiskUloha.setPrintable(VykreslujiciScena.getInstance());
		PrintRequestAttributeSet moznostiTisku = new HashPrintRequestAttributeSet();
		try {
			if (tiskUloha.printDialog(moznostiTisku)) tiskUloha.print(moznostiTisku);	} 
		catch (PrinterException ex) {
			System.out.println("Nelze tisknout");
			ex.printStackTrace();	}	}
}
