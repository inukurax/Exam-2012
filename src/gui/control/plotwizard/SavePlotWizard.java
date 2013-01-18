package gui.control.plotwizard;

import gui.MainFrame;
import gui.Plot;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import spreadsheet.Application;

public class SavePlotWizard extends JFileChooser {
	
	private static final long serialVersionUID = 1L;

	public SavePlotWizard(Plot plot) {
		try {
		FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG file", "png");
		setFileFilter(filter);
		String path = null;
		int returnVal = this.showSaveDialog(MainFrame.instance);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			path = getSelectedFile().getAbsolutePath();
			path = path.endsWith(".png") ?  path : path + ".png";
				plot.save(path);
		}
		} catch (Exception e) {
			Application.instance.reportError(e);
		}
	}

}
