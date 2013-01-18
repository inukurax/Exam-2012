package gui.control;

import gui.BarChart;
import gui.Plot;
import gui.Plot.PlotType;
import gui.PlotWizard;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import spreadsheet.Application;
import spreadsheet.Range;
import spreadsheet.Reference;
import spreadsheet.exception.InvalidPlotSize;

public class PlotListener implements ActionListener {

	public final static PlotListener instance = new PlotListener();
	
	public PlotListener() {

	}
	
	private Plot getPlot() throws InvalidPlotSize {
		Range range = Application.instance.getCurrentRange();
		final int column = range.getColumnCount();
		final int row = range.getRowCount();
		Reference ref = new Reference(Application.instance.getWorksheet(), range);

		BarChart barChart = new BarChart(ref , row, column);
		if  (row > 2 || row < 1)
			throw new InvalidPlotSize("Can only plot 1*1, 1*x or 2*x"); 		 
		return new Plot((column * 50) + 40, 300);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			new PlotWizard(this.getPlot());
		} catch (InvalidPlotSize e) {
			Application.instance.reportError(e);
		}
	}
}
