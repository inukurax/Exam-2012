package gui.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import spreadsheet.Application;
import spreadsheet.Expression;
import spreadsheet.Position;

final public class CopyListener implements ActionListener {
	
	public final static CopyListener instance = new CopyListener();
	
	private Expression copy;
	private Position position;
	
	private CopyListener() {
		this.position = Application.instance.getCurrentPosition();
		this.copy = null;
	}
	
	/**
	 * Accesor method for getting Copy
	 * @return Expression that has selected for copy, otherwise null.
	 */
	public Expression getCopy() {
		return copy;
	}
	/**
	 * Returns the Position copying from.
	 * @return Guaranteed non null Position
	 */
	public Position getPosition() {
		return this.position;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.position = Application.instance.getCurrentPosition();
		this.copy = Application.instance.get(position);
		copy = Application.instance.get(this.position);	
	}
}
