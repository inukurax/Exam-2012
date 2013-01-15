package gui.control;

import gui.StatusView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import spreadsheet.Application;
import spreadsheet.Expression;
import spreadsheet.Position;
import spreadsheet.exception.InvalidReference;

final public class CopyListener implements ActionListener {
	
	public final static CopyListener instance = new CopyListener();
	
	private Expression copy;
	private Position position;
	
	private CopyListener() {
		this.copy = null;
		this.position = Application.instance.getCurrentPosition();
	}
	
	public Expression getCopy() {
		return copy;
	}
	/**
	 * Returns the Position copying from.
	 * @return Guaranteed non null Position
	 */
	public Position getPosition() {
		return Application.instance.getCurrentPosition();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		copy = Application.instance.get();	
		try {
			copy = copy.copy(1, 0); // should be right offsets
		} catch (InvalidReference e) {
			StatusView.instance.errorView.setText(e.getMessage());
		}
	}

}
