package gui.control;

import gui.Plot;
import gui.control.plotwizard.SetTypeWizard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import spreadsheet.Application;
import spreadsheet.Range;
import spreadsheet.exception.InvalidPlotSize;

public class PlotListener implements ActionListener {

	public final static PlotListener instance = new PlotListener();
	
	public PlotListener() {
		//singleton
	}
	/**
	 * Returns a Plot of the selected cells.
	 * @return a Plot with height of 350 and width of (columns * 50)
	 *  + 40 pixel for numbers on left side
	 *  Guaranteed to not be larger than 800 * 350.
	 * @throws InvalidPlotSize if more than two rows is selected
	 */
	private Plot getPlot() throws InvalidPlotSize {
		Range range = Application.instance.getCurrentRange();
		final int column = range.getColumnCount();
		final int row = range.getRowCount();
		if  (row > 2 || row < 1)
			throw new InvalidPlotSize("Can only plot 1*1, 1*x or 2*x"); 
		int width = (column * 50) + 40;
		width = width > 800 ? 800 : width;
		return new Plot(width, 350);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			new SetTypeWizard(this.getPlot());
		} catch (InvalidPlotSize e) {
			Application.instance.reportError(e);
		}
	}
}
