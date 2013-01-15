package gui.control;

import gui.StatusView;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import spreadsheet.Change;
import spreadsheet.History;
import spreadsheet.command.Exit;

/** A listener for the undo menu item.
 * <p>
 * No need for more than one, hence the singleton implementation.
 */
public final class UndoListener
    implements ActionListener {

  public static final UndoListener instance = new UndoListener();

  private UndoListener() {
    // This is a singleton.
  }

  public void actionPerformed(ActionEvent event) {
    Change change = History.instance.pop();
    if (change != null)
    	change.undo();
    else
    	StatusView.instance.errorView.setText("Nothing to Undo");
  }

}
