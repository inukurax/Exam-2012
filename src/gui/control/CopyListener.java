package gui.control;

import gui.StatusView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import spreadsheet.Application;
import spreadsheet.Expression;
import spreadsheet.exception.InvalidReference;

public class CopyListener implements ActionListener {
	
	public final static CopyListener instance = new CopyListener();
	public Expression copy;
	
	private CopyListener() {
		copy = null;
	}
	
	public Expression getCopy() {
		return copy;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		copy = Application.instance.get();	
		try {
				copy.copy(1, 0);
		} catch (InvalidReference e) {
			StatusView.instance.errorView.setText(e.getMessage());
		}
	}

}
