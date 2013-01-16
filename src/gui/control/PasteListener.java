package gui.control;

import gui.StatusView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import spreadsheet.Application;
import spreadsheet.Expression;
import spreadsheet.Position;
import spreadsheet.command.Set;
import spreadsheet.exception.InvalidReference;

public class PasteListener implements ActionListener {
	
	public final static PasteListener instance = new PasteListener();
	public Expression copy;
	private Position position;
	private Position newPosition;
	
	private void getCopy() {
		this.copy = CopyListener.instance.getCopy();
		this.position = CopyListener.instance.getPosition();
		this.newPosition = Application.instance.getCurrentPosition();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.getCopy();
		int columnOffset = position.getColumnOffset(newPosition);
		int rowOffset = position.getRowOffset(newPosition);
		try {
			copy = copy.copy(columnOffset, rowOffset);
		if (copy == null) {
			StatusView.instance.errorView.setText("Nothing to paste");
			return;
		}	
		new Set(newPosition, copy).execute();
		} catch (InvalidReference e) {
			 Application.instance.reportError(e);
			return;
		}
	}

}
