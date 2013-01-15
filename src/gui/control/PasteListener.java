package gui.control;

import gui.StatusView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import spreadsheet.Application;
import spreadsheet.Expression;
import spreadsheet.Position;
import spreadsheet.command.Set;

public class PasteListener implements ActionListener {
	
	public final static PasteListener instance = new PasteListener();
	public Expression copy;
	private Position position;
	
	private void getCopy() {
		copy = CopyListener.instance.getCopy();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.getCopy();
		if (copy == null) {
			StatusView.instance.errorView.setText("Nothing to paste");
			return;
		}	
		new Set(Application.instance.getCurrentPosition(), copy).execute();
	}

}
