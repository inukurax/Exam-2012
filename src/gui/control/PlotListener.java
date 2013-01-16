package gui.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import spreadsheet.Application;
import spreadsheet.Range;
import spreadsheet.Reference;

public class PlotListener implements ActionListener {

	public final static PlotListener instance = new PlotListener();
	
	public PlotListener() {
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Range range = Application.instance.getCurrentRange();
		final int column = range.getColumnCount();
		final int row = range.getRowCount();
		
		Reference ref =  new Reference(Application.instance.getWorksheet(), range);
		
		
	}

}
