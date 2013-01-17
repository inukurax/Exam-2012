package gui.control;

import gui.Plot;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import spreadsheet.Application;
import spreadsheet.Range;
import spreadsheet.exception.InvalidPlotSize;

public class PlotListener implements ActionListener {

	public final static PlotListener instance = new PlotListener();
	
	public PlotListener() {

	}
	
	private Plot getInfo() throws InvalidPlotSize {
		Range range = Application.instance.getCurrentRange();
		final int column = range.getColumnCount();
		final int row = range.getRowCount();
		if  (row > 2 || row < 1)
			throw new InvalidPlotSize("Can only plot 1*1, 1*x or 2*x"); 
		
		return new Plot(((column- 1) * 100) + 30, row * 70);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
	try {
			getInfo().save("test.png");
		} catch (Exception e) {
			Application.instance.reportError(e);
		}	
	}
}
