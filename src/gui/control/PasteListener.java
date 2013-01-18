package gui.control;

import gui.StatusView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import spreadsheet.Application;
import spreadsheet.Expression;
import spreadsheet.Position;
import spreadsheet.command.Set;
import spreadsheet.exception.InvalidReference;

/**
 * Paste last copied Expression to current Position
 * reports possible errors to errorEvent observer in Application.
 */
public class PasteListener implements ActionListener {
	// only need to be initialized once
	public final static PasteListener instance = new PasteListener();
	
	public Expression copy;
	private Position position;
	private Position newPosition;
	
	/**
	 * Sets the Copy from CopyListener, if non set copy to null
	 * also sets position copying from, for updating references.
	 */
	private void getCopy() {
		this.copy = CopyListener.instance.getCopy();
		this.position = CopyListener.instance.getPosition();
		this.newPosition = Application.instance.getCurrentPosition();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.getCopy();
		if (!Application.instance.getCurrentRange().isOnePosition()) {
			Application.instance.reportError("Can only Paste to a single cell");
			return;
		}	
		int columnOffset = position.getColumnOffset(newPosition);
		int rowOffset = position.getRowOffset(newPosition);
		try {
			copy = copy.copy(columnOffset, rowOffset);
			if (copy == null) {
				Application.instance.reportError("Nothing to paste");
				return;
			}
			new Set(newPosition, copy).execute();
		} catch (InvalidReference e) {
			 Application.instance.reportError(e);
			return;
		}
	}

}
